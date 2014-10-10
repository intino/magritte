package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class Facet {

	private String name;
	private String implementation;
	private List<String> parameters = new ArrayList<>();
	private List<Node> inner = new ArrayList<>();

	public Facet(String name, String implementation) {
		this.name = name;
		this.implementation = implementation;
	}

	public String getName() {
		return name;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public boolean add(String parameter) {
		return parameters.add(parameter);
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public List<Node> getInner() {
		return inner;
	}

	public boolean add(Node node) {
		return inner.add(node);
	}
}
