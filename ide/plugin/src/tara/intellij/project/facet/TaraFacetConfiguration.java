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

	private TaraFacetConfigurationProperties properties = new TaraFacetConfigurationProperties();
	String dslVersion = null;

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

	public String dsl() {
		return properties.dsl;
	}

	public void setDsl(String dsl) {
		properties.dsl = dsl;
	}

	public String dslVersion(Module module) {
		if (dslVersion != null) return dslVersion;
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		return project == null ? "" : new MavenHelper(module, project).dslVersion(dslMavenId(module));
	}

	public void dslVersion(Module module, String version) {
		if (module == null) return;
		final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
		if (project != null) new MavenHelper(module, project).dslVersion(dslMavenId(module), version);
		dslVersion = version;
	}

	public SimpleEntry dslMavenId(Module module) {
		if (isArtifactoryDsl()) return fromImportedInfo(module);
		else if (ProteoConstants.PROTEO.equals(dsl())) return proteoId();
		else return mavenId(parentModule(module));
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

	private Module parentModule(Module module) {
		for (Module aModule : ModuleManager.getInstance(module.getProject()).getModules()) {
			TaraFacet taraFacet = TaraFacet.of(aModule);
			if (taraFacet != null && !dsl().equals(taraFacet.getConfiguration().outputDsl()))
				return module;
		}
		return null;
	}

	@NotNull
	private SimpleEntry fromImportedInfo(Module module) {
		final Map<String, Object> info = LanguageManager.getImportedLanguageInfo(dsl(), module.getProject());
		if (info.isEmpty()) return new SimpleEntry("", "");
		return new SimpleEntry(info.get("groupId"), info.get("artifactId"));
	}

	public String outputDsl() {
		return properties.outputDsl;
	}

	public void setArtifactoryDsl(boolean b) {
		properties.artifactoryDsl = b;
	}

	public boolean isArtifactoryDsl() {
		return properties.artifactoryDsl;
	}

	public void outputDsl(String name) {
		properties.outputDsl = name;
	}

	public int getLevel() {
		return properties.level;
	}

	public void setLevel(int level) {
		properties.level = level;
	}

	public boolean isM0() {
		return getLevel() == 0;
	}

	public boolean isDynamicLoad() {
		return properties.dynamicLoad;
	}

	public void setDynamicLoad(boolean load) {
		properties.dynamicLoad = load;
	}

	public boolean isOntology() {
		return properties.ontology;
	}

	public void setOntology(boolean ontology) {
		properties.ontology = ontology;
	}

	public int getEngineRefactorId() {
		return properties.engineRefactorId;
	}

	public void setEngineRefactorId(int id) {
		properties.engineRefactorId = id;
	}

	public int getDomainRefactorId() {
		return properties.domainRefactorId;
	}

	public void setDomainRefactorId(int id) {
		properties.domainRefactorId = id;
	}

	public void setTestModule(boolean testModule) {
		properties.testModule = testModule;
	}

	public boolean isTest() {
		return properties.testModule;
	}
}
