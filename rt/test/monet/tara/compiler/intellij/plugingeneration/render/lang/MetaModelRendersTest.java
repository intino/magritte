package monet.tara.compiler.intellij.plugingeneration.render.lang;

import monet.tara.compiler.codegeneration.render.DefaultRender;
import monet.tara.compiler.codegeneration.render.RendersFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class MetaModelRendersTest {

	private String projectName = "tafat";
	private String TLP_PATH = "intellij_plugin_builder/res_test/render/tpl/";
	private HashMap<String, String> keywordList;

	@Before
	public void setUp() throws Exception {
		keywordList = new HashMap<>();
		keywordList.put("TERM", "Term");
		keywordList.put("SOURCE", "Source");
		keywordList.put("THESAURUS", "Thesaurus");
		keywordList.put("OPERATION", "Operation");
	}

	@Test
	public void grammarRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("grammar", projectName, null);
		assertEquals(new String(Files.readAllBytes(
			Paths.get(TLP_PATH + "lang/grammar_test.bnf")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void SyntaxHighlighterRenderTest() throws Exception {
		DefaultRender highlighterRender = RendersFactory.getRender("SyntaxHighlighter", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatSyntaxHighlighter.java")), UTF_8), highlighterRender.getOutput());
	}

	@Test
	public void HighlighterRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("ParserDefinition", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatParserDefinition.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void LanguageRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("Language", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatLanguage.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void IconsRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("Icons", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatIcons.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void BundleRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("Bundle", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatBundle.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void FindUsagesProviderRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("FindUsagesProvider", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatFindUsagesProvider.java")), UTF_8), defaultRender.getOutput());
	}

	@Test
	public void LexerAdapterRenderTest() throws Exception {
		DefaultRender defaultRender = RendersFactory.getRender("LexerAdapter", projectName, null);
		assertEquals(new String(Files.readAllBytes(Paths.get
			(TLP_PATH + "lang/TafatLexerAdapter.java")), UTF_8), defaultRender.getOutput());
	}
}
