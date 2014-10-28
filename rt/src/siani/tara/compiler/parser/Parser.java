package siani.tara.compiler.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.SyntaxException;
import siani.tara.compiler.parser.antlr.TaraAbstractModelGenerator;
import siani.tara.compiler.parser.antlr.TaraErrorStrategy;
import siani.tara.compiler.parser.antlr.TaraGrammar;
import siani.tara.compiler.parser.antlr.TaraLexer;
import siani.tara.lang.Model;

import java.io.File;
import java.io.IOException;

public class Parser {

	private final File file;
	private final CompilerConfiguration configuration;
	TaraGrammar parser;
	TaraGrammar.RootContext rootContext;

	public Parser(File file, CompilerConfiguration configuration) throws IOException {
		this.file = file;
		this.configuration = configuration;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath());
		TaraLexer lexer = new TaraLexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new TaraGrammar(tokens);
		parser.setErrorHandler(new TaraErrorStrategy());
	}

	public Model convert() throws SyntaxException {
		try {
			Model ast = new Model(file.getName());
			ParseTreeWalker walker = new ParseTreeWalker();
			TaraAbstractModelGenerator extractor = new TaraAbstractModelGenerator(ast, file.getPath(), configuration.getModelsDirectory());
			walker.walk(extractor, rootContext);
			return ast;
		} catch (RecognitionException e) {
			Token token = ((org.antlr.v4.runtime.Parser) e.getRecognizer()).getCurrentToken();
			e.printStackTrace();
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine());
		}
	}

	public void parse() throws SyntaxException {
		try {
			rootContext = parser.root();
		} catch (RecognitionException e) {
			Token token = ((org.antlr.v4.runtime.Parser) e.getRecognizer()).getCurrentToken();
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine());
		}
	}
}
