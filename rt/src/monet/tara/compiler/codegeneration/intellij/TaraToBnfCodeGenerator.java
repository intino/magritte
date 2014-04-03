package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.BnfRender;
import monet.tara.compiler.codegeneration.render.RenderUtils;
import monet.tara.compiler.codegeneration.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.PrintWriter;

public class TaraToBnfCodeGenerator extends CodeGenerator {

	private static final String GRAMMAR_TPL = "grammar";

	public static File toBnf(CompilerConfiguration conf, AST ast) throws TaraException {
		String template = TemplateFactory.getTemplate(GRAMMAR_TPL);
		String outPath = PathManager.getSrcDir(conf.getTempDirectory()) +
			newTemplate(RenderUtils.toProperCase(conf.getProject()), template);
		BnfRender render = new BnfRender(conf.getProject(), template, ast);
		File file = new File(outPath);
		PrintWriter outWriter = getOutWriter(file);
		outWriter.print(render.getOutput());
		outWriter.close();
		return file;
	}


}
