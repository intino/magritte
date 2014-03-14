package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.render.JFlexRender;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.PrintWriter;

public class TaraToJFlexCodeGenerator extends CodeGenerator {

	private static final String LEXER_TPL = "lexer";

	public static File toJFlex(CompilerConfiguration configuration, String tplPath, AST ast) throws TaraException {
		String template = TemplateFactory.getTemplate(LEXER_TPL);
		String outPath = configuration.getTempDirectory().getAbsolutePath() + SEP + SRC + SEP +
			newTemplate(RenderUtils.toProperCase(configuration.getProject()), template);
		JFlexRender render = new JFlexRender(configuration.getProject(), template, tplPath, ast.getIdentifiers());
		File file = new File(outPath);
		PrintWriter outWriter = getOutWriter(file);
		outWriter.print(render.getOutput());
		outWriter.close();
		return file;
	}


}
