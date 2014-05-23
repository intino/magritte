package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

public class TemplatesClassRender extends DefaultRender {

	ASTWrapper wrapper;

	public TemplatesClassRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (ASTWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("templates", addDefinitionTemplates());
	}

	private String addDefinitionTemplates() {
		String mark = "";
		for (ASTNode primeNode : wrapper.getAST())
			if (!primeNode.isAbstract() && primeNode.is(ASTNode.AnnotationType.ROOT)) {
				String nameProperCase = RenderUtils.toProperCase(primeNode.getIdentifier());
				String projectProperName = RenderUtils.toProperCase(this.projectName);
				mark += "templates.put(\"" + projectProperName + " " + nameProperCase + "\", \"" +
					projectProperName + nameProperCase + ".m1\");";
			}
		return mark;
	}


}