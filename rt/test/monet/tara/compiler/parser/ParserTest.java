package monet.tara.compiler.parser;

import monet.tara.lang.ASTWrapper;
import monet.tara.lang.ASTNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ParserTest {


	@Test
	public void parse() throws Exception {
		String projectName = "goros";
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m2"));
		parser.parse();
		ASTWrapper ast = parser.convert();
		ASTNode node = ast.searchAncestry(ast.getAST().get(2));
		Assert.assertEquals(ast.getAST().get(1), node);
//		node = ast.searchAncestry(ast.getAST()[5].getInnerConcepts()[2]);
//		Assert.assertEquals(ast.getAST()[4].getInnerConcepts()[3], node);
//		node = ast.searchAncestry(ast.getAST()[2]);
//		Assert.assertEquals(null, node);
//		Render render = RendersFactory.getRender("Definition", projectName, ast.getAST("Entity"));
//		FileWriter writer = new FileWriter(new File("tara_runtime/res_test/RenderOutTest.java"));
//		writer.write(render.getOutput());
//		writer.close();
	}
}
