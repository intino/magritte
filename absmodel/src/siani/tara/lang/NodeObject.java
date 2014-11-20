package siani.tara.lang;

import java.util.*;

public class NodeObject extends ModelObject {
	String declaredNodeQN;
	boolean subConcept = false;
	List<DeclaredNode> subConcepts;
	transient List<NodeObject> childrenConcepts;
	transient Map<String, Variable> parameters = new LinkedHashMap<>();
	transient Map<String, Variable> variableInits = new LinkedHashMap<>();
	List<Variable> variables = new ArrayList<>();
	List<Annotations.Annotation> annotations = new ArrayList<>();
	List<Facet> facets = new ArrayList<>();
	Map<String, FacetTarget> allowedFacets = new HashMap<>();
	List<FacetTarget> facetTargets = new ArrayList<>();
	String address;

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

	public Map<String, Variable> getParameters() {
		return parameters;
	}

	public Annotations.Annotation[] getAnnotations() {
		return annotations.toArray(new Annotations.Annotation[annotations.size()]);
	}

	public List<Reference> getReferences() {
		return extractElements(variables, Reference.class);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return subConcepts != null ? subConcepts : Collections.EMPTY_LIST;
	}

	public boolean isSub() {
		return subConcept;
	}

	public void setSub(boolean caseConcept) {
		this.subConcept = caseConcept;
	}

	public void addChild(NodeObject child) {
		if (childrenConcepts == null) childrenConcepts = new ArrayList<>();
		childrenConcepts.add(child);
	}

	public boolean add(DeclaredNode sub) {
		if (subConcepts == null) subConcepts = new ArrayList<>();
		return subConcepts.add(sub);
	}

	public void addParameter(String name, Variable value) {
		parameters.put(name, value);
	}

	public void add(Annotations.Annotation annotation) {
		annotations.add(annotation);
	}

	public boolean addAll(Collection<? extends Annotations.Annotation> c) {
		return annotations.addAll(c);
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

	public void putVariableInitialisation(String name, Variable value) {
		variableInits.put(name, value);
	}

	public Map<String, Variable> getVariableInits() {
		return variableInits;
	}

	public String toString() {
		return "NodeObject{" + name + '}';
	}
}
