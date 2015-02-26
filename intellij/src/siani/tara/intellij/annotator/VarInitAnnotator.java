package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.VarInitAnalyzer;
import siani.tara.intellij.lang.psi.TaraVarInit;
import siani.tara.intellij.lang.psi.VarInit;

public class VarInitAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraVarInit.class.isInstance(element)) return;
		analyzeAndAnnotate(new VarInitAnalyzer((VarInit) element));
	}
}
