package tara.builder;

public interface MetadataEnricher {
    class Metadata {
        public final Type type;
        public final Object extra;

        private Metadata(Type type) {
            this(type, null);
        }
        private Metadata(Type type, Object extra) {
            this.type = type;
            this.extra = extra;
        }
    }

    enum Type {
        String, Integer, Natural, Double, Word, Date, Reference
    }

    Metadata get(String qualifiedName);
}
