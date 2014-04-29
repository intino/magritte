package monet.tara.compiler.codegeneration.intellij;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

public class PluginGenerator {

	private static final String AST_JSON = "ast" + PathManager.SEP + "ast.json";
	CompilerConfiguration conf;

	public PluginGenerator(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(Collection<SourceUnit> units) throws TaraException {
		ASTWrapper ast = mergeAST(units);
		serializeNodes(ast.getAST());
		new TaraPluginToJavaCodeGenerator().toJava(conf, ast);
		File grammarFile = TaraToBnfCodeGenerator.toBnf(conf, ast);
		BnfToJavaCodeGenerator.bnfToJava(conf, grammarFile);
		File[] lexFiles = TaraToJFlexCodeGenerator.toJFlex(conf, ast);
		for (File lexFile : lexFiles)
			JFlexToJavaGenerator.jFlexToJava(conf.getTempDirectory(), lexFile);
		PluginCompiler.generateClasses(conf);
		PluginPackager.doPackage(conf);
	}

	private ASTWrapper mergeAST(Collection<SourceUnit> units) {
		ASTWrapper ast = new ASTWrapper();
		for (SourceUnit unit : units) {
			ast.addAll(unit.getAST().getAST());
			ast.putAllIdentifiers(unit.getAST().getIdentifiers());
			ast.putAllInNodeNameTable(unit.getAST().getNodeNameLookUpTable());
		}
		return ast;
	}

	private void serializeNodes(List nodes) throws TaraException {
		try {
			File file = new File(PathManager.getResIdeDir(conf.getTempDirectory()), AST_JSON);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(nodes));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TaraException("Error serializing ast");
		}
	}
}
