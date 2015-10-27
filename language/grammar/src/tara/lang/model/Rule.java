package tara.lang.model;

public interface Rule<T> {

	boolean accept(T value);

	default boolean accept(T value, String metric) {
		return true;
	}

	default String errorMessage() {
		return "";
	}
}
