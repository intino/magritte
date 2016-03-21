package tara.intellij.settings;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class TaraSettingsComponent implements ProjectComponent, Configurable {

	private static final String TARA_CONTROL_PLUGIN_NAME = "Tara Plugin";
	private static final String TARA_CONTROL_COMPONENT_NAME = "ArtifactComponent";

	private final TaraSettings taraSettings;

	private final Project project;

	private ConfigurationPanel configurationPanel;

	public TaraSettingsComponent(Project project) {
		this.project = project;
		this.taraSettings = TaraSettings.getSafeInstance(project);
	}


	public void projectOpened() {
	}


	public void projectClosed() {
	}

	public JComponent createComponent() {
		if (configurationPanel == null) configurationPanel = new ConfigurationPanel();
		return configurationPanel.getRootPanel();
	}

	public boolean isModified() {
		return configurationPanel != null && configurationPanel.isModified(taraSettings);
	}

	public void disposeUIResources() {
		configurationPanel = null;
	}

	public String getHelpTopic() {
		return null;
	}


	public void apply() throws ConfigurationException {
		if (configurationPanel != null) try {
			configurationPanel.applyConfigurationData(taraSettings);
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
		return null;
	}


	public void reset() {
		configurationPanel.loadConfigurationData(taraSettings);
	}


	public void initComponent() {

	}

	public void disposeComponent() {

	}

}
