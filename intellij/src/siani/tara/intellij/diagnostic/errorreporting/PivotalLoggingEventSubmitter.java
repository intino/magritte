package siani.tara.intellij.diagnostic.errorreporting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class PivotalLoggingEventSubmitter {
	private static final String PLUGIN_ID = "plugin.id";
	private static final String PLUGIN_VERSION = "plugin.version";
	private static final String PLUGIN_NAME = "plugin.name";
	private static final String PLUGIN_ERROR_ADDITIONAL_INFO = "plugin.additionalInfo";
	public static final String STORIES_URL = "https://www.pivotaltracker.com/services/v5/projects/1022010/stories";
	public static final String COMMENTS = "/comments";
	private final String description;
	private Properties properties;

	public PivotalLoggingEventSubmitter(Properties properties, String description) {
		this.properties = properties;
		this.description = description;
	}

	void submit() {
		try {
			PivotalStory story = new PivotalStory();
			String storyCreated = createStory(story);
			addInfo(story, new JsonParser().parse(storyCreated));
//			updateStory(story);
			addCommentary(story);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String createStory(PivotalStory story) throws IOException {
		HttpURLConnection connection = createConnection("POST", STORIES_URL);
		sendStory(connection, story);
		checkResponse(connection);
		return getResponse(connection);
	}

	private void addCommentary(PivotalStory story) throws IOException {
		HttpURLConnection connection = createConnection("POST", story.url + COMMENTS);
		addComments(connection, story);
		checkResponse(connection);
		System.out.println(getResponse(connection));
	}

	private void addComments(HttpURLConnection connection, PivotalStory story) throws IOException {
		final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		osw.write("{\"text\":\"" +
			story.comment
			+ "\"}");
		osw.close();
	}

	private void addInfo(PivotalStory story, JsonElement element) {
		JsonObject jobject = element.getAsJsonObject();
		story.id = jobject.get("id").getAsInt();
		story.url = STORIES_URL + "/" + story.id;
	}

	private String getResponse(HttpURLConnection connection) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		StringBuilder builder = new StringBuilder();
		String output;
		while ((output = reader.readLine()) != null)
			builder.append(output);
		System.out.println(builder.toString());
		return builder.toString();
	}

	private void checkResponse(HttpURLConnection connection) throws IOException {
		int responseCode = connection.getResponseCode();
		if (responseCode != 200)
			throw new RuntimeException("Error not submitted. Code: " + responseCode + ". " + connection.getResponseMessage() + "\n");
	}

	private void sendStory(HttpURLConnection connection, PivotalStory pivotalStory) throws IOException {
		final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		osw.write(pivotalStory.toString());
		osw.close();
	}

	private HttpURLConnection createConnection(String method, String url) throws IOException {
		HttpURLConnection httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
		httpcon.setDoOutput(true);
		httpcon.setRequestProperty("Content-Type", "application/json");
		httpcon.setRequestProperty("X-TrackerToken", "ae3d1e4d4bcb011927e2768d7aa39f3a");
		httpcon.setRequestMethod(method);
		httpcon.connect();
		return httpcon;
	}

	private class PivotalStory {
		int id;
		int project_id;
		String name = "Error in plugin v." + properties.get(PLUGIN_VERSION).toString().trim();
		String description = buildDescription(PivotalLoggingEventSubmitter.this.description);
		String story_type = "bug";
		String current_state = "unstarted";
		String url;
		String comment = (String) properties.get(PLUGIN_ERROR_ADDITIONAL_INFO);

		@Override
		public String toString() {
			return "{" +
				"\"current_state\": \"" + current_state + '"' +
				",\"story_type\": \"" + story_type + '"' +
				",\"name\": \"" + name + '"' +
				",\"description\": \"" + description + '"' +
				'}';
		}


		private String buildDescription(String description) {
			StringBuilder builder = new StringBuilder();
			builder.append(PLUGIN_ID).append(": ").append(properties.get(PLUGIN_ID)).append("\n");
			builder.append(PLUGIN_NAME).append(": ").append(properties.get(PLUGIN_NAME)).append("\n");
			builder.append(PLUGIN_VERSION).append(": ").append(properties.get(PLUGIN_VERSION).toString().trim()).append("\n");
			return builder.append(description).toString().replaceAll("'|!", "").replace("\n", "\\\\n").replace("\t", "\\\\t");
		}
	}

	public static class SubmitException extends Throwable {
		public SubmitException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
