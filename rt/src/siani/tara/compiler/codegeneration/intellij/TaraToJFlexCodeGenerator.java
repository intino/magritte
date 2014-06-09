package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.codegeneration.render.JFlexRender;
import siani.tara.compiler.codegeneration.render.TemplateFactory;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.TreeWrapper;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaraToJFlexCodeGenerator extends CodeGenerator {

	public static File[] toJFlex(CompilerConfiguration conf, TreeWrapper ast) throws TaraException {
		List<File> resultFiles = new ArrayList<>();
		for (String template : TemplateFactory.getLexerTemplates()) {
			String outPath = PathManager.getSrcDir(conf.getTempDirectory()) + newTemplate(conf.getProject().toLowerCase(), template);
			JFlexRender render = new JFlexRender(template, conf.getProject(), ast);
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
