package siani.tara.intellij.project.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import siani.tara.intellij.project.module.TaraModuleExtensionProperties;

import java.util.Locale;

public class TaraFacetConfiguration implements FacetConfiguration, PersistentStateComponent<TaraModuleExtensionProperties> {

	private TaraModuleExtensionProperties myProperties = new TaraModuleExtensionProperties();

	public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
		return new FacetEditorTab[]{
			new TaraFacetEditor(this, editorContext.getModule())
		};
	}

	public void readExternal(Element element) throws InvalidDataException {
	}

	public void writeExternal(Element element) throws WriteExternalException {
	}

	public TaraModuleExtensionProperties getState() {
		return myProperties;
	}

	public void loadState(TaraModuleExtensionProperties state) {
		myProperties = state;
	}

	public String getDsl() {
		return myProperties.dsl;
	}

	public void setDsl(String dsl) {
		myProperties.dsl = dsl;
	}

	public String getDictionary() {
		return myProperties.dictionary;
	}

	public Locale getDictionaryAsLocale() {
		return myProperties.dictionary.equals("English") ? Locale.ENGLISH : new Locale("Spanish", "Spain", "es_ES");
	}

	public void setDictionary(String dictionary) {
		myProperties.dictionary = dictionary;
	}


	public String getGeneratedDslName() {
		return myProperties.generatedDslName;
	}

	public void setGeneratedDslName(String generatedDslName) {
		myProperties.generatedDslName = generatedDslName;
	}

	public boolean isCase() {
		return myProperties.generatedDslName.isEmpty();
	}


}
