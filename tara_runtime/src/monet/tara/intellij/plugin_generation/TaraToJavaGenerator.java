package monet.tara.intellij.plugin_generation;

import monet.tara.compiler.code_generation.render.DefaultRender;
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
	private PrintWriter printWriter;

	public void toJava(CompilerConfiguration configuration) throws TaraException {
		this.configuration = configuration;
		for (String template : TemplateFactory.getTemplates().keySet()) {
			String templatePath = TemplateFactory.getTemplate(template).replace("tara", configuration.getProject().toLowerCase());
			openGeneratedFileOutput(new File(getPath(templatePath)));
			writeTemplateBasedFile(template, null);
			closeOutFile();
		}
	}


	private void writeTemplateBasedFile(String template, HashMap<String, String> param) {
		DefaultRender defaultRender = RendersFactory.getRender(template, configuration.getProject(), param);
		printWriter.print(defaultRender.getOutput());
	}


	private String getPath(String templatePath) {
		String path = configuration.getTempDirectory().getAbsolutePath();
		if (templatePath.equals("META-INF/plugin"))
			return path + templatePath + ".xml";
		else if (templatePath.endsWith("grammar"))
			return path + templatePath + ".bnf";
		else if (templatePath.endsWith("lexer"))
			return path + templatePath + ".flex";
		else return path + templatePath + ".java";
	}

	private void openGeneratedFileOutput(File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			out("// ---- " + file.getName() + "\n");
			printWriter = new PrintWriter(new FileOutputStream(file));
		} catch (IOException e) {
			throw new TaraException("Error during plugin generation");
		}
	}

	private void closeOutFile() {
		printWriter.close();
	}

	public void out(String s) {
		System.out.print(s);
	}

}
