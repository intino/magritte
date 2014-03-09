package monet.tara.intellij.plugin_generation;

import monet.tara.compiler.code_generation.render.DefaultRender;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.RendersFactory;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaraToJavaGenerator {

	CompilerConfiguration configuration;
	private PrintWriter writer;

	public void toJava(CompilerConfiguration configuration) throws TaraException {
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates().keySet()) {
			openGeneratedFileOutput(new File(getPath(TemplateFactory.getTemplate(template))));
			writeTemplateBasedFile(template, null);
			closeOutFile();
		}
	}

	private void writeTemplateBasedFile(String template, HashMap<String, String> param) {
		DefaultRender defaultRender = RendersFactory.getRender(template, configuration.getProject(), param);
		writer.print(defaultRender.getOutput());
	}

	private String getPath(String template) {
		String templateRefactored = template.replace("_", "Definition").replace("/tara/", "/" + configuration.getProject() + "/");
		if (!template.contains("META-INF"))
			templateRefactored = templateRefactored.replace("-", RenderUtils.toProperCase(configuration.getProject()));
		return configuration.getTempDirectory().getAbsolutePath() + File.separator + "src" + File.separator + templateRefactored;
	}

	private void openGeneratedFileOutput(File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			out("// ---- " + file.getName() + "\n");
			writer = new PrintWriter(new FileOutputStream(file));
		} catch (IOException e) {
			throw new TaraException("Error during plugin generation");
		}
	}

	private void closeOutFile() {
		writer.close();
	}

	public void out(String s) {
		System.out.print(s);
	}

}
