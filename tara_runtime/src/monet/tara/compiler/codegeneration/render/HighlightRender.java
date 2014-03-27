package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.core.errorcollection.TaraException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HighlightRender extends DefaultRender {
	Map<String, String> identifiers;

	public HighlightRender(String tplName, String projectName, Object identifiers) throws TaraException {
		super(tplName, projectName);
		this.identifiers = (Map<String, String>) identifiers;
	}

	@Override
	protected void init() {
		super.init();
		addMark("highlightKey", buildKeys(makeKeywordList()));
	}

	private String buildKeys(String[] list) {
		StringBuilder builder = new StringBuilder("");
		for (String key : list)
			builder.append("\n\t\t").append("KEYS_1.put(").append(RenderUtils.toProperCase(projectName)).
				append("Types.").append(key).append(", KEYWORD);");
		return builder.toString();
	}

	private String[] makeKeywordList() {
		List<String> list = new ArrayList<>();
		for (String key : identifiers.keySet())
			if (key.contains("CONCEPT"))
				list.add(key);
		return list.toArray(new String[list.size()]);
	}
}

