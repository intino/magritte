package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignatureCompletionContributorRender extends DefaultRender {
	Map<String, String> identifiers;

	public SignatureCompletionContributorRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.identifiers = ((AST)ast).getIdentifiers();
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
		for (String key : identifiers.keySet())
			if (key.contains("KEY"))
				list.add(identifiers.get(key));
		return list.toArray(new String[list.size()]);
	}
}

