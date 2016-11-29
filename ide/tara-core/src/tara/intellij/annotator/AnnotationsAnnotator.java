package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.compiler.shared.Configuration.Level;
import tara.intellij.lang.psi.Annotations;
import tara.intellij.lang.psi.Flags;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.messages.MessageProvider;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Annotations) && !(element instanceof Flags)) return;
		final Level level = TaraUtil.level(element);
		if (level == null) return;
		if ((element instanceof Annotations && (Level.Application.equals(level) || Level.System.equals(level))) || (element instanceof Flags && (level.equals(Level.System))))
			holder.createErrorAnnotation(element, MessageProvider.message("reject.annotations.in.level"));
	}
}
