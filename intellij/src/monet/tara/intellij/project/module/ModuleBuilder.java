package monet.tara.intellij.project.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.options.ConfigurationException;
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
import monet.tara.intellij.metamodel.TaraIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder extends JavaModuleBuilder {
	private final List<Pair<String, String>> myModuleLibraries = new ArrayList<>();
	private String myCompilerOutputPath;
	private List<Pair<String, String>> mySourcePaths;

	private static String getUrlByPath(final String path) {
		return VfsUtil.getUrlForLibraryRoot(new File(path));
	}

	public void setSourcePaths(final List<Pair<String, String>> sourcePaths) {
		mySourcePaths = sourcePaths != null ? new ArrayList<>(sourcePaths) : null;
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
						try {
							VfsUtil.createDirectories(sourceRoot.getParent().getPath() + "res/tpl");
							VfsUtil.createDirectories(sourceRoot.getParent().getPath() + "res/logos");
						} catch (IOException e) {
							e.printStackTrace();
						}
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


	@Override
	public com.intellij.openapi.module.ModuleType getModuleType() {
		return ModuleType.getInstance();
	}

	@Override
	public Icon getNodeIcon() {
		return TaraIcons.ICON_13;
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
}
