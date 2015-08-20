package tara.intellij.project.facet;

import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.utils.FileSystemUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.PROTEO;

public class TaraFacetEditor extends FacetEditorTab {
	private static final Logger LOG = Logger.getInstance(TaraFacetEditor.class.getName());

	private static final String TARA_PREFIX = "Tara -> ";
	private static final String FRAMEWORK = "framework";
	private static final String NONE = "";
	private final TaraFacetConfiguration configuration;
	final FacetEditorContext context;
	JComboBox<String> dslBox;
	JTextField dslGeneratedName;
	JPanel myMainPanel;
	JLabel level;
	JButton importButton;
	private JCheckBox dynamicLoadCheckBox;
	private JCheckBox customizedMorphs;
	private JCheckBox languageExtension;
	private JTextField pathToSource;
	private JPanel generatedLanguagePane;
	private JLabel levelLabel;

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
		new FacetEditorCreator(this, configuration).createUI();
		return myMainPanel;
	}

	public boolean isModified() {
		return !getDslGeneratedName().equals(configuration.getGeneratedDslName()) ||
			!customizedMorphs.isSelected() == configuration.isCustomMorphs() ||
			!dslBox.getSelectedItem().equals(configuration.getDsl()) ||
			!dynamicLoadCheckBox.isSelected() == configuration.isDynamicLoad();
	}

	public void apply() {
		selectedModuleParent = getSelectedParentModule();
		updateFacetConfiguration();
		updateDependencies();
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, AbstractMap.SimpleEntry<String, Integer>> entry : moduleInfo.entrySet())
			if (entry.getValue().getKey().equals(dslBox.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private void updateFacetConfiguration() {
		configuration.setDsl((String) dslBox.getSelectedItem());
		configuration.setCustomMorphs(customizedMorphs.isSelected());
		configuration.setGeneratedDslName(getDslGeneratedName());
		configuration.setLevel(Integer.parseInt(level.getText()));
		configuration.setDynamicLoad(dynamicLoadCheckBox.isSelected());
	}

	private void updateDependencies() {
		ApplicationManager.getApplication().runWriteAction(() -> {
			setMagritteDependency(createDSL(context.getProject().getBaseDir()));
		});
	}

	private void setMagritteDependency(VirtualFile projectDirectory) {
		if (existFalseMirror()) return;
		if (languages.containsKey(dslBox.getSelectedItem().toString())) {
			if (dslBox.getSelectedItem().equals(PROTEO))
				importDslAndFrameWork(projectDirectory);
			applyDslToModule();
		} else if (selectedModuleParent != null) addModuleDependency();
	}

	private boolean existFalseMirror() {
		for (Module module : ModuleManager.getInstance(context.getProject()).getModules())
			if ("false-mirror".equals(module.getName())) return true;
		return false;
	}

	private void applyDslToModule() {
		try {
			String name = TARA_PREFIX + dslBox.getSelectedItem();
			removeOldModuleLibrary();
			Library library = context.findLibrary(name);
			if (library == null) {
				File file = findLibDirectory(context.getProject().getBaseDir());
				library = context.createProjectLibrary(name, new VirtualFile[]{VfsUtil.findFileByURL(file.toURI().toURL())}, VirtualFile.EMPTY_ARRAY);
			}
			context.getModifiableRootModel().addLibraryEntry(library);
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private File findLibDirectory(VirtualFile baseDir) {
		final File framework = new File(baseDir.getPath() + separator + FRAMEWORK, dslBox.getSelectedItem().toString());
		framework.mkdir();
		return framework;
	}

	private void removeOldModuleLibrary() {
		List<OrderEntry> toRemove = new ArrayList<>();
		for (OrderEntry library : context.getModifiableRootModel().getOrderEntries())
			if (library.getPresentableName().startsWith(TARA_PREFIX)) toRemove.add(library);
		final ModifiableRootModel rootModel = context.getModifiableRootModel();
		toRemove.forEach(rootModel::removeOrderEntry);
	}

	private void addModuleDependency() {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final ModifiableRootModel rootModel = context.getModifiableRootModel();
			if (!ModuleRootManager.getInstance(context.getModule()).isDependsOn(selectedModuleParent)) {
				rootModel.addModuleOrderEntry(selectedModuleParent).setExported(true);
			}
			final LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(context.getModule().getProject());
			for (Library library : libraryTable.getLibraries())
				if (library.getName() != null && library.getName().startsWith(TARA_PREFIX)) {
					rootModel.addLibraryEntry(library);
					return;
				}
		});
	}


	private VirtualFile createDSL(VirtualFile projectDir) {
		try {
			final VirtualFile dsl = projectDir.findChild("dsl");
			return dsl != null ? dsl : projectDir.createChildDirectory(null, "dsl");
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	private File importDslAndFrameWork(VirtualFile dslDirectory) {
		final String dsl = (String) dslBox.getSelectedItem();
		final AbstractMap.SimpleEntry<Integer, File> entry = languages.get(dsl);
		final File file = entry.getValue();
		File destiny;
		try {
			if (isJar(file)) {
				destiny = new File(dslDirectory.getPath() + separator + dsl + separator + file.getName());
				FileSystemUtils.copyFile(file.getPath(), destiny.getPath());
			} else {
				ZipUtil.unzip(null, new File(dslDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(dslDirectory.getPath() + separator + dsl);
			}
			reload(dslDirectory.getPath());
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String languagesPath) {
		final String dsl = (String) dslBox.getSelectedItem();
		File reload = new File(languagesPath, dsl + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private String getDslGeneratedName() {
		return dslGeneratedName.isEnabled() ? dslGeneratedName.getText() : NONE;
	}

	public void reset() {
		dslBox.setSelectedItem(configuration.getDsl());
		level.setText(configuration.getLevel() + "");
		dslGeneratedName.setText(configuration.getGeneratedDslName());
		dynamicLoadCheckBox.setSelected(configuration.isDynamicLoad());
		customizedMorphs.setSelected(configuration.isCustomMorphs());
		languageExtension.setSelected(configuration.isLanguageExtension());
		pathToSource.setText(configuration.getExtensionSourcePath());
	}

	public void disposeUIResources() {
	}

	@Override
	public String getHelpTopic() {
		return "tara_facet";
	}
}
