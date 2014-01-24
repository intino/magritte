package monet.tara.transpiler.intellij.plugingeneration.render.file;

import monet.tara.transpiler.DefaultRender;
import monet.tara.transpiler.RendersFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class FileRenderTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/metamodel/file/";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void fileRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("File", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatFile.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void fileTypeRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("FileType", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatFileType.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void fileTypeFactoryRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("FileTypeFactory", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "TafatFileTypeFactory.java")), UTF_8), defaultRender.getOutput());
	}
}
