package monet.tara.compiler.code_generation;

import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.code_generation.render.DefaultRender;
import monet.tara.compiler.code_generation.render.RenderUtils;

import java.util.HashMap;

public class DefinitionRender extends DefaultRender {

	ASTNode rootNode;
	int recursionLevel = 0;

	public DefinitionRender(String tplName, String projectName, Object node) {
		super(tplName, projectName);
		this.rootNode = (ASTNode) node;
	}


	@Override
	protected void init() {
		super.init();
		addMark("root", addDefinition(rootNode, 0));
	}

	private String addDefinition(ASTNode node, int level) {
		HashMap<String, Object> map = new HashMap<>();
		StringBuilder definition = new StringBuilder();
		map.put("modifier", node.getModifier());
		map.put("DefinitionName", node.getIdentifier());
		map.put("implements", getAnnotationsString(node));
		map.put("attributes", addAttributes(node));
		map.put("references", addReferences(node));
		map.put("childrenGetters", getChildrenGetters(node));
		if (node.hasCode()) map.put("id", "id");
		StringBuilder childDefinitions = new StringBuilder();
		for (ASTNode child : node.getChildren())
			childDefinitions.append(addDefinition(child, level + 1));
		map.put("childrenDeclaration", childDefinitions.toString());
		definition.append(block("definition", map));
		String result = definition.toString();
		if (!node.equals(rootNode))
			return result.replaceAll("\n", "\n\t");
		return result.replaceAll("\n[\t]*[ ]*\n[\t]*[ ]*\n", "\n\n");
	}

	private String addAttributes(ASTNode node) {
		StringBuilder attributes = new StringBuilder();
		for (ASTNode.Attribute attribute : node.getAttributes()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("name", attribute.getName());
			map.put("type", attribute.getType());
			attributes.append(block("attribute", map));
		}
		return attributes.toString();
	}

	private String addReferences(ASTNode node) {
		StringBuilder references = new StringBuilder();
		for (String reference : node.getReferences().keySet()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("type", node.getReferences().get(reference));
			map.put("name", reference);
			references.append(block("reference", map));
		}
		return references.toString();
	}

	private String getAnnotationsString(ASTNode node) {
		if (node.getAnnotations().length > 0) {
			StringBuilder annotations = new StringBuilder();
			annotations.append("implements ");
			for (ASTNode.AnnotationType annotationType : node.getAnnotations())
				annotations.append(annotationType.name()).append(" ");
			return annotations.toString();
		}
		return "";
	}

	public String getChildrenGetters(ASTNode node) {
		StringBuilder childGetters = new StringBuilder();
		for (ASTNode child : node.getChildren()) {
			HashMap<String, Object> map = new HashMap<>();
			String childIdentifier =
					RenderUtils.toProperCase((child.getIdentifier().length() > 0) ? child.getIdentifier() : child.getParent());
			if (childIdentifier.contains("[]")) map.put("plural", "plural");
			map.put("childType", childIdentifier);
			childGetters.append(block("childGetter", map)).append("\t");
		}
		return childGetters.toString();
	}


}
