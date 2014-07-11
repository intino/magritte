package siani.tara.compiler;

import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import static java.io.File.separator;

public class LoadingModelTest {

	public static final String JSON = ".json";
	String basePath = "/Users/oroncal/workspace/sandbox/plugins" + separator + "tara" + separator + "classes" + separator + "Monet" + separator;

	@Test
	public void loadModel() throws Exception {
		try {
			File file = new File(basePath, "metamod" + JSON);
			Assert.assertTrue(file.exists());
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Variable.class, new ModelDeserializer());
			gb.registerTypeAdapter(Node.class, new NodeAdapter());
			Model model = gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
			Assert.assertNotNull(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class ModelDeserializer implements JsonDeserializer<Variable> {

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

	public class NodeAdapter implements JsonSerializer<Node>, JsonDeserializer<Node> {
		@Override
		public JsonElement serialize(Node src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject result = new JsonObject();
			result.add("objectType", new JsonPrimitive(src.getClass().getSimpleName()));
			result.add("properties", context.serialize(src, src.getClass()));

			return result;
		}

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
