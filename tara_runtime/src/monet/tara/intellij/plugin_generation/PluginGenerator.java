package monet.tara.intellij.plugin_generation;

import com.intellij.openapi.diagnostic.Logger;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.util.Collection;

public class PluginGenerator {

	public static final Logger LOG = Logger.getInstance("PluginGenerator");
	private static final String SEP = System.getProperty("file.separator");
	CompilerConfiguration configuration;


	public PluginGenerator(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public void generate(Collection<SourceUnit> units) throws TaraException {
		AST ast = mergeAST(units);
		String tplPath = this.getClass().getResource("/tpl/").getPath();
		new TaraToJavaGenerator().toJava(configuration);
		File grammarFile = TaraToBnfGenerator.toBnf(configuration, tplPath, ast);
		File lexFile = TaraToJFlexGenerator.toBnf(configuration, tplPath, ast);
		BnfToJavaGenerator.bnfToJava(configuration, grammarFile);
		JFlexToJavaGenerator.jFlexToJava(configuration, lexFile);
	}

	private AST mergeAST(Collection<SourceUnit> units) {
		AST ast = new AST();
		for (SourceUnit unit : units)
			ast.addAll(unit.getAST().getAstRootNodes());
		return ast;
	}
}
