package tara.intellij.settings;

import com.intellij.openapi.options.ConfigurationException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaraSettingsPanel {

	private JPanel rootPanel;
	private JPanel tracker;
	private JTextField trackerProject;
	private JTextField trackerApi;
	private JScrollPane artifactories;
	private JButton addServerButton;
	private JPanel artifactoriesPanel;
	private JComboBox destinyLanguage;
	private List<ArtifactoryPanel> artifactoryPanels = new ArrayList<>();
	private JCheckBox overrides;

	TaraSettingsPanel() {
		tracker.setBorder(BorderFactory.createTitledBorder("Issue Tracker"));
		addServerButton.addActionListener((e) -> {
			ArtifactoryPanel artifactory = new ArtifactoryPanel();
			artifactoryPanels.add(artifactory);
			artifactoriesPanel.add(artifactory.panel(), getConstraints(artifactoryPanels.size() - 1));
			artifactoriesPanel.validate();
			rootPanel.validate();
			artifactoriesPanel.revalidate();
			artifactoriesPanel.repaint();

		});
	}

	void loadConfigurationData(TaraSettings settings) {
		List<ArtifactoryCredential> artifactories = settings.artifactories();
		for (int i = 0; i < artifactories.size(); i++) {
			ArtifactoryPanel panel = new ArtifactoryPanel();
			panel.setServerId(artifactories.get(i).serverId);
			panel.setUsername(artifactories.get(i).username);
			if (!artifactories.get(i).username.trim().isEmpty()) panel.setPassword(artifactories.get(i).password);
			artifactoriesPanel.add(panel.panel(), getConstraints(i));
			artifactoryPanels.add(panel);
		}
		overrides.setSelected(settings.overrides());
		trackerProject.setText(settings.trackerProjectId());
		trackerApi.setText(settings.trackerApiToken());
		destinyLanguage.setSelectedItem(settings.destinyLanguage());
	}


	void applyConfigurationData(TaraSettings settings) throws ConfigurationException {
//		formValidator.validate();
		for (ArtifactoryPanel panel : artifactoryPanels) addArtifactory(settings, panel);
		settings.overrides(overrides.isSelected());
		settings.trackerProjectId(trackerProject.getText());
		settings.trackerApiToken(trackerApi.getText());
		settings.destinyLanguage(destinyLanguage.getSelectedItem().toString());
		settings.saveState();
	}

	private void addArtifactory(TaraSettings settings, ArtifactoryPanel panel) {
		settings.addArtifactory(new ArtifactoryCredential(panel.getServerId(), panel.getUsername(), panel.getPassword()));
	}

	boolean isModified(TaraSettings taraSettings) {
		return taraSettings.overrides() != overrides.isSelected()
			|| !taraSettings.trackerProjectId().equals(trackerProject.getText()) || !(taraSettings.trackerApiToken().equals(trackerApi.getText()))
			|| !taraSettings.destinyLanguage().equals(destinyLanguage.getSelectedItem());
	}

	private GridBagConstraints getConstraints(int x) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = x;
		constraints.weightx = 0.5;
		constraints.weighty = 1;
		constraints.insets = new Insets(5, 0, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		return constraints;
	}

	JPanel getRootPanel() {
		return rootPanel;
	}

}
