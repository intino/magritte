package monet.tara.compiler.parser;

import monet.tara.compiler.code_generation.render.RendersFactory;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.SyntaxException;
import org.junit.Test;
import org.monet.templation.Render;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ParserTest {


	@Test
	public void parse() throws SyntaxException, IOException {
		String projectName = "goros";
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m2"));
		AST ast = parser.parse();
		Render render = RendersFactory.getRender("Definition", projectName, ast.get("Entity"));
		FileWriter writer = new FileWriter(new File("tara_runtime/res_test/RenderOutTest.java"));
		writer.write(render.getOutput());
		writer.close();
	}
}
