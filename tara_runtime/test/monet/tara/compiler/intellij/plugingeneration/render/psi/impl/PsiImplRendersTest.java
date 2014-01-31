package monet.tara.compiler.intellij.plugingeneration.render.psi.impl;

import monet.tara.compiler.core.render.DefaultRender;
import monet.tara.compiler.core.render.RendersFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class PsiImplRendersTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/psi/impl/";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void namedElementImplRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("NamedElementImpl", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatNamedElementImpl.java")), UTF_8), defaultRender.getOutput());
	}


	@Test
	public void DefinitionIdentifierManipulatorRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("DefinitionIdentifierManipulator", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatDefinitionIdentifierManipulator.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void ElementFactoryImplRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("ElementFactoryImpl", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatElementFactoryImpl.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void UtilRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("Util", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatUtil.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void PsiImplUtilRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("PsiImplUtil", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatPsiImplUtil.java")), UTF_8), defaultRender.getOutput());
	}


}
