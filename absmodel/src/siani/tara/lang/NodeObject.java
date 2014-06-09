package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeObject {
	private Node declaredNode;
	private boolean abstractModifier;
	private boolean finalModifier;
	private boolean caseConcept;
	private boolean base;
	private String doc;
	private String parentName;
	private transient NodeObject parentConcept;
	private transient List<NodeObject> childrenConcepts;
	private String baseParentConcept;
	private String name = "";
	private List<AnnotationType> annotations = new ArrayList<>();
	private List<String> imports = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	private String aPackage;

	public NodeObject() {
	}

	public NodeObject(String name) {
		this.name = name;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.caseConcept = false;
		this.base = false;
	}


	public boolean is(AnnotationType type) {
		return (annotations.contains(type));
	}

	public boolean hasName() {
		return annotations.contains(AnnotationType.HAS_NAME);
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

	public NodeObject getParent() {
		return parentConcept;
	}

	public void setParentConcept(NodeObject parentConcept) {
		this.parentConcept = parentConcept;
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

	public boolean isFinal() {
		return finalModifier;
	}

	public boolean isAbstract() {
		return abstractModifier;
	}

	public boolean isCase() {
		return caseConcept;
	}

	public void setCase(boolean caseConcept) {
		this.caseConcept = caseConcept;
	}

	public boolean isBase() {
		return base;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	public String getModifier() {
		return abstractModifier ? "abstract" : !finalModifier ? "" : "final";
	}

	public void setModifier(String modifier) {
		if ("abstract".equals(modifier))
			abstractModifier = true;
		else finalModifier = true;
	}

	public void addChild(NodeObject child) {
		if (childrenConcepts == null) childrenConcepts = new ArrayList<>();
		childrenConcepts.add(child);
	}

	public void add(AnnotationType annotation) {
		annotations.add(annotation);
	}

	public boolean add(Variable variable) {
		return variables.add(variable);
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


	public String getPackage() {
		return aPackage;
	}

	public void setPackage(String aPackage) {
		this.aPackage = aPackage;
	}

	public String getBaseNode() {
		return baseParentConcept;
	}

	public void setBaseParentConcept(String baseParentConcept) {
		this.baseParentConcept = baseParentConcept;
	}

	public boolean resolveChild(String[] path) {
		if (path.length > 2 || path.length == 0) return false;
		Variable variable = null;
		for (Variable var : variables)
			if (var.getName().equals(path[0])) variable = var;
		if ((variable != null) && variable instanceof NodeWord)
			for (String wordElement : ((NodeWord) variable).getWordTypes())
				if (wordElement.equals(path[1])) return true;
		return variable != null;
	}

	public enum AnnotationType {
		HAS_NAME, ROOT, SINGLETON, MULTIPLE, REQUIRED, GENERIC, INTENTION;
	}
}
