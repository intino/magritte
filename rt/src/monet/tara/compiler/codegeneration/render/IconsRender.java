package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.Node;
import monet.tara.lang.NodeObject;
import monet.tara.lang.TreeWrapper;

public class IconsRender extends DefaultRender {

	TreeWrapper wrapper;

	public IconsRender(String tplName, String projectName, Object astWrapper) throws TaraException {
		super(tplName, projectName);
		this.wrapper = (TreeWrapper) astWrapper;
	}

	@Override
	protected void init() {
		super.init();
		addMark("iconsVar", addIconVariables());
		addMark("icons", addDefinitionIcons());
	}

	private String addIconVariables() {
		String mark = "\tpublic static final String TARA = \"TARA\";\n";
		for (Node node : wrapper.getTree())
			if (!node.getObject().isAbstract() && node.getObject().is(NodeObject.AnnotationType.ROOT)) {
				String name = node.getName();
				mark += "\tpublic static final String " + name.toUpperCase() + "_" + "DEFINITION = \"" + name.toUpperCase() + "\";\n";
			}
		return mark;
	}

	private String addDefinitionIcons() {
		String mark = "\t\ticons.put(\"TARA\", IconLoader.getIcon(\"/icons/Tara.png\"));\n";
		for (Node primeNode : wrapper.getTree())
			if (!primeNode.getObject().isAbstract() && primeNode.getObject().is(NodeObject.AnnotationType.ROOT)) {
				String name = primeNode.getName();
				mark += "\t\ticons.put(\"" + name.toUpperCase() + "\", IconLoader.getIcon(\"/icons/definitions/" + name.toLowerCase() + ".png\"));\n";
			}
		return mark;
	}
}