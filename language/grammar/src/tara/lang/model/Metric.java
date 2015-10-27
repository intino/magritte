package tara.lang.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface Metric<T> extends Rule<T> {

	T value(T value);

	List<String> units();

	default boolean accept(T value) {
		return true;
	}

	default boolean accept(T value, String metric) {
		return units().contains(metric);
	}


	@Override
	default String errorMessage() {
		return "reject.number.parameter.with.erroneous.metric";
	}

	@Override
	default List<String> errorParameters() {
		return Collections.singletonList(String.join(", ", units()));
	}

	interface Converter<T> {
		double convert(T value);
	}

	default List<String> namesOf(Enum[] values) {
		return Arrays.asList(values).stream().map(Enum::name).collect(Collectors.toList());
	}

}
