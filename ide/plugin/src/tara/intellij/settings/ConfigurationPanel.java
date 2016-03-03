package tara.intellij.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class ConfigurationPanel {
	private static final String DEFAULT_SERVER = "siani-maven";
	private final Project project;

	private JPasswordField passwordField;
	private JTextField username;
	private JTextField serverId;
	private JPanel rootPanel;
	private JPanel tracker;
	private JCheckBox overrides;
	private JTextField trackerProject;
	private JTextField trackerApi;
	private JScrollPane artifactories;
	private JButton addServerButton;
	private JPanel artifactoriesPanel;
	private JPanel artifactory;

	public ConfigurationPanel(Project project) {
		this.project = project;
		serverId.setText(DEFAULT_SERVER);
		tracker.setBorder(BorderFactory.createTitledBorder("Issue Tracker"));
		addServerButton.addActionListener((e) -> {
		});
	}

	public void loadConfigurationData(TaraSettings settings) {
		serverId.setText(settings.serverId());
		username.setText(settings.userName());
		if (!settings.userName().trim().isEmpty()) passwordField.setText(settings.password());
		overrides.setSelected(settings.overrides());
		trackerProject.setText(settings.trackerProjectId());
		trackerApi.setText(settings.trackerApiToken());
	}


	public void applyConfigurationData(TaraSettings settings) throws ConfigurationException {
//		formValidator.validate();
		if (!settings.serverId().equals(serverId.getText())) settings.serverId(serverId.getText());
		settings.userName(username.getText().trim());
		settings.setPassword(password());
		settings.overrides(overrides.isSelected());
		settings.trackerProjectId(trackerProject.getText());
		settings.trackerApiToken(trackerApi.getText());
		settings.saveState();
	}

	private String password() {
		return String.valueOf(passwordField.getPassword());
	}

	public boolean isModified(TaraSettings taraSettings) {
		return !taraSettings.serverId().equals(serverId.getText()) || !(taraSettings.userName().equals(username.getText()))
			|| !(taraSettings.password().equals(password())) || taraSettings.overrides() != overrides.isSelected()
			|| taraSettings.trackerProjectId().equals(trackerProject.getText()) || !(taraSettings.trackerApiToken().equals(trackerApi.getText()));
	}

	public JPanel getRootPanel() {
		return rootPanel;
	}

}
