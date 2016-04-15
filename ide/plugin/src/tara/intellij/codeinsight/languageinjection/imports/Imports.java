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
import java.util.*;
import java.util.stream.Collectors;

public class Imports {

	private File miscDirectory;
	private static Map<String, Map<String, Set<String>>> imports;

	public Imports(Project project) {
		miscDirectory = LanguageManager.getMiscDirectory(project);
		imports = loadImports();
	}

	private Map<String, Map<String, Set<String>>> loadImports() {
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

	public Map<String, Map<String, Set<String>>> get() {
		return imports;
	}

	public Map<String, Set<String>> get(String module) {
		return imports.get(module);
	}

	public void save(String destinyFile, String qn, Set<String> newImports) {
		final String fileName = destinyFile + LanguageManager.JSON;
		if (!imports.containsKey(fileName)) imports.put(fileName, new HashMap<>());
		imports.get(fileName).put(qn, newImports == null ? Collections.emptySet() : newImports);
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

	public void refactor(String module, String old, String newQn) {
		final Map<String, Set<String>> map = imports.get(module + LanguageManager.JSON);
		if (map == null) return;
		Map<String, String> qnMap = new HashMap<>();
		final List<String> collect = map.keySet().stream().filter(qn -> qn.startsWith(old)).collect(Collectors.toList());
		collect.forEach(k -> qnMap.put(k, k.replaceFirst(old, newQn)));
		for (String key : qnMap.keySet()) {
			map.put(qnMap.get(key), map.get(key));
			map.remove(key);
		}
		save(module + LanguageManager.JSON);
	}
}
