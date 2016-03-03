package tara.intellij.framework;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import tara.dsl.ProteoConstants;
import tara.intellij.settings.ArtifactorySettings;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static java.lang.String.valueOf;
import static java.nio.channels.Channels.newChannel;

public class ArtifactoryConnector {

	private static final String SOURCE = "https://artifactory.siani.es/artifactory/languages-release/";
	private static final String SOURCE_API = "https://artifactory.siani.es/artifactory/api/storage/languages-release/";
	private static final String LIBS_SOURCE_API = "https://artifactory.siani.es/artifactory/api/storage/libs-release-local/";
	private static final String LANG_EXTENSION = ".dsl";
	private final ArtifactorySettings settings;

	public ArtifactoryConnector(ArtifactorySettings settings) {
		this.settings = settings;
	}

	public void get(File destiny, String name, String version) throws IOException {
		destiny.getParentFile().mkdirs();
		final FileOutputStream stream = new FileOutputStream(destiny);
		stream.getChannel().transferFrom(newChannel(new URL(getUrl(fileName(name, version))).openStream()), 0, Long.MAX_VALUE);
		stream.close();
	}

	public int put(File dsl, String name, String version) throws IOException {
		return put(new URL(getUrl(fileName(name, version))), dsl);
	}

	private String fileName(String name, String version) {
		return name + "/" + version + "/" + name + "-" + version + LANG_EXTENSION;
	}

	public List<String> versions(String dsl) throws IOException {
		if (dsl.equals(ProteoConstants.PROTEO)) return proteoVersion();
		URL url = new URL(getApiUrl(dsl + "/"));
		String input = readResponse(new BufferedReader(new InputStreamReader(url.openStream())));
		final JsonObject o = new Gson().fromJson(input, JsonObject.class);
		return extractUris(o);
	}

	private List<String> proteoVersion() throws IOException {
		URL url = new URL(getLibApiUrl(ProteoConstants.PROTEO_GROUP_ID, ProteoConstants.PROTEO_ARTIFACT_ID));
		String input = readResponse(new BufferedReader(new InputStreamReader(url.openStream())));
		final JsonObject o = new Gson().fromJson(input, JsonObject.class);
		return extractUris(o);
	}

	public List<String> languages() throws IOException {
		URL url = new URL(getApiUrl(""));
		String input = readResponse(new BufferedReader(new InputStreamReader(url.openStream())));
		final JsonObject o = new Gson().fromJson(input, JsonObject.class);
		return extractUris(o);
	}

	private int put(URL url, File origin) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		String userpass = settings.userName() + ":" + settings.password();
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		connection.setRequestProperty("Authorization", basicAuth);
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "multipart/form-data");
		connection.setRequestProperty("Content-Length", valueOf(origin.length()));
		try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
			wr.write(Files.readAllBytes(origin.toPath()));
		}
		connection.getOutputStream().flush();
		return connection.getResponseCode();
	}

	private List<String> extractUris(JsonObject o) {
		List<String> uris = new ArrayList<>();
		o.get("children").getAsJsonArray().forEach(c -> uris.add(c.getAsJsonObject().get("uri").getAsString().substring(1)));
		uris.remove("maven-metadata.xml");
		return uris;
	}

	private String readResponse(BufferedReader reader) {
		StringBuilder everything = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) everything.append(line);
			reader.close();
		} catch (IOException ignored) {
		}
		return everything.toString();
	}


	@NotNull
	private String getUrl(String path) {
		return SOURCE + path;
	}

	@NotNull
	private String getApiUrl(String path) {
		return SOURCE_API + path;
	}

	@NotNull
	private String getLibApiUrl(String groupId, String artifactId) {
		return LIBS_SOURCE_API + groupId.replace(".", "/") + "/" + artifactId;
	}
}
