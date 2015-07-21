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
import tara.intellij.MessageProvider;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.imports.CreateNodeQuickFix;
import tara.intellij.annotator.imports.ImportQuickFix;
import tara.intellij.annotator.imports.TaraReferenceImporter;
import tara.intellij.highlighting.TaraSyntaxHighlighter;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ReferenceAnalyzer extends TaraAnalyzer {

	public static final String MESSAGE = MessageProvider.message("unreached.reference");
	private final IdentifierReference reference;

	public ReferenceAnalyzer(IdentifierReference reference) {
		this.reference = reference;
	}

	@Override
	public void analyze() {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		Identifier element = identifierList.get(identifierList.size() - 1);
		PsiReference reference = element.getReference();
		if (reference == null || reference.resolve() == null) addImportAlternatives(element);
	}

	private void addImportAlternatives(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addImportFix(element, fixes);
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		addCreateNodeFix(element, node != null ? node.type() : "Concept", fixes);
		results.put(element, new TaraAnnotator.AnnotateAndFix(ERROR, MESSAGE, TaraSyntaxHighlighter.UNRESOLVED_ACCESS, createFixes(element, fixes)));
	}

	private IntentionAction[] createFixes(Identifier element, List<LocalQuickFix> fixes) {
		List<IntentionAction> actions = fixes.stream().map(fix -> createIntention(element, fix.getName(), fix)).collect(Collectors.toList());
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private void addCreateNodeFix(Identifier name, String type, List<LocalQuickFix> actions) {
		actions.add(new CreateNodeQuickFix(name.getText(), type, (TaraModel) name.getContainingFile()));
	}

	private IntentionAction createIntention(PsiElement node, String message, LocalQuickFix fix) {
		return createIntention(node, node.getTextRange(), message, fix);
	}

	private IntentionAction createIntention(PsiElement node, TextRange range, String message, LocalQuickFix fix) {
		LocalQuickFix[] quickFixes = {fix};
		CommonProblemDescriptorImpl descriptor = new ProblemDescriptorImpl(node, node, message,
			quickFixes, ProblemHighlightType.GENERIC_ERROR_OR_WARNING, true, range, true);
		return QuickFixWrapper.wrap((ProblemDescriptor) descriptor, 0);
	}

	private void addImportFix(Identifier node, List<LocalQuickFix> actions) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof TaraModel)) return;
		List<ImportQuickFix> importFix = TaraReferenceImporter.proposeImportFix((IdentifierReference) node.getParent());
		actions.addAll(importFix.stream().collect(Collectors.toList()));
	}
}
