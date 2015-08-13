package tara.intellij.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class TaraCodeStyleSettings extends CustomCodeStyleSettings {

	public static final boolean SPACE_WITHIN_BRACES = false;
	public static final boolean SPACE_BEFORE_PY_COLON = false;
	public static final boolean SPACE_AFTER_PY_COLON = true;
	public static final boolean SPACE_BEFORE_LBRACKET = false;
	public static final boolean SPACE_AROUND_EQ_IN_NAMED_PARAMETER = false;
	public static final boolean SPACE_AROUND_EQ_IN_KEYWORD_ARGUMENT = false;
	public static final boolean SPACE_BEFORE_BACKSLASH = true;

	public static final boolean BLANK_LINE_AT_FILE_END = true;

	public static final boolean ALIGN_COLLECTIONS_AND_COMPREHENSIONS = true;
	public static final boolean ALIGN_MULTILINE_IMPORTS = true;

	public static final boolean NEW_LINE_AFTER_COLON = false;

	public static final boolean SPACE_AFTER_NUMBER_SIGN = true;
	public static final boolean SPACE_BEFORE_NUMBER_SIGN = true;

	public static final int BLANK_LINES_AFTER_LOCAL_IMPORTS = 0;


	public TaraCodeStyleSettings(CodeStyleSettings container) {
		super("Tara", container);
	}
}
