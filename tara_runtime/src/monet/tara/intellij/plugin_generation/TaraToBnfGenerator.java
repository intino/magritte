package monet.tara.intellij.plugin_generation;

import monet.tara.compiler.code_generation.render.BnfRender;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class TaraToBnfGenerator {

	private static final String GRAMMAR_TPL = "grammar";

	public static File toBnf(CompilerConfiguration configuration, String tplPath, AST ast) throws TaraException {
		String template = TemplateFactory.getTemplate(GRAMMAR_TPL);
		String outPath = configuration.getTempDirectory().getAbsolutePath() + File.separator + "src" + File.separator + template;
		BnfRender render = new BnfRender(configuration.getProject(),
			TemplateFactory.getTemplate("grammar"), template, ast);
		render.init();
		File file = new File(outPath);
		PrintWriter outWriter = getOutWriter(file);
		outWriter.print(render.getOutput());
		outWriter.close();
		return file;
	}


	private static PrintWriter getOutWriter(File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			out("// ---- " + file.getName() + "\n");
			return  new PrintWriter(new FileOutputStream(file));
		} catch (IOException e) {
			throw new TaraException("Error during plugin generation");
		}
	}


	public static void out(String s) {
		System.out.print(s);
	}
}
