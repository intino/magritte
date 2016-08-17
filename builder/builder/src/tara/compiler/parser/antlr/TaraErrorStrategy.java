package tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TaraErrorStrategy extends DefaultErrorStrategy {

	private static final Logger LOG = Logger.getGlobal();
	private static Token currentError;

	static {
		LOG.setLevel(Level.WARNING);
	}

	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		reportError(recognizer, new InputMismatchException(recognizer));
		return null;
	}

	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		printParameters(recognizer);
		throw new InputMismatchException(recognizer);
	}

	private void printParameters(Parser recognizer) {
		Token token = recognizer.getCurrentToken();
		if (currentError == token) return;
		else currentError = token;
		String[] nameList = recognizer.getTokenNames();
		LOG.info("Line: " + token.getLine() + "\n" +
			"Column: " + token.getCharPositionInLine() + "\n" +
			"Text Length: " + token.getText().length() + "\n" +
			(token.getType() > 0 ? "Token type: " + nameList[token.getType()] + "\n" : "") +
			"Expected tokens: " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames()) +
			"Text: " + token.getText().replace("\n", "\\n"));
	}
}