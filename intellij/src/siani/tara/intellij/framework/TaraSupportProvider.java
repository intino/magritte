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
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.vcsUtil.VcsUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.siani.itrules.model.Frame;
import siani.tara.intellij.framework.maven.ModulePomTemplate;
import siani.tara.intellij.framework.maven.ProjectPomTemplate;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.io.File.separator;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final String PROTEO = "Proteo";
	private static final String POM_XML = "pom.xml";

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

	private void addSupport(final Module module,
	                        final ModifiableRootModel rootModel,
	                        String dsl, String dictionary, String dslGenerate) {
		createModelDirectory(rootModel.getContentEntries()[0]);
		if (rootModel.getProject().isInitialized()) addMavenToProject(module);
		else startWithMaven(module);
		FacetType<TaraFacet, TaraFacetConfiguration> facetType = TaraFacet.getFacetType();
		TaraFacet TaraFacet = FacetManager.getInstance(module).addFacet(facetType, facetType.getDefaultFacetName(), null);
		final TaraFacetConfiguration facetConfiguration = TaraFacet.getConfiguration();
		setParent(facetConfiguration, dsl, module);
		createResources(rootModel.getContentEntries()[0]);
		createGen(rootModel.getContentEntries()[0]);
		facetConfiguration.setDsl(dsl);
		facetConfiguration.setDictionary(dictionary);
		facetConfiguration.setGeneratedDslName(dslGenerate);
	}

	private void startWithMaven(final Module module) {
		StartupManager.getInstance(module.getProject()).registerPostStartupActivity(new Runnable() {
			@Override
			public void run() {
				addMavenToProject(module);
			}
		});
	}

	private void setParent(TaraFacetConfiguration facetConfiguration, String name, Module module) {
		Module parentModule = searchParent(module.getProject(), name);
		updateDependencies(facetConfiguration, name.equals(PROTEO) ? null : parentModule, module);
	}

	private void updateDependencies(TaraFacetConfiguration configuration, final Module parent, Module module) {
		removeParentDependency(configuration, module);
		if (parent != null) addModelDependency(parent, module);
	}

	private void addModelDependency(final Module parent, final Module module) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				if (!ModuleRootManager.getInstance(module).isDependsOn(parent)) {
					ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
					modifiableModel.addModuleOrderEntry(parent);
					modifiableModel.commit();
				}
			}
		});
	}

	private void removeParentDependency(final TaraFacetConfiguration configuration, final Module module) {
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				String dsl = configuration.getDsl();
				if (dsl.isEmpty()) return;
				Module parentModule = searchParent(module.getProject(), dsl);
				if (parentModule == null) return;
				ModifiableRootModel modifiableModel = ModuleRootManager.getInstance(module).getModifiableModel();
				OrderEntry[] orderEntries = modifiableModel.getOrderEntries();
				OrderEntry orderEntry = findOrderEntry(orderEntries, parentModule);
				if (orderEntry != null)
					modifiableModel.removeOrderEntry(orderEntry);
				modifiableModel.commit();
			}
		});
	}

	private Module searchParent(Project project, String parentName) {
		for (Module candidate : getParentModulesCandidates(project)) {
			TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(candidate).getConfiguration();
			if (configuration.getGeneratedDslName().equals(parentName))
				return candidate;
		}
		return null;
	}

	private OrderEntry findOrderEntry(OrderEntry[] orderEntries, Module parentModule) {
		for (OrderEntry entry : orderEntries)
			if (entry instanceof ModuleOrderEntry && parentModule.equals(((ModuleOrderEntry) entry).getModule()))
				return entry;
		return null;
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

	private void addMavenToProject(final Module module) {
		List<VirtualFile> pomFiles = createPoms(module);
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		manager.addManagedFilesOrUnignore(pomFiles);
		manager.importProjects();
		manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles();
	}

	private List<VirtualFile> createPoms(Module module) {
		List<VirtualFile> files = new ArrayList<>();
		files.addAll(isProjectModule(module) ? projectModulePom(module) : modulePom(module));
		return files;
	}

	private Collection<VirtualFile> modulePom(final Module module) {
		final PsiFile[] files = new PsiFile[2];
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				PsiDirectory root = getModuleRoot(module);
				files[0] = findPom(root);
				if (files[0] == null) createPoms(root);
				else updateModulePom(files[0]);
			}

			private void createPoms(PsiDirectory root) {
				files[0] = root.createFile("pom.xml");
				createPom(files[0].getVirtualFile().getPath(), ModulePomTemplate.create().format(createModuleFrame(module)));
				if (!getProjectPom(module).exists())
					files[1] = createProjectPom(module);
			}
		});
		return toVirtual(files);
	}

	private Collection<VirtualFile> toVirtual(PsiFile[] files) {
		List<VirtualFile> vFiles = new ArrayList<>();
		for (PsiFile file : files)
			if (file != null) vFiles.add(file.getVirtualFile());
		return vFiles;
	}

	private PsiFile createProjectPom(Module module) {
		VirtualFile pom = projectPom(module);
		return PsiManager.getInstance(module.getProject()).findFile(pom);
	}

	@NotNull
	private File getProjectPom(Module module) {
		return new File(module.getProject().getBaseDir().getPath() + separator + POM_XML);
	}

	private Collection<VirtualFile> projectModulePom(final Module module) {
		final PsiFile[] file = new PsiFile[1];
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				PsiDirectory root = getModuleRoot(module);
				file[0] = findPom(root);
				if (file[0] == null) {
					file[0] = root.createFile("pom.xml");
					createPom(file[0].getVirtualFile().getPath(), ModulePomTemplate.create().format(createModuleFrame(module)));
				} else updateModulePom(file[0]);
			}
		});
		return new ArrayList<VirtualFile>() {{
			add(file[0].getVirtualFile());
		}};
	}


	private void updateModulePom(PsiFile psiFile) {
		PomHelper helper = new PomHelper(psiFile.getVirtualFile().getPath());
		if (!helper.hasTaraDependency()) helper.addTaraDependency();
	}

	private boolean isProjectModule(Module module) {
		return module.getProject().getBaseDir().getPath().equals(new File(module.getModuleFilePath()).getParent());
	}

	private PsiDirectory getModuleRoot(Module module) {
		VirtualFile moduleFile = module.getModuleFile();
		if (moduleFile != null)
			return PsiManager.getInstance(module.getProject()).findDirectory(moduleFile.getParent());
		else {
			VirtualFile baseDir = module.getProject().getBaseDir();
			return PsiManager.getInstance(module.getProject()).findDirectory(baseDir).findSubdirectory(module.getName());
		}
	}

	private PsiFile findPom(PsiDirectory root) {
		for (PsiElement element : root.getChildren())
			if (element instanceof PsiFile && "pom.xml".equals(((PsiFile) element).getVirtualFile().getName()))
				return (PsiFile) element;
		return null;
	}

	private File createPom(String path, String text) {
		try {
			File file = new File(path);
			FileWriter writer = new FileWriter(file);
			writer.write(text);
			writer.close();
			return file;
		} catch (IOException ignored) {
		}
		return null;
	}

	private VirtualFile projectPom(final Module module) {
		final PsiFile[] file = new PsiFile[1];
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				File pomFile = getProjectPom(module);
				VirtualFile directory = VcsUtil.getVcsRootFor(module.getProject(), VcsUtil.getFilePath(pomFile));
				PsiDirectory root = PsiManager.getInstance(module.getProject()).findDirectory(directory);
				file[0] = findPom(root);
				if (file[0] == null) file[0] = root.createFile("pom.xml");
				createPom(file[0].getVirtualFile().getPath(), ProjectPomTemplate.create().format(createProjectFrame(module)));
			}
		});
		return file[0].getVirtualFile();
	}


	private Frame createModuleFrame(Module module) {
		Frame frame = new Frame(null).addTypes("pom");
		frame.addFrame("project", module.getProject().getName());
		frame.addFrame("module", module.getName());
		return frame;
	}

	private Frame createProjectFrame(Module module) {
		Project project = module.getProject();
		Frame frame = new Frame(null).addTypes("pom");
		frame.addFrame("project", project.getName());
		return frame;
	}

	private void createModelDirectory(ContentEntry contentEntry) {
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

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.getTaraFacetByModule(aModule);
			if (taraFacet == null) continue;
			if (!taraFacet.getConfiguration().isCase()) moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	@NotNull
	@Override
	public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
		return new TaraSupportConfigurable(model);
	}

	private class TaraSupportConfigurable extends FrameworkSupportInModuleConfigurable implements FrameworkSupportModelListener {
		private final Project project;

		private JPanel myMainPanel;
		private JComboBox<String> dictionaryBox;
		private JComboBox<String> dslBox;
		private JCheckBox generateDslCheck;
		private JTextField dslGeneratedName;

		private Module[] candidates;


		private TaraSupportConfigurable(FrameworkSupportModel model) {
			model.addFrameworkListener(this);
			this.project = model.getProject();
		}

		@Nullable
		@Override
		public JComponent createComponent() {
			dslBox.addItem(PROTEO);
			candidates = getParentModulesCandidates(project);
			addModuleDsls();
			addDictionaries();
			addCheckListener();
			return myMainPanel;
		}

		private void addModuleDsls() {
			for (Module candidate : candidates) {
				if (TaraFacet.getTaraFacetByModule(candidate) == null) continue;
				TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(candidate).getConfiguration();
				if (configuration.isCase()) continue;
				dslBox.addItem(configuration.getGeneratedDslName());
			}
			dslBox.setSelectedIndex(0);
		}

		private void addDictionaries() {
			dictionaryBox.addItem("English");
			dictionaryBox.addItem("Español");
		}

		private void addCheckListener() {
			generateDslCheck.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					dslGeneratedName.setEnabled(((JCheckBox) e.getSource()).isSelected());
				}
			});
			generateDslCheck.setSelected(false);
			dslGeneratedName.setEnabled(false);
		}

		@Override
		public void frameworkSelected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
			dslBox.setEnabled(true);
			dictionaryBox.setEnabled(true);
			generateDslCheck.setEnabled(true);
			if (generateDslCheck.isSelected()) dslGeneratedName.setEnabled(true);
		}

		@Override
		public void frameworkUnselected(@NotNull FrameworkSupportProvider frameworkSupportProvider) {
			dslBox.setEnabled(false);
			dictionaryBox.setEnabled(false);
			generateDslCheck.setEnabled(false);
			dslGeneratedName.setEnabled(false);
		}

		@Override
		public void wizardStepUpdated() {
		}

		@Override
		public void addSupport(@NotNull Module module,
		                       @NotNull ModifiableRootModel rootModel,
		                       @NotNull ModifiableModelsProvider modifiableModelsProvider) {
			TaraSupportProvider.this.addSupport(module, rootModel,
				dslBox.getSelectedItem().toString(), dictionaryBox.getSelectedItem().toString(), getDslGenerate());
		}

		private String getDslGenerate() {
			return generateDslCheck.isSelected() ? dslGeneratedName.getText() : "";
		}

	}
}
