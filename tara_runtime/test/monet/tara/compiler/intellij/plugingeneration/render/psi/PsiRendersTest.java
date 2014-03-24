package monet.tara.compiler.intellij.plugingeneration.render.psi;

import monet.tara.compiler.codegeneration.render.DefaultRender;
import monet.tara.compiler.codegeneration.render.RendersFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static junit.framework.Assert.assertEquals;

public class PsiRendersTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void namedElementRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("NamedElement", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "psi/TafatNamedElement.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void elementFactoryRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("ElementFactory", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "psi/TafatElementFactory.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void ElementTypeRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("ElementType", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "psi/TafatElementType.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void tokenTypeRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("TokenType", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "psi/TafatTokenType.java")), UTF_8), defaultRender.getOutput());
	}

}
