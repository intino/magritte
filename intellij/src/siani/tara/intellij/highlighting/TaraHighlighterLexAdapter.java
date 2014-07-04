package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexAdapter;

public class TaraHighlighterLexAdapter extends FlexAdapter {

	public TaraHighlighterLexAdapter() {
		super(new TaraHighlighterLex((java.io.Reader)null));
	}
}
