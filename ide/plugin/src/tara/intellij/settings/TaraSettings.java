package tara.intellij.settings;

import com.intellij.openapi.project.Project;


public class TaraSettings {

	private static TaraSettings settings;
	private State myState;

	private TaraSettings(Project project) {
		myState = new State(project);
	}

	public static TaraSettings getSafeInstance(Project project) {
		return settings != null ? settings : (settings = new TaraSettings(project));
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
		return myState.settings.overrides;
	}

	public void overrides(boolean overrides) {
		myState.settings.overrides = overrides;
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

	public String destinyLanguage() {
		return myState.settings.destinyLanguage;
	}

	public void destinyLanguage(String destinyLanguage) {
		myState.settings.destinyLanguage = destinyLanguage;
	}

	static class State {


		public static final String RESET_STR_VALUE = "";

		public String serverId = RESET_STR_VALUE;
		public String username = RESET_STR_VALUE;
		public String password = RESET_STR_VALUE;


		public String trackerProjectId = RESET_STR_VALUE;
		public String trackerApiToken = RESET_STR_VALUE;
		public Settings settings = new Settings();

		State(Project project) {
			load(project);
		}

		private void load(Project project) {
			final String[] strings = new ArtifactoryCredentialsManager().loadCredentials();
			serverId = strings[0];
			username = strings[1];
			password = strings[2];
			loadSettings(project);

		}

		public void save() {
			new ArtifactoryCredentialsManager().saveCredentials(new String[]{serverId, username, password});
			saveSettings();
		}

		private void loadSettings(Project project) {

		}

		private void saveSettings() {


		}

		public static class Settings {
			public boolean overrides = false;
			public String destinyLanguage = "Java";
		}


	}

}