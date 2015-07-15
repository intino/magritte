package tara.language.model;

public interface Element {

	String file();

	default void file(String file) {

	}

	default int line() {
		return 0;
	}

	default void line(int line) {

	}
}
