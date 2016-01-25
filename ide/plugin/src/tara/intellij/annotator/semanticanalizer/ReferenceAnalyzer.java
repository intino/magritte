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
import tara.Language;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import tara.intellij.annotator.imports.CreateNodeQuickFix;
import tara.intellij.annotator.imports.ImportQuickFix;
import tara.intellij.annotator.imports.TaraReferenceImporter;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE.DECLARATION;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE.ERROR;
import static tara.intellij.highlighting.TaraSyntaxHighlighter.UNRESOLVED_ACCESS;

public class ReferenceAnalyzer extends TaraAnalyzer {

	public static final String MESSAGE = "unreached.reference";
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
		if (resolve == null) {
			if (tryWithADeclaration())
				results.put(reference, new AnnotateAndFix(DECLARATION, MessageProvider.message("declaration.reference")));
			else setError(aReference, element);
		}
	}

	private boolean tryWithADeclaration() {
		final Language language = TaraUtil.getLanguage(reference);
		return language != null && language.instances().keySet().contains(reference.getText());
	}

	private void setError(PsiReference aReference, Identifier element) {
		if (aReference instanceof TaraNodeReferenceSolver) createError(element);
	}

	private void createError(Identifier element) {
		results.put(element, new AnnotateAndFix(ERROR, MessageProvider.message(MESSAGE), UNRESOLVED_ACCESS, createFixes(element)));
	}

	private IntentionAction[] createFixes(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>(createImportFixes(element));
		List<IntentionAction> actions = fixes.stream().map(fix -> toIntention(element, fix.getName(), fix)).collect(Collectors.toList());
		actions.addAll(createNewElementFix(element));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private List<CreateNodeQuickFix> createNewElementFix(Identifier element) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node != null) return singletonList(new CreateNodeQuickFix(element.getText(), (TaraModel) element.getContainingFile()));
		return Collections.emptyList();
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
