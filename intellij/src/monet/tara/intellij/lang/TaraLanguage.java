package monet.tara.intellij.lang;

import com.google.gson.*;
import com.intellij.lang.Language;
import monet.tara.lang.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class TaraLanguage extends Language {

	public static final String TREE_JSON = "/ast/ast.json";
	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static TreeWrapper heritage = null;

	private TaraLanguage() {
		super("Tara");
		loadHeritage();
	}

	public static TreeWrapper getHeritage() {
		if (heritage == null) loadHeritage();
		return heritage;
	}

	public static boolean hasHeritage() {
		return heritage != null;
	}

	private static void loadHeritage() {
		try {
			InputStream heritageInputStream = TaraLanguage.class.getResourceAsStream(TREE_JSON);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Variable.class, new CustomDeserializer());
			heritage = gb.create().fromJson(new InputStreamReader(heritageInputStream), TreeWrapper.class);
		} catch (Exception e) {
			//e.printStackTrace();
			heritage = null;
		}
	}

	public static class CustomDeserializer implements JsonDeserializer<Variable> {

		public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();

			JsonElement e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) return new Reference(
				e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean());

			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new NodeAttribute(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean(), json.getAsJsonObject().get("isProperty").getAsBoolean());

			e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new Resource(e.getAsString(), name, json.getAsJsonObject().get("isProperty").getAsBoolean());
			JsonArray array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
			if (array != null && array.isJsonArray()) {
				NodeWord word = new NodeWord(name);
				for (JsonElement jsonElement : array) word.add(jsonElement.getAsString());
				return word;
			}
			return null;
		}

	}
}