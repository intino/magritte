package monet.tara.compiler.core.ast;

import java.util.ArrayList;
import java.util.HashMap;

public class ASTNode {

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

	public ASTNode(String identifier) {
		this.identifier = identifier;
		abstractModifier = false;
		finalModifier = false;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public ArrayList<AnnotationType> getAnnotations() {
		return annotations;
	}

	public HashMap<String, String> getReferences() {
		return references;
	}

	public ArrayList<ASTNode> getChildren() {
		return children;
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

	public boolean isFinal() {
		return finalModifier;
	}

	public boolean isAbstract() {
		return abstractModifier;
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

	public void add(String key, String identifier) {
		references.put(key,identifier);
	}

	public enum AnnotationType {
		EXTENSIBLE, HASCODE, ROOT, SINGLETON, MULTIPLE, OPTIONAL;
	}

	public static class Attribute {
		String type;
		String name;

		public Attribute(String type, String name) {
			this.type = type;
			this.name = name;
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

		public ArrayList<ASTNode> getNodes() {
			return nodes;
		}

		public ArrayList<AnnotationType> getAnnotations() {
			return annotations;
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
