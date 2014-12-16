package siani.tara.intellij.annotator.semanticAnalizers.metrics;

import java.util.HashMap;
import java.util.Map;

public class Metrics {

	private static Metrics instance;
	private static Map<String, Class<?>> metrics = new HashMap<>();

	public interface Converter {
		public double convert(double value);
	}

	private Metrics() {
	}

	public static Metrics getInstance() {
		if (instance == null) instance = new Metrics();
		return instance;
	}

	public Class<?> add(Class<?> metric) {
		return metrics.put(metric.getClass().getSimpleName(), metric);
	}

	public Class<?>[] getMetrics() {
		return metrics.values().toArray(new Class<?>[metrics.size()]);
	}

	public Class<?> get(String measure) {
		return metrics.get(measure);
	}
}
