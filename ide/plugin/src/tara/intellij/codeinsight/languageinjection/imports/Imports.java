package tara.intellij.codeinsight.languageinjection.imports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.project.Project;
import tara.intellij.lang.LanguageManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Imports {

	private File miscDirectory;
	private static Map<String, Map<String, Set<String>>> imports;

	public Imports(Project project) {
		miscDirectory = LanguageManager.getMiscDirectory(project);
		imports = loadImports();
	}

	public Map<String, Map<String, Set<String>>> loadImports() {
		final File[] files = miscDirectory.listFiles((dir, name) -> name.endsWith(LanguageManager.JSON));
		Gson gson = new Gson();
		Map<String, Map<String, Set<String>>> imports = new HashMap();
		if (files == null) return imports;
		try {
			for (File file : files) {
				imports.put(file.getName(), gson.fromJson(new FileReader(file), new TypeToken<Map<String, Set<String>>>() {
				}.getType()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return imports;
	}

	public static Map<String, Map<String, Set<String>>> get() {
		return imports;
	}

	public void save(String module, String qn, Set<String> newImports) {
		final String fileName = module + LanguageManager.JSON;
		if (!imports.containsKey(fileName)) imports.put(fileName, new HashMap<>());
		imports.get(fileName).put(qn, newImports);
		save(fileName);
	}

	private void save(String fileName) {
		try {
			final File file = new File(miscDirectory, fileName);
			file.getParentFile().mkdirs();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Files.write(file.toPath(), gson.toJson(imports.get(fileName)).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
