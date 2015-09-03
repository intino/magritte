package tara.intellij.actions.dialog;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ImportLanguageDialog extends DialogWrapper {
	private JTextField path;
	private JCheckBox extensible;
	private JPanel dialogContents;
	private JButton sourceButton;
	private JButton importButton;
	private JTextField sourcePath;


	public ImportLanguageDialog(@Nullable Project project, boolean canBeParent) {
		super(project, canBeParent);
		extensible.addChangeListener(e -> {
			final boolean selected = ((JCheckBox) e.getSource()).isSelected();
			sourceButton.setEnabled(selected);
			sourcePath.setEnabled(selected);
			if (!selected) sourcePath.setText("");
		});
		importButton.addActionListener(e -> {
			VirtualFile file = FileChooser.chooseFile(new LanguageFileChooserDescriptor(), null, null);
			if (file != null) path.setText(file.getPath());
		});
		importButton.addActionListener(e -> {
			VirtualFile file = FileChooser.chooseFile(new SourceFolderChooserDescriptor(), null, null);
			if (file != null) sourcePath.setText(file.getPath());
		});
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		return dialogContents;
	}


	public String getLanguagePath() {
		return path.getText();
	}

	public String getSourcePath() {
		return sourcePath.getText();
	}

	public boolean isExtensible() {
		return extensible.isSelected();
	}

}
