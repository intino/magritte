package io.intino.magritte.lang.model;

import io.intino.magritte.lang.model.rules.variable.InstantRule;
import io.intino.magritte.lang.model.rules.variable.TimeRule;
import io.intino.magritte.lang.model.rules.variable.VariableRule;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Primitive {
	INTEGER {
		@Override
		public List<Integer> convert(String... value) {
			return Arrays.stream(value).map(Integer::valueOf).collect(Collectors.toList());
		}
	},
	LONG {
		@Override
		public List<Long> convert(String... value) {
			return Arrays.stream(value).map(Long::valueOf).collect(Collectors.toList());
		}
	},
	DOUBLE {
		@Override
		public List<Double> convert(String... value) {
			return Arrays.stream(value).map(Double::valueOf).collect(Collectors.toList());
		}
	},
	BOOLEAN {
		@Override
		public List<Boolean> convert(String... value) {
			return Arrays.stream(value).map(Boolean::valueOf).collect(Collectors.toList());
		}
	},
	STRING {
		@Override
		public List<String> convert(String... value) {
			return Arrays.asList(value);
		}
	},
	RESOURCE {
		@Override
		public List<File> convert(String... value) {
			return Arrays.stream(value).map(File::new).collect(Collectors.toList());
		}
	},
	REFERENCE,
	WORD,
	FUNCTION,
	OBJECT,
	DATE,
	TIME {
		@Override
		public TimeRule defaultRule() {
			return new TimeRule();

		}
	},
	INSTANT {
		@Override
		public List<Long> convert(String... value) {
			List<Long> list = new ArrayList<>();
			for (String o : value) list.add(Instant.parse(o).toEpochMilli());
			return list;
		}

		@Override
		public InstantRule defaultRule() {
			return new InstantRule();
		}
	},
	EMPTY;


	public List<?> convert(String... value) {
		return Arrays.asList(value);
	}

	public VariableRule defaultRule() {
		return null;
	}

	public List<String> convert(Object... value) {
		List<String> values = new ArrayList<>();
		for (Object o : value) values.add(o.toString());
		return values;
	}

	public static Primitive value(String value) {
		try {
			return Primitive.valueOf(Primitive.class, value.toUpperCase().replaceAll("x|X", ""));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getName() {
		return capitalize(super.name());
	}

	public String javaName() {
		final String capitalized = capitalize(super.name());
		return this.equals(DATE) ? capitalized + "X" : capitalized;
	}

	private static String capitalize(String type) {
		return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
	}

	public static List<Primitive> getPrimitives() {
		List<Primitive> list = new ArrayList<>();
		list.add(INTEGER);
		list.add(LONG);
		list.add(DOUBLE);
		list.add(BOOLEAN);
		list.add(STRING);
		list.add(FUNCTION);
		list.add(WORD);
		list.add(OBJECT);
		list.add(TIME);
		list.add(DATE);
		list.add(INSTANT);
		list.add(RESOURCE);
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
		boolean isToInstance = false;
		List<String> declarationTypes = new ArrayList<>();
		private String path;
		Node reference;

		public Reference(String value) {
			this.destiny = value;
		}

		public Reference(Node reference) {
			this.reference = reference;
			this.destiny = reference == null ? null : reference.name();
		}

		public String get() {
			return reference == null ? destiny : reference.name();
		}

		public Node reference() {
			return reference;
		}

		public void reference(Node destiny) {
			this.reference = destiny;
		}

		public List<String> instanceTypes() {
			return declarationTypes;
		}

		public boolean isToInstance() {
			return reference != null ? reference.is(Tag.Instance) : isToInstance;
		}

		public void setToInstance(boolean toInstance) {
			isToInstance = toInstance;
		}

		public void instanceTypes(List<String> declarationTypes) {
			this.declarationTypes = declarationTypes;
		}

		@Override
		public String toString() {
			return destiny;
		}

		public void path(String path) {
			this.path = path;
		}

		public String path() {
			return path;
		}
	}

	public static class MethodReference {

		private String service;
		private String destiny;

		public MethodReference(String destiny) {
			this.destiny = destiny;
		}

		public void service(String service) {
			this.service = service;
		}

		public String service() {
			return service;
		}

		public String destiny() {
			return destiny;
		}

		@Override
		public String toString() {
			return "@" + destiny;
		}
	}
}
