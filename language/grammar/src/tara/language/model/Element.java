package tara.language.model;

public interface Element {

	String file();

	default void file(String file) {
		throw new UnsupportedOperationException();
	}

	default int line() {
		return 0;
	}

	default void line(int line) {
		throw new UnsupportedOperationException();
	}

	default int column() {
		return 0;
	}

	default void column(int column) {
//		throw new UnsupportedOperationException();
	}

	default String language() {
		return null;
	}

	default void language(String language) {
	}
}
