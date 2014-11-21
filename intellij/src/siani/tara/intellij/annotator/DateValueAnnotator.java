package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.TaraDateValue;
import siani.tara.lang.Primitives;

public class DateValueAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraDateValue.class.isInstance(element)) return;
		TaraDateValue date = (TaraDateValue) element;
		Object[] conversion = Primitives.getConverter(Primitives.DATE).convert(date.getText());
		if (conversion.length == 0)
			holder.createErrorAnnotation(element.getNode(), MessageProvider.message("date.defaultValue.key.error.message"));
	}
}
