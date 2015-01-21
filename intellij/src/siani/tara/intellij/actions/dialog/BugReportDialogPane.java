package siani.tara.intellij.actions.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class BugReportDialogPane extends DialogWrapper {

	private JPanel dialogContents;
	private JLabel info;
	private JTextArea reportText;
	private JComboBox reportType;
	private JTextField title;

	public BugReportDialogPane(final Project project) {
		super(project, false);
		init();
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		return dialogContents;
	}

	public String getReportTitle() {
		return title.getText();
	}

	public String getReportDescription() {
		return reportText.getText();
	}

	public String getReportType() {
		return reportType.getSelectedItem().toString();
	}
}
