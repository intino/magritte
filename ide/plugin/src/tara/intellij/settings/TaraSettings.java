package tara.intellij.settings;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;


public class TaraSettings {

	private State myState = new State();

	public static TaraSettings getSafeInstance(Project project) {
		TaraSettings settings = ServiceManager.getService(project, TaraSettings.class);
		return settings != null ? settings : new TaraSettings();
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

	public boolean overrides() {
		return myState.overrides;
	}

	public void overrides(boolean overrides) {
		myState.overrides = overrides;
	}


	public String trackerProjectId() {
		return myState.trackerProjectId;
	}

	public void trackerProjectId(String trackerProjectId) {
		myState.trackerProjectId = trackerProjectId;
	}

	public String trackerApiToken() {
		return myState.trackerApiToken;
	}

	public void trackerApiToken(String trackerApiToken) {
		myState.trackerApiToken = trackerApiToken;
	}

	public static class State {

		public static final String RESET_STR_VALUE = "";

		public String serverId = RESET_STR_VALUE;
		public String username = RESET_STR_VALUE;
		public String password = RESET_STR_VALUE;

		public boolean overrides = false;
		public String trackerProjectId = RESET_STR_VALUE;
		public String trackerApiToken = RESET_STR_VALUE;

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