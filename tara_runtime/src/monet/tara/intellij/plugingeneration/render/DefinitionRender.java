package monet.tara.intellij.plugingeneration.render;

import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.render.DefaultRender;

import java.util.HashMap;
import java.util.List;

public class DefinitionRender extends DefaultRender {

	ASTNode ASTNode;

	public DefinitionRender(String tplName, String projectName, Object definition) {
		super(tplName, projectName);
		this.ASTNode = (ASTNode) definition;
	}

	@Override
	protected void init() {
		super.init();
		addMark("childrenDeclaration", this.initChildrenDeclaration(ASTNode.getChildren()));
	}

	private String initChildrenDeclaration(List<ASTNode> children) {
		StringBuilder childrenDeclaration = new StringBuilder();

		for (ASTNode child : children)
			childrenDeclaration.append(this.initChildDeclaration(child));

		return childrenDeclaration.toString();
	}


	private String initChildDeclaration(ASTNode child) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("childName", child.getIdentifier());
		map.put("childrenDeclaration", this.initChildrenDeclaration(child.getChildren()));
		return block("childrenDeclaration", map);
	}
}
