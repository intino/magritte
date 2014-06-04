package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.Node;
import monet.tara.lang.NodeTree;
import monet.tara.lang.TreeWrapper;

import java.util.ArrayList;
import java.util.List;

public class SignatureCompletionContributorRender extends DefaultRender {
	NodeTree nodes;

	public SignatureCompletionContributorRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.nodes = ((TreeWrapper) ast).getTree();
	}

	@Override
	protected void init() {
		super.init();
		addMark("identifiers", buildKeys(makeKeywordList()));
	}

	private String buildKeys(String[] list) {
		StringBuilder builder = new StringBuilder("");
		for (String key : list)
			builder.append("\n\t\t\t\t\t").append("resultSet.addElement(LookupElementBuilder.create(\"").append(key).append("\"));");
		return builder.toString();
	}

	private String[] makeKeywordList() {
		List<String> list = new ArrayList<>();
		for (Node node : nodes)
			list.add(node.getName());
		return list.toArray(new String[list.size()]);
	}
}

