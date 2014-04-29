package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.annotator.fix.RemoveAttributeFix;
import monet.::projectName::.intellij.metamodel.psi.Attribute;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

public class AttributeAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Attribute)
			checkDuplicated((Attribute) element);
	}

	private void checkDuplicated(Attribute attribute) {
		if (::projectProperName::Util.findAttributeDuplicates(attribute).length != 1)
			annotateAndFix(attribute, new RemoveAttributeFix(attribute), ::projectProperName::Bundle.message("duplicate.attribute.key.error.message"));
	}
}
