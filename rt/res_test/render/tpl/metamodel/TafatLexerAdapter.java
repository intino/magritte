package siani.tafat.intellij.metamodel;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class TafatLexerAdapter extends FlexAdapter {
	public TafatLexerAdapter() {
		super(new TafatLexer((Reader) null));
	}
}
