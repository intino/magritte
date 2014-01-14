package monet.tara.intellij.metamodel;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by oroncal on 13/01/14.
 */
public class TaraLexerAdapter extends FlexAdapter {
	public TaraLexerAdapter() {
		super(new TaraLexer((Reader) null));
	}
}
