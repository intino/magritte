package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.AddressAnalyzer;
import siani.tara.intellij.lang.psi.TaraAddress;

public class AddressAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (TaraAddress.class.isInstance(element)) analyzeAndAnnotate(new AddressAnalyzer((TaraAddress) element));
	}
}
