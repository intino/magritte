package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.JFlexRender;
import monet.tara.compiler.codegeneration.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaraToJFlexCodeGenerator extends CodeGenerator {

	private static final String[] LEXER_TPL = new String[]{"lexer", "HighlighterLex.flex"};

	public static File[] toJFlex(CompilerConfiguration conf, ASTWrapper ast) throws TaraException {
		List<File> resultFiles = new ArrayList<>();
		for (String tpl : LEXER_TPL) {
			String template = TemplateFactory.getTemplate(tpl);
			String outPath = PathManager.getSrcDir(conf.getTempDirectory()) +
				newTemplate(conf.getProject().toLowerCase(), template);
			JFlexRender render = new JFlexRender(conf.getProject(), template, ast);
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
