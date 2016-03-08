package tara.intellij.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
	name = "Tara.Settings",
	storages = {
		@Storage(id = "TaraSettings", file = "$PROJECT_FILE$"),
		@Storage(file = "$PROJECT_CONFIG_DIR$/taraSettings.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)
public class TaraSettings implements PersistentStateComponent<TaraSettings.State> {

	private static final String RESET_STR_VALUE = "";

	private State myState = new State();
	private Artifactory myArtifactory = new Artifactory();

	public static TaraSettings getSafeInstance(Project project) {
		TaraSettings settings = ServiceManager.getService(project, TaraSettings.class);
		return settings != null ? settings : new TaraSettings();
	}

	public void saveState() {
		myArtifactory.save();
	}

	public String userName() {
		return myArtifactory.username;
	}

	public void userName(String username) {
		myArtifactory.username = username;
	}

	public String serverId() {
		return myArtifactory.serverId;
	}

	public void serverId(String serverId) {
		myArtifactory.serverId = serverId;
	}

	public String password() {
		return myArtifactory.password;
	}

	public void setPassword(String password) {
		myArtifactory.password = password;
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

	public boolean overrides() {
		return myState.overrides;
	}

	public void overrides(boolean overrides) {
		myState.overrides = overrides;
	}

	public String destinyLanguage() {
		return myState.destinyLanguage;
	}

	public void destinyLanguage(String destinyLanguage) {
		myState.destinyLanguage = destinyLanguage;
	}

	@Nullable
	@Override
	public State getState() {
		return myState;
	}

	@Override
	public void loadState(State state) {
		XmlSerializerUtil.copyBean(state, myState);
	}

	public static class State {
		public boolean overrides = false;
		public String destinyLanguage = "Java";
		public String trackerProjectId = RESET_STR_VALUE;
		public String trackerApiToken = RESET_STR_VALUE;

	}

	public static class Artifactory {

		public String serverId = RESET_STR_VALUE;
		public String username = RESET_STR_VALUE;
		public String password = RESET_STR_VALUE;


		public Artifactory() {
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