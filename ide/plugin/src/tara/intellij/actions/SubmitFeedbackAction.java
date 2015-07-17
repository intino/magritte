package tara.intellij.actions;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.actions.dialog.SummitFeedbackDialogPane;
import tara.intellij.diagnostic.errorreporting.PivotalLoggingEventSubmitter;
import tara.intellij.lang.TaraIcons;
import tara.intellij.diagnostic.errorreporting.PluginErrorReportSubmitterBundle;

import java.util.Properties;

public class SubmitFeedbackAction extends AnAction implements DumbAware {
	public static final Logger LOG = Logger.getInstance("Config module Action");
	private static final String PLUGIN_ID_PROPERTY_KEY = "plugin.id";
	private static final String PLUGIN_NAME_PROPERTY_KEY = "plugin.name";
	private static final String PLUGIN_VERSION_PROPERTY_KEY = "plugin.version";
	private static final String REPORT_DESCRIPTION = "report.description";
	private static final String REPORT_TITLE = "report.title";
	private static final String REPORT_TYPE = "report.type";

	@Override
	public void update(@NotNull AnActionEvent e) {
		e.getPresentation().setVisible(true);
		e.getPresentation().setEnabled(true);
		e.getPresentation().setIcon(TaraIcons.ICON_13);
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		if (e.getProject() == null) {
			LOG.error("actionPerformed no project for " + e);
			return;
		}
		SummitFeedbackDialogPane configDialog = new SummitFeedbackDialogPane(e.getProject());
		configDialog.show();
		if (configDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
			sendReport(configDialog.getReportType(), configDialog.getReportTitle(), configDialog.getReportDescription());
			Messages.showInfoMessage(e.getProject(), PluginErrorReportSubmitterBundle.message("successful.dialog.message"), PluginErrorReportSubmitterBundle.message("successful.dialog.title"));
		}
	}

	private void sendReport(String type, String reportTitle, String reportDescription) {
		IdeaPluginDescriptor plugin = PluginManager.getPlugin(PluginId.getId("siani.dev.tara"));
		final Properties properties = createErrorProperties(plugin, reportTitle, reportDescription, type);
		PivotalLoggingEventSubmitter submitter = new PivotalLoggingEventSubmitter(properties);
		submitter.submit();

	}

	private Properties createErrorProperties(PluginDescriptor descriptor, String title, String description, String type) {
		Properties properties = new Properties();
		PluginId descPluginId = descriptor.getPluginId();
		if (descPluginId != null && !StringUtil.isEmptyOrSpaces(descPluginId.getIdString()))
			properties.put(PLUGIN_ID_PROPERTY_KEY, descPluginId.getIdString());
		if (descriptor instanceof IdeaPluginDescriptor) {
			IdeaPluginDescriptor ideaPluginDescriptor = (IdeaPluginDescriptor) descriptor;
			if (!StringUtil.isEmptyOrSpaces(ideaPluginDescriptor.getName()))
				properties.put(PLUGIN_NAME_PROPERTY_KEY, ideaPluginDescriptor.getName());
			String descVersion = ideaPluginDescriptor.getVersion();
			if (!StringUtil.isEmptyOrSpaces(descVersion))
				properties.put(PLUGIN_VERSION_PROPERTY_KEY, descVersion);
			if (description != null)
				properties.put(REPORT_DESCRIPTION, description);
			if (title != null)
				properties.put(REPORT_TITLE, title);
			if (type != null)
				properties.put(REPORT_TYPE, type);
		}
		return properties;
	}
}