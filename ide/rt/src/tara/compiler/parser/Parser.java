package tara.compiler.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import tara.TaracRunner;
import tara.compiler.core.errorcollection.SyntaxException;
import tara.compiler.model.Model;
import tara.compiler.parser.antlr.ModelGenerator;
import tara.compiler.parser.antlr.TaraErrorStrategy;
import tara.compiler.parser.antlr.TaraGrammar;
import tara.compiler.parser.antlr.TaraLexer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	private static final Logger LOG = Logger.getLogger(TaracRunner.class.getName());

	private final File file;
	TaraGrammar parser;
	TaraGrammar.RootContext rootContext;

	public Parser(File file, String sourceEncoding) throws IOException {
		this.file = file;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath(), sourceEncoding);
		TaraLexer lexer = new TaraLexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new TaraGrammar(tokens);
		parser.setErrorHandler(new TaraErrorStrategy());
	}

	public Model convert() throws SyntaxException {
		try {
			ParseTreeWalker walker = new ParseTreeWalker();
			ModelGenerator extractor = new ModelGenerator(file.getPath());
			walker.walk(extractor, rootContext);
			return extractor.getModel();
		} catch (RecognitionException e) {
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}

	private String getExpectedTokens(org.antlr.v4.runtime.Parser recognizer) {
		try {
			return recognizer.getExpectedTokens().toString(VocabularyImpl.fromTokenNames(recognizer.getTokenNames()));
		} catch (Exception e) {
			return "";
		}
	}

	public void parse() throws SyntaxException {
		try {
			rootContext = parser.root();
		} catch (RecognitionException e) {
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}
}
