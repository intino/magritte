package monet.tara.compiler.parser;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ParserTest {


	@Test
	public void parse() throws Exception {
		String projectName = "goros";
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m2"));
		parser.parse();
		AST ast = parser.convert();
		ASTNode node = ast.searchAncestry(ast.getAstRootNodes()[2]);
		Assert.assertEquals(ast.getAstRootNodes()[1], node);
//		node = ast.searchAncestry(ast.getAstRootNodes()[5].getChildren()[2]);
//		Assert.assertEquals(ast.getAstRootNodes()[4].getChildren()[3], node);
//		node = ast.searchAncestry(ast.getAstRootNodes()[2]);
//		Assert.assertEquals(null, node);
//		Render render = RendersFactory.getRender("Definition", projectName, ast.get("Entity"));
//		FileWriter writer = new FileWriter(new File("tara_runtime/res_test/RenderOutTest.java"));
//		writer.write(render.getOutput());
//		writer.close();
	}
}
