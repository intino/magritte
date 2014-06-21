package siani.tara.compiler.codegeneration.intellij;

import com.google.gson.*;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import static siani.tara.compiler.codegeneration.PathManager.SEP;

public class PluginUpdater {

	CompilerConfiguration conf;

	public PluginUpdater(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(TreeWrapper treeWrapper) throws TaraException {
		serializeNodes(treeWrapper);
		System.out.println("Nodes serialized");
		File[] lexFiles = TaraToJFlexCodeGenerator.toJFlex(conf, treeWrapper);
		for (File lexFile : lexFiles)
			JFlexToJavaGenerator.jFlexToJava(conf.getTempDirectory(), lexFile);
		PluginCompiler.generateClasses(conf);
		PluginUpdateApplier.update(conf);
	}

	private void serializeNodes(TreeWrapper treeWrapper) throws TaraException {
		try {
			File file = new File(conf.getPluginDirectory(), "classes" + SEP + conf.getProject() + SEP + conf.getModule() + ".json");
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
			throw new TaraException("Error serializing tree model");
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
				object.addProperty("isMultiple", attribute.isMultiple);
				object.addProperty("isTerminal", attribute.isTerminal);
			} else if (variable instanceof Reference) {
				Reference reference = (Reference) variable;
				object.addProperty("node", reference.type);
				object.addProperty("isMultiple", reference.isMultiple);
			} else if (variable instanceof Resource) {
				Resource reference = (Resource) variable;
				object.addProperty("resourceType", reference.node);
				object.addProperty("isTerminal", reference.isTerminal);
			}
			return object; // or throw an IllegalArgumentException

		}

	}
}
