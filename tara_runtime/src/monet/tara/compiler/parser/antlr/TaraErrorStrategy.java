package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class TaraErrorStrategy extends DefaultErrorStrategy {

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

}

