package monet.tara.intellij.highlighting;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import gnu.trove.THashMap;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TaraSyntaxHighlighter extends SyntaxHighlighterBase {
	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tara_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("Tara_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("Tara_OPERATOR", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("Tara_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tara_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOCUMENTATION = createTextAttributesKey("Tara_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tara_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("Tara_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("Tara_BAD_CHARACTER", new TextAttributes(JBColor.RED, null, null, null, Font.BOLD));
	public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<>(6);

	static {
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<>(TaraBundle.message("options.tara.concept.identifier"), HighlightSeverity.ERROR));
		DISPLAY_NAMES.put(KEYWORD, new Pair<>(TaraBundle.message("options.tara.concept.keyword"), HighlightSeverity.ERROR));
		DISPLAY_NAMES.put(PRIMITIVE, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.primitive"), null));
		DISPLAY_NAMES.put(STRING, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.types.string"), null));
		DISPLAY_NAMES.put(DOCUMENTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.comment"), null));
		DISPLAY_NAMES.put(MODIFIERS, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.modifier"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.operator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<>(TaraBundle.message("invalid.tara.concept.character"), HighlightSeverity.ERROR));
	}

	private static final Map<IElementType, TextAttributesKey> KEYS_1;
	private static final Map<IElementType, TextAttributesKey> KEYS_2;

	@NotNull
	@Override
	public com.intellij.lexer.Lexer getHighlightingLexer() {
		return new TaraHighlighterLexAdapter();
	}

	static {
		KEYS_1 = new THashMap<>();
		KEYS_2 = new THashMap<>();

		KEYS_1.put(TokenType.WHITE_SPACE, null);
		KEYS_1.put(TaraTypes.IDENTIFIER_KEY, IDENTIFIER);

//gen %highlightKey%
		KEYS_1.put(TaraTypes.CONCEPT_KEY, KEYWORD);
//end
		KEYS_1.put(TaraTypes.OPEN_AN, KEYWORD);
		KEYS_1.put(TaraTypes.CLOSE_AN, KEYWORD);
		KEYS_1.put(TaraTypes.NEW, KEYWORD);
		KEYS_1.put(TaraTypes.VAR, KEYWORD);

		KEYS_1.put(TaraTypes.OPTIONAL, ANNOTATION);
		KEYS_1.put(TaraTypes.MULTIPLE, ANNOTATION);
		KEYS_1.put(TaraTypes.EXTENSIBLE, ANNOTATION);
		KEYS_1.put(TaraTypes.HAS_CODE, ANNOTATION);
		KEYS_1.put(TaraTypes.SINGLETON, ANNOTATION);
		KEYS_1.put(TaraTypes.ROOT, ANNOTATION);
		KEYS_1.put(TaraTypes.GENERIC, ANNOTATION);
		KEYS_1.put(TaraTypes.COLON, OPERATOR);
		KEYS_1.put(TaraTypes.LEFT_SQUARE, OPERATOR);
		KEYS_1.put(TaraTypes.RIGHT_SQUARE, OPERATOR);

		KEYS_1.put(TaraTypes.STRING_TYPE, PRIMITIVE);
		KEYS_1.put(TaraTypes.DOUBLE_TYPE, PRIMITIVE);
		KEYS_1.put(TaraTypes.INT_TYPE, PRIMITIVE);
		KEYS_1.put(TaraTypes.UID_TYPE, PRIMITIVE);

		KEYS_1.put(TaraTypes.ABSTRACT, MODIFIERS);
		KEYS_1.put(TaraTypes.FINAL, MODIFIERS);
		KEYS_1.put(TaraTypes.MORPH_KEY, MODIFIERS);
		KEYS_1.put(TaraTypes.POLYMORPHIC_KEY, MODIFIERS);

		KEYS_1.put(TaraTypes.DOC_LINE, DOCUMENTATION);

		KEYS_1.put(TaraTypes.DOUBLE_VALUE_KEY, NUMBERS);
		KEYS_1.put(TaraTypes.NATURAL_VALUE_KEY, NUMBERS);
		KEYS_1.put(TaraTypes.NEGATIVE_VALUE_KEY, NUMBERS);
		KEYS_1.put(TaraTypes.BOOLEAN_VALUE_KEY, NUMBERS);
		KEYS_1.put(TaraTypes.STRING_VALUE_KEY, STRING);

		KEYS_1.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(KEYS_1.get(tokenType), KEYS_2.get(tokenType));
	}
}