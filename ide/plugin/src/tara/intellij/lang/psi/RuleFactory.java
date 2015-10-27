package tara.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.PsiCustomRule;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.rules.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RuleFactory {


	@SuppressWarnings("ConstantConditions")
	public static tara.lang.model.Rule createRule(TaraVariable variable) {
		final TaraRule rule = variable.getRuleContainer().getRule();
		if (rule.isLambda()) return createLambdaRule(variable.type(), rule);
		else if (variable.type().equals(Primitive.NATIVE))
			return new NativeRule(rule.getText(), "", TaraUtil.getLanguage(rule).languageName());
		else return new PsiCustomRule(rule.getText());
	}

	@Nullable
	private static Rule createLambdaRule(Primitive type, TaraRule rule) {
		final List<PsiElement> parameters = Arrays.asList(rule.getChildren());
		switch (type) {
			case DOUBLE:
				return createDoubleRule(rule);
			case INTEGER:
				return createIntegerRule(rule);
			case STRING:
				final String value = valueOf(parameters, StringValue.class);
				return new StringRule(value.substring(1, value.length() - 1));
			case FILE:
				return new FileRule(valuesOf(parameters));
			case NATIVE:
				return new NativeRule(parameters.get(0).getText(), "", TaraUtil.getLanguage(rule).languageName());
			case WORD:
				return new WordRule(Arrays.asList(valuesOf(parameters)));
//			case REFERENCE:
		}
		return null;
	}

	private static Rule createIntegerRule(TaraRule rule) {
		return new IntegerRule(minOf(rule.getRange()).intValue(), maxOf(rule.getRange()).intValue(), valueOf(Arrays.asList(rule.getChildren()), TaraMetric.class));
	}

	private static tara.lang.model.Rule createDoubleRule(TaraRule rule) {
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

	private static String[] valuesOf(List<PsiElement> parameters) {
		List<String> values = parameters.stream().map(PsiElement::getText).collect(Collectors.toList());
		return values.toArray(new String[values.size()]);
	}

	private static String valueOf(List<PsiElement> parameters, Class<? extends PsiElement> aClass) {
		PsiElement value = parameters.stream().filter(aClass::isInstance).findFirst().orElse(null);
		return value == null ? "" : value.getText();
	}

}
