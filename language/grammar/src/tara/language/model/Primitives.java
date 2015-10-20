package tara.language.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Primitives {

	public static final String INTEGER = "integer";
	public static final String NATURAL = "natural";
	public static final String BOOLEAN = "boolean";
	public static final String STRING = "string";
	public static final String RATIO = "ratio";
	public static final String MEASURE = "measure";
	public static final String DOUBLE = "double";
	public static final String FILE = "file";
	public static final String NATIVE = "native";
	public static final String TYPE = "type";
	public static final String REFERENCE = "reference";
	public static final String TUPLE = "tuple";
	public static final String DATE = "date";
	public static final String TIME = "time";
	public static final String WORD = "word";
	public static final String EMPTY = "empty";
	public static final Map<String, Converter> CONVERTER_MAP = new HashMap<>();

	static {
		final Converter stringConverter = new Converter() {
			@Override
			public Object[] convert(String... value) {
				return value;
			}

			@Override
			public String[] convert(Object... value) {
				List<String> strings = new ArrayList<>();
				for (Object o : value) strings.add(o.toString());
				return strings.toArray(new String[strings.size()]);
			}
		};
		CONVERTER_MAP.put(STRING, stringConverter);
		CONVERTER_MAP.put(REFERENCE, stringConverter);

		Converter numberConverter = new Converter() {
			@Override
			public Integer[] convert(String... value) {
				List<Integer> integers = new ArrayList<>();
				for (String o : value) integers.add(Integer.valueOf(o));
				return integers.toArray(new Integer[integers.size()]);
			}

			@Override
			public String[] convert(Object... value) {
				List<String> integers = new ArrayList<>();
				for (Object o : value) integers.add(o.toString());
				return integers.toArray(new String[integers.size()]);
			}
		};
		CONVERTER_MAP.put(NATURAL, numberConverter);
		CONVERTER_MAP.put(INTEGER, numberConverter);
		CONVERTER_MAP.put(DOUBLE, new Converter() {
			@Override
			public Double[] convert(String... value) {
				List<Double> objects = new ArrayList<>();
				for (String o : value) objects.add(Double.valueOf(o));
				return objects.toArray(new Double[objects.size()]);
			}

			@Override
			public String[] convert(Object... value) {
				List<String> list = new ArrayList<>();
				for (Object o : value) list.add(o.toString());
				return list.toArray(new String[list.size()]);
			}
		});

		CONVERTER_MAP.put(BOOLEAN, new Converter() {
			@Override
			public Boolean[] convert(String... value) {
				List<Boolean> list = new ArrayList<>();
				for (String o : value) list.add(Boolean.valueOf(o));
				return list.toArray(new Boolean[list.size()]);
			}

			@Override
			public String[] convert(Object... value) {
				List<String> list = new ArrayList<>();
				for (Object o : value) list.add(o.toString());
				return list.toArray(new String[list.size()]);
			}
		});
	}

	public static String[] getPrimitives() {
		List<String> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(NATURAL);
		list.add(DOUBLE);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(NATIVE);
		list.add(WORD);
		list.add(TYPE);
		list.add(TUPLE);
		list.add(TIME);
		list.add(DATE);
		list.add(FILE);
		list.add(MEASURE);
		list.add(RATIO);
		return list.toArray(new String[list.size()]);
	}

	public static String[] getJavaPrimitives() {
		List<String> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(NATURAL);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(DOUBLE);
		return list.toArray(new String[list.size()]);
	}

	public static Converter getConverter(String type) {
		Converter converter = CONVERTER_MAP.get(type);
		return converter == null ? CONVERTER_MAP.get(STRING) : converter;
	}


	public interface Converter {

		Object[] convert(String... value);

		String[] convert(Object... value);
	}


	public static boolean isPrimitive(String value) {
		for (String primitive : getPrimitives()) if (primitive.equals(value)) return true;
		return false;
	}

	public static boolean isJavaPrimitive(String value) {
		for (String primitive : getPrimitives()) if (primitive.equals(value)) return true;
		return false;
	}

	public static class Expression {
		String value;

		public Expression(String value) {
			this.value = value;
		}

		public String get() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
