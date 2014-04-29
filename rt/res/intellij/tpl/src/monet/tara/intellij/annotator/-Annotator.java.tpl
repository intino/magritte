package monet.::projectName::.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Morph;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Polymorphic;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;

public abstract class ::projectProperName::Annotator implements Annotator {

	protected AnnotationHolder holder = null;

	private void checkPolymorphic(::projectProperName::Polymorphic element) {
		if (!hasMorphs(::projectProperName::PsiImplUtil.getContextOf(element))) {
			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(),
				::projectProperName::Bundle.message("morph.non-existent.in.polymorphic.error.message"));
		}
	}

	private boolean hasMorphs(Definition context) {
		if (context.getBody() != null)
			for (Definition definition \: context.getBody().getDefinitionList())
				if (definition.isMorph()) return true;
		return false;
	}

	private void checkMorph(::projectProperName::Morph element) {
		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(::projectProperName::PsiImplUtil.getContextOf(element));
		if (!definition.isPolymorphic())
			holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("morph.not.in.polymorphic.error.message"));
	}

	protected Annotation annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), message);
		errorAnnotation.registerFix(fix);
		return errorAnnotation;
	}

}