package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public class DefinitionRender extends DefaultRender {

	ASTNode rootNode;

	public DefinitionRender(String tplName, String projectName, Object node) {
		super(tplName, projectName);
		this.rootNode = (ASTNode) node;
	}

	@Override
	protected void init() {
		super.init();
		if (rootNode.hasCode())
			addMark("id", "true");
		addMark("root", addDefinition(rootNode, 0));
	}

	private String addDefinition(ASTNode node, int level) {
		Map<String, Object> map = new HashMap<>();
		StringBuilder definition = new StringBuilder();
		setClassModifiers(node, map, level);
		map.put("DefinitionName", (node.getIdentifier().length() > 0) ? node.getIdentifier() : "_");
		map.put("implements", getAnnotationsString(node));
		map.put("attributes", addAttributes(node));
		map.put("references", addReferences(node));
		map.put("childrenGetters", getChildrenGetters(node));
		if (node.hasCode()) map.put("id", "id");
		StringBuilder childDefinitions = new StringBuilder();
		for (ASTNode child : node.getChildren())
			childDefinitions.append(addDefinition(child, level + 1));
		map.put("childrenDeclaration", childDefinitions.toString());
		definition.append(block("definition", (HashMap<String, Object>) map));
		String result = definition.toString();
		if (!node.equals(rootNode))
			return result.replaceAll("\n", "\n\t");
		return result.replaceAll("\n[\t]*[ ]*\n[\t]*[ ]*\n", "\n\n");
	}

	private void setClassModifiers(ASTNode node, Map<String, Object> map, int level) {
		StringBuilder modifiers = new StringBuilder();
		if (level > 0)
			modifiers.append("static ");
		modifiers.append(node.getModifier()).append((node.getModifier().length() > 0) ? " " : "");
		map.put("modifier", modifiers.toString());
	}

	private String addAttributes(ASTNode node) {
		StringBuilder attributes = new StringBuilder();
		for (ASTNode.Attribute attribute : node.getAttributes()) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", attribute.getName());
			map.put("type", attribute.getPrimitiveType().toUpperCase());
			attributes.append(block("attribute", (HashMap<String, Object>) map));
		}
		return attributes.toString();
	}

	private String addReferences(ASTNode node) {
		StringBuilder references = new StringBuilder();
		for (ASTNode.Reference reference : node.getReferences()) {
			Map<String, Object> map = new HashMap<>();
			map.put("type", reference.getNode());
			map.put("name", reference.getName());
			references.append(block("reference", (HashMap<String, Object>) map));
		}
		return references.toString();
	}

	private String getAnnotationsString(ASTNode node) {
		if (node.getAnnotations().length > 0) {
			StringBuilder annotations = new StringBuilder();
			annotations.append("implements ");
			for (ASTNode.AnnotationType annotationType : node.getAnnotations())
				annotations.append("Metamodel.").append(annotationType.name()).append(", ");
			annotations.replace(annotations.lastIndexOf(","), annotations.lastIndexOf(",") + 1, "");
			return annotations.toString();
		}
		return "";
	}

	public String getChildrenGetters(ASTNode node) {
		StringBuilder childGetters = new StringBuilder();
		for (ASTNode child : node.getChildren()) {
			Map<String, Object> map = new HashMap<>();
			final String childIdentifier =
				RenderUtils.toProperCase((child.getIdentifier().length() > 0) ? child.getIdentifier() : child.getExtendFrom());
			map.put("childGetter", "getChild");
			if (childIdentifier.contains("[]")) {
				map.put("listSuffix", "true");
				map.put("childGetter", "getChildren");
				map.put("listCast", block("multipleCastBlock", new HashMap<String, Object>() {{
					put("childType", childIdentifier);
				}}));
			}
			map.put("childType", childIdentifier);
			childGetters.append(block("childGetter", (HashMap<String, Object>) map)).append("\t");
		}
		return childGetters.toString();
	}


}
