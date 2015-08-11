package tara.intellij.formatter;

import com.intellij.formatting.FormattingMode;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import tara.intellij.lang.TaraLanguage;

public class TaraBlockContext {

	private final CommonCodeStyleSettings mySettings;
	private final TaraCodeStyleSettings myTaraSettings;
	private final SpacingBuilder mySpacingBuilder;
	private final FormattingMode myMode;

	public TaraBlockContext(CodeStyleSettings settings, SpacingBuilder builder, FormattingMode mode) {
		mySettings = settings.getCommonSettings(TaraLanguage.INSTANCE);
		myTaraSettings = settings.getCustomSettings(TaraCodeStyleSettings.class);
		mySpacingBuilder = builder;
		myMode = mode;
	}

	public CommonCodeStyleSettings getSettings() {
		return mySettings;
	}

	public TaraCodeStyleSettings getPySettings() {
		return myTaraSettings;
	}

	public SpacingBuilder getSpacingBuilder() {
		return mySpacingBuilder;
	}

	public FormattingMode getMode() {
		return myMode;
	}
}
