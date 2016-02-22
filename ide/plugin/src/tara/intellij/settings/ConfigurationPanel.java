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
	private JButton testConnexionButton;
	private JLabel connectionStatusLabel;
	private JPanel rootPanel;
	private JPanel artifactoryPanel;

	public ConfigurationPanel(Project project) {
		this.project = project;
		serverId.setText(DEFAULT_SERVER);
		artifactoryPanel.setBorder(BorderFactory.createTitledBorder("Artifactory"));
		testConnexionButton.addActionListener(event -> {
		});
	}

	public void loadConfigurationData(ArtifactorySettings settings) {
		serverId.setText(settings.serverId());
		username.setText(settings.userName());
		if (!settings.userName().trim().isEmpty()) passwordField.setText(settings.password());
	}


	public void applyConfigurationData(ArtifactorySettings settings) throws ConfigurationException {
//		formValidator.validate();
		if (!settings.serverId().equals(serverId.getText())) settings.serverId(serverId.getText());
		settings.userName(username.getText().trim());
		settings.setPassword(password());
		settings.saveState();
	}

	private String password() {
		return String.valueOf(passwordField.getPassword());
	}

	public boolean isModified(ArtifactorySettings artifactorySettings) {
		return !artifactorySettings.serverId().equals(serverId.getText()) || !(artifactorySettings.userName().equals(username.getText()))
			|| !(artifactorySettings.password().equals(password()));
	}

	public JPanel getRootPanel() {
		return rootPanel;
	}

}
