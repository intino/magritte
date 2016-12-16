package tara.intellij.settings;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.TaraLanguage;

import javax.swing.*;


public class TaraSettingsComponent implements ProjectComponent, Configurable {

	private static final String TARA_CONTROL_PLUGIN_NAME = "Tara Plugin";
	private static final String TARA_CONTROL_COMPONENT_NAME = "ArtifactComponent";

	private final TaraSettings taraSettings;
	private final Project project;
	private TaraSettingsPanel taraSettingsPanel;

	public TaraSettingsComponent(Project project) {
		this.taraSettings = TaraSettings.getSafeInstance(project);
		this.project = project;
	}

	public void projectOpened() {
	}

	public void projectClosed() {
		LanguageManager.remove(project);
	}

	public JComponent createComponent() {
		if (taraSettingsPanel == null) taraSettingsPanel = new TaraSettingsPanel();
		return taraSettingsPanel.getRootPanel();
	}

	public boolean isModified() {
		return taraSettingsPanel != null && taraSettingsPanel.isModified(taraSettings);
	}

	public void disposeUIResources() {
		taraSettingsPanel = null;
	}

	public String getHelpTopic() {
		return null;
	}


	public void apply() throws ConfigurationException {
		if (taraSettingsPanel != null) try {
			taraSettingsPanel.applyConfigurationData(taraSettings);
		} catch (Exception ex) {
			throw new ConfigurationException(ex.getMessage());
		}
	}

	@NotNull
	public String getComponentName() {
		return TARA_CONTROL_COMPONENT_NAME;
	}


	@Nls
	public String getDisplayName() {
		return TARA_CONTROL_PLUGIN_NAME;
	}


	public Icon getIcon() {
		return TaraIcons.LOGO_16;
	}


	public void reset() {
		taraSettingsPanel.loadConfigurationData(taraSettings);
	}


	public void initComponent() {

	}

	public void disposeComponent() {

	}

	@NotNull
	public String getId() {
		return TaraLanguage.INSTANCE.getID();
	}
}
