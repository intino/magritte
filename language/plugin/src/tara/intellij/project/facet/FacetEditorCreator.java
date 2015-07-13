package tara.intellij.project.facet;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.actions.utils.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;
import static tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class FacetEditorCreator {

	private static final String PROTEO_LIB = "Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";
	private final TaraFacetEditor editor;
	private final TaraFacetConfiguration configuration;
	Module[] candidates;

	public FacetEditorCreator(TaraFacetEditor editor, TaraFacetConfiguration configuration) {
		this.editor = editor;
		this.configuration = configuration;
		this.candidates = getParentModulesCandidates();
		editor.moduleInfo = collectModulesInfo();
		editor.languages.put(PROTEO, new AbstractMap.SimpleEntry<>(2, new File(PROTEO_DIRECTORY, PROTEO_LIB)));
	}

	public void createUI() {
		if (configuration.getDsl().equals(PROTEO)) editor.dslBox.addItem(PROTEO);
		editor.level.setText("" + configuration.getLevel());
		addDsls();
		addDictionaries();
		addGeneratedLanguageName();
		addListeners();
		addImportAction();
		if ("0".equals(editor.level.getText())) editionOfGenerativeLanguage(false);
	}


	private void addDsls() {
		Map<String, Boolean> toAdd = new HashMap();
		collectDslsFromModules(toAdd);
		if (!toAdd.containsKey(configuration.getDsl())) toAdd.put(configuration.getDsl(), true);
		for (Map.Entry<String, Boolean> entry : toAdd.entrySet()) {
			editor.dslBox.addItem(entry.getKey());
			if (entry.getValue()) editor.dslBox.setSelectedItem(entry.getKey());
		}
	}

	private void collectDslsFromModules(Map<String, Boolean> toAdd) {
		for (Map.Entry<Module, AbstractMap.SimpleEntry<String, Integer>> entry : editor.moduleInfo.entrySet()) {
			if (entry.getValue().getValue().equals(0)) continue;
			toAdd.put(entry.getValue().getKey(), false);
			if (configuration.getDsl().equals(entry.getValue().getKey()))
				toAdd.put(entry.getValue().getKey(), true);
		}
	}

	private Map<Module, AbstractMap.SimpleEntry<String, Integer>> collectModulesInfo() {
		Map<Module, AbstractMap.SimpleEntry<String, Integer>> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = getTaraFacetByModule(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			map.put(candidate, new AbstractMap.SimpleEntry<>(configuration.getGeneratedDslName(), configuration.getLevel()));
		}
		return map;
	}

	private void addDictionaries() {
		editor.dictionaryBox.addItem("English");
		editor.dictionaryBox.addItem("Español");
	}

	private void addGeneratedLanguageName() {
		editor.dslGeneratedName.setText(configuration.getGeneratedDslName());
	}

	private void addListeners() {
		editor.dslBox.addItemListener(e -> {
			if (e.getItem().toString().equals(PROTEO))
				setLevel(2);
			else editor.moduleInfo.values().stream().
				filter(entry -> entry.getKey().equals(e.getItem().toString())).
				forEach(entry -> setLevel(entry.getValue() - 1));
		});
		editor.level.addPropertyChangeListener("text", e -> editionOfGenerativeLanguage(Integer.parseInt(e.getNewValue().toString()) != 0));
	}

	private void addImportAction() {
		editor.importButton.addActionListener(e -> {
			try {
				VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, null);
				if (file == null) return;
				String newLang = getPresentableName(file);
				editor.languages.put(newLang, new AbstractMap.SimpleEntry<>(1, new File(file.getPath())));
				editor.dslBox.addItem(newLang);
				editor.dslBox.setSelectedItem(newLang);
				editor.level.setText("1");
				importDslAndFrameWork();
			} catch (Exception ignored) {
			}
		});
	}

	private File importDslAndFrameWork() {
		final VirtualFile projectDirectory = editor.context.getProject().getBaseDir();
		final String dsl = (String) editor.dslBox.getSelectedItem();
		final AbstractMap.SimpleEntry<Integer, File> entry = editor.languages.get(dsl);
		final File file = entry.getValue();
		File destiny;
		try {
			if (isJar(file)) {
				destiny = new File(projectDirectory.getPath() + separator + dsl + separator + file.getName());
				FileSystemUtils.copyFile(file.getPath(), destiny.getPath());
			} else {
				ZipUtil.unzip(null, new File(projectDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(projectDirectory.getPath() + separator + dsl);
			}
			reload(projectDirectory.getPath());
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (IOException e) {
			return null;
		}
	}

	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String languagesPath) {
		final String dsl = (String) editor.dslBox.getSelectedItem();
		File reload = new File(languagesPath, dsl + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException ignored) {
		}
	}


	@NotNull
	private String getPresentableName(VirtualFile file) {
		String name = file.getName();
		String presentableName = name.substring(0, file.getName().lastIndexOf("."));
		return presentableName.substring(0, 1).toUpperCase() + presentableName.substring(1);
	}

	private void editionOfGenerativeLanguage(boolean visibility) {
		editor.generativeLabel.setVisible(visibility);
		editor.plateRequired.setVisible(visibility);
		editor.dslGeneratedName.setVisible(visibility);
	}

	private void setLevel(int level) {
		editor.level.setText(level + "");
	}

	private Module[] getParentModulesCandidates() {
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(editor.context.getProject()).getModules()) {
			TaraFacet taraFacet = getTaraFacetByModule(aModule);
			if (taraFacet == null) continue;
			if (!aModule.equals(editor.context) && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}
}
