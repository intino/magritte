package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.DSLDeclarationAnalyzer;
import siani.tara.intellij.lang.psi.TaraBoxFile;

public class DSLDeclarationAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraBoxFile) analyzeAndAnnotate(new DSLDeclarationAnalyzer((TaraBoxFile) element));
	}
}
