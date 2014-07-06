package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeObject {
	private transient Node declaredNode;
	private boolean caseConcept = false;
	private List<Node> cases;
	private String doc;
	private String parentName;
	private transient NodeObject parentObject;
	private transient List<NodeObject> childrenConcepts;
	private transient Node baseNode = null;
	private transient String baseName = null;
	private String type = "Concept";
	private String name = "";
	private List<AnnotationType> annotations = new ArrayList<>();
	private List<String> imports = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private List<NodeObject> intentions = new ArrayList<>();
	private List<NodeObject> facets = new ArrayList<>();
	private String box;
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

	public Node getDeclaredNode() {
		return declaredNode;
	}

	public void setDeclaredNode(Node declaredNode) {
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

	public List<String> getImports() {
		return imports;
	}

	public void setImports(String[] imports) {
		if (imports.length > 0)
			Collections.addAll(this.imports, imports);
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Node getBaseNode() {
		return baseNode;
	}

	public void setBaseNode(Node baseObject) {
		this.baseNode = baseObject;
	}

	public NodeObject getParent() {
		return parentObject;
	}

	public void setParentObject(NodeObject parentObject) {
		this.parentObject = parentObject;
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

	public List<Node> getCases() {
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

	public boolean add(Node node) {
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

	public boolean addFacet(NodeObject variable) {
		return facets.add(variable);
	}

	public boolean addIntention(NodeObject variable) {
		return intentions.add(variable);
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

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<NodeObject> getFacets() {
		return facets;
	}

	public void setFacets(List<NodeObject> facets) {
		this.facets = facets;
	}

	public List<NodeObject> getIntentions() {
		return intentions;
	}

	public void setIntentions(List<NodeObject> intentions) {
		this.intentions = intentions;
	}

	public enum AnnotationType {
		NAMEABLE, ROOT, TERMINAL, SINGLE, REQUIRED, PRIVATE, PROPERTY;
	}
}
