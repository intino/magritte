package magritte;

public interface Metric {

	public double value(double value);

	public interface Converter {
		public double convert(double value);
	}
}
