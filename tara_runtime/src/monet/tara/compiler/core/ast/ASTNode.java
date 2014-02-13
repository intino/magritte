package monet.tara.compiler.core.ast;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {

	private String doc;
	private boolean abstractModifier;
	private boolean finalModifier;
	private String extendFrom;
	private ASTNode parent;
	private ArrayList<AnnotationType> annotations = new ArrayList<>();
	private ArrayList<ASTNode> children = new ArrayList<>();
	private ArrayList<SubModel> subModels = new ArrayList<>();
	private ArrayList<Variable> variables = new ArrayList<>();
	private String identifier = "";

	public ASTNode(String identifier, ASTNode parent) {
		this.identifier = identifier;
		abstractModifier = false;
		finalModifier = false;
		this.parent = parent;
	}

	public ASTNode() {
		abstractModifier = false;
		finalModifier = false;
	}

	public int getLineNumber() {
		return 0;
	}

	public int getColumnNumber() {
		return 0;
	}

	public boolean hasCode() {
		for (AnnotationType annotation : annotations)
			if (annotation.name().endsWith(AnnotationType.HasCode.name()))
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

	public ASTNode[] getChildren() {
		return children.toArray(new ASTNode[children.size()]);
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

	public SubModel[] getSubModels() {
		return subModels.toArray(new SubModel[subModels.size()]);
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

	public String getModifier() {
		return abstractModifier ? "abstract" : !finalModifier ? "" : "final";
	}

	public void setModifier(String modifier) {
		if (modifier.equals("abstract"))
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

	public void add(SubModel submodel) {
		subModels.add(submodel);
	}

	public void addReference(String type, String identifier) {
		variables.add(new Reference(type, identifier));
	}

	private <T> List<T> extractElements(List items, Class<T> type) {
		List<T> result = new ArrayList<>();
		for (Object e : items)
			if (type.isAssignableFrom(e.getClass()))
				result.add((T) e);
		return result;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}

	public String getAbsolutePath() {
		return (parent != null) ? parent.getAbsolutePath() + "." + getIdentifier() : getIdentifier();
	}

	public enum AnnotationType {
		Extensible, HasCode, Root, Singleton, Multiple, Optional;
	}

	public static class Attribute extends Variable {
		String primitiveType;
		String name;

		public Attribute(String type, String name) {
			this.primitiveType = type;
			this.name = name;
		}

		public String getPrimitiveType() {
			return primitiveType;
		}

		public String getName() {
			return name;
		}
	}

	public static class Reference extends Variable {
		String node;
		String name;

		public Reference(String node, String name) {
			this.node = node;
			this.name = name;
		}

		public String getNode() {
			return node;
		}

		public String getName() {
			return name;
		}

	}

	public static class SubModel {
		ArrayList<ASTNode> nodes;
		ArrayList<AnnotationType> annotations;

		public SubModel() {
			nodes = new ArrayList<>();
			annotations = new ArrayList<>();
		}

		public void add(AnnotationType annotation) {
			annotations.add(annotation);
		}

		public void add(ASTNode node) {
			nodes.add(node);
		}

		public ASTNode[] getNodes() {
			return nodes.toArray(new ASTNode[nodes.size()]);
		}

		public AnnotationType[] getAnnotations() {
			return annotations.toArray(new AnnotationType[annotations.size()]);
		}
	}

	public static class Word extends Variable {
		ArrayList<String> wordTypes;
		private String identifier;

		public Word(String identifier) {
			this.identifier = identifier;
			this.wordTypes = new ArrayList<>();
		}

		public String getIdentifier() {
			return identifier;
		}

		public ArrayList<String> getWordTypes() {
			return wordTypes;
		}

		public void add(String word) {
			wordTypes.add(word);
		}
	}

	private static class Variable {
	}
}
