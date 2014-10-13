package siani.tara.lang;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Facet {

	private String name;
	private String implementation;
	private Map<String, String> parameters = new LinkedHashMap<>();
	private List<Node> inner = new ArrayList<>();

	public Facet(String name, String implementation) {
		this.name = name;
		this.implementation = implementation;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void add(String name, String parameter) {
		parameters.put(name, parameter);
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
