package io.intino.tara.lang.semantics;

import java.util.ArrayList;
import java.util.List;

public class DeclarationContext {

	private String path;
	private List<String> types = new ArrayList<>();

	public DeclarationContext(List<String> types, String path) {
		this.types = types;
		this.path = path;
	}


	public DeclarationContext doc(String path) {
		this.path = path;
		return this;
	}

	public DeclarationContext types(List<String> types) {
		this.types = types;
		return this;
	}

	public String path() {
		return path;
	}

	public List<String> types() {
		return types;
	}
}
