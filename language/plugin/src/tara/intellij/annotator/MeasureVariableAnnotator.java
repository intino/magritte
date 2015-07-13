package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.MetricClassCreationAnalyzer;
import tara.intellij.lang.psi.TaraAttributeType;

public class MeasureVariableAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!(element instanceof TaraAttributeType)) return;
		TaraAttributeType measureType = (TaraAttributeType) element;
		MetricClassCreationAnalyzer analyzer = new MetricClassCreationAnalyzer(measureType);
		analyzeAndAnnotate(analyzer);
	}

}
