package siani.tara.intellij.framework;

import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelListener;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import siani.tara.intellij.actions.dialog.LanguageFileChooserDescriptor;
import siani.tara.intellij.actions.utils.FileSystemUtils;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.File.separator;
import static siani.tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final String PROTEO = "Proteo";
	private static final String MAGRITTE_LIB = "Magritte-1.0.jar";
	private static final String MAGRITTE_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";
	private String dsl;
	private String dictionary;
	private String dslGenerate;
	private boolean plateRequired;
	private int level;

	private Map<String, File> importedLanguagePaths = new HashMap<>();
	private Module selectedModuleParent = null;

	public TaraSupportProvider() {
		importedLanguagePaths.put(PROTEO, new File(MAGRITTE_DIRECTORY, MAGRITTE_LIB));
	}

	@NotNull
	@Override
	public FrameworkTypeEx getFrameworkType() {
		return TaraFrameworkType.getFrameworkType();
	}

	@Override
	public boolean isEnabledForModuleType(@NotNull ModuleType moduleType) {
		return moduleType instanceof JavaModuleType;
	}

	@Override
	public boolean isSupportAlreadyAdded(@NotNull Module module, @NotNull FacetsProvider facetsProvider) {
		return !facetsProvider.getFacetsByType(module, TaraFacet.ID).isEmpty();
	}

	private void addSupport(final Module module, final ModifiableRootModel rootModel) {
		createModelSourceRoot(rootModel.getContentEntries()[0]);
		createResources(rootModel.getContentEntries()[0]);
		createGenSourceRoot(rootModel.getContentEntries()[0]);
		updateFacetConfiguration(module);
		updateDependencies(module, rootModel);
	}

	private void updateDependencies(Module module, ModifiableRootModel rootModel) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			setMagritteDependency(module, rootModel, createDSL(module.getProject().getBaseDir()));
		});

	}

	private void setMagritteDependency(Module module, ModifiableRootModel rootModel, VirtualFile dslDirectory) {
		if (module.getName().equals(PROTEO)) {
			//TODO
		} else if (importedLanguagePaths.containsKey(this.dsl))
			addDslLibToSdk(rootModel, importDsl(dslDirectory));
		else addModuleDependency(rootModel);
	}

	private void addModuleDependency(ModifiableRootModel rootModel) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			if (!ModuleRootManager.getInstance(rootModel.getModule()).isDependsOn(selectedModuleParent)) {
				final ModuleOrderEntry moduleOrderEntry = rootModel.addModuleOrderEntry(selectedModuleParent);
				moduleOrderEntry.setExported(true);
			}
		});
	}

	private void addDslLibToSdk(ModifiableRootModel rootModel, File file) {
		Sdk sdk = rootModel.getSdk();
		if (sdk == null) return;
		if (!file.isDirectory()) addToSdk(sdk, file);
		else for (File lib : file.listFiles()) addToSdk(sdk, lib);
	}

	private void addToSdk(Sdk sdk, File file) {
		VirtualFile vFile = VfsUtil.findFileByIoFile(file, true);
		if (!isAlreadyAdded(sdk.getSdkModificator(), vFile))
			sdk.getSdkModificator().addRoot(vFile, OrderRootType.CLASSES);
		sdk.getSdkModificator().commitChanges();
	}

	private boolean isAlreadyAdded(SdkModificator sdkModificator, VirtualFile vFile) {
		for (VirtualFile virtualFile : sdkModificator.getRoots(OrderRootType.CLASSES))
			if (virtualFile.equals(vFile)) return true;
		return false;
	}

	private File importDsl(VirtualFile dslDirectory) {
		final File file = importedLanguagePaths.get(this.dsl);
		File destiny;
		try {
			if (isJar(file)) {
				destiny = new File(dslDirectory.getPath() + separator + dsl + separator + "metamodel" + separator + file.getName());
				FileSystemUtils.copyFile(file.getPath(), destiny.getPath());
			} else {
				ZipUtil.unzip(null, new File(dslDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(dslDirectory.getPath() + separator + dsl, "metamodel" + separator);
			}
			reload(file.getName(), file.getParentFile().getAbsolutePath());
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (
			IOException e
			)

		{
			e.printStackTrace();
			return null;
		}
	}

	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String fileName, String languagesPath) {
		File reload = new File(languagesPath + fileName.substring(0, fileName.lastIndexOf(".")) + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateFacetConfiguration(Module module) {
		FacetType<TaraFacet, TaraFacetConfiguration> facetType = TaraFacet.getFacetType();
		TaraFacet TaraFacet = FacetManager.getInstance(module).addFacet(facetType, facetType.getDefaultFacetName(), null);
		final TaraFacetConfiguration facetConfiguration = TaraFacet.getConfiguration();
		facetConfiguration.setDsl(dsl);
		facetConfiguration.setDictionary(dictionary);
		facetConfiguration.setGeneratedDslName(dslGenerate);
		facetConfiguration.setPlateRequired(plateRequired);
		facetConfiguration.setLevel(level);
		facetConfiguration.setDslsDirectory(module.getProject().getBasePath() + separator + "dsl" + separator);
	}

	private void createResources(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot = file.createChildDirectory(null, "res");
			contentEntry.addSourceFolder(sourceRoot, JavaResourceRootType.RESOURCE);
		} catch (IOException ignored) {
		}
	}

	private VirtualFile createDSL(VirtualFile projectDir) {
		try {
			final VirtualFile dsl = projectDir.findChild("dsl");
			return dsl != null ? dsl : projectDir.createChildDirectory(null, "dsl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void createGenSourceRoot(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot = file.createChildDirectory(null, "gen");
			JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", true);
			contentEntry.addSourceFolder(sourceRoot, JavaSourceRootType.SOURCE, properties);
		} catch (IOException ignored) {
		}
	}


	private void createModelSourceRoot(ContentEntry contentEntry) {
		try {
			if (contentEntry.getFile() == null) return;
			String modulePath = contentEntry.getFile().getPath();
			VirtualFile templates = VfsUtil.createDirectories(modulePath + separator + "model");
			if (templates != null) {
				JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", false);
				contentEntry.addSourceFolder(templates, JavaSourceRootType.SOURCE, properties);
			}
		} catch (IOException ignored) {
		}
	}

	@NotNull
	@Override
	public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
		return new TaraSupportConfigurable(model);
	}

	private class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {
		private static final String NONE = "";
		private final Project project;
		private final Map<Module, AbstractMap.SimpleEntry<String, Integer>> moduleInfo;
		private JPanel myMainPanel;
		private JComboBox<String> dictionaryBox;
		private JComboBox<String> dslBox;
		private JTextField dslGeneratedName;
		private JCheckBox plateRequired;
		private JLabel generativeLabel;
		private JSpinner level;
		private JButton importButton;
		private Module[] candidates;


		private TaraSupportConfigurable(FrameworkSupportModel model) {
			model.addFrameworkListener(this);
			this.project = model.getProject();
			candidates = getParentModulesCandidates(project);
			moduleInfo = collectModulesInfo();
		}

		@Nullable
		@Override
		public JComponent createComponent() {
			addModuleDsls();
			addDictionaries();
			addListeners();
			addImportAction();
			if (project == null || !project.isInitialized()) {
				dslBox.addItem(PROTEO);
				setLevel(2, true);
			} else setLevel(getLevel(dslBox.getSelectedItem().toString()), false);

			return myMainPanel;
		}

		private int getLevel(String selectedItem) {
			for (AbstractMap.SimpleEntry<String, Integer> entry : moduleInfo.values())
				if (entry.getKey().equals(selectedItem)) return entry.getValue() - 1;
			return 2;
		}

		private void addImportAction() {
			if (project != null) importButton.setVisible(false);
			else importButton.addActionListener(e -> {
				try {
					VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, null);
					if (file == null) return;
					String newLang = getPresentableName(file);
					importedLanguagePaths.put(newLang, new File(file.getPath()));
					dslBox.addItem(newLang);
					dslBox.setSelectedItem(newLang);
				} catch (Exception ignored) {
				}
			});
		}

		@NotNull
		private String getPresentableName(VirtualFile file) {
			String name = file.getName();
			String presentableName = name.substring(0, file.getName().lastIndexOf("."));
			return presentableName.substring(0, 1).toUpperCase() + presentableName.substring(1);
		}

		private void addModuleDsls() {
			moduleInfo.entrySet().stream().
				filter(entry -> !entry.getValue().getValue().equals(0)).
				forEach(entry -> dslBox.addItem(entry.getValue().getKey()));
		}

		private void addDictionaries() {
			dictionaryBox.addItem("English");
			dictionaryBox.addItem("Español");
		}

		private void addListeners() {
			dslBox.addItemListener(e -> {
				if (e.getItem().toString().equals(PROTEO)) setLevel(2, true);
				else moduleInfo.values().stream().
					filter(entry -> entry.getKey().equals(e.getItem().toString())).
					forEach(entry -> setLevel(entry.getValue() - 1, false));
			});
			level.addChangeListener(e -> editionOfGenerativeLanguage((Integer) ((JSpinner) e.getSource()).getValue() != 0));
		}

		private void editionOfGenerativeLanguage(boolean editable) {
			generativeLabel.setEnabled(editable);
			plateRequired.setEnabled(editable);
			dslGeneratedName.setEnabled(editable);
		}

		private void setLevel(int level, boolean enabled) {
			this.level.setValue(level);
			this.level.setEnabled(enabled);
		}

		@Override
		public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
			dslBox.setEnabled(true);
			dictionaryBox.setEnabled(true);
			dslGeneratedName.setEnabled(true);
			plateRequired.setEnabled(true);
			if (project == null || !project.isInitialized()) level.setEnabled(false);
		}

		@Override
		public void frameworkUnselected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
			dslBox.setEnabled(false);
			dictionaryBox.setEnabled(false);
			dslGeneratedName.setEnabled(false);
			plateRequired.setEnabled(false);
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
			TaraSupportProvider.this.dsl = dslBox.getSelectedItem().toString();
			TaraSupportProvider.this.dictionary = dictionaryBox.getSelectedItem().toString();
			TaraSupportProvider.this.dslGenerate = level.getValue().equals(0) ? NONE : dslGeneratedName.getText();
			TaraSupportProvider.this.plateRequired = !level.getValue().equals(0) && plateRequired.isSelected();
			TaraSupportProvider.this.level = (int) level.getValue();

			TaraSupportProvider.this.selectedModuleParent = getSelectedParentModule();
			TaraSupportProvider.this.addSupport(module, rootModel);
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
				final TaraFacet facet = getTaraFacetByModule(candidate);
				if (facet == null) continue;
				TaraFacetConfiguration configuration = facet.getConfiguration();
				map.put(candidate, new AbstractMap.SimpleEntry<>(configuration.getGeneratedDslName(), configuration.getLevel()));
			}
			return map;

		}


	}
}
