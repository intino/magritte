package tara.intellij.project.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import tara.intellij.project.module.TaraFacetConfigurationProperties;

public class TaraFacetConfiguration implements FacetConfiguration, PersistentStateComponent<TaraFacetConfigurationProperties> {

	private TaraFacetConfigurationProperties properties = new TaraFacetConfigurationProperties();

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

	public String getDslVersion() {
		return properties.dslVersion;
	}

	public void setDslVersion(String version) {
		properties.dslVersion = version;
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
