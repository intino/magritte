package monet.tara.intellij.plugin_generation;

import com.intellij.openapi.diagnostic.Logger;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;

public class PluginGenerator {

	public static final Logger LOG = Logger.getInstance("PluginGenerator");
	private static final String SEP = System.getProperty("file.separator");
	CompilerConfiguration configuration;


	public PluginGenerator(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public void generate(SourceUnit[] units) throws TaraException {
		new TaraToJavaGenerator().toJava(configuration);
		File grammarFile = TaraToBnfGenerator.toBnf(configuration, units);
		File lexFile = TaraToJFlexGenerator.toBnf(configuration, units);
		BnfToJavaGenerator.bnfToJava(configuration, grammarFile);
		JFlexToJavaGenerator.jFlexToJava(configuration, lexFile);
	}
}
