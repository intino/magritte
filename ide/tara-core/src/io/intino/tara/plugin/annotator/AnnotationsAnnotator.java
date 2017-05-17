package io.intino.tara.plugin.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.lang.psi.Annotations;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.messages.MessageProvider;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.plugin.lang.psi.Flags;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Annotations) && !(element instanceof Flags)) return;
		final Level level = TaraUtil.level(element);
		if (level == null) return;
		if ((element instanceof Annotations && (Level.Product.equals(level) || Level.System.equals(level))) || (element instanceof Flags && (level.equals(Level.System))))
			holder.createErrorAnnotation(element, MessageProvider.message("reject.annotations.in.level"));
	}
}
