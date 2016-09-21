package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Annotations;
import tara.intellij.lang.psi.Flags;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.configuration.Configuration.ModuleType;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Annotations) && !(element instanceof Flags)) return;
		final ModuleType level = TaraUtil.moduleType(element);
		if (level == null) return;
		if (element instanceof Annotations && (ModuleType.Application.compareLevelWith(level) <= 0 || element instanceof Flags && level.compareLevelWith(ModuleType.System) == 0))
			holder.createErrorAnnotation(element, MessageProvider.message("reject.annotations.in.level"));
	}
}
