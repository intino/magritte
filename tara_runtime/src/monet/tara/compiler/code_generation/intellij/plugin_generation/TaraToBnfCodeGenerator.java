package monet.tara.compiler.code_generation.intellij.plugin_generation;

import monet.tara.compiler.code_generation.render.BnfRender;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.PrintWriter;

public class TaraToBnfCodeGenerator extends CodeGenerator {

	private static final String GRAMMAR_TPL = "grammar";

	public static File toBnf(CompilerConfiguration configuration, String tplPath, AST ast) throws TaraException {
		String template = TemplateFactory.getTemplate(GRAMMAR_TPL);
		String outPath = configuration.getTempDirectory().getAbsolutePath() +
			File.separator + "src" +
			File.separator +
			newTemplate(configuration.getProject(), template);
		BnfRender render = new BnfRender(configuration.getProject(), template, tplPath, ast);
		File file = new File(outPath);
		PrintWriter outWriter = getOutWriter(file);
		outWriter.print(render.getOutput());
		outWriter.close();
		return file;
	}


}
