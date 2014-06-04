package monet.tara.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractNode {
	private boolean abstractModifier;
	private boolean finalModifier;
	private boolean caseConcept;
	private boolean base;
	private String doc;
	private String parentName;
	private transient AbstractNode parentConcept;
	private transient List<AbstractNode> childrenConcepts;
	private String baseParentConcept;
	private String identifier = "";
	private String file;
	private int line;
	private List<AnnotationType> annotations = new ArrayList<>();
	private List<String> imports = new ArrayList<>();
	private AbstractTree innerConcepts = new AbstractTree();
	private List<Variable> variables = new ArrayList<>();
	private transient AbstractNode container;
	private String aPackage;
	private String qualifiedName;

	public AbstractNode() {
	}

	public AbstractNode(String identifier, AbstractNode container, String file) {
		this.identifier = identifier;
		this.container = container;
		this.file = file;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.caseConcept = false;
		this.base = false;
	}

	public AbstractNode(String file) {
		this.file = file;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.caseConcept = false;
		this.base = false;
		this.container = null;
	}


	public boolean isPrime() {
		return getContainer() == null;
	}

	public boolean is(AnnotationType type) {
		return (annotations.contains(type));
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public boolean hasName() {
		return annotations.contains(AnnotationType.HAS_NAME);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public NodeAttribute[] getAttributes() {
		List<NodeAttribute> result = extractElements(variables, NodeAttribute.class);
		return result.toArray(new NodeAttribute[result.size()]);
	}

	public AnnotationType[] getAnnotations() {
		return annotations.toArray(new AnnotationType[annotations.size()]);
	}

	public Reference[] getReferences() {
		List<Reference> result = extractElements(variables, Reference.class);
		return result.toArray(new Reference[result.size()]);
	}

	public AbstractTree getInnerConcepts() {
		return innerConcepts;
	}

	public AbstractNode getChildByName(String name) {
		for (AbstractNode child \: getInnerConcepts())
			if (child.getIdentifier().equals(name))
				return child;
		return null;
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

	public AbstractNode getParentConcept() {
		return parentConcept;
	}

	public void setParentConcept(AbstractNode parentConcept) {
		this.parentConcept = parentConcept;
	}

	public List<AbstractNode> getChildren() {
		return childrenConcepts;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public AbstractNode[] getCases() {
		List<AbstractNode> cases = new ArrayList<>();
		if (base) {
			for (AbstractNode child \: innerConcepts)
				if (child.isCase()) cases.add(child);
			return cases.toArray(new AbstractNode[cases.size()]);
		} else return new AbstractNode[0];
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
		return abstractModifier ? "abstract" \: !finalModifier ? "" \: "final";
	}

	public void setModifier(String modifier) {
		if ("abstract".equals(modifier))
			abstractModifier = true;
		else finalModifier = true;
	}

	public void addChild(AbstractNode child) {
		if (childrenConcepts == null) childrenConcepts = new ArrayList<>();
		childrenConcepts.add(child);
	}

	public void add(AnnotationType annotation) {
		annotations.add(annotation);
	}

	public boolean add(Variable variable) {
		return variables.add(variable);
	}

	public void add(AbstractNode innerConcept) {
		innerConcepts.add(innerConcept);
	}


	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e \: items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public AbstractNode getContainer() {
		return container;
	}

	public void setContainer(AbstractNode container) {
		this.container = container;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public String getAbsolutePath() {
		return aPackage + "." + getConceptRoute();
	}

	public String getQualifiedName() {
		return (qualifiedName == null) ? qualifiedName = getConceptRoute() \: qualifiedName;
	}

	private String getConceptRoute() {
		return container != null ? container.getConceptRoute() +
			(!container.isBase() ?
				!"".equals(getIdentifier()) ? "." + getIdentifier() \: "." + parentName \: "") \: getIdentifier();
	}

	public String getFile() {
		return file;
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
		for (Variable var \: variables)
			if (var.getName().equals(path[0])) variable = var;
		if ((variable != null) && variable instanceof NodeWord)
			for (String wordElement \: ((NodeWord) variable).getWordTypes())
				if (wordElement.equals(path[1])) return true;
		return variable != null;
	}

	public enum AnnotationType {
		HAS_NAME, ROOT, SINGLETON, MULTIPLE, REQUIRED, GENERIC, INTENTION;
	}

}
