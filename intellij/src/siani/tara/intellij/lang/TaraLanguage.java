package siani.tara.intellij.lang;

import com.google.gson.*;
import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
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
	public static final Map<String, TreeWrapper> heritages = new HashMap<>();
	public static final TaraLanguage INSTANCE = new TaraLanguage();


	private TaraLanguage() {
		super("Tara");
	}

	@Nullable
	public static TreeWrapper getHeritage(Module module) {
		TreeWrapper treeWrapper;
		if (module == null) return null;
		ModuleConfiguration moduleConfiguration = (ModuleConfiguration) module.getComponent("ModuleConfiguration");
		String parent = moduleConfiguration.getParentName();
		if (parent.isEmpty()) return null;
		String projectName = module.getProject().getName();
		String basePath = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator + projectName + separator;
		if ((treeWrapper = heritages.get(parent)) != null && haveToReload(basePath))
			return treeWrapper;
		return loadHeritage(parent, basePath);
	}

	public static TreeWrapper getHeritage(String parent) {
		if (parent == null) return null;
		TreeWrapper treeWrapper;
		String[] splitName = parent.split("\\.");
		String basePath = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator + splitName[0] + separator;
		if ((treeWrapper = heritages.get(parent)) != null && haveToReload(basePath))
			return treeWrapper;
		return loadHeritage(parent, basePath);
	}

	public static TreeWrapper getHeritage(PsiFile file) {
		Module moduleFile = TaraUtil.getModuleOfFile(file);
		return TaraLanguage.getHeritage(moduleFile);
	}

	private static TreeWrapper loadHeritage(String parent, String basePath) {
		try {
			String heritageFile = basePath + parent + JSON;
			File file = new File(heritageFile);
			if (!file.exists()) return null;
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Variable.class, new ModelDeserializer());
			TreeWrapper treeWrapper = gb.create().fromJson(new InputStreamReader(heritageInputStream), TreeWrapper.class);
			heritages.put(parent, treeWrapper);
			return treeWrapper;
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
				e.getAsString(), name, json.getAsJsonObject().get("isMultiple").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());

			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new NodeAttribute(e.getAsString(), name, json.getAsJsonObject().get("isMultiple").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());

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