package monet.tara.compiler.codegeneration.intellij;

import com.google.gson.*;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.lang.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class PluginGenerator {

	private static final String TREE_JSON = "ast" + PathManager.SEP + "ast.json";
	CompilerConfiguration conf;

	public PluginGenerator(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(TreeWrapper ast) throws TaraException {
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

	private void serializeNodes(TreeWrapper treeWrapper) throws TaraException {
		try {
			File file = new File(PathManager.getSourceResIdeDir(conf.getTempDirectory()), TREE_JSON);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(Variable.class, new VariableSerializer());
			Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(treeWrapper));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TaraException("Error serializing ast");
		}
	}

	public static class VariableSerializer implements JsonSerializer<Variable> {
		@Override
		public JsonElement serialize(Variable variable, Type type, JsonSerializationContext jsonSerializationContext) {
			final JsonObject object = new JsonObject();
			object.addProperty("name", variable.name);
			if (variable instanceof NodeWord) {
				NodeWord word = (NodeWord) variable;
				final JsonArray list = new JsonArray();
				for (String wordType : word.wordTypes) list.add(new JsonPrimitive(wordType));
				object.add("wordTypes", list);
			} else if (variable instanceof NodeAttribute) {
				NodeAttribute attribute = (NodeAttribute) variable;
				object.addProperty("primitiveType", attribute.primitiveType);
				object.addProperty("isList", attribute.isList);
				object.addProperty("isProperty", attribute.isProperty);
			} else if (variable instanceof Reference) {
				Reference reference = (Reference) variable;
				object.addProperty("node", reference.node);
				object.addProperty("isList", reference.isList);
			} else if (variable instanceof Resource) {
				Resource reference = (Resource) variable;
				object.addProperty("type", reference.type);
				object.addProperty("isProperty", reference.isProperty);
			}
			return object; // or throw an IllegalArgumentException

		}

	}
}
