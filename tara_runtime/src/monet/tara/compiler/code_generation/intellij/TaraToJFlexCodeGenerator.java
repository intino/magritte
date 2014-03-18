package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.render.JFlexRender;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaraToJFlexCodeGenerator extends CodeGenerator {

	private static final String[] LEXER_TPL = new String[]{"lexer", "HighlighterLex.flex"};

	public static File[] toJFlex(CompilerConfiguration configuration, String tplPath, AST ast) throws TaraException {
		ArrayList<File> resultFiles = new ArrayList<>();
		for (String tpl : LEXER_TPL) {
			String template = TemplateFactory.getTemplate(tpl);
			String outPath = configuration.getTempDirectory().getAbsolutePath() + SEP + SRC +
				newTemplate(RenderUtils.toProperCase(configuration.getProject()), template);
			JFlexRender render = new JFlexRender(configuration.getProject(), template, tplPath, ast.getIdentifiers());
			File file = new File(outPath);
			resultFiles.add(file);
			writeTemplate(render, file);
		}
		return resultFiles.toArray(new File[2]);
	}

	private static void writeTemplate(JFlexRender render, File file) throws TaraException {
		PrintWriter outWriter = getOutWriter(file);
		outWriter.print(render.getOutput());
		outWriter.close();
	}


}
