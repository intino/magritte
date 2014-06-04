package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.NodeObject;

public class DefinitionRender extends DefaultRender {

	NodeObject rootNode;

	public DefinitionRender(String tplPath, String tplName, String projectName, Object node) throws TaraException {
		super(tplName, projectName);
		this.rootNode = (NodeObject) node;
		setPath(tplPath);
	}

	@Override
	protected void init() {
		super.init();
		if (rootNode.hasName())
			addMark("id", "true");
	}





}
