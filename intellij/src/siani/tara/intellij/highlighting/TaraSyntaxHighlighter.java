package siani.tara.intellij.highlighting;

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
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.TaraTypes;

import java.awt.*;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TaraSyntaxHighlighter extends SyntaxHighlighterBase implements TaraTypes {

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tara_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("Tara_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("Tara_OPERATOR", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tara_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOCUMENTATION = createTextAttributesKey("Tara_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tara_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("Tara_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey REFERENCE = createTextAttributesKey("Tara_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
	public static final TextAttributesKey BRACKETS = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.BRACKETS);
	public static final TextAttributesKey SEMICOLON_KEY = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.SEMICOLON);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("Tara_BAD_CHARACTER");
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
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.identifier"), null));
		DISPLAY_NAMES.put(KEYWORD, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.keyword"), null));
		DISPLAY_NAMES.put(PRIMITIVE, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.primitive"), null));
		DISPLAY_NAMES.put(STRING, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.types.string"), null));
		DISPLAY_NAMES.put(DOCUMENTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.comment"), null));
		DISPLAY_NAMES.put(SEMICOLON_KEY, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.semicolon"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.operator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(NUMBERS, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.number"), null));
		DISPLAY_NAMES.put(BRACKETS, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<>(TaraBundle.message("invalid.tara.concept.character"), HighlightSeverity.ERROR));
	}

	private static final Map<IElementType, TextAttributesKey> KEYS;

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new TaraHighlighterLexAdapter();
	}

	static {
		KEYS = new THashMap<>();

		KEYS.put(METAIDENTIFIER_KEY, KEYWORD);
		KEYS.put(EMPTY_REF, KEYWORD);
		KEYS.put(INTENTION_KEY, KEYWORD);
		KEYS.put(METAMODEL, KEYWORD);
		KEYS.put(REQUIRED, ANNOTATION);
		KEYS.put(SINGLE, ANNOTATION);
		KEYS.put(ROOT, ANNOTATION);
		KEYS.put(TERMINAL, ANNOTATION);
		KEYS.put(PROPERTY, ANNOTATION);
		KEYS.put(PRIVATE, ANNOTATION);
		KEYS.put(NAMED, ANNOTATION);
		KEYS.put(RESOURCE_KEY, PRIMITIVE);
		KEYS.put(IDENTIFIER_KEY, IDENTIFIER);
		KEYS.put(CASE_KEY, KEYWORD);
		KEYS.put(USE_KEY, KEYWORD);
		KEYS.put(BOX_KEY, KEYWORD);
		KEYS.put(IS, ANNOTATION);
		KEYS.put(VAR, KEYWORD);
		KEYS.put(AS, KEYWORD);
		KEYS.put(ON, KEYWORD);
		KEYS.put(WITH, KEYWORD);
		KEYS.put(STAR, OPERATOR);
		KEYS.put(COLON, OPERATOR);
		KEYS.put(EQUALS, OPERATOR);
		KEYS.put(META_WORD, OPERATOR);
		KEYS.put(LIST, OPERATOR);

		KEYS.put(WORD_KEY, PRIMITIVE);
		KEYS.put(STRING_TYPE, PRIMITIVE);
		KEYS.put(DOUBLE_TYPE, PRIMITIVE);
		KEYS.put(INT_TYPE, PRIMITIVE);
		KEYS.put(DATE_TYPE, PRIMITIVE);
		KEYS.put(COORDINATE_TYPE, PRIMITIVE);
		KEYS.put(BOOLEAN_TYPE, PRIMITIVE);
		KEYS.put(NATURAL_TYPE, PRIMITIVE);
		KEYS.put(REFERENCE_TYPE, PRIMITIVE);

		KEYS.put(DOC_LINE, DOCUMENTATION);

		KEYS.put(DOUBLE_VALUE_KEY, NUMBERS);
		KEYS.put(NATURAL_VALUE_KEY, NUMBERS);
		KEYS.put(NEGATIVE_VALUE_KEY, NUMBERS);
		KEYS.put(BOOLEAN_VALUE_KEY, KEYWORD);
		KEYS.put(CODE_VALUE_KEY, REFERENCE);
		KEYS.put(STRING_VALUE_KEY, STRING);
		KEYS.put(STRING_MULTILINE_VALUE_KEY, STRING);
		KEYS.put(DATE_VALUE_KEY, NUMBERS);
		KEYS.put(COORDINATE_VALUE_KEY, NUMBERS);
		KEYS.put(TokenType.WHITE_SPACE, null);
		KEYS.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(KEYS.get(tokenType));
	}

}