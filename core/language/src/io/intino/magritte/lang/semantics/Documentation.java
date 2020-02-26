package io.intino.magritte.lang.semantics;

public class Documentation {

	private final String layer;
	private final String file;
	private final int line;
	private final String doc;

	public Documentation(String layer, String file, int line, String doc) {
		this.layer = layer;
		this.file = file;
		this.line = line;
		this.doc = doc;
	}

	public String layer() {
		return layer;
	}

	public String file() {
		return file;
	}

	public int line() {
		return line;
	}

	public String description() {
		return doc;
	}
}
