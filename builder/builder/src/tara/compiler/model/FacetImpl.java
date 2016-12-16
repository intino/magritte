package tara.compiler.model;


import tara.lang.model.Facet;
import tara.lang.model.Node;

public class FacetImpl implements Facet {

	private String file;
	private int line;
	private Node container;
	private String type;
	private String language;

	public FacetImpl(String type) {
		this.type = type;
	}

	@Override
	public Node container() {
		return container;
	}

	@Override
	public void container(Node container) {
		this.container = container;
	}

	private String shortType() {
		return type().contains(".") ? type().substring(type().lastIndexOf(".") + 1) : type();
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String file() {
		return file;
	}

	@Override
	public void file(String file) {
		this.file = file;
	}

	@Override
	public int line() {
		return line;
	}

	@Override
	public void line(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return type();
	}

	@Override
	public String language() {
		return language;
	}

	@Override
	public void language(String language) {
		this.language = language;
	}
}
