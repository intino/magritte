package tara.lang.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Primitive {
	INTEGER {
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
	},
	DOUBLE {
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
	},
	BOOLEAN {
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
	},
	STRING {
		@Override
		public String[] convert(String... value) {
			return value;
		}

		@Override
		public String[] convert(Object... value) {
			List<String> strings = new ArrayList<>();
			for (Object o : value) strings.add(o.toString());
			return strings.toArray(new String[strings.size()]);
		}
	},
	FILE {
		@Override
		public File[] convert(String... value) {
			List<File> list = new ArrayList<>();
			for (String o : value) list.add(new File(o));
			return list.toArray(new File[list.size()]);
		}

		@Override
		public String[] convert(Object... value) {
			List<String> strings = new ArrayList<>();
			for (Object o : value) strings.add(o.toString());
			return strings.toArray(new String[strings.size()]);
		}
	},
	REFERENCE,
	WORD,
	NATIVE,
	TUPLE,
	DATE,
	TIME, EMPTY;


	public Object[] convert(String... value) {
		return value;
	}

	public String[] convert(Object... value) {
		List<String> values = new ArrayList<>();
		for (Object o : value) values.add(o.toString());
		return values.toArray(new String[values.size()]);
	}

	public static Primitive value(String value) {
		try {
			return Primitive.valueOf(Primitive.class, value.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getName() {
		return super.name().toLowerCase();
	}

	public String javaName() {
		return capitalize(this.name());
	}

	private static String capitalize(String type) {
		return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
	}


	public static List<Primitive> getPrimitives() {
		List<Primitive> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(DOUBLE);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(NATIVE);
		list.add(WORD);
		list.add(TUPLE);
		list.add(TIME);
		list.add(DATE);
		list.add(FILE);
		return list;
	}

	public static boolean isPrimitive(String value) {
		for (Primitive primitive : getPrimitives()) if (primitive.getName().equalsIgnoreCase(value)) return true;
		return false;
	}

	public static boolean isJavaPrimitive(String value) {
		for (Primitive primitive : getPrimitives()) if (primitive.getName().equalsIgnoreCase(value)) return true;
		return false;
	}

	public static List<Primitive> getJavaPrimitives() {
		List<Primitive> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(DOUBLE);
		return list;
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

	public static class Reference {
		String destiny;

		public Reference(String value) {
			this.destiny = value;
		}

		public String get() {
			return destiny;
		}

		@Override
		public String toString() {
			return destiny;
		}
	}
}
