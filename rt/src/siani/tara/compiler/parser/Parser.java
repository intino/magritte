package siani.tara.compiler.parser;

import siani.tara.compiler.core.errorcollection.SyntaxException;
import siani.tara.compiler.parser.antlr.TaraAbstractTreeGenerator;
import siani.tara.compiler.parser.antlr.TaraErrorStrategy;
import siani.tara.compiler.parser.antlr.TaraGrammar;
import siani.tara.compiler.parser.antlr.TaraLexer;
import siani.tara.lang.TreeWrapper;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;

public class Parser {

	private final File file;
	TaraGrammar parser;
	TaraGrammar.RootContext rootContext;

	public Parser(File file) throws IOException {
		this.file = file;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath());
		TaraLexer lexer = new TaraLexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new TaraGrammar(tokens);
		parser.setErrorHandler(new TaraErrorStrategy());
	}

	public TreeWrapper convert() throws SyntaxException {
		try {
			TreeWrapper ast = new TreeWrapper();
			ParseTreeWalker walker = new ParseTreeWalker();
			TaraAbstractTreeGenerator extractor = new TaraAbstractTreeGenerator(ast, file.getPath());
			walker.walk(extractor, rootContext);
			return ast;
		} catch (RecognitionException e) {
			Token token = ((org.antlr.v4.runtime.Parser) e.getRecognizer()).getCurrentToken();
			e.printStackTrace();
			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine());
//			e.printStackTrace();
//			return null;
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
