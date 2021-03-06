package io.intino.magritte.compiler.parser.antlr;

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
	protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
		reportError(recognizer, null);
	}

	@Override
	protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
		reportError(recognizer, null);
	}

	@Override
	protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
		reportError(recognizer, null);
	}

	@Override
	protected void reportUnwantedToken(Parser recognizer) {
		reportError(recognizer, null);
	}

	@Override
	protected void reportMissingToken(Parser recognizer) {
		reportError(recognizer, null);
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
		System.out.println("Line: " + token.getLine() + "\n" +
				"Column: " + token.getCharPositionInLine() + "\n" +
				"Text Length: " + token.getText().length() + "\n" +
				(token.getType() > 0 ? "Token type: " + nameList[token.getType()] + "\n" : "") +
				"Expected tokens: " + recognizer.getExpectedTokens().toString(recognizer.getVocabulary()) + "\n" +
				"Text: " + token.getText().replace("\n", "\\n"));
	}
}