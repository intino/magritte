package tara.intellij.project.facet;

import com.intellij.facet.FacetManager;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModuleRootManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.ImportLanguageAction;
import tara.intellij.lang.TaraLanguage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaraFacetEditor extends FacetEditorTab {
	private static final Logger LOG = Logger.getInstance(TaraFacetEditor.class.getName());

	private static final String TARA_PREFIX = "Tara -> ";
	private static final String FRAMEWORK = "framework";
	private static final String NONE = "";
	private final TaraFacetConfiguration configuration;
	final FacetEditorContext context;

	private JPanel myMainPanel;
	JComboBox dslBox;
	JTextField dslGeneratedName;
	JButton reload;
	JCheckBox customizedMorphs;
	JCheckBox dynamicLoadCheckBox;
	JRadioButton newLanguage;
	JRadioButton newModel;
	JLabel dslName;
	private JPanel generatedLanguagePane;
	private JLabel sourceLabel;

	private Module selectedModuleParent = null;
	Map<Module, AbstractMap.SimpleEntry<String, Integer>> moduleInfo;
	Map<String, AbstractMap.SimpleEntry<Integer, File>> languages = new HashMap<>();

	public TaraFacetEditor(TaraFacetConfiguration configuration, FacetEditorContext context) {
		this.configuration = configuration;
		this.context = context;
	}

	@Nls
	public String getDisplayName() {
		return "Tara Framework";
	}

	@NotNull
	public JComponent createComponent() {
		new FacetEditorUICreator(this, configuration).createUI();
		return myMainPanel;
	}

	public boolean isModified() {
		return !getDslGeneratedName().equals(configuration.getGeneratedDslName()) ||
			!customizedMorphs.isSelected() == configuration.isCustomLayers() ||
			!dslBox.getSelectedItem().equals(configuration.getDsl()) ||
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad();
	}

	public void apply() {
		selectedModuleParent = getSelectedParentModule();
		updateFacetConfiguration();
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, AbstractMap.SimpleEntry<String, Integer>> entry : moduleInfo.entrySet())
			if (entry.getValue().getKey().equals(dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void updateFacetConfiguration() {
		if (!getDslGeneratedName().equals(configuration.getGeneratedDslName())) propagateDslNameChange();
		configuration.setDsl((String) dslBox.getSelectedItem());
		configuration.setCustomLayers(customizedMorphs.isSelected());
		configuration.setGeneratedDslName(getDslGeneratedName());
		configuration.setDynamicLoad(dynamicLoadCheckBox.isSelected());
	}

	private void propagateDslNameChange() {
		final Module contextModule = context.getModule();
		for (Module aModule : ModuleManager.getInstance(context.getProject()).getModules())
			if (Arrays.asList(ModuleRootManager.getInstance(aModule).getDependencies()).contains(contextModule) && hasOldLanguage(aModule))
				refactorLanguage(aModule, getDslGeneratedName());

	}

	private boolean hasOldLanguage(Module aModule) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(aModule);
		return facet != null && configuration.getGeneratedDslName().equals(facet.getConfiguration().getDsl());
	}

	private void refactorLanguage(Module aModule, String dslGeneratedName) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(aModule);
			if (facet == null) return;
			facet.disposeFacet();
			facet.getConfiguration().setDsl(dslGeneratedName);
			FacetManager.getInstance(aModule).createModifiableModel().commit();
		});
	}

	void reload() {
		try {
			if (getSelectedParentModule() == null && !dslBox.getSelectedItem().equals(TaraLanguage.PROTEO)) {
				ImportLanguageAction action = new ImportLanguageAction();
				final File file = action.importLanguage(context.getModule());
				if (file != null) configuration.setImportedLanguagePath(file.getAbsolutePath());
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private String getDslGeneratedName() {
		return dslGeneratedName.isEnabled() ? dslGeneratedName.getText() : NONE;
	}

	public void reset() {
		dslBox.setSelectedItem(configuration.getDsl());
		dslGeneratedName.setText(configuration.getGeneratedDslName());
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
		customizedMorphs.setSelected(configuration.isCustomLayers());
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}
}
