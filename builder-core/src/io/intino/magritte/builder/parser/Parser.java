package io.intino.magritte.builder.parser;

import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.errorcollection.SyntaxException;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.parser.antlr.ModelGenerator;
import io.intino.magritte.builder.parser.antlr.TaraErrorStrategy;
import io.intino.magritte.lang.grammar.TaraGrammar;
import io.intino.magritte.lang.grammar.TaraLexer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.antlr.v4.runtime.CharStreams.fromString;

public class Parser {

	private static final Logger LOG = Logger.getGlobal();

	private final File file;
	private final CompilerConfiguration.Language language;
	private final String outDsl;
	private final TaraGrammar grammar;
	private TaraGrammar.RootContext rootContext;

	public Parser(File file, CompilerConfiguration.Language language, String sourceEncoding, String outDsl) throws IOException {
		this.file = file;
		this.language = language;
		this.outDsl = outDsl;
		TaraLexer lexer = new TaraLexer(fromString(Files.readString(file.toPath(), Charset.forName(sourceEncoding)).trim()));
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		grammar = new TaraGrammar(tokens);
		grammar.setErrorHandler(new TaraErrorStrategy());
		grammar.addErrorListener(new GrammarErrorListener());
	}

	public void parse() throws SyntaxException {
		try {
			rootContext = grammar.root();
		} catch (RecognitionException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
			throwError(e);
		}
	}

	public Model convert() throws SyntaxException {
		try {
			ParseTreeWalker walker = new ParseTreeWalker();
			ModelGenerator extractor = new ModelGenerator(file.getPath(), language, outDsl);
			walker.walk(extractor, rootContext);
			if (!extractor.getErrors().isEmpty())
				throw extractor.getErrors().get(0);
			return extractor.getModel();
		} catch (RecognitionException e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return throwError(e);
		}
	}

	private Model throwError(RecognitionException e) throws SyntaxException {
		org.antlr.v4.runtime.Parser recognizer = (org.antlr.v4.runtime.Parser) e.getRecognizer();
		Token token = recognizer.getCurrentToken();
		throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine(), getExpectedTokens(recognizer));
	}

	private String getExpectedTokens(org.antlr.v4.runtime.Parser recognizer) {
		try {
			return recognizer.getExpectedTokens().toString(recognizer.getVocabulary());
		} catch (Exception e) {
			LOG.log(Level.INFO, e.getMessage(), e);
			return "";
		}
	}
}
