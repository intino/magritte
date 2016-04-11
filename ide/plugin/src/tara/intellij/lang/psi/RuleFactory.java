package tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.PsiCustomWordRule;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Tag;
import tara.lang.model.rules.variable.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RuleFactory {


	@SuppressWarnings("ConstantConditions")
	public static VariableRule createRule(TaraVariable variable) {
		final TaraRule rule = variable.getRuleContainer().getRule();
		if (rule.isLambda()) return createLambdaRule(variable.type(), rule);
		else if (variable.type().equals(Primitive.FUNCTION) || variable.flags().contains(Tag.Reactive))
			return new NativeRule(rule.getText(), "", Collections.emptyList());
		else if (variable.type().equals(Primitive.OBJECT))
			return new NativeObjectRule(rule.getText());
		else return new PsiCustomWordRule(rule.getText(), ModuleProvider.getModuleOf(variable));
	}

	@Nullable
	private static VariableRule createLambdaRule(Primitive type, TaraRule rule) {
		final List<PsiElement> parameters = Arrays.asList(rule.getChildren());
		switch (type) {
			case DOUBLE:
				return createDoubleRule(rule);
			case INTEGER:
				return createIntegerRule(rule);
			case STRING:
				final String value = valueOf(parameters, StringValue.class);
				return new StringRule(value.substring(1, value.length() - 1));
			case RESOURCE:
				return new FileRule(valuesOf(parameters));
			case FUNCTION:
				return new NativeRule(parameters.get(0).getText(), "", Collections.emptyList());
			case WORD:
				return new WordRule(valuesOf(parameters));
			case OBJECT:
				return new NativeObjectRule(valuesOf(parameters).get(0));
//			case REFERENCE: TODO
		}
		return null;
	}

	private static VariableRule createIntegerRule(TaraRule rule) {
		return new IntegerRule(minOf(rule.getRange()).intValue(), maxOf(rule.getRange()).intValue(), valueOf(Arrays.asList(rule.getChildren()), TaraMetric.class));
	}

	private static VariableRule createDoubleRule(TaraRule rule) {
		return new DoubleRule(minOf(rule.getRange()), maxOf(rule.getRange()), valueOf(Arrays.asList(rule.getChildren()), TaraMetric.class));
	}

	private static Double minOf(TaraRange range) {
		if (range == null) return Double.NEGATIVE_INFINITY;
		final String min = range.getChildren()[0].getText();
		return min.equals("*") ? Double.NEGATIVE_INFINITY : Double.parseDouble(min);
	}

	private static Double maxOf(TaraRange range) {
		if (range == null) return Double.POSITIVE_INFINITY;
		final String max = range.getChildren()[range.getChildren().length - 1].getText();
		return max.equals("*") ? Double.POSITIVE_INFINITY : Double.parseDouble(max);
	}

	private static List<String> valuesOf(List<PsiElement> parameters) {
		return parameters.stream().map(PsiElement::getText).collect(Collectors.toList());
	}

	private static String valueOf(List<PsiElement> parameters, Class<? extends PsiElement> aClass) {
		PsiElement value = parameters.stream().filter(aClass::isInstance).findFirst().orElse(null);
		return value == null ? "" : value.getText();
	}

}
