/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/
package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;

public class TaraErrorStrategy extends DefaultErrorStrategy {

	private void printParameters(Parser recognizer) {
		Token token = recognizer.getCurrentToken();
		System.out.println("Line: " + token.getLine());
		System.out.println("Column: " + token.getCharPositionInLine());
		System.out.println("Text Length: " + token.getText().length());
		System.out.println("Text: " + token.getText().replace("\n", "\\n"));
		System.out.println("Expected tokens: " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames()));
	}

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		printParameters(recognizer);
		throw e;
	}

	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		printParameters(recognizer);
		throw new RuntimeException(new InputMismatchException(recognizer));
	}

	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		printParameters(recognizer);
		throw new RuntimeException(e);
	}

	@Override
	public void sync(Parser recognizer) {
	}
}

