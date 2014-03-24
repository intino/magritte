package monet.tara.compiler.codegeneration.render;

import java.util.ArrayList;
import java.util.HashMap;

public class HighlightRender extends DefaultRender {
	HashMap<String, String> identifiers;

	public HighlightRender(String tplName, String projectName, Object identifiers) {
		super(tplName, projectName);
		this.identifiers = (HashMap<String, String>) identifiers;
	}

	@Override
	protected void init() {
		super.init();
		addMark("highlightKeys", buildKeys(makeKeywordList()));
	}

	private String buildKeys(String[] list) {
		StringBuilder builder = new StringBuilder("");
		for (String key : list)
			builder.append("\n\t\t").append("keys1.put(").append(RenderUtils.toProperCase(projectName)).
				append("Types.").append(key).append(", KEYWORD);");
		return builder.toString();
	}

	private String[] makeKeywordList() {
		ArrayList<String> list = new ArrayList<>();
		for (String key : identifiers.keySet())
			if (key.contains("CONCEPT"))
				list.add(key);
		return list.toArray(new String[list.size()]);
	}
}

