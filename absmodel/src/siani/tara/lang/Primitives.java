package siani.tara.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Primitives {

	public static final String INTEGER = "integer";
	public static final String NATURAL = "natural";
	public static final String BOOLEAN = "boolean";
	public static final String STRING = "string";
	public static final String DOUBLE = "double";
	public static final String COORDINATE = "coordinate";
	public static final String RESOURCE = "resource";
	public static final String REFERENCE = "identifier";
	public static final String DATE = "date";
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
		CONVERTER_MAP.put(COORDINATE, stringConverter);
		Converter coordinateConverter = new Converter() {
			@Override
			public Object[] convert(String... values) {
				List<Double[]> coordinatesList = new ArrayList<>();
				for (String o : values) {
					String[] split = o.split("-");
					List<Double> doubles = new ArrayList<>();
					for (String coordinate : split)
						doubles.add(Double.valueOf(coordinate));
					coordinatesList.add(doubles.toArray(new Double[doubles.size()]));
				}
				return coordinatesList.toArray();
			}

			@Override
			public String[] convert(Object... value) {
				List<String> strings = new ArrayList<>();
				for (Object o : value) {
					if (!(o instanceof Double[])) return new String[0];
					Double[] doubles = (Double[]) o;
					String stringCoordinate = "";
					for (Double aDouble : doubles)
						stringCoordinate += "-" + aDouble;
					strings.add(stringCoordinate.substring(1));
				}
				return strings.toArray(new String[strings.size()]);
			}
		};
		CONVERTER_MAP.put(REFERENCE, coordinateConverter);

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

		CONVERTER_MAP.put(DATE, new Converter() {
			SimpleDateFormat[] formats = new SimpleDateFormat[]{
				new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()),
				new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.getDefault()),
				new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault()),
				new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()),
				new SimpleDateFormat("yyyy", Locale.getDefault())
			};

			@Override
			public Date[] convert(String... date) {
				List<Date> objects = new ArrayList<>();
				for (String aDate : date)
					for (SimpleDateFormat format : formats)
						try {
							objects.add(format.parse(aDate));
							break;
						} catch (ParseException ignored) {
						}
				return objects.toArray(new Date[objects.size()]);
			}

			@Override
			public String[] convert(Object... value) {
				List<String> strings = new ArrayList<>();
				for (Object o : value) strings.add(formats[0].format((Date) o));
				return strings.toArray(new String[strings.size()]);
			}
		});
	}

	public static String[] getPrimitives() {
		List<String> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(NATURAL);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(DOUBLE);
		list.add(COORDINATE);
		list.add(DATE);
		list.add(RESOURCE);
		return list.toArray(new String[list.size()]);
	}

	public static Converter getConverter(String type) {
		Converter converter = CONVERTER_MAP.get(type);
		return converter == null ? CONVERTER_MAP.get(STRING) : converter;
	}


	public interface Converter {

		public Object[] convert(String... value);

		public String[] convert(Object... value);
	}
}
