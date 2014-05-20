package monet.::projectName::.intellij.highlighting;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import gnu.trove.THashMap;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class ::projectProperName::SyntaxHighlighter extends SyntaxHighlighterBase implements ::projectProperName::Types {

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("::projectProperName::_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("::projectProperName::_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("::projectProperName::_OPERATOR", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("::projectProperName::_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("::projectProperName::_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOCUMENTATION = createTextAttributesKey("::projectProperName::_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("::projectProperName::_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("::projectProperName::_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("::projectProperName::_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("::projectProperName::_BAD_CHARACTER");
	public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<>(6);
	public static final TextAttributesKey UNRESOLVED_ACCESS = TextAttributesKey.createTextAttributesKey("Unresolved reference");
	public static final TextAttributesKey WARNING = TextAttributesKey.createTextAttributesKey("Annotation warning");
	public static final TextAttributesKey ANNOTATION_ERROR = TextAttributesKey.createTextAttributesKey("Annotation unsupported in this context");


	static {
		WARNING.getDefaultAttributes().setForegroundColor(JBColor.LIGHT_GRAY);
		WARNING.getDefaultAttributes().setEffectType(EffectType.WAVE_UNDERSCORE);
		UNRESOLVED_ACCESS.getDefaultAttributes().setForegroundColor(JBColor.RED);
		UNRESOLVED_ACCESS.getDefaultAttributes().setFontType(Font.BOLD);
		BAD_CHARACTER.getDefaultAttributes().setForegroundColor(JBColor.RED);
		BAD_CHARACTER.getDefaultAttributes().setFontType(Font.BOLD);
		ANNOTATION_ERROR.getDefaultAttributes().setForegroundColor(JBColor.RED);
		ANNOTATION_ERROR.getDefaultAttributes().setFontType(Font.BOLD);
	}

	static {
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.identifier"), null));
		DISPLAY_NAMES.put(KEYWORD, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.keyword"), null));
		DISPLAY_NAMES.put(PRIMITIVE, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.primitive"), null));
		DISPLAY_NAMES.put(STRING, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.types.string"), null));
		DISPLAY_NAMES.put(DOCUMENTATION, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.comment"), null));
		DISPLAY_NAMES.put(MODIFIERS, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.modifier"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.operator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<>(::projectProperName::Bundle.message("invalid.::projectName::.definition.character"), HighlightSeverity.ERROR));
	}

	private static final Map<IElementType, TextAttributesKey> KEYS;

	\@NotNull
	\@Override
	public Lexer getHighlightingLexer() {
		return new ::projectProperName::HighlighterLexAdapter();
	}

	static {
		KEYS = new THashMap<>();

		KEYS.put(IDENTIFIER_KEY, IDENTIFIER);

::highlightKey::

		KEYS.put(CASE_KEY, KEYWORD);
		KEYS.put(BASE_KEY, KEYWORD);
		KEYS.put(IMPORT_KEY, KEYWORD);
		KEYS.put(PACKAGE, KEYWORD);
		KEYS.put(OPEN_AN, ANNOTATION);
		KEYS.put(CLOSE_AN, ANNOTATION);
		KEYS.put(VAR, KEYWORD);

		KEYS.put(REQUIRED, ANNOTATION);
		KEYS.put(MULTIPLE, ANNOTATION);
		KEYS.put(SINGLETON, ANNOTATION);
		KEYS.put(INTENTION_KEY, ANNOTATION);
		KEYS.put(ROOT, ANNOTATION);
		KEYS.put(GENERIC, ANNOTATION);
		KEYS.put(COLON, OPERATOR);
		KEYS.put(LEFT_SQUARE, OPERATOR);
		KEYS.put(RIGHT_SQUARE, OPERATOR);

		KEYS.put(WORD_KEY, PRIMITIVE);
		KEYS.put(STRING_TYPE, PRIMITIVE);
		KEYS.put(DOUBLE_TYPE, PRIMITIVE);
		KEYS.put(INT_TYPE, PRIMITIVE);
		KEYS.put(UID_TYPE, PRIMITIVE);

		KEYS.put(ABSTRACT, MODIFIERS);
		KEYS.put(FINAL, MODIFIERS);

		KEYS.put(DOC_LINE, DOCUMENTATION);

		KEYS.put(DOUBLE_VALUE_KEY, NUMBERS);
		KEYS.put(NATURAL_VALUE_KEY, NUMBERS);
		KEYS.put(NEGATIVE_VALUE_KEY, NUMBERS);
		KEYS.put(BOOLEAN_VALUE_KEY, NUMBERS);
		KEYS.put(STRING_VALUE_KEY, STRING);
		KEYS.put(TokenType.WHITE_SPACE, null);
		KEYS.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	\@NotNull
	\@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(KEYS.get(tokenType));
	}

}