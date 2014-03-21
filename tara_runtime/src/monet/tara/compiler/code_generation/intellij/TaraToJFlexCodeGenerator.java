package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.PathManager;
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

	public static File[] toJFlex(CompilerConfiguration conf, String tplPath, AST ast) throws TaraException {
		ArrayList<File> resultFiles = new ArrayList<>();
		for (String tpl : LEXER_TPL) {
			String template = TemplateFactory.getTemplate(tpl);
			String outPath = PathManager.getSrcDir(conf.getTempDirectory()) +
				newTemplate(RenderUtils.toProperCase(conf.getProject()), template);
			JFlexRender render = new JFlexRender(conf.getProject(), template, tplPath, ast.getIdentifiers());
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
