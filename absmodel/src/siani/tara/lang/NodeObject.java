package siani.tara.lang;

import java.util.*;

public class NodeObject extends ModelObject {
	String declaredNodeQN;
	boolean caseConcept = false;
	List<DeclaredNode> cases;
	transient List<NodeObject> childrenConcepts;
	List<AnnotationType> annotations = new ArrayList<>();
	List<Variable> variables = new ArrayList<>();
	List<NodeObject> facets = new ArrayList<>();
	Map<String, List<Variable>> allowedFacets = new HashMap<>();
	Map<String, String> allowedFacetsConstrains = new HashMap<>();
	List<String> parameters = new ArrayList<>();

	public NodeObject() {
		super();
	}

	public NodeObject(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public boolean is(AnnotationType type) {
		return (annotations.contains(type));
	}

	public NodeAttribute[] getAttributes() {
		List<NodeAttribute> result = extractElements(variables, NodeAttribute.class);
		return result.toArray(new NodeAttribute[result.size()]);
	}

	public String getDeclaredNodeQN() {
		return declaredNodeQN;
	}

	public void setDeclaredNodeQN(String declaredNodeQN) {
		this.declaredNodeQN = declaredNodeQN;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public AnnotationType[] getAnnotations() {
		return annotations.toArray(new AnnotationType[annotations.size()]);
	}

	public List<Reference> getReferences() {
		return extractElements(variables, Reference.class);
	}

	public List<NodeObject> getChildren() {
		return childrenConcepts;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public NodeWord[] getWords() {
		List<NodeWord> result = extractElements(variables, NodeWord.class);
		return result.toArray(new NodeWord[result.size()]);
	}

	public List<DeclaredNode> getCases() {
		return cases != null ? cases : Collections.EMPTY_LIST;
	}

	public boolean isCase() {
		return caseConcept;
	}

	public void setCase(boolean caseConcept) {
		this.caseConcept = caseConcept;
	}

	public void addChild(NodeObject child) {
		if (childrenConcepts == null) childrenConcepts = new ArrayList<>();
		childrenConcepts.add(child);
	}

	public boolean add(DeclaredNode node) {
		if (cases == null) cases = new ArrayList<>();
		return cases.add(node);
	}

	public boolean addParameter(String parameter) {
		return parameters.add(parameter);
	}

	public void add(AnnotationType annotation) {
		annotations.add(annotation);
	}

	public boolean add(Variable variable) {
		return variables.add(variable);
	}

	public void add(int index, Variable element) {
		variables.add(index, element);
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public boolean addFacet(NodeObject object) {
		return facets.add(object);
	}

	public Map<String, List<Variable>> getAllowedFacets() {
		return allowedFacets;
	}

	public List<NodeObject> getFacets() {
		return facets;
	}

	public void setFacets(List<NodeObject> facets) {
		this.facets = facets;
	}

	public void addAllowedFacet(String name, List<Variable> variables) {
		allowedFacets.put(name, variables);
	}

	public String putFacetConstrain(String facet, String facetConstrain) {
		return allowedFacetsConstrains.put(facet, facetConstrain);
	}

	public Map<String, String> getAllowedFacetsConstrains() {
		return allowedFacetsConstrains;
	}

	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e : items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public String toString() {
		return "NodeObject{" + name + '}';
	}
}
