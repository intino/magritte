package siani.tara.intellij.annotator.semanticAnalizers.metrics;

public interface Metric {

	public double value(double value);

	public interface Converter {
		public double convert(double value);
	}
}
