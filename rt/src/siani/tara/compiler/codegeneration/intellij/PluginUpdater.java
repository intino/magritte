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

	public void generate(Model model) throws TaraException {
		serializeNodes(model);
		System.out.println("Nodes serialized. Plugin Updated");
	}

	private void serializeNodes(Model model) throws TaraException {
		try {
			String parent = conf.getPluginDirectory() + SEP + "classes" + SEP + conf.getProject();
			File file = new File(parent, conf.getModule() + ".json");
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(Variable.class, new VariableSerializer());
			Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(model));
			writer.close();
			new File(parent, ".model_reload").createNewFile();
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
				object.addProperty("isTerminal", word.isTerminal);
				object.addProperty("defaultWord", word.getDefaultWord());
				object.addProperty("isProperty", word.isProperty());
			} else if (variable instanceof NodeAttribute) {
				NodeAttribute attribute = (NodeAttribute) variable;
				object.addProperty("primitiveType", attribute.primitiveType);
				object.addProperty("value", attribute.getValue());
				object.addProperty("isSingle", attribute.isSingle);
				object.addProperty("isTerminal", attribute.isTerminal);
				object.addProperty("isProperty", attribute.isProperty());
			} else if (variable instanceof Reference) {
				Reference reference = (Reference) variable;
				object.addProperty("node", reference.type);
				object.addProperty("isSingle", reference.isSingle);
				object.addProperty("isTerminal", reference.isTerminal);
				object.addProperty("isProperty", reference.isProperty());
				object.addProperty("isEmpty", reference.isEmpty());
			} else if (variable instanceof Resource) {
				Resource resource = (Resource) variable;
				object.addProperty("resourceType", resource.node);
				object.addProperty("isProperty", resource.isProperty());
				object.addProperty("isTerminal", resource.isTerminal);
			}
			return object; // or throw an IllegalArgumentException

		}

	}
}
