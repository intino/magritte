package monet.tara.intellij.metamodel;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class TaraHighlighterLexAdapter extends FlexAdapter {
	public TaraHighlighterLexAdapter() {
		super(new TaraHighlighterLex((Reader) null));
	}
}
