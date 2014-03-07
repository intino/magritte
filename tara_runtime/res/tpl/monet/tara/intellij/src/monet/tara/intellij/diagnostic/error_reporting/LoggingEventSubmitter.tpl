package monet.::projectName::.intellij.diagnostic.error_reporting;

import com.intellij.openapi.diagnostic.Logger;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class LoggingEventSubmitter {
	private static final String TARA_MAIL_ADMIN_FROM = "::projectName::.mail.admin.from";
	private static final String TARA_MAIL_ADMIN_TO = "::projectName::.mail.admin.to";
	private static final String TARA_MAIL_ADMIN_HOST = "::projectName::.mail.admin.host";
	private static final String TARA_MAIL_ADMIN_PORT = "::projectName::.mail.admin.port";
	private static final String TARA_MAIL_ADMIN_USERNAME = "::projectName::.mail.admin.username";
	private static final String TARA_MAIL_ADMIN_PASSWORD = "::projectName::.mail.admin.password";
	private static final String PLUGIN_ID = "plugin.id";
	private static final String PLUGIN_VERSION = "plugin.version";
	private static final String PLUGIN_NAME = "plugin.name";
	private static final Logger LOGGER = Logger.getInstance(LoggingEventSubmitter.class.getName());
	private final String subject;
	private String htmlBody;
	private Properties emailProperties;

	public LoggingEventSubmitter(Properties emailProperties, String subject, String textBody) {
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
		this.emailProperties = emailProperties;
		this.subject = subject;
		this.htmlBody = toHtml(prepareBody(textBody));
	}

	private String prepareBody(String textBody) {
		StringBuilder builder = new StringBuilder();
		builder.append(PLUGIN_ID).append(": ").append(emailProperties.get(PLUGIN_ID)).append("\n");
		builder.append(PLUGIN_NAME).append(": ").append(emailProperties.get(PLUGIN_NAME)).append("\n");
		builder.append(PLUGIN_VERSION).append(": ").append(emailProperties.get(PLUGIN_VERSION)).append("\n\n");
		return builder.append(textBody).toString();
	}


	void submit() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("About to send logging events");

		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.connectiontimeout", 1000);

			Session session = Session.getInstance(props);
			MimeMessage message = prepareMimeMessage(session);
			Transport transport = prepareTransport(session);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private Transport prepareTransport(Session session) throws MessagingException {
		Transport transport = session.getTransport("smtp");
		transport.connect(emailProperties.getProperty(TARA_MAIL_ADMIN_HOST),
			Integer.parseInt(emailProperties.getProperty(TARA_MAIL_ADMIN_PORT)),
			emailProperties.getProperty(TARA_MAIL_ADMIN_USERNAME),
			emailProperties.getProperty(TARA_MAIL_ADMIN_PASSWORD));
		return transport;
	}

	private MimeMessage prepareMimeMessage(Session session) throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailProperties.getProperty(TARA_MAIL_ADMIN_FROM)));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailProperties.getProperty(TARA_MAIL_ADMIN_TO)));
		message.setSubject(subject);
		message.setText(htmlBody);
		return message;
	}


	private String toHtml(String eventsProcessed) {
		return "<html><body><pre>" + eventsProcessed + "</pre></body></html>";
	}

	public static class SubmitException extends Throwable {
		public SubmitException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
