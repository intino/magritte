package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class TaraErrorStrategy extends DefaultErrorStrategy {

	private void printParameters(Parser recognizer) {
		Token token = recognizer.getCurrentToken();
		String[] nameList = recognizer.getTokenNames();
		System.out.println("Line: " + token.getLine());
		System.out.println("Column: " + token.getCharPositionInLine());
		System.out.println("Text Length: " + token.getText().length());
		System.out.println("Token type: " + nameList[token.getType()]);
		System.out.println("Text: " + token.getText().replace("\n", "\\n"));
		System.out.println("Expected tokens: " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames()));
	}

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		throw new RecognitionException(recognizer, recognizer.getInputStream(), recognizer.getContext());
	}

	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		throw new RecognitionException(recognizer, recognizer.getInputStream(), recognizer.getContext());
	}

	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		throw new RecognitionException(recognizer, recognizer.getInputStream(), recognizer.getContext());
	}

	@Override
	public void sync(Parser recognizer) {
	}
}

