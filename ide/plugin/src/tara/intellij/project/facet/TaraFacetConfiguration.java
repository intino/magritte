package tara.intellij.project.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.dsl.ProteoConstants;
import tara.intellij.lang.LanguageManager;
import tara.intellij.project.facet.maven.MavenHelper;
import tara.intellij.project.module.TaraFacetConfigurationProperties;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import static tara.dsl.ProteoConstants.PROTEO_ARTIFACT_ID;
import static tara.dsl.ProteoConstants.PROTEO_GROUP_ID;

public class TaraFacetConfiguration implements FacetConfiguration, PersistentStateComponent<TaraFacetConfigurationProperties> {

	public enum ModuleType {
		System, Application, Ontology, ProductLine, Platform;

		public int compareLevelWith(@NotNull ModuleType type) {
			if (type.ordinal() == this.ordinal()) return 0;
			if ((type.ordinal() == 1 || type.ordinal() == 2) && (this.ordinal() == 1 || this.ordinal() == 2)) return 0;
			if ((type.ordinal() == 3 || type.ordinal() == 4) && (this.ordinal() == 3 || this.ordinal() == 4)) return 0;
			return type.ordinal() - this.ordinal();
		}
	}

	private TaraFacetConfigurationProperties properties = new TaraFacetConfigurationProperties();
	private String dslVersion = null;

	public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
		return new FacetEditorTab[]{
			new TaraFacetEditor(this, editorContext)
		};
	}

	public void readExternal(Element element) throws InvalidDataException {
	}

	public void writeExternal(Element element) throws WriteExternalException {
	}

	public TaraFacetConfigurationProperties getProperties() {
		return properties;
	}

	public TaraFacetConfigurationProperties getState() {
		return properties;
	}

	public void loadState(TaraFacetConfigurationProperties state) {
		properties = state;
	}

	public ModuleType type() {
		return ModuleType.valueOf(properties.type);
	}

	public void type(ModuleType type) {
		properties.type = type.name();
	}

	public String platformDsl() {
		return properties.platformDsl;
	}

	public String platformDsl(String dsl) {
		return properties.platformDsl = dsl;
	}

	public String applicationDsl() {
		return properties.applicationDsl;
	}

	public void applicationDsl(String dsl) {
		properties.applicationDsl = dsl;
	}

	public String systemDsl() {
		return properties.systemDsl;
	}

	public void systemDsl(String dsl) {
		properties.systemDsl = dsl;
	}

	public String platformOutDsl() {
		return properties.platformOutDsl;
	}

	public void platformOutDsl(String dsl) {
		properties.platformOutDsl = dsl;
	}

	public String applicationOutDsl() {
		return properties.applicationOutDsl;
	}

	public void applicationOutDsl(String dsl) {
		properties.applicationOutDsl = dsl;
	}

	public String languageByModuleType(ModuleType type) {
		if (type == ModuleType.System) return systemDsl();
		if (type == ModuleType.Application) return applicationDsl();
		else return "Proteo";
	}

	public String dslVersion(Module module, String dsl) {
		if (dslVersion != null) return dslVersion;
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		return project == null ? "" : new MavenHelper(module).dslVersion(dslMavenId(module, dsl));
	}

	public void dslVersion(Module module, String dsl, String version) {
		if (module == null) return;
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project != null) new MavenHelper(module).dslVersion(dslMavenId(module, dsl), version);
		dslVersion = version;
	}

	private SimpleEntry dslMavenId(Module module, String dsl) {
		if ((dsl.equals(applicationDsl()) && isApplicationImportedDsl()) || (dsl.equals(systemDsl()) && isSystemImportedDsl()))
			return fromImportedInfo(module, dsl);
		else if (ProteoConstants.PROTEO.equals(dsl)) return proteoId();
		else return mavenId(parentModule(module, dsl));
	}

	private SimpleEntry proteoId() {
		return new SimpleEntry(PROTEO_GROUP_ID, PROTEO_ARTIFACT_ID);
	}

	private SimpleEntry mavenId(Module module) {
		if (module == null) return new SimpleEntry("", "");
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project == null) return new SimpleEntry("", "");
		return new SimpleEntry(project.getMavenId().getGroupId(), project.getMavenId().getArtifactId());
	}

	private Module parentModule(Module module, String dsl) {
		for (Module aModule : ModuleManager.getInstance(module.getProject()).getModules()) {
			TaraFacet facet = TaraFacet.of(aModule);
			if (facet != null && dsl.equals(facet.getConfiguration().platformOutDsl()))
				return module;
		}
		return null;
	}

	@NotNull
	private SimpleEntry fromImportedInfo(Module module, String dsl) {
		final Map<String, Object> info = LanguageManager.getImportedLanguageInfo(dsl, module.getProject());
		if (info.isEmpty()) return new SimpleEntry("", "");
		return new SimpleEntry(info.get("groupId"), info.get("artifactId"));
	}

	public void applicationImportedDsl(boolean b) {
		properties.applicationImportedDsl = b;
	}

	public boolean isApplicationImportedDsl() {
		return properties.applicationImportedDsl;
	}

	public void systemImportedDsl(boolean b) {
		properties.systemImportedDsl = b;
	}

	public boolean isSystemImportedDsl() {
		return properties.systemImportedDsl;
	}

	public boolean isLazyLoad() {
		return properties.lazyLoad;
	}

	public void lazyLoad(boolean load) {
		properties.lazyLoad = load;
	}


	public boolean isPersistent() {
		return properties.persistent;
	}

	public void persistent(boolean persistent) {
		properties.persistent = persistent;
	}

	public boolean isOntology() {
		return ModuleType.Ontology.name().equals(properties.type);
	}

	public int platformRefactorId() {
		return properties.platformRefactorId;
	}

	public void platformRefactorId(int id) {
		properties.platformRefactorId = id;
	}

	public int applicationRefactorId() {
		return properties.applicationRefactorId;
	}

	public void applicationRefactorId(int id) {
		properties.applicationRefactorId = id;
	}

	public boolean isTest() {
		return properties.testModule;
	}

	public void setTestModule(boolean testModule) {
		properties.testModule = testModule;
	}
}
