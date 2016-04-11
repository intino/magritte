package tara.lang.model;


import tara.lang.model.rules.variable.VariableRule;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public interface Metric<T> extends VariableRule<T> {

	T value(T value);

	default List<String> units() {
		return asList(this.getClass().getFields()).stream().filter(Field::isEnumConstant).map(Field::getName).collect(toList());
	}

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
	default List<Object> errorParameters() {
		return Collections.singletonList(String.join(", ", units()));
	}

	interface Converter<T> {
		T convert(T value);
	}
}
