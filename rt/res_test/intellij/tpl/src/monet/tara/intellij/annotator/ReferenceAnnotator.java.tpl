package monet.::projectName::.intellij.annotator;

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
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.annotator.imports.CreateDefinitionQuickFix;
import monet.::projectName::.intellij.annotator.imports.ImportQuickFix;
import monet.::projectName::.intellij.annotator.imports.RemoveImportFix;
import monet.::projectName::.intellij.annotator.imports.::projectProperName::ReferenceImporter;
import monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighter;
import monet.::projectName::.intellij.lang.psi.*;
import monet.::projectName::.intellij.lang.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReferenceAnnotator extends ::projectProperName::Annotator {

	private PsiElement element;

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.element = element;
		this.holder = holder;
		if (!(element instanceof Identifier)) return;
		if (element.getParent() instanceof HeaderReference || element.getParent() instanceof IdentifierReference)
			checkWellReferenced();
	}

public void checkWellReferenced() {
		PsiElement reference = ReferenceManager.resolve((Identifier) element, false);
		if (reference == null && !isWellMetaReferenced(element.getParent().getText())) {
			Annotation errorAnnotation;
			if (element.getParent() instanceof IdentifierReference)
				addImportAlternatives((Identifier) element);
			else {
				String message = ::projectProperName::Bundle.message("reference.definition.key.error.message");
				errorAnnotation = annotateAndFix(element, new RemoveImportFix((::projectProperName::PsiElement) element.getParent()), message);
				errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.UNRESOLVED_ACCESS);
			}
		}
	}

	private boolean isWellMetaReferenced(String reference) {
        monet.tara.lang.ASTWrapper heritage = monet.goros.intellij.lang.GorosLanguage.getHeritage();
        String[] refRoute = reference.split("\\\\.");
        List<monet.tara.lang.ASTNode> astNodes = heritage.getNodeNameLookUpTable().get(refRoute[0]);
        return astNodes != null && astNodes.get(0) != null &&
            astNodes.get(0).resolveChild(java.util.Arrays.copyOfRange(refRoute, 1, refRoute.length));
    }

	private void addImportAlternatives(Identifier element) {
		String message = ::projectProperName::Bundle.message("reference.definition.key.error.message");
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addImportFix(element, fixes);
		addCreateDefinitionFix(element, fixes);
		Annotation errorAnnotation = holder.createErrorAnnotation(element, message);
		errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.UNRESOLVED_ACCESS);
		for (LocalQuickFix fix \: fixes)
			errorAnnotation.registerFix(createIntention(element, fix.getName(), fix));
	}

	private void addCreateDefinitionFix(Identifier name, List<LocalQuickFix> actions) {
		actions.add(new CreateDefinitionQuickFix(name.getText(), name.getContainingFile().getParent()));
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

	private void addImportFix(PsiElement node, List<LocalQuickFix> actions) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof ::projectProperName::File)) return;
		List<ImportQuickFix> importFix = ::projectProperName::ReferenceImporter.proposeImportFix(node);
		for (ImportQuickFix importQuickFix \: importFix) actions.add(importQuickFix);
	}
}
