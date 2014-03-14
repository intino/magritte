package monet.tara.compiler.parser;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.SyntaxException;
import monet.tara.compiler.parser.antlr.TaraASTGeneratorListener;
import monet.tara.compiler.parser.antlr.TaraErrorStrategy;
import monet.tara.compiler.parser.antlr.TaraM2Grammar;
import monet.tara.compiler.parser.antlr.TaraM2Lexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;

public class Parser {

	TaraM2Grammar parser;
	TaraM2Grammar.RootContext rootContext;

	public Parser(File file) {
		try {
			ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath());
			TaraM2Lexer lexer = new TaraM2Lexer(input);
			lexer.reset();
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new TaraM2Grammar(tokens);
			parser.setErrorHandler(new TaraErrorStrategy());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AST convert() throws SyntaxException {
		try {
			AST ast = new AST();
			ParseTreeWalker walker = new ParseTreeWalker();
			TaraASTGeneratorListener extractor = new TaraASTGeneratorListener(ast);
			walker.walk(extractor, rootContext);
			return ast;
		} catch (RecognitionException e) {
			Token token = ((org.antlr.v4.runtime.Parser) e.getRecognizer()).getCurrentToken();
			throw new SyntaxException("Syntax error in " + token.getText(), token.getLine(), token.getStartIndex());
		}
	}

	public void parse() throws Exception {
		rootContext = parser.root();
	}
}
