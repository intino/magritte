package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.DSLDeclarationAnalyzer;
import tara.intellij.lang.file.StashFileType;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.TaraModel;

public class DSLDeclarationAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraModel && !((TaraModel) element).getName().endsWith("." + StashFileType.INSTANCE.getDefaultExtension() + "." + TaraFileType.INSTANCE.getDefaultExtension()))
			analyzeAndAnnotate(new DSLDeclarationAnalyzer((TaraModel) element));
	}
}
