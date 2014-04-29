package monet.tara.compiler.codegeneration.render;

import monet.tara.lang.ASTWrapper;
import monet.tara.lang.ASTNode;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.util.ArrayList;
import java.util.List;

public class SignatureCompletionContributorRender extends DefaultRender {
	List<ASTNode> nodes;

	public SignatureCompletionContributorRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.nodes = ((ASTWrapper) ast).getAST();
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
		for (ASTNode node : nodes)
			list.add(node.getIdentifier());
		return list.toArray(new String[list.size()]);
	}
}

