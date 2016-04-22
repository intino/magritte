package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Annotations;
import tara.intellij.lang.psi.Flags;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration.ModuleType;
import tara.intellij.project.module.ModuleProvider;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Annotations) && !(element instanceof Flags)) return;
		final TaraFacet taraFacetByModule = TaraFacet.of(ModuleProvider.getModuleOf(element));
		if (taraFacetByModule == null) return;
		final ModuleType level = TaraUtil.moduleType(element);
		if (level != null && ((element instanceof Annotations && level.compareLevelWith(ModuleType.Application) <= 0) || (element instanceof Flags && level.compareLevelWith(ModuleType.System) == 0)))
			holder.createErrorAnnotation(element, MessageProvider.message("reject.annotations.in.level"));
	}
}
