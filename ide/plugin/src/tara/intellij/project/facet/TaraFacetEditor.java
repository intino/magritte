package tara.intellij.project.facet;

import com.intellij.facet.FacetManager;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.framework.FrameworkImporter;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;
import java.io.File;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaraFacetEditor extends FacetEditorTab {
	private static final Logger LOG = Logger.getInstance(TaraFacetEditor.class.getName());

	private static final String NONE = "";
	private final TaraFacetConfiguration configuration;
	final FacetEditorContext context;

	private JPanel myMainPanel;
	JComboBox dslBox;
	JTextField dslGeneratedName;
	JButton reload;
	JCheckBox customizedLayers;
	JCheckBox dynamicLoadCheckBox;
	JRadioButton newLanguage;
	JRadioButton newModel;
	JLabel dslName;

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
			!customizedLayers.isSelected() == configuration.isCustomLayers() ||
			!dslBox.getSelectedItem().equals(configuration.getDsl()) ||
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad();
	}

	public void apply() {
		if (isModified()) updateFacetConfiguration();
	}

	public void reset() {
		dslBox.setSelectedItem(configuration.getDsl());
		dslGeneratedName.setText(configuration.getGeneratedDslName());
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
		customizedLayers.setSelected(configuration.isCustomLayers());
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}

	private void updateFacetConfiguration() {
		configuration.setDsl((String) dslBox.getSelectedItem());
		configuration.setGeneratedDslName(getDslGeneratedName());
		configuration.setDynamicLoad(dynamicLoadCheckBox.isSelected());
		configuration.setCustomLayers(customizedLayers.isSelected());
		propagateChanges(configuration);
	}

	private void propagateChanges(TaraFacetConfiguration configuration) {
		final Module contextModule = context.getModule();
		for (Module aModule : ModuleManager.getInstance(context.getProject()).getModules())
			if (Arrays.asList(ModuleRootManager.getInstance(aModule).getDependencies()).contains(contextModule))
				propagateChanges(aModule, configuration);
	}

	private void propagateChanges(Module module, TaraFacetConfiguration conf) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final TaraFacet facet = TaraFacet.of(module);
			if (facet == null) return;
			facet.disposeFacet();
			facet.getConfiguration().setDsl(conf.getGeneratedDslName());
			facet.getConfiguration().setDynamicLoad(conf.isDynamicLoad());
			facet.getConfiguration().setCustomLayers(conf.isCustomLayers());
			FacetManager.getInstance(module).createModifiableModel().commit();
		});
		WriteCommandAction.runWriteCommandAction(module.getProject(), () -> {
			for (TaraModel model : TaraUtil.getTaraFilesOfModule(module))
				model.updateDSL(conf.getGeneratedDslName());
		});

	}

	void reload() {
		if (getSelectedParentModule() == null) {
			FrameworkImporter importer = new FrameworkImporter(context.getModule());
			importer.importLanguage(configuration.getDslKey(), LanguageInfo.LATEST_VERSION);
		}
		reload.setEnabled(false);
		reload.setIcon(IconLoader.getIcon("/icons/reload_disabled.png"));
	}

	private String getDslGeneratedName() {
		return dslGeneratedName.isEnabled() ? dslGeneratedName.getText() : NONE;
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, AbstractMap.SimpleEntry<String, Integer>> entry : moduleInfo.entrySet())
			if (entry.getValue().getKey().equals(dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}
}
