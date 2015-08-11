package tara.intellij.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class TaraCodeStyleSettings extends CustomCodeStyleSettings {

	public boolean SPACE_WITHIN_BRACES = false;
	public boolean SPACE_BEFORE_PY_COLON = false;
	public boolean SPACE_AFTER_PY_COLON = true;
	public boolean SPACE_BEFORE_LBRACKET = false;
	public boolean SPACE_AROUND_EQ_IN_NAMED_PARAMETER = false;
	public boolean SPACE_AROUND_EQ_IN_KEYWORD_ARGUMENT = false;
	public boolean SPACE_BEFORE_BACKSLASH = true;

	public boolean BLANK_LINE_AT_FILE_END = true;

	public boolean ALIGN_COLLECTIONS_AND_COMPREHENSIONS = true;
	public boolean ALIGN_MULTILINE_IMPORTS = true;

	public boolean NEW_LINE_AFTER_COLON = false;

	public boolean SPACE_AFTER_NUMBER_SIGN = true;
	public boolean SPACE_BEFORE_NUMBER_SIGN = true;

	public int BLANK_LINES_AFTER_LOCAL_IMPORTS = 0;


	public TaraCodeStyleSettings(CodeStyleSettings container) {
		super("Tara", container);
	}
}
