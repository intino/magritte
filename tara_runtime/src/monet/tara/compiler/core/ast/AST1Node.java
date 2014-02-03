package monet.tara.compiler.core.ast;

import java.util.ArrayList;
import java.util.HashMap;

public class AST1Node {

	private String doc;
	private boolean abstractModifier;
	private boolean finalModifier;
	private String parent;
	private ArrayList<AnnotationType> annotations = new ArrayList<>();
	private ArrayList<Attribute> attributes = new ArrayList<>();
	private ArrayList<Word> words = new ArrayList<>();
	private ArrayList<ASTNode> children = new ArrayList<>();
	private ArrayList<SubModel> subModels = new ArrayList<>();
	private HashMap<String, String> references = new HashMap<>();

	private String identifier = "";

	public AST1Node(String identifier) {
		this.identifier = identifier;
		abstractModifier = false;
		finalModifier = false;
	}

	public AST1Node() {
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
		return attributes.toArray(new Attribute[attributes.size()]);
	}

	public AnnotationType[] getAnnotations() {
		return annotations.toArray(new AnnotationType[annotations.size()]);
	}

	public HashMap<String, String> getReferences() {
		return references;
	}

	public ASTNode[] getChildren() {
		return children.toArray(new ASTNode[children.size()]);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
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
		return words.toArray(new Word[words.size()]);
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
		attributes.add(attribute);
	}

	public void add(Word word) {
		words.add(word);
	}

	public void add(ASTNode child) {
		children.add(child);
	}

	public void add(SubModel submodel) {
		subModels.add(submodel);
	}

	public void addReference(String type, String identifier) {
		references.put(identifier, type);
	}

	public enum AnnotationType {
		Extensible, HasCode, Root, Singleton, Multiple, Optional
	}

	public static class Attribute {
		String type;
		String name;

		public Attribute(String type, String name) {
			this.type = type;
			this.name = name;
		}

		public String getType() {
			return type;
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

	public static class Word {
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

}
