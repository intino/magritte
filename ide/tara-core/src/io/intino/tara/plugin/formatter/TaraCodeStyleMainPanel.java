package io.intino.tara.plugin.formatter;

import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import io.intino.tara.plugin.lang.TaraLanguage;

class TaraCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
	TaraCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
		super(TaraLanguage.INSTANCE, currentSettings, settings);
	}

}
