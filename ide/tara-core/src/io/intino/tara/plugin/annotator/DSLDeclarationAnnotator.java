package io.intino.tara.plugin.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.annotator.semanticanalizer.DSLDeclarationAnalyzer;
import io.intino.tara.plugin.lang.file.StashFileType;
import io.intino.tara.plugin.lang.file.TaraFileType;
import io.intino.tara.plugin.lang.psi.TaraModel;

public class DSLDeclarationAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraModel && !((TaraModel) element).getName().endsWith("." + StashFileType.INSTANCE.getDefaultExtension() + "." + TaraFileType.instance().getDefaultExtension()))
			analyzeAndAnnotate(new DSLDeclarationAnalyzer((TaraModel) element));
	}
}
