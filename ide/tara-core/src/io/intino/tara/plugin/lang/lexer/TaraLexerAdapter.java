package io.intino.tara.plugin.lang.lexer;

import com.intellij.lexer.FlexAdapter;
import tara.intellij.lang.lexer.TaraLexer;

public class TaraLexerAdapter extends FlexAdapter {

	public TaraLexerAdapter() {
		super(new TaraLexer(null));
	}
}