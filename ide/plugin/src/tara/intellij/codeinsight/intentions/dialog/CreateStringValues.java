package tara.intellij.codeinsight.intentions.dialog;

import com.intellij.openapi.module.Module;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static com.intellij.openapi.util.io.FileUtilRt.getNameWithoutExtension;
import static javax.swing.SwingConstants.LEFT;
import static tara.intellij.lang.psi.impl.TaraUtil.getResourcesRoot;

public class CreateStringValues extends JDialog {
	private static final String PROPERTIES = ".properties";
	private static final String MESSAGES = "messages";
	private static final String MESSAGE = "message_";
	private String key;
	private JPanel contentPane;
	private JButton OKButton;
	private JButton cancelButton;
	private JButton newLanguage;
	private JPanel valuesPanel;
	private Map<JComponent, JBTextField> fields = new LinkedHashMap<>();
	private File messagesDirectory;

	public CreateStringValues(Module module, String key) {
		this.OKButton.addActionListener(e -> onOK());
		this.newLanguage.addActionListener(e -> onNewLanguage());
		this.cancelButton.addActionListener(e -> onCancel());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
		this.messagesDirectory = new File(getResourcesRoot(module), MESSAGES);
		this.key = key;
		this.contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		initLanguages();
		setContentPane(contentPane);
		setModal(true);
		setResizable(true);
		setTitle("Set i18n values");
		setLocationByPlatform(true);
		setLocationRelativeTo(this.getParent());
		getRootPane().setDefaultButton(OKButton);
	}


	private void onOK() {
		save();
		dispose();
	}

	private void save() {
		for (Map.Entry<JComponent, JBTextField> entry : fields.entrySet()) {
			final File inFile = new File(messagesDirectory, MESSAGE + getText(entry.getKey()) + PROPERTIES);
			if (!inFile.exists() && !createNewFile(inFile)) continue;
			put(entry.getValue().getText(), inFile);
		}

	}

	private String getText(JComponent key) {
		return key instanceof JBLabel ? ((JBLabel) key).getText() : ((JBTextField) key).getText();
	}

	private void onNewLanguage() {
		final JBTextField value = new JBTextField();
		final JBTextField newLanguage = new JBTextField("New Language");
		newLanguage.setMaximumSize(new Dimension(30, 0));
		newLanguage.setPreferredSize(new Dimension(20, 0));
		newLanguage.setHorizontalAlignment(LEFT);
		fields.put(newLanguage, value);
		valuesPanel.add(newLanguage);
		valuesPanel.add(value);
		pack();
		repaint();
	}

	private void put(String value, File file) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(file));
			p.put(key, value);
			p.store(new FileOutputStream(file), getNameWithoutExtension(file.getName()) + " messages");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean createNewFile(File file) {
		try {
			file.getParentFile().mkdirs();
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void onCancel() {
		dispose();
	}

	private void createUIComponents() {
		final GridLayout layout = new GridLayout(0, 2);
		valuesPanel = new JBPanel<>(layout);
		layout.setVgap(5);
		layout.setHgap(30);
	}

	private void initLanguages() {
		if (!messagesDirectory.exists() || messagesDirectory.listFiles((dir, name) -> name.endsWith(PROPERTIES)).length == 0) {
			onNewLanguage();
			return;
		}
		for (File messageFile : messagesDirectory.listFiles((dir, name) -> name.endsWith(PROPERTIES))) {
			final JBLabel jbLabel = new JBLabel(name(messageFile));
			jbLabel.setMaximumSize(new Dimension(30, 0));
			jbLabel.setHorizontalAlignment(LEFT);
			fields.put(jbLabel, new JBTextField());
		}
		for (Map.Entry<JComponent, JBTextField> entry : fields.entrySet()) {
			valuesPanel.add(entry.getKey());
			valuesPanel.add(entry.getValue());
		}
		pack();
		repaint();
	}

	private String name(File messageFile) {
		return getNameWithoutExtension(messageFile.getName().split("_")[1]);
	}
}
