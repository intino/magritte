package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.WordClassCreationAnalyzer;
import tara.intellij.lang.psi.TaraAttributeType;
import tara.language.model.Primitive;
import tara.language.model.Variable;

public class WordVariableAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!(element instanceof TaraAttributeType)) return;
		TaraAttributeType measureType = (TaraAttributeType) element;
		Variable variable = getVariableContext(measureType);
		if (variable == null || !Primitive.WORD.equals(variable.type())) return;
		WordClassCreationAnalyzer analyzer = new WordClassCreationAnalyzer(measureType);
		analyzeAndAnnotate(analyzer);
	}

	private Variable getVariableContext(TaraAttributeType element) {
		PsiElement parent = element;
		while (parent != null)
			if (parent instanceof Variable) return (Variable) parent;
			else parent = parent.getParent();
		return null;
	}

}
