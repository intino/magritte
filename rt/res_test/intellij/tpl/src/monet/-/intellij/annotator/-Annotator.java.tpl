package monet.::projectName::.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;

public abstract class ::projectProperName::Annotator implements Annotator {

	protected AnnotationHolder holder = null;

//	private void checkBase(::projectProperName::Based element) {
//		if (!hasCases(::projectProperName::PsiImplUtil.getContextOf(element))) {
//			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(),
//				::projectProperName::Bundle.message("case.non-existent.in.base.error.message"));
//		}
//	}
//
//	private boolean hasCases(Definition context) {
//		if (context.getBody() != null)
//			for (Definition definition \: context.getBody().getDefinitionList())
//				if (definition.isCase()) return true;
//		return false;
//	}
//
//	private void checkCase(::projectProperName::Cased element) {
//		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(::projectProperName::PsiImplUtil.getContextOf(element));
//		if (!definition.isBase())
//			holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("case.not.in.base.error.message"));
//	}

	protected Annotation annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), message);
		errorAnnotation.registerFix(fix);
		return errorAnnotation;
	}

}