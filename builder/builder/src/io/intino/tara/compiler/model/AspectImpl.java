package io.intino.tara.compiler.model;


import io.intino.tara.lang.model.Aspect;
import io.intino.tara.lang.model.Node;

public class AspectImpl implements Aspect {
	private String file;
	private int line;
	private Node container;
	private String type;
	private String fullType;
	private String language;

	public AspectImpl(String type) {
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

	@Override
	public String type() {
		return type;
	}

	@Override
	public void fullType(String type) {
		this.fullType = type;
	}

	@Override
	public String fullType() {
		return fullType;
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
	public String languageName() {
		return language;
	}

	@Override
	public void languageName(String language) {
		this.language = language;
	}
}
