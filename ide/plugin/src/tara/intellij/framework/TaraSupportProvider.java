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
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.facet.maven.ModuleMavenManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;
import static tara.intellij.lang.LanguageManager.DSL;
import static tara.intellij.lang.LanguageManager.getImportedLanguageInfo;

public class TaraSupportProvider extends FrameworkSupportInModuleProvider {

	private static final Logger LOG = Logger.getInstance(TaraSupportProvider.class.getName());
	private static final String MODEL = "model";
	private static final String GEN = "gen";
	private static final String RES = "res";
	private static final String TEST = "test";

	String dslName;
	boolean customLayers;
	String dslGenerated;
	boolean dynamicLoad;
	boolean test;
	int level;
	Map<String, LanguageInfo> toImport = new HashMap<>();
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
		createDSLDirectory(LanguageManager.getTaraDirectory(rootModel.getProject()));
		createModelSourceRoot(rootModel.getContentEntries()[0]);
		createGenSourceRoot(rootModel.getContentEntries()[0]);
		createResources(rootModel.getContentEntries()[0]);
		if (test) createTest(rootModel.getContentEntries()[0]);
		final TaraFacet facetConfiguration = createFacetConfiguration(module);
		buildLanguage(module, rootModel);
		fillFacetConfiguration(module, facetConfiguration);
	}

	private void fillFacetConfiguration(Module module, TaraFacet taraFacet) {
		final TaraFacetConfiguration conf = taraFacet.getConfiguration();
		conf.setDsl(dslName);
		conf.setGeneratedDslName(dslGenerated);
		conf.setTestModule(test);
		if (dslName.equals(TaraLanguage.PROTEO) || selectedModuleParent != null) {
			conf.setDynamicLoad(dynamicLoad);
			conf.setCustomLayers(customLayers);
			conf.setDomainRefactorId(dynamicLoad ? 0 : -1);
			conf.setEngineRefactorId(dynamicLoad ? 0 : -1);
		} else inheritPropertiesFromImportedLanguage(module, conf);
		conf.setLevel(level);
	}

	private void buildLanguage(Module module, ModifiableRootModel rootModel) {
		if (toImport.containsKey(this.dslName)) importDsl(module);
		else mavenize(module, rootModel);
	}

	private void mavenize(Module module, ModifiableRootModel rootModel) {
		ModuleMavenManager mavenizer = new ModuleMavenManager(dslName, module);
		if (rootModel.getProject().isInitialized()) mavenizer.mavenize();
		else startWithMaven(mavenizer, module.getProject());
	}

	private void startWithMaven(final ModuleMavenManager mavenizer, Project project) {
		StartupManager.getInstance(project).registerPostStartupActivity(mavenizer::mavenize);
	}

	private void importDsl(Module module) {
		ApplicationManager.getApplication().runWriteAction(() -> {
			final LanguageInfo languageInfo = toImport.get(dslName);
			new FrameworkImporter(module).importLanguage(languageInfo.getKey(), languageInfo.getVersion());
		});
	}

	private TaraFacet createFacetConfiguration(Module module) {
		FacetType<TaraFacet, TaraFacetConfiguration> facetType = TaraFacet.getFacetType();
		return FacetManager.getInstance(module).addFacet(facetType, facetType.getDefaultFacetName(), null);
	}

	private void inheritPropertiesFromImportedLanguage(Module module, TaraFacetConfiguration conf) {
		final Map<String, Object> importedLanguageInfo = getImportedLanguageInfo(dslName, module.getProject());
		if (importedLanguageInfo.isEmpty()) return;
		conf.setDynamicLoad(Boolean.parseBoolean(importedLanguageInfo.get("dynamicLoad").toString()));
		conf.setCustomLayers(Boolean.parseBoolean(importedLanguageInfo.get("customLayers").toString()));
		conf.setDomainRefactorId(conf.isDynamicLoad() ? 0 : -1);
		conf.setEngineRefactorId(conf.isDynamicLoad() ? 0 : -1);
	}

	private void createResources(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot;
			if ((sourceRoot = file.findChild(RES)) == null) sourceRoot = file.createChildDirectory(null, RES);
			contentEntry.addSourceFolder(sourceRoot, JavaResourceRootType.RESOURCE);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void createTest(ContentEntry contentEntry) {
		try {
			VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			VirtualFile sourceRoot;
			if ((sourceRoot = file.findChild(TEST)) == null) sourceRoot = file.createChildDirectory(null, TEST);
			contentEntry.addSourceFolder(sourceRoot, true);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private VirtualFile createDSLDirectory(VirtualFile taraDirectory) {
		try {
			final VirtualFile aDsl = taraDirectory.findChild(DSL);
			return aDsl != null ? aDsl : taraDirectory.createChildDirectory(null, DSL);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	private void createGenSourceRoot(ContentEntry contentEntry) {
		try {
			VirtualFile moduleDir = contentEntry.getFile();
			if (moduleDir == null) return;
			VirtualFile sourceRoot = moduleDir.findChild(GEN);
			if (sourceRoot == null) sourceRoot = moduleDir.createChildDirectory(null, GEN);
			JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", true);
			contentEntry.addSourceFolder(sourceRoot, JavaSourceRootType.SOURCE, properties);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private void createModelSourceRoot(ContentEntry contentEntry) {
		try {
			final VirtualFile file = contentEntry.getFile();
			if (file == null) return;
			String modulePath = file.getPath();
			VirtualFile templates = VfsUtil.createDirectories(modulePath + separator + MODEL);
			if (templates != null) {
				JavaSourceRootProperties properties = JpsJavaExtensionService.getInstance().createSourceRootProperties("", false);
				contentEntry.addSourceFolder(templates, JavaSourceRootType.SOURCE, properties);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
