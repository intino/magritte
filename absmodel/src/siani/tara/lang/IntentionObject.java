package siani.tara.lang;

import java.util.*;

public class IntentionObject extends NodeObject {

	List<IntentionObject> objectTargets = new ArrayList<>();
	transient List<String> facetConstrains = new ArrayList<>();
	Map<String, Set<Variable>> facetTargets = new HashMap<>();

	public IntentionObject() {
	}

	public IntentionObject(String type, String name) {
		super(type, name);
	}

	public boolean addFacetObjectTarget(IntentionObject object) {
		return objectTargets.add(object);
	}

	public List<IntentionObject> getFacetTargets() {
		return objectTargets;
	}

	public void setFacetTargets(Map<String, Set<Variable>> facetTargets) {
		this.facetTargets = facetTargets;
	}

	public List<String> getFacetConstrains() {
		return facetConstrains;
	}

	public void setFacetConstrains(List<String> facetConstrains) {
		this.facetConstrains = facetConstrains;
	}

	public boolean addFacetConstrain(String constraint) {
		return facetConstrains.add(constraint);
	}
}
