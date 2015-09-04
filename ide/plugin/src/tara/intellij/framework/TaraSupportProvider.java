package tara.intellij.framework;

import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import tara.intellij.TaraRuntimeException;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final Logger LOG = Logger.getInstance(TaraSupportProvider.class.getName());

	private static final String DSL = "dsl";
	private static final String MODEL = "model";
	private static final String SRC = "src";

	String dsl;
	boolean customMorphs;
	String dslGenerate;
	boolean dynamicLoad;
	String languageExtension;
	int level;
	Map<String, File> languages = new HashMap<>();
	Module selectedModuleParent = null;

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

	@NotNull
	@Override
	public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
		return new TaraSupportConfigurable(this, model);
	}

	void addSupport(final Module module, final ModifiableRootModel rootModel) {
		createDSL(rootModel.getProject().getBaseDir());
		createModelSourceRoot(rootModel.getContentEntries()[0]);
		createResources(rootModel.getContentEntries()[0]);
		createGenSourceRoot(rootModel.getContentEntries()[0]);
		if (languageExtension != null) importSources(rootModel.getContentEntries()[0].getSourceFolderFiles());
		updateDependencies(rootModel);
		updateFacetConfiguration(module);
	}

	private void importSources(VirtualFile[] sourceFolders) {
		VirtualFile modelFile = find(MODEL, sourceFolders);
		VirtualFile srcFile = find(SRC, sourceFolders);
		if (!new File(languageExtension).exists()) return;
		final File baseDirectory = new File(languageExtension).getParentFile();
		importSources(new File(baseDirectory, MODEL), modelFile);
		importSources(new File(baseDirectory, SRC), srcFile);
	}

	private void importSources(File source, VirtualFile destiny) {
		for (File file : source.listFiles())
			try {
				final File destFile = new File(destiny.getPath(), file.getName());
				if (file.isDirectory()) FileSystemUtils.copyDir(file, destFile);
				else
					FileSystemUtils.copyFile(file.getAbsolutePath(), destFile.getAbsolutePath());
			} catch (FileSystemException e) {
				throw new TaraRuntimeException("Impossible to import sources: " + e.getMessage());
			}
	}

	private VirtualFile find(String src, VirtualFile[] files) {
		for (VirtualFile entry : files) if (entry.getName().equals(src)) return entry;
		return null;
	}

	private void updateDependencies(ModifiableRootModel rootModel) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			if (languageExtension.isEmpty())
				new FrameworkDependencyCreator(languages, this.dsl, selectedModuleParent).setFrameworkDependency(rootModel);
			else {
				final FrameworkLanguageExtensionSupport support = new FrameworkLanguageExtensionSupport(new File(languageExtension).getParentFile(), new File(languageExtension));
				support.importLanguageDependency(rootModel);
				dsl = support.getDsl();
				level = support.getLevel();
			}
		});
	}


	private void updateFacetConfiguration(Module module) {
		FacetType<TaraFacet, TaraFacetConfiguration> facetType = TaraFacet.getFacetType();
		TaraFacet taraFacet = FacetManager.getInstance(module).addFacet(facetType, facetType.getDefaultFacetName(), null);
		final TaraFacetConfiguration facetConfiguration = taraFacet.getConfiguration();
		facetConfiguration.setDsl(dsl);
		facetConfiguration.setCustomMorphs(customMorphs);
		facetConfiguration.setLanguageExtension(languageExtension);
		facetConfiguration.setGeneratedDslName(dslGenerate);
		facetConfiguration.setDynamicLoad(dynamicLoad);
		facetConfiguration.setLevel(level);
	}

	private void createResources(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot = file.createChildDirectory(null, "res");
			contentEntry.addSourceFolder(sourceRoot, JavaResourceRootType.RESOURCE);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private VirtualFile createDSL(VirtualFile projectDir) {
		try {
			final VirtualFile aDsl = projectDir.findChild(DSL);
			return aDsl != null ? aDsl : projectDir.createChildDirectory(null, DSL);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
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
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
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
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
