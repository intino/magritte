package monet.tara.compiler.parser;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.SyntaxException;
import org.junit.Test;

import java.io.File;

public class ParserTest {

	@Test
	public void parse() throws SyntaxException {
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m2"));
		AST ast = parser.parse();
	}
}
