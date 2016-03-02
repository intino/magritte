package tara.intellij.settings;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;

public class ArtifactorySettings {

	private State myState = new State();

	public static ArtifactorySettings getSafeInstance(Project project) {
		ArtifactorySettings settings = ServiceManager.getService(project, ArtifactorySettings.class);
		return settings != null ? settings : new ArtifactorySettings();
	}

	public void saveState() {
		myState.save();
	}

	public String userName() {
		return myState.username;
	}

	public void userName(String username) {
		myState.username = username;
	}

	public String serverId() {
		return myState.serverId;
	}

	public void serverId(String serverId) {
		myState.serverId = serverId;
	}

	public String password() {
		return myState.password;
	}

	public void setPassword(String password) {
		myState.password = password;
	}


	public static class State {

		public static final String RESET_STR_VALUE = "";

		public String serverId = RESET_STR_VALUE;
		public String username = RESET_STR_VALUE;
		public String password = RESET_STR_VALUE;

		public State() {
			load();
		}

		private void load() {
			final String[] strings = new ArtifactoryCredentialsManager().loadCredentials();
			serverId = strings[0];
			username = strings[1];
			password = strings[2];
		}

		public void save() {
			new ArtifactoryCredentialsManager().saveCredentials(new String[]{serverId, username, password});
		}
	}

}