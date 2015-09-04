package tara.intellij.project.facet;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;

import java.io.File;
import java.util.*;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;
import static tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class FacetEditorCreator {
	private static final Logger LOG = Logger.getInstance(FacetEditorCreator.class.getName());

	private static final String PROTEO_LIB = "lib/Proteo.jar";
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
		addDsls();
		addGeneratedLanguageName();
		editor.reload.addActionListener(e -> editor.reload());
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
			TaraFacetConfiguration conf = facet.getConfiguration();
			map.put(candidate, new AbstractMap.SimpleEntry<>(conf.getGeneratedDslName(), conf.getLevel()));
		}
		return map;
	}

	private void addGeneratedLanguageName() {
		editor.dslGeneratedName.setText(configuration.getGeneratedDslName());
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
