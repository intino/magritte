package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RemoveAttributeFix;
import siani.tara.intellij.lang.psi.Attribute;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

public class AttributeAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Attribute)
			checkDuplicated((Attribute) element);
	}

	private void checkDuplicated(Attribute attribute) {
		if (TaraUtil.findAttributeDuplicates(attribute).length != 1)
			annotateAndFix(attribute, new RemoveAttributeFix(attribute), TaraBundle.message("duplicate.attribute.key.error.message"));
	}
}
