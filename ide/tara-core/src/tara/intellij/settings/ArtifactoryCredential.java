package tara.intellij.settings;

public class ArtifactoryCredential {
	private static final String RESET_STR_VALUE = "";
	public String serverId = RESET_STR_VALUE;
	public String username = RESET_STR_VALUE;
	public String password = RESET_STR_VALUE;


	public ArtifactoryCredential(String serverId, String username, String password) {
		this.serverId = serverId;
		this.username = username;
		this.password = password;
	}
}
