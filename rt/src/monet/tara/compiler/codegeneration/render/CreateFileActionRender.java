package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.TreeWrapper;

public class CreateFileActionRender extends DefaultRender {

	TreeWrapper wrapper;

	public CreateFileActionRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (TreeWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("definitions", addDefinitionBuilders());
	}

	private String addDefinitionBuilders() {
		String mark = "";
		for (AbstractNode primeNode : wrapper.getTree())
			if (!primeNode.isAbstract() && primeNode.is(AbstractNode.AnnotationType.ROOT)) {
				String name = primeNode.getIdentifier();
				String projectProperName = RenderUtils.toProperCase(this.projectName);
				mark += "\t\tbuilder.addKind(\"" + name + "\", " + projectProperName + "Icons." + name.toUpperCase() + "_DEFINITION, " +
					projectProperName + "Templates." + name.toUpperCase() + "_DEFINITION);\n ";
			}
		return mark;
	}


}
