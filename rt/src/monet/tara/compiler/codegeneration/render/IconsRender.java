package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

public class IconsRender extends DefaultRender {

	ASTWrapper wrapper;

	public IconsRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (ASTWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("iconsVar", addIconVariables());
		addMark("icons", addDefinitionIcons());
	}

	private String addIconVariables() {
		String mark = "\tpublic static final String TARA = \"TARA\";\n";
		for (ASTNode primeNode : wrapper.getAST())
			if (!primeNode.isAbstract() && primeNode.is(ASTNode.AnnotationType.ROOT)) {
				String name = primeNode.getIdentifier();
				mark += "\tpublic static final String " + name.toUpperCase() + "_" + "DEFINITION = \"" + name.toUpperCase() + "\";\n";
			}
		return mark;
	}

	private String addDefinitionIcons() {
		String mark = "\t\ticons.put(\"TARA\", IconLoader.getIcon(\"/icons/Tara.png\"));\n";
		for (ASTNode primeNode : wrapper.getAST())
			if (!primeNode.isAbstract() && primeNode.is(ASTNode.AnnotationType.ROOT)) {
				String name = primeNode.getIdentifier();
				mark += "\t\ticons.put(\"" + name.toUpperCase() + "\", IconLoader.getIcon(\"/icons/definitions/" + name.toLowerCase() + ".png\"));\n";
			}
		return mark;
	}
}