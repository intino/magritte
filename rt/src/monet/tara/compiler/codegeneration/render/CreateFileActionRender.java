package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

public class CreateFileActionRender extends DefaultRender {

	ASTWrapper wrapper;

	public CreateFileActionRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (ASTWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("definitions", addDefinitionBuilders());
	}

	private String addDefinitionBuilders() {
		String mark = "";
		for (ASTNode primeNode : wrapper.getAST())
			if (!primeNode.isAbstract() && primeNode.is(ASTNode.AnnotationType.ROOT)) {
				String name = primeNode.getIdentifier();
				String projectProperName = RenderUtils.toProperCase(this.projectName);
				mark += "\t\tbuilder.addKind(\"" + name + "\", " + projectProperName + "Icons." + name.toUpperCase() + "_DEFINITION, " +
					projectProperName + "Templates." + name.toUpperCase() + "_DEFINITION);\n ";
			}
		return mark;
	}


}
