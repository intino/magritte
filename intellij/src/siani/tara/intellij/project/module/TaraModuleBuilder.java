package siani.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import siani.tara.intellij.actions.TaraTemplates;
import siani.tara.intellij.actions.TaraTemplatesFactory;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.project.module.ui.TaraWizardStep;
import siani.tara.intellij.project.sdk.TaraJdk;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class TaraModuleBuilder extends JavaModuleBuilder {

	private static final Logger LOG = Logger.getInstance(TaraModuleBuilder.class.getName());
	private static final String MODEL_EXT = ".json";
	private static final String RES = "res";
	private static final String MODEL = "model";
	private static final String SRC = "src";
	private static final String GEN = "gen";
	private final List<Pair<String, String>> myModuleLibraries = new ArrayList<>();
	private String parentLanguage;
	private String locale;
	private String generatedLanguage;
	private boolean terminal = false;
	private File configFile;
	private String myCompilerOutputPath;
	private List<Pair<String, String>> mySourcePaths = new ArrayList<>();

	public TaraModuleBuilder() {
	}

	private static String getUrlByPath(final String path) {
		return VfsUtil.getUrlForLibraryRoot(new File(path));
	}

	public void setSourcePaths(final List<Pair<String, String>> sourcePaths) {
		if (sourcePaths != null)
			mySourcePaths.addAll(sourcePaths);
	}

	@Override
	public boolean isSuitableSdkType(SdkTypeId sdk) {
		return sdk == TaraJdk.getInstance();
	}

	@Override
	public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
		return new ModuleWizardStep[]{
			new TaraWizardStep(this, wizardContext, wizardContext.getProject())};
	}

	@Override
	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		final CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
		compilerModuleExtension.setExcludeOutput(true);
		rootModel.inheritSdk();
		ContentEntry contentEntry = doAddContentEntry(rootModel);
		addContentEntries(contentEntry);
		setOutputPath(compilerModuleExtension);
		updateLibraries(rootModel);
		addParentDependency(rootModel);
		persistTempConf();
		createFirstFile(rootModel);
	}

	private void createFirstFile(ModifiableRootModel rootModel) {
		if (!rootModel.getProject().isInitialized()) return;
		String module = rootModel.getModule().getName();
		String[] list = new String[]{"MODULE_NAME", module, "PARENT_MODULE_NAME", parentLanguage};
		String name = generatedLanguage.isEmpty() ? "Main" : generatedLanguage;
		String fileName = name + "." + TaraFileType.INSTANCE.getDefaultExtension();
		PsiDirectory directory = getModelSourceRoot(rootModel.getProject(), rootModel.getSourceRoots());
		if (directory == null) return;
		TaraTemplatesFactory.createFromTemplate(directory, name, fileName, TaraTemplates.getTemplate("MODEL"), false, list);
	}

	private PsiDirectory getModelSourceRoot(Project project, VirtualFile[] sourceRoots) {
		for (VirtualFile sourceRoot : sourceRoots)
			if (sourceRoot.getName().equals("model") && sourceRoot.isDirectory())
				return PsiManager.getInstance(project).findDirectory(sourceRoot);//TODO crear fichero cutremente.
		return null;
	}

	private void addParentDependency(ModifiableRootModel rootModel) {
		if (parentLanguage != null) {
			Module module = searchParentLanguageModule(rootModel.getProject());
			if (module != null) rootModel.addModuleOrderEntry(module);
		}
	}

	private void updateLibraries(ModifiableRootModel rootModel) {
		LibraryTable libraryTable = rootModel.getModuleLibraryTable();
		for (Pair<String, String> libInfo : myModuleLibraries) {
			final String moduleLibraryPath = libInfo.first;
			final String sourceLibraryPath = libInfo.second;
			Library library = libraryTable.createLibrary();
			Library.ModifiableModel modifiableModel = library.getModifiableModel();
			modifiableModel.addRoot(getUrlByPath(moduleLibraryPath), OrderRootType.CLASSES);
			if (sourceLibraryPath != null)
				modifiableModel.addRoot(getUrlByPath(sourceLibraryPath), OrderRootType.SOURCES);
			modifiableModel.commit();
		}
	}

	private void setOutputPath(CompilerModuleExtension compilerModuleExtension) {
		if (myCompilerOutputPath != null) {
			String canonicalPath;
			try {
				canonicalPath = FileUtil.resolveShortWindowsName(myCompilerOutputPath);
			} catch (IOException e) {
				canonicalPath = myCompilerOutputPath;
				LOG.error(e.getMessage(), e);
			}
			compilerModuleExtension.setCompilerOutputPath(VfsUtil.pathToUrl(FileUtil.toSystemIndependentName(canonicalPath)));
		} else compilerModuleExtension.inheritCompilerOutputPath(true);
	}

	private void addContentEntries(ContentEntry contentEntry) {
		if (contentEntry != null) {
			addSrcIfNotExists(getContentEntryPath());
			mySourcePaths.add(Pair.create(getContentEntryPath() + separator + MODEL, ""));
			String parentPath = "";
			if (mySourcePaths != null) {
				parentPath = createSourcePaths(contentEntry, parentPath);
				createResources(contentEntry, parentPath);
				createGen(contentEntry);
				createConfigDir(contentEntry);
			}
		}
	}

	private String createSourcePaths(ContentEntry contentEntry, String parentPath) {
		for (final Pair<String, String> sourcePath : mySourcePaths) {
			new File(sourcePath.first).mkdirs();
			final VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(sourcePath.first));
			if (sourceRoot != null) {
				parentPath = sourceRoot.getParent().getPath();
				contentEntry.addSourceFolder(sourceRoot, false, sourcePath.second);
			}
		}
		return parentPath;
	}

	private void createConfigDir(ContentEntry contentEntry) {
		try {
			String modulePath = contentEntry.getFile().getPath();
			VfsUtil.createDirectories(modulePath + separator + ".config");
			configFile = new File(modulePath + separator + ".config", "tara.conf");
			configFile.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void addSrcIfNotExists(String contentEntryPath) {
		for (Pair<String, String> mySourcePath : mySourcePaths)
			if (mySourcePath.first.endsWith(separator + SRC))
				return;
		mySourcePaths.add(Pair.create(contentEntryPath + separator + SRC, ""));
	}

	@Nullable
	private Module searchParentLanguageModule(Project project) {
		ModuleManager instance = ModuleManager.getInstance(project);
		for (Module module : instance.getModules())
			if (ModuleConfiguration.getInstance(module).getGeneratedModelName().equals(parentLanguage))
				return module;
		return null;
	}

	private void persistTempConf() {
		try {
			FileWriter writer = new FileWriter(configFile);
			writer.write(((parentLanguage != null) ? parentLanguage : "null") + "\n");
			writer.write(locale + "\n");
			writer.write(generatedLanguage + "\n");
			writer.write(terminal + "\n");
			writer.close();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private File getModelOfParentLanguage(Project project) {
		Module module = searchParentLanguageModule(project);
		if (module != null)
			return new File(TaraLanguage.MODELS_PATH + parentLanguage + MODEL_EXT);
		else {
			Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
			if (projectSdk != null && projectSdk.getSdkType().equals(TaraJdk.getInstance()))
				return new File(projectSdk.getHomePath() + File.separator + TaraLanguage.DSL + File.separator + parentLanguage + MODEL_EXT);
			return null;
		}
	}

	private void createGen(ContentEntry contentEntry) {
		Pair<String, String> resPath = Pair.create(getContentEntryPath() + separator + GEN, "");
		mySourcePaths.add(resPath);
		new File(resPath.first).mkdirs();
		final VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(resPath.first));
		if (sourceRoot != null) {
			JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", true);
			contentEntry.addSourceFolder(sourceRoot, JavaSourceRootType.SOURCE, properties);
		}
	}

	public void setParentLanguage(String metamodel) {
		this.parentLanguage = metamodel;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setGeneratedLanguage(String generatedLanguage) {
		this.generatedLanguage = generatedLanguage;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}

	private void createResources(ContentEntry contentEntry, String parentPath) {
		try {
			Pair<String, String> resPath = Pair.create(getContentEntryPath() + separator + RES, "");
			mySourcePaths.add(resPath);
			new File(resPath.first).mkdirs();
			final VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(resPath.first));
			if (sourceRoot != null) {
				parentPath = sourceRoot.getParent().getPath();
				contentEntry.addSourceFolder(sourceRoot, JavaResourceRootType.RESOURCE);
			}
			VfsUtil.createDirectories(parentPath + separator + RES);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public com.intellij.openapi.module.ModuleType getModuleType() {
		return TaraModuleType.getInstance();
	}

	@Override
	public Icon getNodeIcon() {
		return TaraIcons.getIcon(TaraIcons.ICON_13);
	}

	@Override
	public String getName() {
		return "Tara";
	}

	@Override
	public String getBuilderId() {
		return getClass().getName();
	}

	@Override
	public String getPresentableName() {
		return "Tara";
	}

	@Override
	public String getDescription() {
		return "Tara modules are used for developing <b>JVM-based</b> applications with Tara model descriptions. " +
			"You can create either a blank Tara module or a module based on a <b>Tara module</b>.";
	}
}
