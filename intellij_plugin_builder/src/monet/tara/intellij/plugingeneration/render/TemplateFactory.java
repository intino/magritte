package monet.tara.intellij.plugingeneration.render;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

	private static Map<String, String> templates = new HashMap<>();
	private static final String PROJECT_PATH = "monet/tara/intellij/";


	static {
		templates.put("lexer", PROJECT_PATH + "metamodel/lexer");
		templates.put("grammar", PROJECT_PATH + "metamodel/grammar");
		templates.put("LexerAdapter", PROJECT_PATH + "metamodel/LexerAdapter");
		templates.put("ParserDefinition", PROJECT_PATH + "metamodel/ParserDefinition");
		templates.put("SyntaxHighlighter", PROJECT_PATH + "metamodel/SyntaxHighlighter");
		templates.put("Language", PROJECT_PATH + "metamodel/Language");
		templates.put("Icons", PROJECT_PATH + "metamodel/Icons");
		templates.put("Bundle", PROJECT_PATH + "metamodel/Bundle");
		templates.put("FindUsagesProvider", PROJECT_PATH + "metamodel/FindUsagesProvider");

		templates.put("NamedElement", PROJECT_PATH + "psi/NamedElement");
		templates.put("ElementFactory", PROJECT_PATH + "psi/ElementFactory");
		templates.put("ElementType", PROJECT_PATH + "psi/ElementType");
		templates.put("TokenType", PROJECT_PATH + "psi/TokenType");

		templates.put("NamedElementImpl", PROJECT_PATH + "psi/impl/NamedElementImpl");
		templates.put("DefinitionIdentifierManipulator", PROJECT_PATH + "psi/impl/DefinitionIdentifierManipulator");
		templates.put("ElementFactoryImpl", PROJECT_PATH + "psi/impl/ElementFactoryImpl");
		templates.put("Util", PROJECT_PATH + "psi/impl/Util");
		templates.put("PsiImplUtil", PROJECT_PATH + "psi/impl/PsiImplUtil");

		templates.put("File", PROJECT_PATH + "metamodel/file/File");
		templates.put("FileType", PROJECT_PATH + "metamodel/file/FileType");
		templates.put("FileTypeFactory", PROJECT_PATH + "metamodel/file/FileTypeFactory");

		templates.put("ParserUtil", PROJECT_PATH + "parser/ParserUtil");
		templates.put("GeneratedParserUtilBase", PROJECT_PATH + "parser/GeneratedParserUtilBase");

		templates.put("SyntaxHighlighterFactory", PROJECT_PATH + "highlighting/SyntaxHighlighterFactory");

		templates.put("plugin", "META-INF/plugin");

	}

	public static String getTemplate(String className) {
		String template;
		if ((template = templates.get(className)) != null)
			return template;
		else
			throw new RuntimeException("Template not defined");
	}
}