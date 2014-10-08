package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.TaraDateValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateValueAnnotator extends TaraAnnotator {

	SimpleDateFormat[] formats = new SimpleDateFormat[]{
		new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()),
		new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.getDefault()),
		new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault()),
		new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()),
		new SimpleDateFormat("yyyy", Locale.getDefault())
	};

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraDateValue.class.isInstance(element)) return;
		TaraDateValue date = (TaraDateValue) element;
		for (SimpleDateFormat format : formats) {
			try {
				format.parse(date.getText());
				return;
			} catch (ParseException ignored) {
			}
		}
		holder.createErrorAnnotation(element.getNode(), TaraBundle.message("date.value.key.error.message"));
	}
}
