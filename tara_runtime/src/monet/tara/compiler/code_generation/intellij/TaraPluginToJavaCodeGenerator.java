package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.render.DefaultRender;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.RendersFactory;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaraPluginToJavaCodeGenerator extends CodeGenerator {

	CompilerConfiguration configuration;
	private PrintWriter writer;

	public void toJava(CompilerConfiguration configuration, AST ast) throws TaraException {
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates().keySet()) {
			writer = getOutWriter(new File(this.getPath(TemplateFactory.getTemplate(template))));
			writeTemplateBasedFile(template, ast.getIdentifiers());
			closeOutFile();
		}
	}

	private void writeTemplateBasedFile(String template, HashMap<String, String> param) {
		DefaultRender defaultRender = RendersFactory.getRender(template, configuration.getProject(), param);
		writer.print(defaultRender.getOutput());
	}

	private String getPath(String template) {
		String templateRefactored = template.replace("_", "Definition").replace("/tara/", "/" + configuration.getProject() + "/");
		templateRefactored = templateRefactored.replace("tpl/","");
		if (!template.contains("META-INF"))
			templateRefactored = templateRefactored.replace("-", RenderUtils.toProperCase(configuration.getProject()));
		return configuration.getTempDirectory().getAbsolutePath() + File.separator + SRC + File.separator + templateRefactored;
	}

	private void closeOutFile() {
		writer.close();
	}

}