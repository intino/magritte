package siani.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.project.sdk.TaraJdk;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class TaraModuleBuilder extends JavaModuleBuilder {
	public static final String RES = "res";
	public static final String ICONS = "icons";
	public static final String GEN = "gen";
	public static final String MODEL = "model";
	private static final Logger LOG = Logger.getInstance(TaraModuleBuilder.class.getName());
	private static final String ITRULES = "itrules";
	private static final String SRC = "src";
	private final List<Pair<String, String>> myModuleLibraries = new ArrayList<>();
	private Module parentModule;
	private boolean system = false;
	private File configFile;
	private String myCompilerOutputPath;
	private List<Pair<String, String>> mySourcePaths = new ArrayList<>();

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
		return (new ModuleWizardStep[]{
			new TaraWizardStep(this, wizardContext, wizardContext.getProject())});
	}

	@Override
	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		final CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
		compilerModuleExtension.setExcludeOutput(true);
		rootModel.inheritSdk();
		ContentEntry contentEntry = doAddContentEntry(rootModel);
		if (contentEntry != null) {
			addSrcIfNotExists(getContentEntryPath());
			mySourcePaths.add(Pair.create(getContentEntryPath() + separator + GEN, ""));
			mySourcePaths.add(Pair.create(getContentEntryPath() + separator + MODEL, ""));
			String parentPath = "";
			if (mySourcePaths != null) {
				for (final Pair<String, String> sourcePath : mySourcePaths) {
					String first = sourcePath.first;
					new File(first).mkdirs();
					final VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(first));
					if (sourceRoot != null) {
						parentPath = sourceRoot.getParent().getPath();
						contentEntry.addSourceFolder(sourceRoot, false, sourcePath.second);
					}
				}
				createResources(parentPath);
			}
		}
		if (myCompilerOutputPath != null) {
			String canonicalPath;
			try {
				canonicalPath = FileUtil.resolveShortWindowsName(myCompilerOutputPath);
			} catch (IOException e) {
				canonicalPath = myCompilerOutputPath;
			}
			compilerModuleExtension.setCompilerOutputPath(VfsUtil.pathToUrl(FileUtil.toSystemIndependentName(canonicalPath)));
		} else compilerModuleExtension.inheritCompilerOutputPath(true);
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
		if (parentModule != null)
			rootModel.addModuleOrderEntry(parentModule);
		persistTempConf();
	}

	private void addSrcIfNotExists(String contentEntryPath) {
		for (Pair<String, String> mySourcePath : mySourcePaths)
			if (mySourcePath.first.endsWith(separator + "src"))
				return;
		mySourcePaths.add(Pair.create(contentEntryPath + separator + SRC, ""));
	}

	private void persistTempConf() {
		try {
			FileWriter writer = new FileWriter(configFile);
			writer.write(((parentModule != null) ? parentModule.getName() : "null") + "\n");
			writer.write(((parentModule != null) ? parentModule.getModuleFilePath() : "null") + "\n");
			writer.write(system + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setParentModule(Module module) {
		parentModule = module;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	private void createResources(String parentPath) {
		try {
			VfsUtil.createDirectories(parentPath + separator + RES);
			VfsUtil.createDirectories(parentPath + separator + RES + separator + ITRULES);
			VfsUtil.createDirectories(parentPath + separator + RES + separator + ICONS);
			VfsUtil.createDirectories(parentPath + separator + RES + separator + ICONS + separator + "definitions");
			VfsUtil.createDirectories(parentPath + separator + ".config");
			configFile = new File(parentPath + separator + ".config", "tara.conf");
			configFile.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage());
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
