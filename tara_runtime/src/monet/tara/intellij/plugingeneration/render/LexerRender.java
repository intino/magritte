package monet.tara.intellij.plugingeneration.render;

import monet.tara.compiler.code_generation.render.DefaultRender;

import java.util.HashMap;

public class LexerRender extends DefaultRender {

	HashMap<String, String> keywordsList;

	public LexerRender(String tlpName, String projectName, Object keywordsList) {

		super(tlpName, projectName);
		this.keywordsList = (HashMap<String, String>) keywordsList;
	}

	public void setKeywords(HashMap<String, String> keywords) {
		this.keywordsList = keywords;
	}

	@Override
	protected void init() {
		super.init();
		StringBuilder keywords = new StringBuilder();
		for (String keyword : keywordsList.keySet()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("keyword", keyword);
			map.put("property", keywordsList.get(keyword));
			keywords.append(block("keyword", map));
		}
		addMark("keywords", keywords.toString());
	}
}
