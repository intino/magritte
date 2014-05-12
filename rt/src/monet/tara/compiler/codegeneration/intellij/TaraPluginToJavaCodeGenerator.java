package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.DefaultRender;
import monet.tara.compiler.codegeneration.render.RenderUtils;
import monet.tara.compiler.codegeneration.render.RendersFactory;
import monet.tara.compiler.codegeneration.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTWrapper;

import java.io.File;
import java.io.PrintWriter;

public class TaraPluginToJavaCodeGenerator extends CodeGenerator {

	CompilerConfiguration configuration;

	private PrintWriter writer;

	public File toJava(CompilerConfiguration configuration, ASTWrapper ast) throws TaraException {
		File bnfFile = null;
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates()) {
			if (template.endsWith("HighlighterLex.flex")) continue;
			File destiny = new File(getDestinyOf(TemplateFactory.getTemplate(template)));
			writer = getOutWriter(destiny);
			writeTemplateBasedFile(template, calculateParam(template, ast));
			writer.close();
			if (template.endsWith(".bnf")) bnfFile = destiny;
		}
		return bnfFile;
	}

	private Object calculateParam(String template, ASTWrapper ast) {
		if (TemplateFactory.getPluginDescriptorTemplate().equals(template)) return configuration;
		return ast;
	}


	private void writeTemplateBasedFile(String template, Object param) throws TaraException {
		DefaultRender defaultRender = RendersFactory.getRender(template, configuration.getProject(), param);
		writer.print(defaultRender.getOutput());
	}

	private String getDestinyOf(String template) {
		String templateRefactored = template.replace("_", "Definition").replace("/tara/", "/" + configuration.getProject().toLowerCase() + "/");
		templateRefactored = templateRefactored.replace("tpl/", "");
		if (!template.contains("META-INF"))
			templateRefactored = templateRefactored.replace("-", RenderUtils.toProperCase(configuration.getProject()));
		return PathManager.getSrcDir(configuration.getTempDirectory()) + templateRefactored;
	}
}