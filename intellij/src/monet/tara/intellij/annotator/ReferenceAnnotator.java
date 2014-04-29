package monet.tara.intellij.annotator;

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
import com.intellij.psi.PsiReference;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.annotator.imports.*;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReferenceAnnotator extends TaraAnnotator {

	private PsiElement element;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.element = element;
		this.holder = holder;
		if (!(element instanceof Identifier)) return;
		if (element.getParent() instanceof HeaderReference || element.getParent() instanceof IdentifierReference)
			checkWellReferenced();
	}

	public void checkWellReferenced() {
		PsiElement reference = TaraUtil.resolveReference(element);
		if (reference == null) {
			Annotation errorAnnotation;
			if (element.getParent() instanceof IdentifierReference)
				addImportAlternatives((Identifier) element);
			else {
				String message = TaraBundle.message("reference.concept.key.error.message");
				errorAnnotation = annotateAndFix(element, new RemoveImportFix((TaraPsiElement) element.getParent()), message);
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
			}
		}
	}

	private void addImportAlternatives(Identifier element) {
		String message = TaraBundle.message("reference.concept.key.error.message");
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addAutoImportFix(element, element.getReference(), fixes);
		String packet = ((TaraFile) element.getContainingFile()).getPackage().getHeaderReference().getText();
		addCreateConceptFix(element, fixes);
		Annotation errorAnnotation = holder.createErrorAnnotation(element, message);
		errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
		for (LocalQuickFix fix : fixes)
			errorAnnotation.registerFix(createIntention(element, fix.getName(), fix));


	}

	private void addCreateConceptFix(Identifier name, List<LocalQuickFix> actions) {
		actions.add(new CreateConceptQuickFix(name.getText(), name.getContainingFile().getParent()));
	}

	private boolean suppressHintForAutoImport(TaraPsiElement node, AutoImportQuickFix importFix) {
		return false;
	}

	private IntentionAction createIntention(PsiElement node, String message, LocalQuickFix fix) {
		return createIntention(node, node.getTextRange(), message, fix);
	}

	private IntentionAction createIntention(PsiElement node, TextRange range, String message, LocalQuickFix fix) {
		LocalQuickFix[] quickFixes = {fix};
		CommonProblemDescriptorImpl descr = new ProblemDescriptorImpl(node, node, message,
			quickFixes, ProblemHighlightType.GENERIC_ERROR_OR_WARNING, true,
			range, true);
		return QuickFixWrapper.wrap((ProblemDescriptor) descr, 0);
	}

	private void addAutoImportFix(TaraPsiElement node, PsiReference reference, List<LocalQuickFix> actions) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof TaraFile)) return;
//		AutoImportQuickFix importFix = TaraReferenceImporter.proposeImportFix(node, reference);
//		if (importFix != null) {
//			if (!suppressHintForAutoImport(node, importFix)) {
//				final AutoImportHintAction autoImportHintAction = new AutoImportHintAction(importFix);
//				actions.add(autoImportHintAction);
//			} else {
//				actions.add(importFix);
//			}
//		}
	}
}
