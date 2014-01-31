package monet.tara.compiler.intellij.metamodel;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class TaraLexerAdapter extends FlexAdapter {
	public TaraLexerAdapter() {
		super(new TaraLexer((Reader) null));
	}
}
