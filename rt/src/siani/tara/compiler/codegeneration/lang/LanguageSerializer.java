package siani.tara.compiler.codegeneration.lang;

import siani.tara.compiler.codegeneration.CodeGenerator;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.codegeneration.java.JavaCommandHelper;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.model.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageSerializer extends CodeGenerator {
	private static final Logger LOG = Logger.getLogger(LanguageSerializer.class.getName());

	private static final String JAVA = ".java";

	CompilerConfiguration conf;

	public LanguageSerializer(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void serialize(Model model) throws TaraException {
		if (!serialize(LanguageCreator.create(model), new File(conf.getLanguageDirectory(), model.getName() + JAVA)))
			throw new TaraException("Error sav¡ng model.");
		try {
			new File(conf.getLanguageDirectory(), model.getName() + ".reload").createNewFile();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new TaraException("Error sav¡ng model.");
		}
	}

	private boolean serialize(String content, File file) throws TaraException {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
			compile(file);
			return true;
		} catch (IOException e) {
			throw new TaraException("Error saving language");
		} catch (InterruptedException e) {
			throw new TaraException("Error compiling language");
		}
	}

	private void compile(File file) throws TaraException, IOException, InterruptedException {
		List<String> command = JavaCommandHelper.buildJavaCompileCommandLine(
			new String[]{ResourceManager.getFile("tara-semantic.jar").getAbsolutePath()}, new String[0], conf.getLanguageDirectory(), file);
		Runtime rt = Runtime.getRuntime();
		Process compileProcess = rt.exec(JavaCommandHelper.join(command.toArray(new String[command.size()]), " "));
		if (compileProcess.waitFor() == -1) return;
		file.deleteOnExit();
		printResult(compileProcess);
	}


}
