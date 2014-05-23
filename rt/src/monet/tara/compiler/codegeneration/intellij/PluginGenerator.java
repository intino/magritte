package monet.tara.compiler.codegeneration.intellij;

import com.google.gson.*;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;

public class PluginGenerator {

	private static final String AST_JSON = "ast" + PathManager.SEP + "ast.json";
	CompilerConfiguration conf;

	public PluginGenerator(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(Collection<SourceUnit> units) throws TaraException {
		ASTWrapper ast = mergeAST(units);
		serializeNodes(ast);
		File bnfFile = new TaraPluginToJavaCodeGenerator().toJava(conf, ast);
		BnfToJavaCodeGenerator.bnfToJava(conf, bnfFile);
		File[] lexFiles = TaraToJFlexCodeGenerator.toJFlex(conf, ast);
		for (File lexFile : lexFiles)
			JFlexToJavaGenerator.jFlexToJava(conf.getTempDirectory(), lexFile);
		TemplateGenerator.generateDefinitionTemplates(conf, ast);
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

	private void serializeNodes(ASTWrapper astWrapper) throws TaraException {
		try {
			File file = new File(PathManager.getSourceResIdeDir(conf.getTempDirectory()), AST_JSON);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(ASTNode.Variable.class, new VariableSerializer());
			Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(astWrapper));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TaraException("Error serializing ast");
		}
	}

	public static class VariableSerializer implements JsonSerializer<ASTNode.Variable> {
		@Override
		public JsonElement serialize(ASTNode.Variable variable, Type type, JsonSerializationContext jsonSerializationContext) {
			final JsonObject object = new JsonObject();
			object.addProperty("name", variable.name);
			if (variable instanceof ASTNode.Word) {
				ASTNode.Word word = (ASTNode.Word) variable;
				final JsonArray list = new JsonArray();
				for (String wordType : word.wordTypes) list.add(new JsonPrimitive(wordType));
				object.add("wordTypes", list);
			} else if (variable instanceof ASTNode.Attribute) {
				ASTNode.Attribute attribute = (ASTNode.Attribute) variable;
				object.addProperty("primitiveType", attribute.primitiveType);
				object.addProperty("isList", attribute.isList);
			} else if (variable instanceof ASTNode.Reference) {
				ASTNode.Reference reference = (ASTNode.Reference) variable;
				object.addProperty("node", reference.node);
				object.addProperty("isList", reference.isList);
			}
			return object; // or throw an IllegalArgumentException

		}

	}
}
