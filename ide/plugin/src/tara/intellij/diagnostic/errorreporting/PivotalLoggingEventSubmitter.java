package tara.intellij.diagnostic.errorreporting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.intellij.openapi.diagnostic.Logger;
import tara.intellij.TaraRuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

public class PivotalLoggingEventSubmitter {

	private static final String PROJECT = "www.pivotaltracker.com/services/v5/projects/1022010/";
	private static final String TOKEN = "ae3d1e4d4bcb011927e2768d7aa39f3a";
	public static final String STORIES_URL = "https://" + PROJECT + "stories";
	public static final String COMMENTS = "/comments";
	private static final Logger LOG = Logger.getInstance(PivotalLoggingEventSubmitter.class.getName());
	private static final String PLUGIN_ID = "plugin.id";
	private static final String PLUGIN_VERSION = "plugin.version";
	private static final String PLUGIN_NAME = "plugin.name";
	private static final String REPORT_ADDITIONAL_INFO = "report.additionalInfo";
	private static final String REPORT_DESCRIPTION = "report.description";
	private static final String REPORT_TITLE = "report.title";
	private static final String REPORT_TYPE = "report.type";
	private Properties properties;

	public PivotalLoggingEventSubmitter(Properties properties) {
		this.properties = properties;
	}

	public void submit() {
		try {
			PivotalStory story = new PivotalStory();
			String response = createStory(story);
			addInfo(story, new JsonParser().parse(response));
			addCommentary(story);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
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
	}

	private void addComments(HttpURLConnection connection, PivotalStory story) throws IOException {
		final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), Charset.defaultCharset());
		osw.write("{\"text\":\"" + story.comment + "\"}");
		osw.close();
	}

	private void addInfo(PivotalStory story, JsonElement element) {
		JsonObject jobject = element.getAsJsonObject();
		story.id = jobject.get("id").getAsInt();
		story.url = STORIES_URL + "/" + story.id;
	}

	private String getResponse(HttpURLConnection connection) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
		StringBuilder builder = new StringBuilder();
		String output;
		while ((output = reader.readLine()) != null)
			builder.append(output);
		reader.close();
		return builder.toString();
	}

	private void checkResponse(HttpURLConnection connection) throws IOException {
		int responseCode = connection.getResponseCode();
		if (responseCode != 200)
			throw new TaraRuntimeException("Error not submitted. Code: " + responseCode + ". " + connection.getResponseMessage() + "\n");
	}

	private void sendStory(HttpURLConnection connection, PivotalStory pivotalStory) throws IOException {
		final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), Charset.defaultCharset());
		osw.write(pivotalStory.asJson().toString());
		osw.close();
	}

	private HttpURLConnection createConnection(String method, String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("X-TrackerToken", TOKEN);
		connection.setRequestMethod(method);
		connection.connect();
		return connection;
	}

	public static class SubmitException extends Exception {
		public SubmitException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	private class PivotalStory {
		int id;
		String name = buildName();
		String description = buildDescription(PivotalLoggingEventSubmitter.this.properties.get(REPORT_DESCRIPTION).toString());
		String storyType = getReportType();
		String currentState = "unstarted";
		String url;
		String comment = (String) properties.get(REPORT_ADDITIONAL_INFO);

		private String buildName() {
			Object title = properties.get(REPORT_TITLE);
			return "Error in plugin v." + properties.get(PLUGIN_VERSION).toString().trim() + (title != null ? ": " + title.toString() : "");
		}

		private String buildDescription(String description) {
			StringBuilder builder = new StringBuilder();
			builder.append(PLUGIN_ID).append(": ").append(properties.get(PLUGIN_ID)).append("\n");
			builder.append(PLUGIN_NAME).append(": ").append(properties.get(PLUGIN_NAME)).append("\n");
			builder.append(PLUGIN_VERSION).append(": ").append(properties.get(PLUGIN_VERSION).toString().trim()).append("\n");
			return builder.append(description).toString();
		}

		public String getReportType() {
			Object reportType = properties.get(REPORT_TYPE);
			return reportType != null ? reportType.toString().replace("apunt", "feature") : "bug";
		}

		public JsonElement asJson() {
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("name", new JsonPrimitive(name));
			jsonObject.add("current_state", new JsonPrimitive(currentState));
			jsonObject.add("story_type", new JsonPrimitive(storyType));
			jsonObject.add("description", new JsonPrimitive(description));
			return jsonObject;
		}
	}

}
