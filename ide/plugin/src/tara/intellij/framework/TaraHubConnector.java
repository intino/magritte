package tara.intellij.framework;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.valueOf;
import static java.nio.channels.Channels.newChannel;

public class TaraHubConnector {

	private static final String SOURCE = "http://hub.tara.siani.es";
	private String key;
	private String version = LanguageInfo.LATEST_VERSION;

	public TaraHubConnector(String key, String version) {
		this.key = key;
		this.version = version;
	}

	public TaraHubConnector() {
	}

	public void downloadTo(File destiny) throws IOException {
		destiny.getParentFile().mkdirs();
		final FileOutputStream stream = new FileOutputStream(destiny);
		stream.getChannel().transferFrom(newChannel(new URL(getUrl("/" + this.key + "/" + version)).openStream()), 0, Long.MAX_VALUE);
		stream.close();
	}

	public String newDsl(String dsl) throws IOException {
		URL url = new URL(SOURCE);
		String urlParameters = "dsl=" + dsl;
		return doPost(url, urlParameters);
	}

	public void putDsl(String key, File origin) throws IOException {
		put(new URL(getUrl("/" + key)), origin);
	}

	private void put(URL url, File origin) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", "multipart/form-data");
		connection.setRequestProperty("Content-Length", valueOf(origin.length()));
		try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
			wr.write(Files.readAllBytes(origin.toPath()));
		}
		connection.getOutputStream().flush();
		if (connection.getResponseCode() != 200) System.out.println(connection.getResponseMessage());
	}

	public String nameOf(String key) throws IOException {
		return doGet(new URL(getUrl("/" + key.trim()) + "/name"));

	}

	public List<String> versions(String key) throws IOException {
		URL url = new URL(getUrl("/" + key) + "/versions");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String input = in.readLine();
		in.close();
		return input == null ? Collections.emptyList() : Arrays.asList(input.split(";"));
	}

	@NotNull
	private String getUrl(String key) {
		return SOURCE + key;
	}

	@NotNull
	private String doGet(URL url) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String text = "";
		String input;
		while ((input = in.readLine()) != null)
			text += input;
		in.close();
		return text;
	}

	private String doPost(URL url, String urlParameters) throws IOException {
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		connection.setUseCaches(false);
		try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
			wr.write(postData);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String text = br.readLine();
		br.close();
		return text;
	}

	public List<String> list() throws IOException {
		URL url = new URL(getUrl("/list"));
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String input = in.readLine();
		in.close();
		return input == null ? Collections.emptyList() : Arrays.asList(input.split(";"));
	}
}
