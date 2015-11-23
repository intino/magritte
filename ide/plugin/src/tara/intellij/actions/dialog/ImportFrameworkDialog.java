package tara.intellij.actions.dialog;

import tara.intellij.framework.TaraHubConnector;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ImportFrameworkDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField key;
	private JComboBox versions;
	private JLabel name;
	private boolean ok;

	public ImportFrameworkDialog() {
		setContentPane(contentPane);
		setModal(true);
		setTitle("Import from Tara Hub");
		setLocationRelativeTo(this.getParent());
		getRootPane().setDefaultButton(buttonOK);
		buttonOK.addActionListener(e -> onOK());
		buttonCancel.addActionListener(e -> onCancel());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
		addKeyListener();
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		ok = true;
		dispose();
	}

	private void addKeyListener() {
		key.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				setName(e);
			}

			private void setName(DocumentEvent e) {
				try {
					final String name = new TaraHubConnector().nameOf(key.getText());
					buttonOK.setEnabled(name != null && !name.isEmpty());
					ImportFrameworkDialog.this.name.setText(name);
					calculateVersions();
				} catch (IOException ignored) {
					ImportFrameworkDialog.this.name.setText("");
				}

			}

			public void insertUpdate(DocumentEvent e) {
				setName(e);
			}

			public void removeUpdate(DocumentEvent e) {
				setName(e);
			}
		});
	}

	private void calculateVersions() {
		try {
			this.versions.removeAllItems();
			final List<String> versions = new TaraHubConnector().versions(key.getText());
			Collections.reverse(versions);
			versions.forEach(this.versions::addItem);
		} catch (IOException e) {
			this.versions.removeAllItems();
		}

	}

	public boolean isOk() {
		return ok;
	}

	public String language() {
		return key.getText();
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
}
