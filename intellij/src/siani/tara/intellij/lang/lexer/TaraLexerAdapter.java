package siani.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.project.Project;

public class TaraLexerAdapter extends FlexAdapter {

	public TaraLexerAdapter(Project project) {
		super(new TaraLexerProxy(project));
	}
}