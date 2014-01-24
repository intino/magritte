package monet.::projectName::.intellij.metamodel;

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
import monet.::projectName::.intellij.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class ::projectProperName::SyntaxHighlighter extends SyntaxHighlighterBase {
	private static final Map<IElementType, TextAttributesKey> keys1;
	private static final Map<IElementType, TextAttributesKey> keys2;

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("::projectProperName::_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("::projectProperName::_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("::projectProperName::_OPERATOR", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("::projectProperName::_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("::projectProperName::_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey DOC_COMMENT = createTextAttributesKey("::projectProperName::_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey COMMENT = createTextAttributesKey("::projectProperName::_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("::projectProperName::_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("::projectProperName::_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("::projectProperName::_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("::projectProperName::_BAD_CHARACTER", new TextAttributes(JBColor.RED, null, null, null, Font.BOLD));

	\@NotNull
	\@Override
	public com.intellij.lexer.Lexer getHighlightingLexer() {
		return new ::projectProperName::LexerAdapter();
	}

	static {
		keys1 = new THashMap<>();
		keys2 = new THashMap<>();

		keys1.put(TokenType.WHITE_SPACE, null);

		keys1.put(::projectProperName::Types.IDENTIFIER_KEY, IDENTIFIER);

		keys1.put(::projectProperName::Types.DEFINITION_KEY, KEYWORD);
		keys1.put(::projectProperName::Types.AS, KEYWORD);
		keys1.put(::projectProperName::Types.FROM_KEY, KEYWORD);
		keys1.put(::projectProperName::Types.OPEN_AN, KEYWORD);
		keys1.put(::projectProperName::Types.CLOSE_AN, KEYWORD);
		keys1.put(::projectProperName::Types.VAR, KEYWORD);
		keys1.put(::projectProperName::Types.OPTIONAL, OPERATOR);
		keys1.put(::projectProperName::Types.MULTIPLE, OPERATOR);

		keys1.put(::projectProperName::Types.ANNOTATION, ANNOTATION);

		keys1.put(::projectProperName::Types.ASSIGN, OPERATOR);
		keys1.put(::projectProperName::Types.LEFT_BRACKET, OPERATOR);
		keys1.put(::projectProperName::Types.RIGHT_BRACKET, OPERATOR);

		keys1.put(::projectProperName::Types.STRING_TYPE, STRING);
		keys1.put(::projectProperName::Types.DOUBLE_TYPE, PRIMITIVE);
		keys1.put(::projectProperName::Types.INT_TYPE, PRIMITIVE);
		keys1.put(::projectProperName::Types.STRING_TYPE, PRIMITIVE);
		keys1.put(::projectProperName::Types.UID_TYPE, PRIMITIVE);

		keys1.put(::projectProperName::Types.ABSTRACT, MODIFIERS);
		keys1.put(::projectProperName::Types.FINAL, MODIFIERS);

		keys1.put(::projectProperName::Types.DOC_LINE, DOC_COMMENT);
		keys1.put(::projectProperName::Types.DOC_BLOCK, DOC_COMMENT);

		keys1.put(::projectProperName::Types.DOUBLE_VALUE, NUMBERS);
		keys1.put(::projectProperName::Types.NATURAL_VALUE, NUMBERS);
		keys1.put(::projectProperName::Types.INTEGER_VALUE, NUMBERS);
		keys1.put(::projectProperName::Types.DOUBLE_VALUE, NUMBERS);
		keys1.put(::projectProperName::Types.BOOLEAN_VALUE, NUMBERS);

		keys1.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	\@NotNull
	\@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(keys1.get(tokenType), keys2.get(tokenType));
	}

	public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<>(6);

	static {
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.identifier"), null));
		DISPLAY_NAMES.put(MODIFIERS, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.modifier"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.key.value.separator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("options.::projectName::.definition.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<String, HighlightSeverity>(::projectProperName::Bundle.message("invalid.::projectName::.definition.character"), null));
//		DISPLAY_NAMES.put(PROPERTIES_INVALID_STRING_ESCAPE, new Pair<>(OptionsBundle.message("options.properties.attribute.descriptor.invalid.string.escape"), HighlightSeverity.WARNING));
	}
}