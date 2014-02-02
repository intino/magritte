package monet.tara.compiler.intellij.plugingeneration.render.parser;

import monet.tara.compiler.code_generation.render.DefaultRender;
import monet.tara.compiler.code_generation.render.RendersFactory;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class ParserTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/parser/";

	@Test
	public void parserUtilRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("ParserUtil", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatParserUtil.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void GeneratedParserUtilBaseRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("GeneratedParserUtilBase", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "GeneratedParserUtilBase.java")), UTF_8), defaultRender.getOutput());
	}
}
