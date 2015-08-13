package tara.intellij.formatter;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import tara.intellij.lang.TaraLanguage;

public class TaraCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
	protected TaraCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
		super(TaraLanguage.INSTANCE, currentSettings, settings);
	}

}