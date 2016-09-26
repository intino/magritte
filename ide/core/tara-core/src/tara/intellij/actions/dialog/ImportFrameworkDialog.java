package tara.intellij.actions.dialog;

import tara.intellij.framework.ArtifactoryConnector;

import javax.swing.*;
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
	private JComboBox versions;
	private JComboBox languages;
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
		initLanguagesBox();
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		ok = true;
		dispose();
	}

	private void initLanguagesBox() {
		try {
			new ArtifactoryConnector().languages().forEach(l -> languages.addItem(l));
		} catch (IOException ignored) {
		}
		languages.addActionListener(e -> {
			if (((JComboBox) e.getSource()).getItemCount() == 0) return;
			calculateVersions();
		});
		calculateVersions();
	}

	private void calculateVersions() {
		try {
			this.versions.removeAllItems();
			if (language().isEmpty()) return;
			final List<String> versions = new ArtifactoryConnector().versions(language());
			Collections.reverse(versions);
			versions.forEach(this.versions::addItem);
		} catch (IOException e) {
			this.versions.removeAllItems();
		}
	}

	public String language() {
		final Object selectedItem = languages.getSelectedItem();
		return selectedItem == null ? "" : selectedItem.toString();
	}

	public boolean isOk() {
		return ok;
	}

	public String selectedVersion() {
		return versions.getSelectedItem() == null ? "" : versions.getSelectedItem().toString();
	}

	public String name() {
		return languages.getSelectedItem().toString().split("\\(")[0].trim();
	}

	private void onCancel() {
		dispose();
	}
}