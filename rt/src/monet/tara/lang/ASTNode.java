package monet.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {

	private boolean abstractModifier;
	private boolean finalModifier;
	private boolean morph;
	private boolean polymorphic;
	private String doc;
	private String extendFrom;
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
		this.morph = false;
		this.polymorphic = false;
	}

	public ASTNode(String file) {
		this.file = file;
		this.abstractModifier = false;
		this.finalModifier = false;
		this.morph = false;
		this.polymorphic = false;
		this.parent = null;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public boolean hasCode() {
		for (AnnotationType annotation : annotations)
			if (annotation.name().endsWith(AnnotationType.HAS_CODE.name()))
				return true;
		return false;
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

	public ASTNode[] getMorphs() {
		List<ASTNode> morphs = new ArrayList<>();
		if (polymorphic) {
			for (ASTNode child : children)
				if (child.isMorph()) morphs.add(child);
			return morphs.toArray(new ASTNode[morphs.size()]);
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

	public boolean isMorph() {
		return morph;
	}

	public void setMorph(boolean morph) {
		this.morph = morph;
	}

	public boolean isPolymorphic() {
		return polymorphic;
	}

	public void setPolymorphic(boolean polymorphic) {
		this.polymorphic = polymorphic;
	}

	public String getModifier() {
		return abstractModifier ? "abstract" : !finalModifier ? "" : "final";
	}

	public void setModifier(String modifier) {
		if ("abstract".equals(modifier))
			abstractModifier = true;
		else finalModifier = true;
	}

	public void add(AnnotationType extension) {
		annotations.add(extension);
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
		return (parent != null) ? parent.getAbsolutePath() +
			((!"".equals(getIdentifier())) ? "." + getIdentifier() : ".annonymous(" + extendFrom + ")") : getIdentifier();
	}

	public String getFile() {
		return file;
	}

	public void addImports(ArrayList<String> imports) {
		imports.addAll(imports);
	}

	public String getPackage() {
		return aPackage;
	}

	public void setPackage(String aPackage) {
		this.aPackage = aPackage;
	}


	public enum AnnotationType {
		EXTENSIBLE, HAS_CODE, ROOT, SINGLETON, MULTIPLE, OPTIONAL, GENERIC;
	}

	public static class Attribute extends Variable {
		String primitiveType;
		String name;
		String value;
		boolean isList;

		public Attribute(String type, String name, boolean isList) {
			this.primitiveType = type;
			this.name = name;
			this.isList = isList;
		}

		public String getPrimitiveType() {
			return primitiveType;
		}

		public String getName() {
			return name;
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
		String node;
		String name;
		boolean isList;

		public Reference(String node, String name, boolean isList) {
			this.node = node;
			this.name = name;
			this.isList = isList;
		}

		public String getNode() {
			return node;
		}

		public String getName() {
			return name;
		}

		public boolean isList() {
			return isList;
		}
	}

	public static class Word extends Variable {
		List<String> wordTypes;
		private String identifier;

		public Word(String identifier) {
			this.identifier = identifier;
			this.wordTypes = new ArrayList<>();
		}

		public String getIdentifier() {
			return identifier;
		}

		public List<String> getWordTypes() {
			return wordTypes;
		}

		public void add(String word) {
			wordTypes.add(word);
		}
	}

	private static class Variable {
	}
}
