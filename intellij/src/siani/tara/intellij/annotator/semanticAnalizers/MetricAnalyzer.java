package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.semanticAnalizers.inflectors.EnglishInflector;
import siani.tara.lang.Attribute;
import siani.tara.lang.Model;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class MetricAnalyzer {

	private Model metamodel;
	private Variable variable;
	private String[] values;
	private String measure;

	public MetricAnalyzer(Model metamodel, Variable variable, String[] values, String measure) {
		this.metamodel = metamodel;
		this.variable = variable;
		this.values = values;
		this.measure = measure;
	}

	public TaraAnnotator.AnnotateAndFix analyze() {
		if (!hasCorrectMetric(values, measure)) return new TaraAnnotator.AnnotateAndFix(ERROR,
			MessageProvider.message("metric.mismatched") + " Allowed metrics: " + metricsToString(shouldBePlural(values), getAllowedMetrics()));
		return null;
	}


	private List<String> getAllowedMetrics() {
		List<String> list = new ArrayList<>();
		for (Map.Entry<String, List<Map.Entry<String, String>>> entry : metamodel.getMetrics().entrySet())
			if (entry.getKey().equalsIgnoreCase(((Attribute) variable).getMeasureType()))
				for (Map.Entry<String, String> stringEntry : entry.getValue())
					list.add(stringEntry.getValue());
		return list;
	}

	private boolean hasCorrectMetric(String[] values, String measure) {
		boolean shouldBePlural = shouldBePlural(values);
		for (String allowedMetric : getAllowedMetrics()) {
			if (!shouldBePlural && allowedMetric.equalsIgnoreCase(measure))
				return true;
			else if (shouldBePlural && new EnglishInflector().plural(allowedMetric.toLowerCase()).equalsIgnoreCase(measure))
				return true;
		}
		return false;
	}

	private boolean shouldBePlural(String[] values) {
		return !(values.length == 1 && toDouble(values[0]) == 1.0);
	}

	private double toDouble(String value) {
		try {
			return Double.valueOf(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private String metricsToString(boolean plural, List<String> allowedMetrics) {
		EnglishInflector inflector = new EnglishInflector();
		String metrics = "";
		for (String allowedMetric : allowedMetrics)
			metrics += ", " + (plural ? inflector.plural(allowedMetric.toLowerCase()) : allowedMetric.toLowerCase());
		return metrics.isEmpty() ? "" : metrics.substring(2);
	}
}
