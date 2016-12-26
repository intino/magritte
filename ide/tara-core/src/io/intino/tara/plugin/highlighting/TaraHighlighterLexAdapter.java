package io.intino.tara.plugin.highlighting;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.project.Project;
import tara.intellij.highlighting.TaraHighlighterLex;

public class TaraHighlighterLexAdapter extends FlexAdapter {

	public TaraHighlighterLexAdapter(Project project) {
		super(new TaraHighlighterLex(null, project));
	}
}
