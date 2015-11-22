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

	private TaraFacetConfigurationProperties myProperties = new TaraFacetConfigurationProperties();

	public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
		return new FacetEditorTab[]{
			new TaraFacetEditor(this, editorContext)
		};
	}

	public void readExternal(Element element) throws InvalidDataException {
	}

	public void writeExternal(Element element) throws WriteExternalException {
	}

	public TaraFacetConfigurationProperties getState() {
		return myProperties;
	}

	public void loadState(TaraFacetConfigurationProperties state) {
		myProperties = state;
	}

	public String getDsl() {
		return myProperties.dsl;
	}

	public void setDsl(String dsl) {
		myProperties.dsl = dsl;
	}

	public String getDslKey() {
		return myProperties.dslKey;
	}

	public void setDslKey(String key) {
		myProperties.dslKey = key;
	}

	public String getDslVersion() {
		return myProperties.dslVersion;
	}

	public void setDslVersion(String version) {
		myProperties.dslVersion = version;
	}

	public boolean isCustomLayers() {
		return myProperties.customLayers;
	}

	public void setCustomLayers(boolean customMorphs) {
		myProperties.customLayers = customMorphs;
	}

	public String getGeneratedDslName() {
		return myProperties.generatedDslName;
	}

	public void setGeneratedDslName(String generatedDslName) {
		myProperties.generatedDslName = generatedDslName;
	}

	public String getGeneratedDslKey() {
		return myProperties.generatedDslKey;
	}

	public void setGeneratedDslKey(String generatedDslKey) {
		myProperties.generatedDslKey = generatedDslKey;
	}

	public boolean isM0() {
		return getLevel() == 0;
	}

	public int getLevel() {
		return myProperties.level;
	}

	public void setLevel(int level) {
		myProperties.level = level;
	}

	public void setDynamicLoad(boolean load) {
		myProperties.dynamicLoad = load;
	}

	public boolean isDynamicLoad() {
		return myProperties.dynamicLoad;
	}


	public long getRefactorId() {
		return myProperties.refactorId;
	}

	public void setRefactorId(long level) {
		myProperties.refactorId = level;
	}


}
