package siani.tara.intellij.lang;

import com.google.gson.*;
import com.intellij.lang.Language;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
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
		if ((treeWrapper = heritages.get(parent)) != null)
			return treeWrapper;
		return loadHeritage(module.getProject(), parent);
	}

	public static TreeWrapper getHeritage(PsiFile file) {
		Module moduleFile = TaraUtil.getModuleOfFile(file);
		return TaraLanguage.getHeritage(moduleFile);
	}

	private static TreeWrapper loadHeritage(Project project, String parent) {
		try {
			String heritageFile = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator + parent + separator + parent + JSON;
			InputStream heritageInputStream = new FileInputStream(new File(heritageFile));
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Variable.class, new CustomDeserializer());
			TreeWrapper treeWrapper = gb.create().fromJson(new InputStreamReader(heritageInputStream), TreeWrapper.class);
			heritages.put(parent, treeWrapper);
			return treeWrapper;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

			e = json.getAsJsonObject().get("resourceType");
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