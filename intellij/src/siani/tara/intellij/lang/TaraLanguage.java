package siani.tara.intellij.lang;

import com.google.gson.*;
import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class TaraLanguage extends Language {

	public static final String JSON = ".json";
	public static final Map<String, Model> heritages = new HashMap<>();
	public static final TaraLanguage INSTANCE = new TaraLanguage();


	private TaraLanguage() {
		super("Tara");
	}

	public static Model getHeritage(String parent) {
		if (parent == null) return null;
		Model model;
		String[] splitName = parent.split("\\.");
		String basePath = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator + splitName[0] + separator;
		if ((model = heritages.get(parent)) != null && haveToReload(basePath))
			return model;
		model = loadHeritage(basePath, splitName[1]);
		heritages.put(parent, model);
		return model;
	}

	public static Model getHeritage(PsiFile file) {
		return TaraLanguage.getHeritage(((TaraFile)file).getParentModel());
	}

	private static Model loadHeritage(String basePath, String parent) {
		try {
			File file = new File(basePath, parent + JSON);
			if (!file.exists()) return null;
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Variable.class, new ModelDeserializer());
			return gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean haveToReload(String basePath) {
		File reload = new File(basePath, ".model_reload");
		if (reload.exists()) {
			reload.delete();
			return true;
		}
		return false;
	}

	protected static class ModelDeserializer implements JsonDeserializer<Variable> {

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
}