package tara.intellij.actions.dialog;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ImportLanguageDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField language;
	private JComboBox versions;
	private JLabel name;
	private boolean ok;

	public ImportLanguageDialog() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		buttonOK.addActionListener(e -> onOK());
		buttonCancel.addActionListener(e -> onCancel());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		ok = true;
		dispose();
	}

	public boolean isOk() {
		return ok;
	}

	public String language() {
		return language.getText();
	}

	public String selectedVersion() {
		return versions.getSelectedItem().toString();
	}

	public String name() {
		return name.getText();
	}

	private void onCancel() {
		dispose();
	}


	public static void main(String[] args) {
		ImportLanguageDialog dialog = new ImportLanguageDialog();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
