package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.project.Project;

public class TaraHighlighterLexAdapter extends FlexAdapter {

	public TaraHighlighterLexAdapter(Project project) {
		super(new TaraHighlighterLexProxy(project));
	}
}
