package monet.::projectName::.intellij.project.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.diagnostic.Logger;
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
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder extends JavaModuleBuilder {
	private static final Logger LOG = Logger.getInstance(ModuleBuilder.class.getName());
	private final List<Pair<String, String>> myModuleLibraries = new ArrayList<>();
	private String myCompilerOutputPath;
	private List<Pair<String, String>> mySourcePaths;

	private static String getUrlByPath(final String path) {
		return VfsUtil.getUrlForLibraryRoot(new File(path));
	}

	public void setSourcePaths(final List<Pair<String, String>> sourcePaths) {
		mySourcePaths = sourcePaths != null ? new ArrayList<>(sourcePaths) \: null;
	}

	public void addSourcePath(final Pair<String, String> sourcePathInfo) {
		if (mySourcePaths == null) mySourcePaths = new ArrayList<>();
		mySourcePaths.add(sourcePathInfo);
	}

	\@Override
	public ModuleWizardStep[] createWizardSteps(\@NotNull WizardContext wizardContext, \@NotNull ModulesProvider modulesProvider) {
		return new ModuleWizardStep[]{};
	}

	\@Override
	public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
		final CompilerModuleExtension compilerModuleExtension = rootModel.getModuleExtension(CompilerModuleExtension.class);
		compilerModuleExtension.setExcludeOutput(true);
		rootModel.inheritSdk();
		ContentEntry contentEntry = doAddContentEntry(rootModel);
		if (contentEntry != null) {
			final List<Pair<String, String>> sourcePaths = getSourcePaths();

			if (sourcePaths != null) {
				for (final Pair<String, String> sourcePath \: sourcePaths) {
					String first = sourcePath.first;
					new File(first).mkdirs();
					final VirtualFile sourceRoot = LocalFileSystem.getInstance()
						.refreshAndFindFileByPath(FileUtil.toSystemIndependentName(first));
					if (sourceRoot != null) {
						contentEntry.addSourceFolder(sourceRoot, false, sourcePath.second);
						try {
							VfsUtil.createDirectories(sourceRoot.getParent().getPath() + File.separator + "res");
							VfsUtil.createDirectories(sourceRoot.getParent().getPath() + File.separator + "res" + File.separator + "tpl");
							VfsUtil.createDirectories(sourceRoot.getParent().getPath() + File.separator + "res" + File.separator + "logos");
//							File logo = new File(logos.getPath() + File.separator + rootModel.getProject().getName() + ".png");
//							copyFile(new File(this.getClass().getResource(File.separator + "logos" + File.separator + "logo.png").getPath()), logo);
						} catch (IOException e) {
							LOG.error(e.getMessage());
						}
					}
				}
			}
		}
		if (myCompilerOutputPath != null) {
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
		for (Pair<String, String> libInfo \: myModuleLibraries) {
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

	\@Override
	public com.intellij.openapi.module.ModuleType getModuleType() {
		return ModuleType.getInstance();
	}

	\@Override
	public Icon getNodeIcon() {
		return ::projectProperName::Icons.ICON_13;
	}

	\@Override
	public String getName() {
		return "::projectProperName::";
	}

	\@Override
	public String getBuilderId() {
		return getClass().getName();
	}

	\@Override
	public String getPresentableName() {
		return "::projectProperName::";
	}

	private Boolean copyFile(File source, File destination) {
		new File(destination.getParentFile().getAbsolutePath()).mkdirs();
		try {
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			while (len > 0)
				out.write(buf, 0, len);
			in.close();
			out.close();
		} catch (IOException ex) {
			return false;
		}

		return true;
	}
}
