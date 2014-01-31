package monet.tara.compiler.core.render;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

	private static final Map<String, String> TEMPLATES = new HashMap<>();
	private static final String PROJECT_PATH = "monet/tara/";
	private static final String IDE = "intellij/";



	static {
		TEMPLATES.put("lexer", PROJECT_PATH +IDE+ "metamodel/lexer");
		TEMPLATES.put("grammar", PROJECT_PATH + IDE + "metamodel/grammar");
		TEMPLATES.put("LexerAdapter", PROJECT_PATH + IDE +"metamodel/LexerAdapter");
		TEMPLATES.put("ParserDefinition", PROJECT_PATH + IDE +"metamodel/ParserDefinition");
		TEMPLATES.put("SyntaxHighlighter", PROJECT_PATH + IDE +"metamodel/SyntaxHighlighter");
		TEMPLATES.put("Language", PROJECT_PATH + IDE +"metamodel/Language");
		TEMPLATES.put("Icons", PROJECT_PATH + IDE +"metamodel/Icons");
		TEMPLATES.put("Bundle", PROJECT_PATH + IDE + "metamodel/Bundle");
		TEMPLATES.put("FindUsagesProvider", PROJECT_PATH + IDE +"metamodel/FindUsagesProvider");

		TEMPLATES.put("NamedElement", PROJECT_PATH + IDE +"psi/NamedElement");
		TEMPLATES.put("ElementFactory", PROJECT_PATH + IDE + "psi/ElementFactory");
		TEMPLATES.put("ElementType", PROJECT_PATH + IDE + "psi/ElementType");
		TEMPLATES.put("TokenType", PROJECT_PATH + IDE +"psi/TokenType");

		TEMPLATES.put("NamedElementImpl", PROJECT_PATH + IDE + "psi/impl/NamedElementImpl");
		TEMPLATES.put("DefinitionIdentifierManipulator", PROJECT_PATH + IDE + "psi/impl/DefinitionIdentifierManipulator");
		TEMPLATES.put("ElementFactoryImpl", PROJECT_PATH + IDE +"psi/impl/ElementFactoryImpl");
		TEMPLATES.put("Util", PROJECT_PATH + IDE +"psi/impl/Util");
		TEMPLATES.put("PsiImplUtil", PROJECT_PATH + IDE + "psi/impl/PsiImplUtil");

		TEMPLATES.put("File", PROJECT_PATH + IDE + "metamodel/file/File");
		TEMPLATES.put("FileType", PROJECT_PATH + "metamodel/file/FileType");
		TEMPLATES.put("FileTypeFactory", PROJECT_PATH + IDE + "metamodel/file/FileTypeFactory");

		TEMPLATES.put("ParserUtil", PROJECT_PATH + "parser/ParserUtil");
		TEMPLATES.put("GeneratedParserUtilBase", PROJECT_PATH + IDE +"parser/GeneratedParserUtilBase");

		TEMPLATES.put("SyntaxHighlighterFactory", PROJECT_PATH + IDE +"highlighting/SyntaxHighlighterFactory");

		TEMPLATES.put("plugin", "META-INF/plugin");

		TEMPLATES.put("Definition", PROJECT_PATH+ "metamodel/Definition");
		TEMPLATES.put("Metamodel", PROJECT_PATH+ "metamodel/Metamodel");

	}

	public static Map<String, String> getTemplates() {
		return TEMPLATES;
	}

	public static String getTemplate(String className) {
		String template;
		if ((template = TEMPLATES.get(className)) != null)
			return template;
		else
			throw new RuntimeException("Template not defined");
	}
}