package siani.tara.intellij.highlighting;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.TaraTypes;

import java.awt.*;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TaraSyntaxHighlighter extends SyntaxHighlighterBase implements TaraTypes {

	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tara_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey META_IDENTIFIER = createTextAttributesKey("Tara_METAIDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_METHOD);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("Tara_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("Tara_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tara_STRING", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey ADDRESS = createTextAttributesKey("Tara_ADDRESS", DefaultLanguageHighlighterColors.DOC_COMMENT_MARKUP);
	public static final TextAttributesKey DOCUMENTATION = createTextAttributesKey("Tara_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tara_PRIMITIVE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
	public static final TextAttributesKey ANNOTATION = createTextAttributesKey("Tara_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBER = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey REFERENCE = createTextAttributesKey("Tara_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
	public static final TextAttributesKey BRACKETS = createTextAttributesKey("Tara_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
	public static final TextAttributesKey SEMICOLON_KEY = createTextAttributesKey("Tara_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("Tara_BAD_CHARACTER");
	public static final TextAttributesKey ANNOTATION_ERROR = createTextAttributesKey("ANNOTATION_ERROR", errorTextAttributes());
	public static final TextAttributesKey UNRESOLVED_ACCESS = createTextAttributesKey("UNRESOLVED_ACCESS", errorTextAttributes());

	public static final TextAttributesKey WARNING = createTextAttributesKey("WARNING",
		new TextAttributes(null, null, JBColor.YELLOW, EffectType.WAVE_UNDERSCORE, Font.PLAIN));

	public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<>();
	private static final Map<IElementType, TextAttributesKey> KEYS;

	public TaraSyntaxHighlighter() {
		BAD_CHARACTER.getDefaultAttributes().setForegroundColor(JBColor.RED);
		BAD_CHARACTER.getDefaultAttributes().setFontType(Font.BOLD);
	}

	private static TextAttributes errorTextAttributes() {
		return new TextAttributes(JBColor.RED, null, null, null, Font.BOLD);
	}

	static {
		DISPLAY_NAMES.put(IDENTIFIER, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.identifier"), null));
		DISPLAY_NAMES.put(KEYWORD, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.keyword"), null));
		DISPLAY_NAMES.put(PRIMITIVE, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.primitive"), null));
		DISPLAY_NAMES.put(STRING, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.types.string"), null));
		DISPLAY_NAMES.put(DOCUMENTATION, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.comment"), null));
		DISPLAY_NAMES.put(SEMICOLON_KEY, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.semicolon"), null));
		DISPLAY_NAMES.put(OPERATOR, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.operator"), null));
		DISPLAY_NAMES.put(ANNOTATION, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(NUMBER, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.number"), null));
		DISPLAY_NAMES.put(BRACKETS, new Pair<String, HighlightSeverity>(MessageProvider.message("options.tara.concept.annotation"), null));
		DISPLAY_NAMES.put(BAD_CHARACTER, new Pair<>(MessageProvider.message("invalid.tara.concept.character"), HighlightSeverity.ERROR));
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new TaraHighlighterLexAdapter(ProjectManager.getInstance().getDefaultProject());
	}

	static {
		KEYS = new THashMap<>();

		KEYS.put(METAIDENTIFIER_KEY, META_IDENTIFIER);
		KEYS.put(DSL, KEYWORD);
		KEYS.put(PROTEO, KEYWORD);
		KEYS.put(EMPTY_REF, KEYWORD);
		KEYS.put(HAS, KEYWORD);
		KEYS.put(EXTENDS, KEYWORD);
		KEYS.put(REQUIRED, ANNOTATION);
		KEYS.put(SINGLE, ANNOTATION);
		KEYS.put(FACET, ANNOTATION);
		KEYS.put(INTENTION, ANNOTATION);
		KEYS.put(COMPONENT, ANNOTATION);
		KEYS.put(TERMINAL, ANNOTATION);
		KEYS.put(PROPERTY, ANNOTATION);
		KEYS.put(ABSTRACT, ANNOTATION);
		KEYS.put(ENCLOSED, ANNOTATION);
		KEYS.put(ASSOCIATED, ANNOTATION);
		KEYS.put(ADDRESSED, ANNOTATION);
		KEYS.put(AGGREGATED, ANNOTATION);
		KEYS.put(TACIT, ANNOTATION);
		KEYS.put(CASE, ANNOTATION);
		KEYS.put(READONLY, ANNOTATION);
		KEYS.put(NEW_LINE_INDENT, KEYWORD);

		KEYS.put(NAMED, ANNOTATION);
		KEYS.put(RESOURCE_KEY, PRIMITIVE);
		KEYS.put(IDENTIFIER_KEY, IDENTIFIER);
		KEYS.put(SUB, KEYWORD);
		KEYS.put(USE, KEYWORD);
		KEYS.put(IS, KEYWORD);
		KEYS.put(VAR, KEYWORD);
		KEYS.put(AS, KEYWORD);
		KEYS.put(ON, KEYWORD);
		KEYS.put(WITH, KEYWORD);
		KEYS.put(STAR, OPERATOR);
		KEYS.put(PLUS, OPERATOR);
		KEYS.put(COLON, OPERATOR);
		KEYS.put(EQUALS, OPERATOR);
		KEYS.put(META_WORD, OPERATOR);
		KEYS.put(ALWAYS, ANNOTATION);
		KEYS.put(LIST, OPERATOR);
		KEYS.put(LEFT_SQUARE, OPERATOR);
		KEYS.put(RIGHT_SQUARE, OPERATOR);
		KEYS.put(MEASURE_VALUE, PRIMITIVE);
		KEYS.put(NEW_LINE_INDENT, KEYWORD);
		KEYS.put(WORD_KEY, PRIMITIVE);
		KEYS.put(RATIO_TYPE, PRIMITIVE);
		KEYS.put(MEASURE_TYPE_KEY, PRIMITIVE);
		KEYS.put(STRING_TYPE, PRIMITIVE);
		KEYS.put(DOUBLE_TYPE, PRIMITIVE);
		KEYS.put(INT_TYPE, PRIMITIVE);
		KEYS.put(DATE_TYPE, PRIMITIVE);
		KEYS.put(BOOLEAN_TYPE, PRIMITIVE);
		KEYS.put(NATURAL_TYPE, PRIMITIVE);

		KEYS.put(DOC_LINE, DOCUMENTATION);

		KEYS.put(ADDRESS_VALUE, ADDRESS);
		KEYS.put(DOUBLE_VALUE_KEY, NUMBER);
		KEYS.put(NATURAL_VALUE_KEY, NUMBER);
		KEYS.put(NEGATIVE_VALUE_KEY, NUMBER);
		KEYS.put(BOOLEAN_VALUE_KEY, KEYWORD);
		KEYS.put(QUOTE_BEGIN, STRING);
		KEYS.put(QUOTE_END, STRING);
		KEYS.put(CHARACTER, STRING);
		KEYS.put(STRING_MULTILINE_VALUE_KEY, STRING);
		KEYS.put(DATE_VALUE_KEY, NUMBER);
		KEYS.put(TokenType.WHITE_SPACE, null);
		KEYS.put(TokenType.BAD_CHARACTER, BAD_CHARACTER);
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(KEYS.get(tokenType));
	}

}