package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HighlightRender extends DefaultRender {
	Map<String, String> identifiers;

	public HighlightRender(String tplName, String projectName, Object ast) throws TaraException {
		super(tplName, projectName);
		this.identifiers = ((ASTWrapper) ast).getIdentifiers();
	}

	@Override
	protected void init() {
		super.init();
		addMark("highlightKey", buildKeys(makeKeywordList()));
	}

	private String buildKeys(String[] list) {
		StringBuilder builder = new StringBuilder("");
		for (String key : list)
			builder.append("\n\t\t").append("KEYS.put(").append(RenderUtils.toProperCase(projectName)).
				append("Types.").append(key).append(", KEYWORD);");
		builder.append("\n\t\tKEYS.put(").append(RenderUtils.toProperCase(projectName)).append("Types.CODE, DOCUMENTATION);\n");
		return builder.toString();
	}

	private String[] makeKeywordList() {
		List<String> list = new ArrayList<>();
		for (String key : identifiers.keySet())
			if (key.contains("KEY"))
				list.add(key);
		return list.toArray(new String[list.size()]);
	}
}

