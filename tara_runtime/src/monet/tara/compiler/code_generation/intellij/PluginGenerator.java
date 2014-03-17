package monet.tara.compiler.code_generation.intellij;

import com.intellij.openapi.diagnostic.Logger;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.util.Collection;

public class PluginGenerator {

	public static final Logger LOG = Logger.getInstance("PluginGenerator");
	private static final String SEP = File.separator;
	CompilerConfiguration configuration;


	public PluginGenerator(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public void generate(Collection<SourceUnit> units) throws TaraException {
		AST ast = mergeAST(units);
		String tplPath = this.getClass().getResource(SEP + "tpl").getPath();
		new TaraPluginToJavaCodeGenerator().toJava(configuration, ast);
		File grammarFile = TaraToBnfCodeGenerator.toBnf(configuration, tplPath, ast);
		BnfToJavaCodeGenerator.bnfToJava(configuration, grammarFile);
		File[] lexFiles = TaraToJFlexCodeGenerator.toJFlex(configuration, tplPath, ast);
		for (File lexFile : lexFiles)
			JFlexToJavaGenerator.jFlexToJava(configuration, lexFile);
		PluginCompiler.generateClasses(configuration);
		PluginPackager.doPackage(configuration);
	}

	private AST mergeAST(Collection<SourceUnit> units) {
		AST ast = new AST();
		for (SourceUnit unit : units) {
			ast.addAll(unit.getAST().getAstRootNodes());
			ast.putAllIdentifiers(unit.getAST().getIdentifiers());
			ast.putAllLookupTable(unit.getAST().getLookUpTable());
		}
		return ast;
	}
}
