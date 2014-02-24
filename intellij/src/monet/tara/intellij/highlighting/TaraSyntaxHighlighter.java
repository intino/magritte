package monet.tara.intellij.highlighting;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.options.OptionsBundle;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import gnu.trove.THashMap;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import monet.tara.intellij.TaraBundle;
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
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.key.value.separator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(TaraBundle.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<>(TaraBundle.message("invalid.tara.concept.character"), HighlightSeverity.ERROR));
	}

	private static final Map<IElementType, TextAttributesKey> keys1;
	private static final Map<IElementType, TextAttributesKey> keys2;

	@NotNull
	@Override
	public com.intellij.lexer.Lexer getHighlightingLexer() {
		return new TaraHighlighterLexAdapter();
	}

	static {
		keys1 = new THashMap<>();
		keys2 = new THashMap<>();

		keys1.put(TokenType.WHITE_SPACE, null);
		keys1.put(TaraTypes.IDENTIFIER_KEY, IDENTIFIER);

		keys1.put(TaraTypes.CONCEPT_KEY, KEYWORD);
		keys1.put(TaraTypes.AS, KEYWORD);
		keys1.put(TaraTypes.FROM_KEY, KEYWORD);
		keys1.put(TaraTypes.OPEN_AN, KEYWORD);
		keys1.put(TaraTypes.CLOSE_AN, KEYWORD);
		keys1.put(TaraTypes.NEW, KEYWORD);
		keys1.put(TaraTypes.VAR, KEYWORD);

		keys1.put(TaraTypes.OPTIONAL, ANNOTATION);
		keys1.put(TaraTypes.MULTIPLE, ANNOTATION);
		keys1.put(TaraTypes.EXTENSIBLE, ANNOTATION);
		keys1.put(TaraTypes.HAS_CODE, ANNOTATION);
		keys1.put(TaraTypes.SINGLETON, ANNOTATION);
		keys1.put(TaraTypes.ROOT, ANNOTATION);

		keys1.put(TaraTypes.ASSIGN, OPERATOR);
		keys1.put(TaraTypes.LEFT_SQUARE, OPERATOR);
		keys1.put(TaraTypes.RIGHT_SQUARE, OPERATOR);

		keys1.put(TaraTypes.STRING_TYPE, PRIMITIVE);
		keys1.put(TaraTypes.DOUBLE_TYPE, PRIMITIVE);
		keys1.put(TaraTypes.INT_TYPE, PRIMITIVE);
		keys1.put(TaraTypes.UID_TYPE, PRIMITIVE);

		keys1.put(TaraTypes.ABSTRACT, MODIFIERS);
		keys1.put(TaraTypes.FINAL, MODIFIERS);

		keys1.put(TaraTypes.DOC_LINE, DOCUMENTATION);

		keys1.put(TaraTypes.DOUBLE_VALUE, NUMBERS);
		keys1.put(TaraTypes.NATURAL_VALUE, NUMBERS);
		keys1.put(TaraTypes.INTEGER_VALUE, NUMBERS);
		keys1.put(TaraTypes.DOUBLE_VALUE, NUMBERS);
		keys1.put(TaraTypes.BOOLEAN_VALUE, NUMBERS);
		keys1.put(TaraTypes.STRING_VALUE, STRING);

		keys1.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(keys1.get(tokenType), keys2.get(tokenType));
	}
}