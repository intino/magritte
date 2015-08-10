package tara.intellij.framework;

import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import static java.io.File.separator;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final String FRAMEWORK = "framework";
	private static final String PROTEO_LIB = "Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";
	private static final String TARA_PREFIX = "Tara -> ";
	private static final String DSL = "dsl";
	private static final String MODEL = "model";

	boolean fromDsl = false;
	String dsl;
	File referenceModel;
	String dictionary;
	String dslGenerate;
	boolean plateRequired;
	boolean dynamicLoad;
	int level;
	Map<String, AbstractMap.SimpleEntry<Integer, File>> languages = new HashMap<>();
	Module selectedModuleParent = null;

	public TaraSupportProvider() {
		languages.put(TaraLanguage.PROTEO, new AbstractMap.SimpleEntry<>(2, new File(PROTEO_DIRECTORY, PROTEO_LIB)));
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

	void addSupport(final Module module, final ModifiableRootModel rootModel) {
		createDSL(rootModel.getProject().getBaseDir());
		final SourceFolder modelSourceRoot = createModelSourceRoot(rootModel.getContentEntries()[0]);
		if (!fromDsl) copyModel(collectModelFiles(findModelPath(referenceModel)), modelSourceRoot);
		createResources(rootModel.getContentEntries()[0]);
		createGenSourceRoot(rootModel.getContentEntries()[0]);

		updateFacetConfiguration(module);
		updateDependencies(rootModel);

	}

	private void updateDependencies(ModifiableRootModel rootModel) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			setFrameworkDependency(rootModel, rootModel.getProject().getBaseDir());
		});
	}

	private void setFrameworkDependency(ModifiableRootModel rootModel, VirtualFile projectDir) {
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

	private void updateFacetConfiguration(Module module) {
		FacetType<TaraFacet, TaraFacetConfiguration> facetType = TaraFacet.getFacetType();
		TaraFacet TaraFacet = FacetManager.getInstance(module).addFacet(facetType, facetType.getDefaultFacetName(), null);
		final TaraFacetConfiguration facetConfiguration = TaraFacet.getConfiguration();
		facetConfiguration.setDsl(dsl);
		facetConfiguration.setDictionary(dictionary);
		facetConfiguration.setGeneratedDslName(dslGenerate);
		facetConfiguration.setDynamicLoad(dynamicLoad);
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

	private VirtualFile createDSL(VirtualFile projectDir) {
		try {
			final VirtualFile dsl = projectDir.findChild(DSL);
			return dsl != null ? dsl : projectDir.createChildDirectory(null, DSL);
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

	private SourceFolder createModelSourceRoot(ContentEntry contentEntry) {
		try {
			if (contentEntry.getFile() == null) return null;
			String modulePath = contentEntry.getFile().getPath();
			VirtualFile templates = VfsUtil.createDirectories(modulePath + separator + MODEL);
			if (templates != null) {
				JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", false);
				return contentEntry.addSourceFolder(templates, JavaSourceRootType.SOURCE, properties);
			}
		} catch (IOException ignored) {
		}
		return null;
	}


	private List<File> collectModelFiles(File modelPath) {
		List<File> files = new ArrayList<>();
		FileSystemUtils.getAllFiles(modelPath, files, (dir, name) -> name.endsWith("." + TaraFileType.INSTANCE.getDefaultExtension()));
		return files;
	}

	private File findModelPath(File modulePath) {
		return new File(modulePath, MODEL);
	}

	private void copyModel(List<File> model, SourceFolder folder) {
		File newModelPath = new File(folder.getFile().getPath(), MODEL);
		for (File file : model) copyModel(file, newModelPath);
	}

	private void copyModel(File file, File modelPath) {
		FileSystemUtils.copyFile(file.getPath(), new File(modelPath, file.getName()).getPath());
	}

	@NotNull
	@Override
	public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
		final TaraSupportConfigurable taraSupportConfigurable = new TaraSupportConfigurable(this, model);
		model.addFrameworkListener(taraSupportConfigurable);
		return taraSupportConfigurable;
	}

}
