package monet.::projectName::.intellij.lang;

import com.google.gson.*;
import com.intellij.lang.Language;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class ::projectProperName::Language extends Language {

	public static final String AST_JSON = "/ast/ast.json";
	public static final ::projectProperName::Language INSTANCE = new ::projectProperName::Language();
	public static ASTWrapper heritage = null;

	private ::projectProperName::Language() {
		super("::projectProperName::");
		loadHeritage();
	}

	public static ASTWrapper getHeritage() {
		if (heritage == null) loadHeritage();
		return heritage;
	}

	public static boolean hasHeritage() {
		return heritage != null;
	}

	private static void loadHeritage() {
		try {
			InputStream heritageInputStream = ::projectProperName::Language.class.getResourceAsStream(AST_JSON);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(ASTNode.Variable.class, new CustomDeserializer());
			heritage = gb.create().fromJson(new InputStreamReader(heritageInputStream), ASTWrapper.class);
		} catch (Exception e) {
			//e.printStackTrace();
			heritage = null;
		}
	}

	public static class CustomDeserializer implements JsonDeserializer<ASTNode.Variable> {

		public ASTNode.Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();

			JsonElement e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) return new ASTNode.Reference(name,
				e.getAsString(), json.getAsJsonObject().get("isList").getAsBoolean());

			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new ASTNode.Attribute(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean());

			JsonArray array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
			if (array != null && array.isJsonArray()) {
				ASTNode.Word word = new ASTNode.Word(name);
				for (JsonElement jsonElement \: array) word.add(jsonElement.getAsString());
				return word;
			}
			return null;
		}

	}
}