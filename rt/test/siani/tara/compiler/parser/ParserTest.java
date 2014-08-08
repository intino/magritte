package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ParserTest {

	@Test
	public void parse() throws Exception {
		String projectName = "goros";
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m"), new CompilerConfiguration());
		parser.parse();
		Model ast = parser.convert();
		DeclaredNode node = ast.searchAncestry(ast.getTreeModel().get(2));
		Assert.assertEquals(ast.getTreeModel().get(1), node);
//		node = ast.searchAncestry(ast.getTreeModel()[5].getInnerConcepts()[2]);
//		Assert.assertEquals(ast.getTreeModel()[4].getInnerConcepts()[3], node);
//		node = ast.searchAncestry(ast.getTreeModel()[2]);
//		Assert.assertEquals(null, node);
//		Render render = RendersFactory.getRender("Definition", projectName, ast.getTreeModel("Entity"));
//		FileWriter writer = new FileWriter(new File("tara_runtime/res_test/RenderOutTest.java"));
//		writer.write(render.getOutput());
//		writer.close();
	}
}
