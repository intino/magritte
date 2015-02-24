package siani.tara.lang;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Facet {

	private String name;
	private boolean isIntention = false;
	private String implementation;
	private Map<String, Variable> parameters = new LinkedHashMap<>();
	private List<Node> inner = new ArrayList<>();

	public Facet(String name, String implementation) {
		this.name = name;
		this.implementation = implementation;
	}

	public String getName() {
		return name;
	}

	public Map<String, Variable> getParameters() {
		return parameters;
	}

	public void add(String name, Variable parameter) {
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

	public boolean isIntention() {
		return isIntention;
	}

	public void setIntention(boolean isIntention) {
		this.isIntention = isIntention;
	}
}
