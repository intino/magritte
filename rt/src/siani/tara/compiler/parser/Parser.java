package siani.tara.compiler.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import siani.tara.TaracRunner;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.SyntaxException;
import siani.tara.compiler.parser.antlr.TaraAbstractModelGenerator;
import siani.tara.compiler.parser.antlr.TaraErrorStrategy;
import siani.tara.compiler.parser.antlr.TaraGrammar;
import siani.tara.compiler.parser.antlr.TaraLexer;
import siani.tara.lang.Model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	private static final Logger LOG = Logger.getLogger(TaracRunner.class.getName());

	private final File file;
	private final CompilerConfiguration configuration;
	TaraGrammar parser;
	TaraGrammar.RootContext rootContext;

	public Parser(File file, CompilerConfiguration configuration) throws IOException {
		this.file = file;
		this.configuration = configuration;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath(), "UTF-8");
		TaraLexer lexer = new TaraLexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new TaraGrammar(tokens);
		parser.setErrorHandler(new TaraErrorStrategy());
	}

	public Model convert() throws SyntaxException {
		try {
			Model model = new Model(file.getName());
			ParseTreeWalker walker = new ParseTreeWalker();
			TaraAbstractModelGenerator extractor = new TaraAbstractModelGenerator(model, file.getPath());
			walker.walk(extractor, rootContext);
			return model;
		} catch (RecognitionException e) {
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}

	private String getExpectedTokens(org.antlr.v4.runtime.Parser recognizer) {
		try {
			return recognizer.getExpectedTokens().toString(recognizer.getTokenNames());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return "";
		}
	}

	public void parse() throws SyntaxException {
		try {
			rootContext = parser.root();
		} catch (RecognitionException e) {
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}
}
