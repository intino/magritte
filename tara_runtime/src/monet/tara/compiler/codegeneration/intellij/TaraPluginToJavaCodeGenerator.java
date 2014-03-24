package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.render.*;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

public class TaraPluginToJavaCodeGenerator extends CodeGenerator {

	CompilerConfiguration configuration;
	private PrintWriter writer;

	public void toJava(CompilerConfiguration configuration, AST ast) throws TaraException {
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates().keySet()) {
			writer = getOutWriter(new File(getDestinyOf(TemplateFactory.getTemplate(template))));
			writeTemplateBasedFile(template, ast.getIdentifiers());
			writer.close();
		}
		addIcons();
	}

	private void addIcons() throws TaraException {
		for (String icon : IconFactory.getIcons().keySet())
			FileSystemUtils.copyFile(getClass().getResource(IconFactory.getIcon(icon)).getPath(), getDestinyOf(IconFactory.getIcon(icon)));
		if (configuration.getProjectIcon() != null)
			FileSystemUtils.copyFile(configuration.getProjectIcon(), this.getDestinyOf(IconFactory.getIcon("-.png")));
	}

	private void writeTemplateBasedFile(String template, Map<String, String> param) {
		DefaultRender defaultRender = RendersFactory.getRender(template, configuration.getProject(), param);
		writer.print(defaultRender.getOutput());
	}

	private String getDestinyOf(String template) {
		String templateRefactored = template.replace("_", "Definition").replace("/tara/", "/" + configuration.getProject() + "/");
		templateRefactored = templateRefactored.replace("tpl/", "");
		if (!template.contains("META-INF"))
			templateRefactored = templateRefactored.replace("-", RenderUtils.toProperCase(configuration.getProject()));
		return PathManager.getSrcDir(configuration.getTempDirectory()) + templateRefactored;
	}
}
