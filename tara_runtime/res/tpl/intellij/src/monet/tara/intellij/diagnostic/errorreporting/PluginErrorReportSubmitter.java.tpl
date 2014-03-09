package monet.::projectName::.intellij.diagnostic.errorreporting;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class PluginErrorReportSubmitter extends ErrorReportSubmitter {
	private static final Logger LOGGER = Logger.getInstance(LoggingEventSubmitter.class.getName());
	@NonNls
	private static final String ERROR_SUBMITTER_PROPERTIES_PATH = "error_reporting" + File.separator + "errorReporter.properties";
	@NonNls
	private static final String PLUGIN_ID_PROPERTY_KEY = "plugin.id";
	@NonNls
	private static final String PLUGIN_NAME_PROPERTY_KEY = "plugin.name";
	@NonNls
	private static final String PLUGIN_VERSION_PROPERTY_KEY = "plugin.version";
	@NonNls
	private static final String EMAIL_TO_PROPERTY_KEY = "::projectName::.mail.admin.to";

	public String getReportActionText() {
		return PluginErrorReportSubmitterBundle.message("report.error.to.plugin.vendor");
	}

	public SubmittedReportInfo submit(IdeaLoggingEvent[] events, Component parentComponent) {
		PluginDescriptor pluginDescriptor = getPluginDescriptor();
		final Properties reportingProperties = new Properties();
		queryPluginDescriptor(pluginDescriptor, reportingProperties);
		LOGGER.debug("Properties read from plugin descriptor: " + reportingProperties);
		queryPropertiesFile(pluginDescriptor, reportingProperties);
		LOGGER.debug("Final properties to be applied: " + reportingProperties);
		final LoggingEventSubmitter.SubmitException[] ex = new LoggingEventSubmitter.SubmitException[]{null};
		Runnable runnable = getRunnable(events, reportingProperties);
		ProgressManager progressManager = ProgressManager.getInstance();
		progressManager.runProcessWithProgressSynchronously(runnable, PluginErrorReportSubmitterBundle.message("progress.dialog.title"), false, null);
		if (processExceptions(parentComponent, ex[0]))
			return new SubmittedReportInfo(null, null, SubmittedReportInfo.SubmissionStatus.FAILED);
		LOGGER.info("Error submission successful");
		Messages.showInfoMessage(parentComponent, PluginErrorReportSubmitterBundle.message("successful.dialog.message"),
			PluginErrorReportSubmitterBundle.message("successful.dialog.title"));
		return new SubmittedReportInfo(null, null, SubmittedReportInfo.SubmissionStatus.NEW_ISSUE);
	}


	private boolean processExceptions(Component parentComponent, LoggingEventSubmitter.SubmitException e) {
		if (e != null) {
			LOGGER.info("Error submission failed", e);
			Messages.showErrorDialog(parentComponent, e.getMessage(), PluginErrorReportSubmitterBundle.message("error.dialog.title"));
			return true;
		}
		return false;
	}

	private Runnable getRunnable(final IdeaLoggingEvent[] events, final Properties reportingProperties) {
		return new Runnable() {
			public void run() {
				ProgressIndicator indicator = ProgressManager.getInstance().getProgressIndicator();
				indicator.setText(PluginErrorReportSubmitterBundle.message("progress.dialog.text"));
				indicator.setIndeterminate(true);
				String eventsProcessed = processEvents(events);
				LoggingEventSubmitter submitter = new LoggingEventSubmitter(reportingProperties, "::projectProperName:: Plugin Error", eventsProcessed);
				submitter.submit();
			}
		};
	}


	private String processEvents(IdeaLoggingEvent[] events) {
		StringBuilder stream = new StringBuilder();
		for (IdeaLoggingEvent event : events) {
			stream.append(event.getMessage() != null ? event.getMessage() : "");
			stream.append(event.getThrowableText() != null ? event.getThrowableText() : "");
		}
		return stream.toString();
	}

	private void queryPluginDescriptor(@NotNull PluginDescriptor pluginDescriptor, @NotNull Properties properties) {
		PluginId descPluginId = pluginDescriptor.getPluginId();
		if (descPluginId != null)
			if (!StringUtil.isEmptyOrSpaces(descPluginId.getIdString()))
				properties.put(PLUGIN_ID_PROPERTY_KEY, descPluginId.getIdString());
		if (pluginDescriptor instanceof IdeaPluginDescriptor) {
			IdeaPluginDescriptor ideaPluginDescriptor = (IdeaPluginDescriptor) pluginDescriptor;
			if (!StringUtil.isEmptyOrSpaces(ideaPluginDescriptor.getName()))
				properties.put(PLUGIN_NAME_PROPERTY_KEY, ideaPluginDescriptor.getName());
			String descVersion = ideaPluginDescriptor.getVersion();
			if (!StringUtil.isEmptyOrSpaces(descVersion))
				properties.put(PLUGIN_VERSION_PROPERTY_KEY, descVersion);
			String descEmail = ideaPluginDescriptor.getVendorEmail();
			if (!StringUtil.isEmptyOrSpaces(descEmail))
				properties.put(EMAIL_TO_PROPERTY_KEY, descEmail);
		}
	}

	private void queryPropertiesFile(@NotNull PluginDescriptor pluginDescriptor, @NotNull Properties properties) {
		ClassLoader loader = pluginDescriptor.getPluginClassLoader();
		InputStream stream = loader.getResourceAsStream(ERROR_SUBMITTER_PROPERTIES_PATH);
		if (stream != null) {
			LOGGER.debug("Reading ErrorReporter.properties from file system: " + ERROR_SUBMITTER_PROPERTIES_PATH);
			try {
				properties.load(stream);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.info("Could not read in ErrorReporter.properties from file system", e);
			}
		}
	}


}
