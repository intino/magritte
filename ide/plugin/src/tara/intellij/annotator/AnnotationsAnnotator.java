package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.Annotations;
import tara.intellij.lang.psi.Flags;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Annotations) && !(element instanceof Flags)) return;
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(element));
		if (taraFacetByModule == null) return;
		final int level = taraFacetByModule.getConfiguration().getLevel();
		if ((element instanceof Annotations && level <= 1) || (element instanceof Flags && level == 0))
			holder.createErrorAnnotation(element, MessageProvider.message("reject.annotations.in.level"));
	}
}
