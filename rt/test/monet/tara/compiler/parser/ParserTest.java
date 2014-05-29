package monet.tara.compiler.parser;

import monet.tara.lang.AbstractNode;
import monet.tara.lang.TreeWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ParserTest {


	@Test
	public void parse() throws Exception {
		String projectName = "goros";
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m2"));
		parser.parse();
		TreeWrapper ast = parser.convert();
		AbstractNode node = ast.searchAncestry(ast.getTree().get(2));
		Assert.assertEquals(ast.getTree().get(1), node);
//		node = ast.searchAncestry(ast.getTree()[5].getInnerConcepts()[2]);
//		Assert.assertEquals(ast.getTree()[4].getInnerConcepts()[3], node);
//		node = ast.searchAncestry(ast.getTree()[2]);
//		Assert.assertEquals(null, node);
//		Render render = RendersFactory.getRender("Definition", projectName, ast.getTree("Entity"));
//		FileWriter writer = new FileWriter(new File("tara_runtime/res_test/RenderOutTest.java"));
//		writer.write(render.getOutput());
//		writer.close();
	}
}
