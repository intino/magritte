package monet.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraBased;
import monet.tara.intellij.metamodel.psi.TaraCased;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;

public abstract class TaraAnnotator implements Annotator {

	protected AnnotationHolder holder = null;

	private void checkBase(TaraBased element) {
		if (!hasCases(TaraPsiImplUtil.getContextOf(element))) {
			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(),
				TaraBundle.message("case.non-existent.in.base.error.message"));
		}
	}

	private boolean hasCases(Concept context) {
		if (context.getBody() != null)
			for (Concept concept : context.getBody().getConceptList())
				if (concept.isMorph()) return true;
		return false;
	}

	private void checkCase(TaraCased element) {
		Concept concept = TaraPsiImplUtil.getContextOf(TaraPsiImplUtil.getContextOf(element));
		if (!concept.isPolymorphic())
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("case.not.in.base.error.message"));
	}

	protected Annotation annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), message);
		errorAnnotation.registerFix(fix);
		return errorAnnotation;
	}

}