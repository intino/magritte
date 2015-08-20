package tara.intellij.framework;

import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelListener;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import tara.intellij.actions.dialog.SourceFolderChooserDescriptor;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;

import static tara.intellij.lang.TaraLanguage.PROTEO;

class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {

	private static final String NONE = "";
	private TaraSupportProvider provider;
	private final Project project;
	private final Map<Module, AbstractMap.SimpleEntry<String, Integer>> moduleInfo;
	private JPanel myMainPanel;
	private JComboBox dslBox;
	private JTextField dslGeneratedName;
	private JLabel level;
	private JButton importButton;
	private JLabel levelLabel;
	private JCheckBox dynamicLoadCheckBox;
	private JPanel generatedLanguagePane;
	private JTextField pathToSource;
	private JCheckBox customizedMorphs;
	private JCheckBox languageExtension;
	private Module[] candidates;


	TaraSupportConfigurable(TaraSupportProvider provider, FrameworkSupportModel model) {
		this.provider = provider;
		this.project = model.getProject();
		candidates = getParentModulesCandidates(project);
		moduleInfo = collectModulesInfo();
		languageExtension.addItemListener(e -> pathToSource.setVisible(((JCheckBox) e.getItem()).isSelected()));
		dslBox.addItemListener(e -> {
			if (PROTEO.equals(e.getItem().toString())) setLevel(2);
			else moduleInfo.values().stream().
				filter(entry -> entry.getKey().equals(e.getItem().toString())).
				forEach(entry -> setLevel(entry.getValue() - 1));
		});
		level.addPropertyChangeListener("text", e -> editionOfGenerativeLanguage(Integer.parseInt(e.getNewValue().toString()) != 0));
		pathToSource.addMouseListener(fileMouseListener());
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		addModuleDsls();
		addImportAction();
		if (project == null || !project.isInitialized()) {
			dslBox.addItem(PROTEO);
			setLevel(2);
		} else setLevel(getLevel(dslBox.getSelectedItem().toString()));
		return myMainPanel;
	}

	private int getLevel(String selectedItem) {
		for (AbstractMap.SimpleEntry<String, Integer> entry : moduleInfo.values())
			if (entry.getKey().equals(selectedItem)) return entry.getValue() - 1;
		return 2;
	}

	private void addImportAction() {
		importButton.addActionListener(e -> selectLanguage());
	}

	private void selectLanguage() {
		VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, null);
		if (file == null) return;
		String newLang = FileUtilRt.getNameWithoutExtension(file.getName());
		provider.languages.put(newLang, new AbstractMap.SimpleEntry<>(1, new File(file.getPath())));
		dslBox.addItem(newLang);
		dslBox.setSelectedItem(newLang);
		level.setText("1");
	}

	private void addModuleDsls() {
		moduleInfo.entrySet().stream().
			filter(entry -> !entry.getValue().getValue().equals(0)).
			forEach(entry -> dslBox.addItem(entry.getValue().getKey()));
	}

	private void editionOfGenerativeLanguage(boolean editable) {
		dslGeneratedName.setVisible(editable);
	}

	private void setLevel(int level) {
		this.level.setText(level + "");
	}

	@Override
	public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
		dslBox.setEnabled(true);
		levelLabel.setEnabled(true);
		dslGeneratedName.setVisible(true);
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void frameworkUnselected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
		dslBox.setEnabled(false);
		levelLabel.setEnabled(false);
		dslGeneratedName.setEnabled(false);
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void wizardStepUpdated() {
		if (project == null || !project.isInitialized()) level.setEnabled(false);
	}

	@Override
	public void addSupport(@NotNull Module module,
	                       @NotNull ModifiableRootModel rootModel,
	                       @NotNull ModifiableModelsProvider modifiableModelsProvider) {
		provider.dsl = dslBox.getSelectedItem().toString();
		provider.dslGenerate = "0".equals(level.getText()) ? NONE : dslGeneratedName.getText();
		provider.dynamicLoad = dynamicLoadCheckBox.isSelected();
		provider.customMorphs = customizedMorphs.isSelected();
		provider.languageExtension = languageExtension.isSelected() ? pathToSource.getText() : "";
		provider.level = Integer.parseInt(level.getText());
		provider.selectedModuleParent = getSelectedParentModule();
		provider.addSupport(module, rootModel);
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, AbstractMap.SimpleEntry<String, Integer>> entry : moduleInfo.entrySet())
			if (entry.getValue().getKey().equals(dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	private Map<Module, AbstractMap.SimpleEntry<String, Integer>> collectModulesInfo() {
		Map<Module, AbstractMap.SimpleEntry<String, Integer>> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.getTaraFacetByModule(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration configuration = facet.getConfiguration();
			map.put(candidate, new AbstractMap.SimpleEntry<>(configuration.getGeneratedDslName(), configuration.getLevel()));
		}
		return map;
	}

	@NotNull
	private MouseListener fileMouseListener() {
		return new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VirtualFile file = FileChooser.chooseFile(new SourceFolderChooserDescriptor(), null, null);
				if (file == null) return;
				((JTextField) e.getSource()).setText(file.getPath());
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		};
	}
}
