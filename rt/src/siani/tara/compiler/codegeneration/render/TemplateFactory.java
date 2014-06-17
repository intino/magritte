package siani.tara.compiler.codegeneration.render;

public class TemplateFactory {

	private static final String[] lexerTemplates = new String[]{"intellij/tpl/hl_lexer.flex", "intellij/tpl/lexer.flex"};

	private TemplateFactory() {
	}


	public static String[] getLexerTemplates() {
		return lexerTemplates;
	}
}