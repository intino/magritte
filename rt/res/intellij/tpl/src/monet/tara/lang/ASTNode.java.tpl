package monet.tara.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ASTNode {

	private boolean abstractModifier;
	private boolean finalModifier;
	private boolean caseConcept;
	private boolean base;
	private String doc;
	private String extendFrom;
	private String baseConcept;
	private String identifier = "";
	private String file;
	private int line;
	private List<AnnotationType> annotations = new ArrayList<>();
	private List<String> imports = new ArrayList<>();
	private AST children = new AST();
	private List<Variable> variables = new ArrayList<>();
	private transient ASTNode parent;
	private String aPackage;

	public ASTNode() {
	}

	public ASTNode(String identifier, ASTNode parent, String file) {
		this.identifier = identifier;
		this.parent = parent;
		this.file = file;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.caseConcept = false;
		this.base = false;
	}

	public ASTNode(String file) {
		this.file = file;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.caseConcept = false;
		this.base = false;
		this.parent = null;
	}

	public boolean isPrime() {
		return getParent() == null;
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

	public Attribute[] getAttributes() {
		List<Attribute> result = extractElements(variables, Attribute.class);
		return result.toArray(new Attribute[result.size()]);
	}

	public AnnotationType[] getAnnotations() {
		return annotations.toArray(new AnnotationType[annotations.size()]);
	}

	public Reference[] getReferences() {
		List<Reference> result = extractElements(variables, Reference.class);
		return result.toArray(new Reference[result.size()]);
	}

	public AST getChildren() {
		return children;
	}

	public ASTNode getChildByName(String name) {
		for (ASTNode child : getChildren())
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

	public String getExtendFrom() {
		return extendFrom;
	}

	public void setExtendFrom(String extendFrom) {
		this.extendFrom = extendFrom;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public ASTNode[] getCases() {
		List<ASTNode> cases = new ArrayList<>();
		if (base) {
			for (ASTNode child : children)
				if (child.isCase()) cases.add(child);
			return cases.toArray(new ASTNode[cases.size()]);
		} else return new ASTNode[0];
	}

	public Word[] getWords() {
		List<Word> result = extractElements(variables, Word.class);
		return result.toArray(new Word[result.size()]);
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

	public void add(AnnotationType annotation) {
		annotations.add(annotation);
	}

	public void add(Attribute attribute) {
		variables.add(attribute);
	}

	public void add(Word word) {
		variables.add(word);
	}

	public void add(ASTNode child) {
		children.add(child);
	}

	public void addReference(String type, String identifier, boolean isList) {
		variables.add(new Reference(type, identifier, isList));
	}

	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e : items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public ASTNode getParent() {
		return parent;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public String getAbsolutePath() {
		return aPackage + "." + getConceptRoute();
	}

	private String getConceptRoute() {
		return ((parent != null) ? parent.getConceptRoute() +
			((!"".equals(getIdentifier())) ? "." + getIdentifier() : ".annonymous(" + extendFrom + ")") : getIdentifier());
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
		return baseConcept;
	}

	public void setBaseConcept(String baseConcept) {
		this.baseConcept = baseConcept;
	}

	public boolean resolveChild(String[] path) {
		if (path.length > 2 || path.length == 0) return false;
		Variable variable = null;
		for (Variable var : variables)
			if (var.getName().equals(path[0])) variable = var;
		if ((variable != null) && variable instanceof Word)
			for (String wordElement : ((Word) variable).getWordTypes()) if (wordElement.equals(path[1])) return true;
		return variable != null;
	}


	public enum AnnotationType {
		HAS_NAME, ROOT, SINGLETON, MULTIPLE, REQUIRED, GENERIC;
	}

	public static class Attribute extends Variable {
		public String primitiveType;
		public String value;
		public boolean isList;

		public Attribute(String type, String name, boolean isList) {
			this.name = name;
			this.primitiveType = type;
			this.name = name;
			this.isList = isList;
		}

		public String getPrimitiveType() {
			return primitiveType;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isList() {
			return isList;
		}
	}

	public static class Reference extends Variable {
		public String node;
		public boolean isList;

		public Reference(String node, String name, boolean isList) {
			this.name = name;
			this.node = node;
			this.isList = isList;
		}

		public String getNode() {
			return node;
		}

		public boolean isList() {
			return isList;
		}
	}

	public static class Word extends Variable {
		public List<String> wordTypes;

		public Word(String name) {
			this.name = name;
			this.wordTypes = new ArrayList<>();
		}

		public List<String> getWordTypes() {
			return wordTypes;
		}

		public void add(String word) {
			wordTypes.add(word);
		}
	}

	public static class Variable {
		public String name;

		public Variable() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
