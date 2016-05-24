package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.RuleClassCreationAnalyzer;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Variable;

import static tara.lang.model.Primitive.OBJECT;

public class RuleAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		final Variable variable = TaraPsiImplUtil.getContainerByType(element, Variable.class);
		if (!(element instanceof TaraRuleContainer) || variable == null || OBJECT.equals(variable.type())) return;
		TaraRuleContainer attributeType = (TaraRuleContainer) element;
		RuleClassCreationAnalyzer analyzer = new RuleClassCreationAnalyzer(attributeType);
		analyzeAndAnnotate(analyzer);
	}
}