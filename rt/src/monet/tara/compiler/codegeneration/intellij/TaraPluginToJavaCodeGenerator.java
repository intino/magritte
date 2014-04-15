package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.DefaultRender;
import monet.tara.compiler.codegeneration.render.RenderUtils;
import monet.tara.compiler.codegeneration.render.RendersFactory;
import monet.tara.compiler.codegeneration.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.PrintWriter;

public class TaraPluginToJavaCodeGenerator extends CodeGenerator {

	CompilerConfiguration configuration;
	private PrintWriter writer;

	public void toJava(CompilerConfiguration configuration, AST ast) throws TaraException {
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates().keySet()) {
			if (!"grammar".equals(template) && !"lexer".equals(template) && !"HighlighterLex.flex".equals(template)) {
				writer = getOutWriter(new File(getDestinyOf(TemplateFactory.getTemplate(template))));
				writeTemplateBasedFile(template, calculateParam(template, ast));
				writer.close();
			}
		}
	}

	private Object calculateParam(String template, AST ast) {
		if ("plugin.xml".equals(template)) return configuration;
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
