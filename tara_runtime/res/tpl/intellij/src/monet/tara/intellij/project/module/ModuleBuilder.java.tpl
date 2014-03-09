package monet.::projectName::.intellij.project.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
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
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder extends com.intellij.ide.util.projectWizard.ModuleBuilder implements SourcePathsBuilder {
	private final List<Pair<String, String>> myModuleLibraries = new ArrayList<Pair<String, String>>();
	private String myCompilerOutputPath;
	private List<Pair<String, String>> mySourcePaths;

	private static String getUrlByPath(final String path) {
		return VfsUtil.getUrlForLibraryRoot(new File(path));
	}

	public List<Pair<String, String>> getSourcePaths() {
		if (mySourcePaths == null) {
			final List<Pair<String, String>> paths = new ArrayList<>();
			@NonNls final String path = getContentEntryPath() + File.separator + "src";
			new File(path).mkdirs();
			paths.add(Pair.create(path, ""));
			return paths;
		}
		return mySourcePaths;
	}

	public void setSourcePaths(final List<Pair<String, String>> sourcePaths) {
		mySourcePaths = sourcePaths != null ? new ArrayList<>(sourcePaths) : null;
	}

	public final void setCompilerOutputPath(String compilerOutputPath) {
		myCompilerOutputPath = acceptParameter(compilerOutputPath);
	}

	public void addSourcePath(final Pair<String, String> sourcePathInfo) {
		if (mySourcePaths == null) mySourcePaths = new ArrayList<>();
		mySourcePaths.add(sourcePathInfo);
	}

	@Override
	public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
		return new ModuleWizardStep[]{};
	}

	@Override
	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		final CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
		compilerModuleExtension.setExcludeOutput(true);
		rootModel.inheritSdk();
		ContentEntry contentEntry = doAddContentEntry(rootModel);
		if (contentEntry != null) {
			final List<Pair<String, String>> sourcePaths = getSourcePaths();

			if (sourcePaths != null) {
				for (final Pair<String, String> sourcePath : sourcePaths) {
					String first = sourcePath.first;
					new File(first).mkdirs();
					final VirtualFile sourceRoot = LocalFileSystem.getInstance()
						.refreshAndFindFileByPath(FileUtil.toSystemIndependentName(first));
					if (sourceRoot != null) {
						contentEntry.addSourceFolder(sourceRoot, false, sourcePath.second);
					}
				}
			}
		}
		if (myCompilerOutputPath != null) {
			// should set only absolute paths
			String canonicalPath;
			try {
				canonicalPath = FileUtil.resolveShortWindowsName(myCompilerOutputPath);
			} catch (IOException e) {
				canonicalPath = myCompilerOutputPath;
			}
			compilerModuleExtension
				.setCompilerOutputPath(VfsUtil.pathToUrl(FileUtil.toSystemIndependentName(canonicalPath)));
		} else {
			compilerModuleExtension.inheritCompilerOutputPath(true);
		}
		LibraryTable libraryTable = rootModel.getModuleLibraryTable();
		for (Pair<String, String> libInfo : myModuleLibraries) {
			final String moduleLibraryPath = libInfo.first;
			final String sourceLibraryPath = libInfo.second;
			Library library = libraryTable.createLibrary();
			Library.ModifiableModel modifiableModel = library.getModifiableModel();
			modifiableModel.addRoot(getUrlByPath(moduleLibraryPath), OrderRootType.CLASSES);
			if (sourceLibraryPath != null) {
				modifiableModel.addRoot(getUrlByPath(sourceLibraryPath), OrderRootType.SOURCES);
			}
			modifiableModel.commit();
		}

	}

	public void addModuleLibrary(String moduleLibraryPath, String sourcePath) {
		myModuleLibraries.add(Pair.create(moduleLibraryPath, sourcePath));
	}

	@Override
	public com.intellij.openapi.module.ModuleType getModuleType() {
		return ModuleType.getInstance();
	}

	@Override
	public Icon getNodeIcon() {
		return ::projectProperName::Icons.ICON_13;
	}

	@Override
	public String getName() {
		return "::projectProperName::";
	}

	@Override
	public String getBuilderId() {
		return getClass().getName();
	}

	@Override
	public String getPresentableName() {
		return "::projectProperName::";
	}

	@Nullable
	@Override
	public Module commitModule(@NotNull Project project, @Nullable ModifiableModuleModel model) {
		return super.commitModule(project, model);

	}
}
