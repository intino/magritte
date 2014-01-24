package monet.tara.transpiler.metamodel.render;

import monet.tara.transpiler.DefaultRender;
import monet.tara.transpiler.metamodel.metamodeldescription.Definition;

import java.util.HashMap;
import java.util.List;

/**
 * Created by oroncal on 20/01/14.
 */
public class DefinitionRender extends DefaultRender {

	Definition definition;

	public DefinitionRender(String tplName, String projectName, Object definition) {
		super(tplName, projectName);
		this.definition = (Definition) definition;
	}

	@Override
	protected void init() {
		super.init();
		addMark("childrenDeclaration", this.initChildrenDeclaration(definition.getChildren()));
	}

	private String initChildrenDeclaration(List<Definition> children) {
		StringBuilder childrenDeclaration = new StringBuilder();

		for (Definition child : children)
			childrenDeclaration.append(this.initChildDeclaration(child));

		return childrenDeclaration.toString();
	}


	private String initChildDeclaration(Definition child) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("childName", child.getName());
		map.put("childrenDeclaration", this.initChildrenDeclaration(child.getChildren()));
		return block("childrenDeclaration", map);
	}
}
