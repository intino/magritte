package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.TreeWrapper;

public class TemplatesClassRender extends DefaultRender {

	TreeWrapper wrapper;

	public TemplatesClassRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (TreeWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("templates", addDefinitionTemplates());
	}

	private String addDefinitionTemplates() {
		String mark = "";
		for (AbstractNode primeNode : wrapper.getTree())
			if (!primeNode.isAbstract() && primeNode.is(AbstractNode.AnnotationType.ROOT)) {
				String nameProperCase = RenderUtils.toProperCase(primeNode.getIdentifier());
				String projectProperName = RenderUtils.toProperCase(this.projectName);
				mark += "templates.put(\"" + projectProperName + " " + nameProperCase + "\", \"" +
					projectProperName + nameProperCase + ".m1\");";
			}
		return mark;
	}


}