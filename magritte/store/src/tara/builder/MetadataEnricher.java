package tara.builder;

public interface MetadataEnricher {
	class Metadata {
		public final String name;
		public final Type type;
		public final Object extra;

		Metadata(String name, Type type) {
			this(name, type, null);
		}

		Metadata(String name, Type type, Object extra) {
			this.name = name;
			this.type = type;
			this.extra = extra;
		}
	}

	enum Type {
		String, Integer, Natural, Double, Word, Date, Reference
	}

	Metadata get(String qualifiedName);
}
