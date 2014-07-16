package siani.tara.compiler.codegeneration.model;

import com.google.gson.*;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class ModelProvider {

	public static final String JSON = ".json";

	public static Model getModel(String modelDirectory, String model) throws TaraException {
		try {
			File file = new File(modelDirectory, model + JSON);
			if (!file.exists()) throw new TaraException("Model file not Found: " + file.getAbsolutePath());
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Node.class, new NodeAdapter());
			gb.registerTypeAdapter(Variable.class, new VariableDeserializer());
			return gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
		} catch (Exception e) {
			throw new TaraException("Error deserializing model: " + e.getMessage());
		}
	}

	private static class VariableDeserializer implements JsonDeserializer<Variable> {

		public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();

			JsonElement e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) return new Reference(
				e.getAsString(), name, json.getAsJsonObject().get("isSingle").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());
			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new NodeAttribute(e.getAsString(), name, json.getAsJsonObject().get("isSingle").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());
			e = json.getAsJsonObject().get("resourceType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new Resource(e.getAsString(), name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
			JsonArray array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
			if (array != null && array.isJsonArray()) {
				NodeWord word = new NodeWord(name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
				for (JsonElement jsonElement : array) word.add(jsonElement.getAsString());
				return word;
			}
			return null;
		}
	}

	private static class NodeAdapter implements JsonDeserializer<Node> {
		@Override
		public Node deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			String type = jsonObject.get("objectType").getAsString();
			JsonElement element = jsonObject.get("properties");
			try {
				return context.deserialize(element, Class.forName("siani.tara.lang." + type));
			} catch (ClassNotFoundException cnfe) {
				throw new JsonParseException("Unknown element type: " + type, cnfe);
			}
		}
	}
}
