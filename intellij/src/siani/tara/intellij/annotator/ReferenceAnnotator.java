package siani.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.CommonProblemDescriptorImpl;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ex.ProblemDescriptorImpl;
import com.intellij.codeInspection.ex.QuickFixWrapper;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.imports.CreateConceptQuickFix;
import siani.tara.intellij.annotator.imports.ImportQuickFix;
import siani.tara.intellij.annotator.imports.RemoveImportFix;
import siani.tara.intellij.annotator.imports.TaraReferenceImporter;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Word;

import java.util.ArrayList;
import java.util.List;

public class ReferenceAnnotator extends TaraAnnotator {

	public static final String MESSAGE = TaraBundle.message("reference.concept.key.error.message");
	private PsiElement element;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.element = element;
		this.holder = holder;
		if (element instanceof IdentifierReference && !isInFacetApply(element))
			checkWellReferenced();
	}

	private boolean isInFacetApply(PsiElement element) {
		PsiElement aElement = element;
		TaraFacetApply result;
		while ((aElement.getParent() != null) && !(aElement.getParent() instanceof Concept) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		result = (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
		return result != null;
	}

	public void checkWellReferenced() {
		PsiElement reference = ReferenceManager.resolve((IdentifierReference) element);
		if (reference == null && !checkAsMetaWord(TaraPsiImplUtil.getConceptContextOf(element), element.getText())) {
			Annotation errorAnnotation;
			if (element instanceof IdentifierReference) {
				List<? extends Identifier> identifierList = ((IdentifierReference) element).getIdentifierList();
				addImportAlternatives(identifierList.get(identifierList.size() - 1));
			} else {
				errorAnnotation = annotateAndFix(element, new RemoveImportFix((TaraPsiElement) element.getParent()), MESSAGE);
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
			}
		}
	}

	private boolean checkAsMetaWord(Concept concept, String wordName) {
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null) return false;
		Node node = findNode(concept, model);
		if (node == null) return false;
		Word[] words = node.getObject().getWords();
		if (words.length == 0) return false;
		for (Word word : words)
			if (word.contains(wordName))
				return true;
		return true;
	}

	private void addImportAlternatives(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addImportFix(element, fixes);
		addCreateConceptFix(element, "Concept", fixes);
		Annotation errorAnnotation = holder.createErrorAnnotation(element, MESSAGE);
		errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
		for (LocalQuickFix fix : fixes)
			errorAnnotation.registerFix(createIntention(element, fix.getName(), fix));
	}

	private void addCreateConceptFix(Identifier name, String type, List<LocalQuickFix> actions) {
		actions.add(new CreateConceptQuickFix(name.getText(), type, name.getContainingFile().getParent()));
	}

	private IntentionAction createIntention(PsiElement node, String message, LocalQuickFix fix) {
		return createIntention(node, node.getTextRange(), message, fix);
	}

	private IntentionAction createIntention(PsiElement node, TextRange range, String message, LocalQuickFix fix) {
		LocalQuickFix[] quickFixes = {fix};
		CommonProblemDescriptorImpl descr = new ProblemDescriptorImpl(node, node, message,
			quickFixes, ProblemHighlightType.GENERIC_ERROR_OR_WARNING, true, range, true);
		return QuickFixWrapper.wrap((ProblemDescriptor) descr, 0);
	}

	private void addImportFix(Identifier node, List<LocalQuickFix> actions) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof TaraBoxFile)) return;
		List<ImportQuickFix> importFix = TaraReferenceImporter.proposeImportFix((IdentifierReference) node.getParent());
		for (ImportQuickFix importQuickFix : importFix) actions.add(importQuickFix);
	}
}
