package siani.tara.intellij.framework;

import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModelListener;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

import static java.io.File.separator;
import static siani.tara.intellij.project.facet.TaraFacet.getTaraFacetByModule;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final String PROTEO = "Proteo";
	private String dsl;
	private String dictionary;
	private String dslGenerate;
	private boolean plateRequired;
	private int level;

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
		mavenize(module, rootModel);
		createModel(rootModel.getContentEntries()[0]);
		createResources(rootModel.getContentEntries()[0]);
		createGen(rootModel.getContentEntries()[0]);
		updateFacetConfiguration(module);
	}

	private void mavenize(Module module, ModifiableRootModel rootModel) {
		MavenManager mavenizer = new MavenManager(dsl, module);
		if (rootModel.getProject().isInitialized()) mavenizer.mavenize();
		else startWithMaven(mavenizer, module.getProject());
	}

	private void startWithMaven(final MavenManager mavenizer, Project project) {
		StartupManager.getInstance(project).registerPostStartupActivity(mavenizer::mavenize);
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

	private void createGen(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot = file.createChildDirectory(null, "gen");
			JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", true);
			contentEntry.addSourceFolder(sourceRoot, JavaSourceRootType.SOURCE, properties);
		} catch (IOException ignored) {
		}
	}


	private void createModel(ContentEntry contentEntry) {
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
			dslBox.addItem(PROTEO);
			level.setValue(2);
			addModuleDsls();
			addDictionaries();
			addListeners();
			return myMainPanel;
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
				if (e.getItem().toString().equals(PROTEO))
					setLevel(2, true);
				else moduleInfo.values().stream().
					filter(entry -> entry.getKey().equals(e.getItem().toString())).
					forEach(entry -> setLevel(entry.getValue() - 1, false));
			});
			level.addChangeListener(e -> visibilityOfGenerativeLanguage((Integer) ((JSpinner) e.getSource()).getValue() != 0));
		}

		private void visibilityOfGenerativeLanguage(boolean visibility) {
			generativeLabel.setEnabled(visibility);
			plateRequired.setEnabled(visibility);
			dslGeneratedName.setEnabled(visibility);
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
		}

		@Override
		public void frameworkUnselected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
			dslBox.setEnabled(false);
			dictionaryBox.setEnabled(false);
			dslGeneratedName.setEnabled(false);
			plateRequired.setEnabled(false);
		}

		@Override
		public void wizardStepUpdated() {
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
			TaraSupportProvider.this.addSupport(module, rootModel);
		}

		private Module[] getParentModulesCandidates(Project project) {
			if (project == null || !project.isInitialized()) return new Module[0];
			List<Module> moduleCandidates = new ArrayList<>();
			for (Module aModule : ModuleManager.getInstance(project).getModules()) {
				TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
				if (taraFacet == null) continue;
				if (!taraFacet.getConfiguration().isM0()) moduleCandidates.add(aModule);
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
