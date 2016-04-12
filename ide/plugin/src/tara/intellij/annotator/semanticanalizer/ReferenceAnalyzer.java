package tara.intellij.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.CommonProblemDescriptorImpl;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ex.ProblemDescriptorImpl;
import com.intellij.codeInspection.ex.QuickFixWrapper;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import tara.intellij.annotator.fix.CreateClassFromMethodReferenceFix;
import tara.intellij.annotator.fix.CreateTableQuickFix;
import tara.intellij.annotator.imports.AlternativesForReferenceFix;
import tara.intellij.annotator.imports.CreateNodeQuickFix;
import tara.intellij.annotator.imports.ImportQuickFix;
import tara.intellij.annotator.imports.TaraReferenceImporter;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.MethodReferenceSolver;
import tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;
import tara.intellij.lang.psi.resolve.TaraTableReferenceSolver;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static tara.intellij.highlighting.TaraSyntaxHighlighter.UNRESOLVED_ACCESS;
import static tara.intellij.messages.MessageProvider.message;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.DECLARATION;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class ReferenceAnalyzer extends TaraAnalyzer {

	private static final String MESSAGE = "unreached.reference";
	private final IdentifierReference reference;

	public ReferenceAnalyzer(IdentifierReference reference) {
		this.reference = reference;
	}

	@Override
	public void analyze() {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		Identifier element = identifierList.get(identifierList.size() - 1);
		PsiReference aReference = element.getReference();
		if (aReference == null) return;
		final PsiElement resolve = aReference.resolve();
		if (resolve != null) return;
		if (isInstanceReference() && aReference instanceof TaraNodeReferenceSolver)
			results.put(reference, new AnnotateAndFix(DECLARATION, message("instance.reference")));
		else setError(aReference, element);
	}

	private boolean isInstanceReference() {
		final Language language = TaraUtil.getLanguage(reference);
		return language != null && language.instances().keySet().contains(reference.getText());
	}

	private void setError(PsiReference aReference, Identifier element) {
		if (aReference instanceof TaraNodeReferenceSolver) createNodeError(element);
		else if (aReference instanceof TaraTableReferenceSolver) createTableReferenceError(element);
		else if (aReference instanceof MethodReferenceSolver) createMethodReferenceError(element);
	}

	private void createNodeError(Identifier element) {
		results.put(element, new AnnotateAndFix(ERROR, message(MESSAGE), UNRESOLVED_ACCESS, createNodeReferenceFixes(element)));
	}

	private void createTableReferenceError(Identifier element) {
		results.put(element, new AnnotateAndFix(ERROR, message(MESSAGE), UNRESOLVED_ACCESS, createTableReferenceFixes(element)));
	}

	private void createMethodReferenceError(Identifier element) {
		results.put(element, new AnnotateAndFix(ERROR, message(MESSAGE), UNRESOLVED_ACCESS, createMethodReferenceFixes(element)));
	}

	private IntentionAction[] createNodeReferenceFixes(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>(createImportFixes(element));
		List<IntentionAction> actions = fixes.stream().map(fix -> toIntention(element, fix.getName(), fix)).collect(Collectors.toList());
		actions.addAll(alternativesForReferenceFix(element));
		actions.addAll(createNewElementFix(element));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private IntentionAction[] createTableReferenceFixes(Identifier element) {
		List<IntentionAction> actions = new ArrayList<>(createTableFix(element));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private IntentionAction[] createMethodReferenceFixes(Identifier element) {
		List<IntentionAction> actions = new ArrayList<>(createMethodFix(element));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private List<CreateNodeQuickFix> createNewElementFix(Identifier element) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node != null) return singletonList(new CreateNodeQuickFix(element.getText(), (TaraModel) element.getContainingFile()));
		return Collections.emptyList();
	}

	private List<CreateTableQuickFix> createTableFix(Identifier element) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node != null) return singletonList(new CreateTableQuickFix(TaraPsiImplUtil.getContainerByType(element, TaraWithTable.class)));
		return Collections.emptyList();
	}

	private List<IntentionAction> createMethodFix(Identifier element) {
		Valued valued = TaraPsiImplUtil.getContainerByType(element, Valued.class);
		if (valued != null) return getFix(element, valued);
		return Collections.emptyList();
	}

	@NotNull
	private List<IntentionAction> getFix(Identifier element, Valued valued) {
		return singletonList(new CreateClassFromMethodReferenceFix(valued, element));
	}

	private List<AlternativesForReferenceFix> alternativesForReferenceFix(Identifier element) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		return node != null ? singletonList(new AlternativesForReferenceFix(element)) : Collections.emptyList();
	}

	private IntentionAction toIntention(PsiElement node, String message, LocalQuickFix fix) {
		return toIntention(node, node.getTextRange(), message, fix);
	}

	private IntentionAction toIntention(PsiElement node, TextRange range, String message, LocalQuickFix fix) {
		LocalQuickFix[] quickFixes = {fix};
		CommonProblemDescriptorImpl descriptor = new ProblemDescriptorImpl(node, node, message,
			quickFixes, ProblemHighlightType.GENERIC_ERROR_OR_WARNING, true, range, true);
		return QuickFixWrapper.wrap((ProblemDescriptor) descriptor, 0);
	}

	private List<ImportQuickFix> createImportFixes(Identifier node) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof TaraModel)) return Collections.emptyList();
		return TaraReferenceImporter.proposeImportFix((IdentifierReference) node.getParent());
	}
}
