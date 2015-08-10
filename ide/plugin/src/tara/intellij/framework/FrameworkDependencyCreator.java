package tara.intellij.framework;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.Map;

import static java.io.File.separator;

public class FrameworkDependencyCreator {

	private static final String FRAMEWORK = "framework";
	private static final String TARA_PREFIX = "Tara -> ";
	private static final String DSL = "dsl";

	private final Map<String, AbstractMap.SimpleEntry<Integer, File>> languages;
	private final String dsl;
	private final Module selectedModuleParent;

	public FrameworkDependencyCreator(Map<String, AbstractMap.SimpleEntry<Integer, File>> languages, String dsl, Module selectedModuleParent) {

		this.languages = languages;
		this.dsl = dsl;
		this.selectedModuleParent = selectedModuleParent;
	}


	void setFrameworkDependency(ModifiableRootModel rootModel, VirtualFile projectDir) {
		if (languages.containsKey(this.dsl)) {
			importDslAndFramework(projectDir);
			addDslLibToProject(rootModel);
		} else addModuleDependency(rootModel);
	}

	private File importDslAndFramework(VirtualFile projectDirectory) {
		final AbstractMap.SimpleEntry<Integer, File> entry = languages.get(this.dsl);
		final File file = entry.getValue();
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
			return null;
		}
	}

	private void addDslLibToProject(ModifiableRootModel rootModel) {
		final Library library = addProjectLibrary(rootModel.getModule(), TARA_PREFIX + dsl, getLibDirectory(rootModel.getProject().getBaseDir()), VirtualFile.EMPTY_ARRAY);
		rootModel.addLibraryEntry(library);
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

	private File getLibDirectory(VirtualFile baseDir) {
		final File framework = new File(baseDir.getPath() + separator + FRAMEWORK, dsl);
		framework.mkdir();
		return framework;
	}

	private static Library addProjectLibrary(final Module module, final String name, final File jarsDirectory, final VirtualFile[] sources) {
		return new WriteAction<Library>() {
			protected void run(@NotNull final Result<Library> result) throws MalformedURLException {
				final LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(module.getProject());
				Library library = libraryTable.getLibraryByName(name);
				if (library == null) {
					library = libraryTable.createLibrary(name);
					final Library.ModifiableModel model = library.getModifiableModel();
					final VirtualFile vFile = VfsUtil.findFileByURL(jarsDirectory.toURI().toURL());
					if (vFile == null) return;
					vFile.refresh(true, true);
					model.addJarDirectory(vFile, false);
					for (VirtualFile sourceRoot : sources) model.addRoot(sourceRoot, OrderRootType.SOURCES);
					model.commit();
				}
				result.setResult(library);
			}
		}.execute().getResultObject();
	}

	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String languagesPath) {
		File reload = new File(languagesPath, dsl + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
