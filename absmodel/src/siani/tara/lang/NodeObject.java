package siani.tara.lang;

import java.util.*;

public class NodeObject extends ModelObject {
	String declaredNodeQN;
	boolean caseConcept = false;
	List<DeclaredNode> subconcepts;
	transient List<NodeObject> childrenConcepts;
	List<Annotations.Annotation> annotations = new ArrayList<>();
	List<Variable> variables = new ArrayList<>();
	List<Facet> facets = new ArrayList<>();
	Map<String, FacetTarget> allowedFacets = new HashMap<>();
	List<FacetTarget> facetTargets = new ArrayList<>();
	List<String> parameters = new ArrayList<>();

	public NodeObject() {
		super();
	}

	public NodeObject(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public boolean is(Annotations.Annotation type) {
		return (annotations.contains(type));
	}

	public Attribute[] getAttributes() {
		List<Attribute> result = extractElements(variables, Attribute.class);
		return result.toArray(new Attribute[result.size()]);
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

	public Annotations.Annotation[] getAnnotations() {
		return annotations.toArray(new Annotations.Annotation[annotations.size()]);
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

	public Word[] getWords() {
		List<Word> result = extractElements(variables, Word.class);
		return result.toArray(new Word[result.size()]);
	}

	public List<DeclaredNode> getSubConcepts() {
		return subconcepts != null ? subconcepts : Collections.EMPTY_LIST;
	}

	public boolean isSub() {
		return caseConcept;
	}

	public void setCase(boolean caseConcept) {
		this.caseConcept = caseConcept;
	}

	public void addChild(NodeObject child) {
		if (childrenConcepts == null) childrenConcepts = new ArrayList<>();
		childrenConcepts.add(child);
	}

	public boolean add(DeclaredNode sub) {
		if (subconcepts == null) subconcepts = new ArrayList<>();
		return subconcepts.add(sub);
	}

	public boolean addParameter(String parameter) {
		return parameters.add(parameter);
	}

	public void add(Annotations.Annotation annotation) {
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

	public boolean addFacet(Facet object) {
		return facets.add(object);
	}

	public Map<String, FacetTarget> getAllowedFacets() {
		return allowedFacets;
	}

	public void addAllowedFacet(String name, FacetTarget facetTarget) {
		allowedFacets.put(name, facetTarget);
	}

	public List<FacetTarget> getFacetTargets() {
		return facetTargets;
	}

	public boolean addFacetTarget(FacetTarget object) {
		return facetTargets.add(object);
	}

	public List<Facet> getFacets() {
		return facets;
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
