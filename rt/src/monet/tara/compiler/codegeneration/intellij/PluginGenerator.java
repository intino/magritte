package monet.tara.compiler.codegeneration.intellij;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class PluginGenerator {

	private static final String AST_JSON = "m2" + PathManager.SEP + "ast.json";
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

	private void serializeNodes(AST nodes) throws TaraException {
		try {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			File file = new File(PathManager.getResIdeDir(conf.getTempDirectory()), AST_JSON);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(nodes));
			writer.close();
			Type collectionType = new TypeToken<Collection<ASTNode>>() {}.getType();
			List<String> astNodes = gson.fromJson(new InputStreamReader(new FileInputStream("ast.json")), collectionType);
		} catch (IOException e) {
			e.printStackTrace();
			throw new TaraException("Error serializing ast");
		}
	}
}
