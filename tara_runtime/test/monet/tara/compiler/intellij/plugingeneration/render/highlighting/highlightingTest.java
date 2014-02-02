package monet.tara.compiler.intellij.plugingeneration.render.highlighting;

import monet.tara.compiler.code_generation.render.DefaultRender;
import monet.tara.compiler.code_generation.render.RendersFactory;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class highlightingTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/highlighting/";

	@Test
	public void highlightingUtilRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("SyntaxHighlighterFactory", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatSyntaxHighlighterFactory.java")), UTF_8), defaultRender.getOutput());
	}
}
