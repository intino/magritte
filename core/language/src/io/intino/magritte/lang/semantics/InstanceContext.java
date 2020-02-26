package io.intino.magritte.lang.semantics;

import java.util.List;

public class InstanceContext {

	private String path;
	private List<String> types;

	public InstanceContext(List<String> types, String path) {
		this.types = types;
		this.path = path;
	}


	public InstanceContext doc(String path) {
		this.path = path;
		return this;
	}

	public InstanceContext types(List<String> types) {
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
