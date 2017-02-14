package io.intino.tara.compiler.parser;

import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.SyntaxException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.parser.antlr.ModelGenerator;
import io.intino.tara.compiler.parser.antlr.TaraErrorStrategy;
import io.intino.tara.lang.grammar.TaraGrammar;
import io.intino.tara.lang.grammar.TaraLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	private static final Logger LOG = Logger.getGlobal();

	private final File file;
	private final List<CompilerConfiguration.DSL> languages;
	private final String outDsl;
	private TaraGrammar grammar;
	private TaraGrammar.RootContext rootContext;

	public Parser(File file, List<CompilerConfiguration.DSL> languages, String sourceEncoding, String outDsl) throws IOException {
		this.file = file;
		this.languages = languages;
		this.outDsl = outDsl;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath(), sourceEncoding);
		TaraLexer lexer = new TaraLexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		grammar = new TaraGrammar(tokens);
		grammar.setErrorHandler(new TaraErrorStrategy());
		grammar.addErrorListener(new GrammarErrorListener());
	}

	public Model convert() throws SyntaxException {
		try {
			ParseTreeWalker walker = new ParseTreeWalker();
			ModelGenerator extractor = new ModelGenerator(file.getPath(), languages, outDsl);
			walker.walk(extractor, rootContext);
			if (!extractor.getErrors().isEmpty())
				throw extractor.getErrors().get(0);
			return extractor.getModel();
		} catch (RecognitionException e) {
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			LOG.log(Level.SEVERE, e.getMessage());
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}

	private String getExpectedTokens(org.antlr.v4.runtime.Parser recognizer) {
		try {
			return recognizer.getExpectedTokens().toString(VocabularyImpl.fromTokenNames(recognizer.getTokenNames()));
		} catch (Exception e) {
			LOG.log(Level.INFO, e.getMessage(), e);
			return "";
		}
	}

	public void parse() throws SyntaxException {
		try {
			rootContext = grammar.root();
		} catch (RecognitionException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
			org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
			Token token = recognizer.getCurrentToken();
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
		}
	}
}
