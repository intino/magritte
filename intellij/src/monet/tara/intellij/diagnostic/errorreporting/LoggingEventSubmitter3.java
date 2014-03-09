package monet.tara.intellij.diagnostic.errorreporting;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class LoggingEventSubmitter3 {
	private static final String TARA_MAIL_ADMIN_FROM = "tara.mail.admin.from";
	private static final String TARA_MAIL_ADMIN_TO = "tara.mail.admin.to";
	private static final String TARA_MAIL_ADMIN_CC = "tara.mail.admin.cc";
	private static final String TARA_SERVER_URL = "tara.admin.server.url";
	private static final String PLUGIN_ID = "plugin.id";
	private static final String PLUGIN_VERSION = "plugin.version";
	private static final String PLUGIN_NAME = "plugin.name";
	private static final Logger LOGGER = Logger.getInstance(LoggingEventSubmitter3.class.getName());
	private final String subject;
	private String body;
	private Properties properties;

	public LoggingEventSubmitter3(Properties properties, String subject, String textBody) {
		this.properties = properties;
		this.subject = subject;
		this.body = toHtml(prepareBody(textBody));
	}

	private String prepareBody(String textBody) {
		StringBuilder builder = new StringBuilder();
		builder.append(PLUGIN_ID).append(": ").append(properties.get(PLUGIN_ID)).append("\n");
		builder.append(PLUGIN_NAME).append(": ").append(properties.get(PLUGIN_NAME)).append("\n");
		builder.append(PLUGIN_VERSION).append(": ").append(properties.get(PLUGIN_VERSION)).append("\n\n");
		return builder.append(textBody).toString();
	}


	void submit() throws SubmitException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("About to send logging events");

		URLConnection connection;
		connection = prepareConnection();
		DataOutputStream stream = sendErrors(connection);
		processResponse(connection, stream);
		disconnect(connection);
	}

	private void disconnect(URLConnection connection) {
		if (connection instanceof HttpURLConnection) {
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			try {
				int responseCode = httpConnection.getResponseCode();
				String responseMessage = httpConnection.getResponseMessage();

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Response code: " + responseCode);
					LOGGER.debug("Response message: " + responseMessage);
				}
			} catch (IOException ioe) {
				LOGGER.info("Unable to retrieve response status");
			} finally {
				httpConnection.disconnect();
			}
		}
	}

	private URLConnection prepareConnection() throws SubmitException {
		URLConnection connection;
		try {
			String server_url = (String) properties.get(TARA_SERVER_URL);
			URL url = new URL(server_url);
			connection = url.openConnection();
			connection.setRequestProperty("Content-type", "application/octet-stream");
			connection.setConnectTimeout(15 * 1000);
			connection.setReadTimeout(15 * 1000);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			connection.getOutputStream();
		} catch (IOException ioe) {
			LOGGER.info("Unable to connect to server", ioe);
			throw new SubmitException("Unable to connect to server", ioe);
		}
		return connection;
	}

	private DataOutputStream sendErrors(URLConnection connection) throws SubmitException {
		DataOutputStream stream = null;
		try {
			stream = new DataOutputStream(new DeflaterOutputStream(connection.getOutputStream()));
			stream.writeUTF(get(PLUGIN_ID) != null ? get(PLUGIN_ID) : "");
			stream.writeUTF(get(PLUGIN_NAME) != null ? get(PLUGIN_NAME) : "");
			stream.writeUTF(get(PLUGIN_VERSION) != null ? get(PLUGIN_VERSION) : "");
			//stream.writeUTF(ideaBuild != null ? ideaBuild : "");
			stream.writeUTF(get(TARA_MAIL_ADMIN_TO) != null ? StringUtil.join(get(TARA_MAIL_ADMIN_TO), ":") : "");
			stream.writeUTF(get(TARA_MAIL_ADMIN_CC) != null ? StringUtil.join(get(TARA_MAIL_ADMIN_CC), ":") : "");
			//stream.writeInt(events.length);
			stream.writeUTF(subject + "\n");
			stream.writeUTF(body);
			stream.flush();
		} catch (IOException ioe) {
			LOGGER.info("Unable to send data to server", ioe);
			throw new SubmitException("Unable to send data to server", ioe);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException ioe) {
					LOGGER.info("Unable to disconnect from server after sending data", ioe);
				}
			}
		}
		LOGGER.debug("Logging events sent successfully");
		return stream;
	}

	private void processResponse(URLConnection connection, DataOutputStream stream) throws SubmitException {
		DataInputStream inputStream;
		try {
			inputStream = new DataInputStream(new InflaterInputStream(connection.getInputStream()));
			boolean statusOK = inputStream.readBoolean();
			String statusMessage = inputStream.readUTF();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Status OK: " + statusOK);
				LOGGER.debug("Status message: " + statusMessage);
			}

			if (!statusOK) {
				LOGGER.info("Status returned by server is NOK");
				throw new SubmitException(statusMessage, null);
			} else {
				LOGGER.info("Status returned by server is OK");
			}
		} catch (IOException ioe) {
			LOGGER.info("Unable to receive data from server", ioe);
			throw new SubmitException("Unable to receive data from server", ioe);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException ioe) {
					LOGGER.info("Unable to disconnect from server after receiving data", ioe);
				}
			}
		}
	}


	private String toHtml(String eventsProcessed) {
		return "<html> <body><pre>" + eventsProcessed + "</pre></body></html>";
	}

	public String get(String property) {
		return (String) properties.get(property);
	}

	public static class SubmitException extends Throwable {
		public SubmitException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
