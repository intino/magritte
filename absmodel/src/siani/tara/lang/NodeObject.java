package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class NodeObject {
	private String declaredNode;
	private boolean caseConcept = false;
	private List<DeclaredNode> cases;
	private String doc;
	private String parentName;
	private transient NodeObject parentObject;
	private transient List<NodeObject> childrenConcepts;
	private String type = "Concept";
	private String name = "";
	private List<AnnotationType> annotations = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private List<DeclaredNode> facetTargets = new ArrayList<>();
	private List<DeclaredNode> facetApplies = new ArrayList<>();
	private List<String> parameters = new ArrayList<>();


	public NodeObject() {
	}

	public NodeObject(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public boolean is(AnnotationType type) {
		return (annotations.contains(type));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeAttribute[] getAttributes() {
		List<NodeAttribute> result = extractElements(variables, NodeAttribute.class);
		return result.toArray(new NodeAttribute[result.size()]);
	}

	public String getDeclaredNode() {
		return declaredNode;
	}

	public void setDeclaredNode(String declaredNode) {
		this.declaredNode = declaredNode;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public AnnotationType[] getAnnotations() {
		return annotations.toArray(new AnnotationType[annotations.size()]);
	}

	public Reference[] getReferences() {
		List<Reference> result = extractElements(variables, Reference.class);
		return result.toArray(new Reference[result.size()]);
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public NodeObject getParent() {
		return parentObject;
	}

	public void setParentObject(NodeObject parentObject) {
		this.parentObject = parentObject;
		parentName = parentObject.getName();
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
		return cases;
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

	public boolean applyFacet(DeclaredNode object) {
		return facetApplies.add(object);
	}

	public boolean addFacetTarget(DeclaredNode object) {
		return facetTargets.add(object);
	}

	public void add(int index, Variable element) {
		variables.add(index, element);
	}

	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e : items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DeclaredNode> getFacetApplies() {
		return facetApplies;
	}

	public void setFacetApplies(List<DeclaredNode> facetApplies) {
		this.facetApplies = facetApplies;
	}

	public List<DeclaredNode> getFacetTargets() {
		return facetTargets;
	}

	public void setFacetTargets(List<DeclaredNode> facetTargets) {
		this.facetTargets = facetTargets;
	}

	public enum AnnotationType {
		NAMEABLE, ROOT, TERMINAL, SINGLE, REQUIRED, PRIVATE, PROPERTY;
	}
}
