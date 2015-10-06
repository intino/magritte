package tara.intellij.framework;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.io.File.separator;

public class FrameworkDependencyCreator {
	private static final Logger LOG = Logger.getInstance(FrameworkDependencyCreator.class.getName());

	private static final String FRAMEWORK = "framework";
	private static final String TARA_PREFIX = "Tara -> ";
	private static final String DSL = "dsl";

	private final Map<String, File> languages;
	private final String dsl;
	private final Module selectedModuleParent;

	public FrameworkDependencyCreator(Map<String, File> languages, String dsl, Module selectedModuleParent) {
		this.languages = languages;
		this.dsl = dsl;
		this.selectedModuleParent = selectedModuleParent;
	}

	void setFrameworkDependency(ModifiableRootModel rootModel) {
		if (languages.containsKey(this.dsl)) {
			final File file = importDslAndFramework(rootModel.getProject().getBaseDir());
			if (file != null) addDslLibToProject(rootModel);
		} else addModuleDependency(rootModel);
	}

	private File importDslAndFramework(VirtualFile projectDirectory) {
		final File file = languages.get(this.dsl);
		if (!file.exists()) return null;
		File destiny;
		try {
			if (isJar(file)) {
				destiny = new File(projectDirectory.getPath() + separator + FRAMEWORK + separator + dsl, file.getName());
				FileSystemUtils.copyFile(file.getPath(), destiny.getPath());
			} else {
				ZipUtil.unzip(null, new File(projectDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(projectDirectory.getPath() + separator + dsl);
			}
			if (!TaraLanguage.PROTEO.equals(dsl)) reload(new File(projectDirectory.getPath(), DSL).getPath());
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private void addDslLibToProject(ModifiableRootModel rootModel) {
		final Library library = FrameworkSetupHelper.addProjectLibrary(rootModel.getModule(), TARA_PREFIX + dsl, getFrameworkDirectory(rootModel.getProject().getBaseDir()), VirtualFile.EMPTY_ARRAY);
		if (library != null) rootModel.addLibraryEntry(library);
	}

	private void addModuleDependency(ModifiableRootModel rootModel) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			if (!ModuleRootManager.getInstance(rootModel.getModule()).isDependsOn(selectedModuleParent)) {
				final ModuleOrderEntry moduleOrderEntry = rootModel.addModuleOrderEntry(selectedModuleParent);
				moduleOrderEntry.setExported(true);
			}
			final LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(rootModel.getModule().getProject());
			for (Library library : libraryTable.getLibraries())
				if (library.getName() != null && library.getName().startsWith(TARA_PREFIX)) {
					rootModel.addLibraryEntry(library);
					return;
				}
		});
	}

	private File getFrameworkDirectory(VirtualFile baseDir) {
		final File framework = new File(baseDir.getPath() + separator + FRAMEWORK, dsl);
		framework.mkdir();
		return framework;
	}


	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String languagesPath) {
		File reload = new File(languagesPath, dsl + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
