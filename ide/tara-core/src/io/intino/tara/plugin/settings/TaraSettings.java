package io.intino.tara.plugin.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@State(
	name = "Tara.Settings",
	storages = {
		@Storage(id = "TaraSettings", file = "$PROJECT_FILE$"),
		@Storage(file = "$PROJECT_CONFIG_DIR$/taraSettings.xml", scheme = StorageScheme.DIRECTORY_BASED)
	}
)
public class TaraSettings implements PersistentStateComponent<TaraSettings.State> {

	private State myState = new State();
	private List<ArtifactoryCredential> artifactories = null;

	public static TaraSettings getSafeInstance(Project project) {
		TaraSettings settings = ServiceManager.getService(project, TaraSettings.class);
		return settings != null ? settings : new TaraSettings();
	}

	public void saveState() {
		new ArtifactoryCredentialsManager().saveCredentials(artifactories);
	}

	public List<ArtifactoryCredential> artifactories() {
		return artifactories == null ? artifactories = new ArtifactoryCredentialsManager().loadCredentials() : artifactories;
	}

	public void addArtifactory(ArtifactoryCredential artifactory) {
		ArtifactoryCredential saved = artifactories.stream().filter(a -> a.serverId.equals(artifactory.serverId)).findFirst().orElse(null);
		if (saved == null) artifactories.add(artifactory);
		else {
			saved.username = artifactory.username;
			saved.password = artifactory.password;
		}
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
		@Tag("destinyLanguage")
		public String destinyLanguage = "Java";

		@Tag("trackerProjectId")
		public String trackerProjectId = "1022010";

		@Tag("trackerApiToken")
		public String trackerApiToken = "ae3d1e4d4bcb011927e2768d7aa39f3a";
	}

}