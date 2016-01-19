package tara.intellij.codeinsight.intentions.dialog;

import com.intellij.openapi.module.Module;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static tara.intellij.lang.psi.impl.TaraUtil.getResourcesRoot;

public class CreateStringValues extends JDialog {
    private static final String PROPERTIES = ".properties";
    private String key;
    private JPanel contentPane;
    private JButton OKButton;
    private JButton cancelButton;
    private JTextField valueField;
    private JComboBox languages;
    private JButton applyButton;
    private boolean ok;
    private File messagesDirectory;

    public CreateStringValues(Module module, String key) {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Set i18n values");
        setLocationRelativeTo(this.getParent());
        getRootPane().setDefaultButton(OKButton);
        OKButton.addActionListener(e -> onOK());
        applyButton.addActionListener(e -> onApply());
        cancelButton.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        messagesDirectory = new File(getResourcesRoot(module), "messages");
        this.key = key;
        initLanguagesBox();
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initLanguagesBox() {
        if (!messagesDirectory.exists()) return;
        for (File messageFile : messagesDirectory.listFiles((dir, name) -> name.endsWith(PROPERTIES)))
            languages.addItem(messageFile.getName());
    }

    private void onOK() {
        ok = true;
        dispose();
    }

    private void onApply() {
        final String language = languages.getSelectedItem().toString();
        if (language.isEmpty() || valueField.getText().isEmpty()) return;
        final File file = new File(messagesDirectory, language + PROPERTIES);
        if (!file.exists() && !createNewFile(file)) return;
        put(file);
    }

    private void put(File file) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(file));
            p.put(key, valueField.getText());
            p.store(new FileOutputStream(file), language() + " messages");
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

    public String language() {
        return languages.getSelectedItem().toString().split(" ")[0];
    }

    public boolean isOk() {
        return ok;
    }

    public String value() {
        return this.valueField.getText();
    }

    public String name() {
        return languages.getSelectedItem().toString().split("\\(")[1].replace(")", "").trim();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
