package monet.tara.compiler.parser;

import monet.tara.compiler.core.errorcollection.SyntaxException;
import monet.tara.compiler.parser.antlr.TaraAbstractTreeGenerator;
import monet.tara.compiler.parser.antlr.TaraErrorStrategy;
import monet.tara.compiler.parser.antlr.TaraM2Grammar;
import monet.tara.compiler.parser.antlr.TaraM2Lexer;
import monet.tara.lang.TreeWrapper;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;

public class Parser {

	private final File file;
	TaraM2Grammar parser;
	TaraM2Grammar.RootContext rootContext;

	public Parser(File file) throws IOException {
		this.file = file;
		ANTLRInputStream input = new ANTLRFileStream(file.getAbsolutePath());
		TaraM2Lexer lexer = new TaraM2Lexer(input);
		lexer.reset();
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new TaraM2Grammar(tokens);
		parser.setErrorHandler(new TaraErrorStrategy());
	}

	public TreeWrapper convert() throws SyntaxException {
		try {
			TreeWrapper ast = new TreeWrapper();
			ParseTreeWalker walker = new ParseTreeWalker();
			TaraAbstractTreeGenerator extractor = new TaraAbstractTreeGenerator(ast, file.getPath());
			walker.walk(extractor, rootContext);
			return ast;
		} catch (RecognitionException | NullPointerException e) {
//			Token token = ((org.antlr.v4.runtime.Parser) e.getRecognizer()).getCurrentToken();
//			throw new SyntaxException("Syntax error in " + file.getName(), token.getLine(), token.getCharPositionInLine());
			e.printStackTrace();
			return null;
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
