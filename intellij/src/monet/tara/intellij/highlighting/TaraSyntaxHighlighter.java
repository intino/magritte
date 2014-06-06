package monet.tara.intellij.highlighting;

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
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.lang.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TaraSyntaxHighlighter extends SyntaxHighlighterBase implements TaraTypes {

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tara_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("Tara_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("Tara_OPERATOR", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("Tara_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tara_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOCUMENTATION = createTextAttributesKey("Tara_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tara_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("Tara_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
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
		DISPLAY_NAMES.put(MODIFIERS, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.modifier"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.operator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.annotation"), null));
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

//gen %highlightKey%
		KEYS.put(METAIDENTIFIER_KEY, KEYWORD);
		KEYS.put(REQUIRED, ANNOTATION);
		KEYS.put(MULTIPLE, ANNOTATION);
		KEYS.put(SINGLETON, ANNOTATION);
		KEYS.put(INTENTION_KEY, ANNOTATION);
		KEYS.put(ROOT, ANNOTATION);
		KEYS.put(GENERIC, ANNOTATION);
		KEYS.put(HAS_NAME, ANNOTATION);
		KEYS.put(RESOURCE_KEY, PRIMITIVE);
		KEYS.put(PROPERTY, KEYWORD);
//end

		KEYS.put(IDENTIFIER_KEY, IDENTIFIER);
		KEYS.put(CASE_KEY, KEYWORD);
		KEYS.put(BASE_KEY, KEYWORD);
		KEYS.put(IMPORT_KEY, KEYWORD);
		KEYS.put(PACKAGE, KEYWORD);
		KEYS.put(OPEN_AN, ANNOTATION);
		KEYS.put(CLOSE_AN, ANNOTATION);
		KEYS.put(VAR, KEYWORD);

		KEYS.put(COLON, OPERATOR);
		KEYS.put(LEFT_SQUARE, OPERATOR);
		KEYS.put(RIGHT_SQUARE, OPERATOR);

		KEYS.put(WORD_KEY, PRIMITIVE);
		KEYS.put(STRING_TYPE, PRIMITIVE);
		KEYS.put(DOUBLE_TYPE, PRIMITIVE);
		KEYS.put(INT_TYPE, PRIMITIVE);
		KEYS.put(ALIAS_TYPE, PRIMITIVE);

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

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(KEYS.get(tokenType));
	}

}