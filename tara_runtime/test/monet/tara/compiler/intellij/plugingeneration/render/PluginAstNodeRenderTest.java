package monet.tara.compiler.intellij.plugingeneration.render;

import monet.tara.compiler.core.render.DefaultRender;
import monet.tara.compiler.core.render.RendersFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class PluginASTNodeRenderTest {
	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/META-INF/";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void pluginDefinitionRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("plugin", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "plugin.xml")), UTF_8), defaultRender.getOutput());
	}

}
