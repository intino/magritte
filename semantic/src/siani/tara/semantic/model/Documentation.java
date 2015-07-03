package siani.tara.semantic.model;

public class Documentation {

	private final String file;
	private final int line;
	private final String doc;

	public Documentation(String file, int line, String doc) {
		this.file = file;
		this.line = line;
		this.doc = doc;
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
