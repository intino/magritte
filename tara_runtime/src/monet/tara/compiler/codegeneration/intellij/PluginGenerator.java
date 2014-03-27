package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.ResourceManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.util.Collection;

public class PluginGenerator {

	CompilerConfiguration conf;


	public PluginGenerator(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(Collection<SourceUnit> units) throws TaraException {
		AST ast = mergeAST(units);
		String tplPath = ResourceManager.get("");
		new TaraPluginToJavaCodeGenerator().toJava(conf, ast);
		File grammarFile = TaraToBnfCodeGenerator.toBnf(conf, tplPath, ast);
		BnfToJavaCodeGenerator.bnfToJava(conf, grammarFile);
		File[] lexFiles = TaraToJFlexCodeGenerator.toJFlex(conf, tplPath, ast);
		for (File lexFile : lexFiles)
			JFlexToJavaGenerator.jFlexToJava(conf, lexFile);
		PluginCompiler.generateClasses(conf);
		PluginPackager.doPackage(conf);
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
