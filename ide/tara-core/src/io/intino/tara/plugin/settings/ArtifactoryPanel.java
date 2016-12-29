package io.intino.tara.plugin.settings;

import javax.swing.*;

public class ArtifactoryPanel {
	private JPanel artifactory;
	private JTextField serverId;
	private JTextField username;
	private JPasswordField passwordField;
	private JPanel root;

	public JPanel panel() {
		return artifactory;
	}

	public String getServerId() {
		return serverId.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}

	public void setServerId(String serverId) {
		this.serverId.setText(serverId);
	}

	public void setUsername(String username) {
		this.username.setText(username);
	}

	public void setPassword(String password) {
		this.passwordField.setText(password);
	}
}
