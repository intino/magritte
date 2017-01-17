package io.intino.tara.plugin.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.annotator.semanticanalizer.NodeRuleAnalyzer;
import io.intino.tara.plugin.annotator.semanticanalizer.VariableRuleClassAnalyzer;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraRuleContainer;
import io.intino.tara.lang.model.Variable;

import static io.intino.tara.lang.model.Primitive.OBJECT;

public class RuleAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		final Variable variable = TaraPsiImplUtil.getContainerByType(element, Variable.class);
		if (!(element instanceof TaraRuleContainer) || (variable != null && OBJECT.equals(variable.type()))) return;
		TaraRuleContainer ruleContainer = (TaraRuleContainer) element;
		analyzeAndAnnotate(TaraPsiImplUtil.getContainerByType(ruleContainer, Variable.class) != null ?
			new VariableRuleClassAnalyzer(ruleContainer) : new NodeRuleAnalyzer(ruleContainer));
	}
}