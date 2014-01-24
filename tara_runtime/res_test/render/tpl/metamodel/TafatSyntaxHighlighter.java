package monet.tafat.intellij.metamodel;

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
import monet.tafat.intellij.psi.TafatTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TafatSyntaxHighlighter extends SyntaxHighlighterBase {
	private static final Map<IElementType, TextAttributesKey> keys1;
	private static final Map<IElementType, TextAttributesKey> keys2;

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tafat_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("Tafat_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("Tafat_OPERATOR", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("Tafat_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tafat_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("Tafat_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey COMMENT = createTextAttributesKey("Tafat_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tafat_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("Tafat_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("Tafat_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("Tafat_BAD_CHARACTER", new TextAttributes(JBColor.RED, null, null, null, Font.BOLD));

	@NotNull
	@Override
	public com.intellij.lexer.Lexer getHighlightingLexer() {
		return new TafatLexerAdapter();
	}

	static {
		keys1 = new THashMap<>();
		keys2 = new THashMap<>();

		keys1.put(TokenType.WHITE_SPACE, null);

		keys1.put(TafatTypes.IDENTIFIER_KEY, IDENTIFIER);

		keys1.put(TafatTypes.DEFINITION_KEY, KEYWORD);
		keys1.put(TafatTypes.AS, KEYWORD);
		keys1.put(TafatTypes.FROM_KEY, KEYWORD);
		keys1.put(TafatTypes.OPEN_AN, KEYWORD);
		keys1.put(TafatTypes.CLOSE_AN, KEYWORD);
		keys1.put(TafatTypes.VAR, KEYWORD);
		keys1.put(TafatTypes.OPTIONAL, OPERATOR);
		keys1.put(TafatTypes.MULTIPLE, OPERATOR);

		keys1.put(TafatTypes.ANNOTATION, ANNOTATION);

		keys1.put(TafatTypes.ASSIGN, OPERATOR);
		keys1.put(TafatTypes.LEFT_BRACKET, OPERATOR);
		keys1.put(TafatTypes.RIGHT_BRACKET, OPERATOR);

		keys1.put(TafatTypes.STRING_TYPE, STRING);
		keys1.put(TafatTypes.DOUBLE_TYPE, PRIMITIVE);
		keys1.put(TafatTypes.INT_TYPE, PRIMITIVE);
		keys1.put(TafatTypes.STRING_TYPE, PRIMITIVE);
		keys1.put(TafatTypes.UID_TYPE, PRIMITIVE);

		keys1.put(TafatTypes.ABSTRACT, MODIFIERS);
		keys1.put(TafatTypes.FINAL, MODIFIERS);

		keys1.put(TafatTypes.DOC_LINE, DOC_COMMENT);
		keys1.put(TafatTypes.DOC_BLOCK, DOC_COMMENT);

		keys1.put(TafatTypes.DOUBLE_VALUE, NUMBERS);
		keys1.put(TafatTypes.NATURAL_VALUE, NUMBERS);
		keys1.put(TafatTypes.INTEGER_VALUE, NUMBERS);
		keys1.put(TafatTypes.DOUBLE_VALUE, NUMBERS);
		keys1.put(TafatTypes.BOOLEAN_VALUE, NUMBERS);

		keys1.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(keys1.get(tokenType), keys2.get(tokenType));
	}

	public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<>(6);

	static {
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<String, HighlightSeverity>(TafatBundle.message("options.tafat.definition.identifier"), null));
		DISPLAY_NAMES.put(MODIFIERS, new Pair<String, HighlightSeverity>(TafatBundle.message("options.tafat.definition.modifier"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.key.value.separator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(TafatBundle.message("options.tafat.definition.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<String, HighlightSeverity>(TafatBundle.message("invalid.tafat.definition.character"), null));
//		DISPLAY_NAMES.put(PROPERTIES_INVALID_STRING_ESCAPE, new Pair<>(OptionsBundle.message("options.properties.attribute.descriptor.invalid.string.escape"), HighlightSeverity.WARNING));
	}
}