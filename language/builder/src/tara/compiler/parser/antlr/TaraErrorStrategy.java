package tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;

import java.util.logging.Logger;

public class TaraErrorStrategy extends DefaultErrorStrategy {

	private static final Logger LOG = Logger.getLogger(TaraErrorStrategy.class.getName());
	private static Token currentError;

	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		printParameters(recognizer);
		throw new InputMismatchException(recognizer);
	}

	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		reportError(recognizer, new InputMismatchException(recognizer));
		return null;
	}

	private void printParameters(Parser recognizer) {
		Token token = recognizer.getCurrentToken();
		if (currentError == token) return;
		else currentError = token;
		String[] nameList = recognizer.getTokenNames();
		LOG.severe("Line: " + token.getLine() + "\n" +
			"Column: " + token.getCharPositionInLine() + "\n" +
			"Text Length: " + token.getText().length() + "\n" +
			(token.getType() > 0 ? "Token type: " + nameList[token.getType()] + "\n" : "") +
			"Expected tokens: " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames()) +
			"Text: " + token.getText().replace("\n", "\\n"));
	}
}