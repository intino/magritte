package monet.tara.metamodel;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.JBColor;
import monet.tara.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class TaraSyntaxHighlighter extends SyntaxHighlighterBase {

	static final IElementType[] KEYWORD_LIST = { TaraTypes.CONCEPT, TaraTypes.IS, TaraTypes.HAS, TaraTypes.REF, TaraTypes.IS };

	static final IElementType[] ANNOTATION_LIST = { TaraTypes.NAMEABLE, TaraTypes.AT_ROOT, TaraTypes.EXTENSIBLE, TaraTypes.ACTION };

	static final IElementType[] PRIMITIVE_TYPE_LIST = { TaraTypes.DOUBLE_TYPE, TaraTypes.INT_TYPE, TaraTypes.STRING_TYPE, TaraTypes.ID_TYPE };


	public static final TextAttributesKey SEPARATOR = createTextAttributesKey("Tara_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey KEYWORD = createTextAttributesKey("Tara_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey MODIFIERS = createTextAttributesKey("Tara_MODIFIERS", DefaultLanguageHighlighterColors.STATIC_FIELD);
	public static final TextAttributesKey STRING = createTextAttributesKey("Tara_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey COMMENT = createTextAttributesKey("Tara_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey PRIMITIVE = createTextAttributesKey("Tara_PRIMITIVE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey ANNOTATION =
	createTextAttributesKey("Tara_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey NUMBERS = createTextAttributesKey("Tara_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("Tara_BAD_CHARACTER", new TextAttributes(JBColor.RED, null, null, null, Font.BOLD));

	private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{ BAD_CHARACTER };
	private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{ KEYWORD };
	private static final TextAttributesKey[] MODIFIERS_KEYS = new TextAttributesKey[]{ MODIFIERS };
	private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{ STRING };
	private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{ COMMENT };
	private static final TextAttributesKey[] ANNOTATION_KEY = new TextAttributesKey[]{ ANNOTATION };
	private static final TextAttributesKey[] NUMBERS_KEY = new TextAttributesKey[]{ NUMBERS };
	private static final TextAttributesKey[] PRIMITIVE_KEY = new TextAttributesKey[]{ PRIMITIVE };
	private static final TextAttributesKey[] IDENTIFIERS_KEYS = new TextAttributesKey[0];

	@NotNull
	@Override
	public com.intellij.lexer.Lexer getHighlightingLexer() {
		return new FlexAdapter(new TaraLexer((Reader) null));
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		if (isAnnotation(tokenType)) {
			return ANNOTATION_KEY;
		} else if (tokenType.equals(TaraTypes.DOUBLE) || tokenType.equals(TaraTypes.INT)) {
			return NUMBERS_KEY;
		} else if (tokenType.equals(TaraTypes.COMMENT)) {
			return COMMENT_KEYS;
		} else if (isPrimitiveType(tokenType)) {
			return PRIMITIVE_KEY;
		} else if (isKeyword(tokenType)) {
			return KEYWORD_KEYS;
		} else if (tokenType.equals(TaraTypes.MODIFIERS)) {
			return MODIFIERS_KEYS;
		} else if (tokenType.equals(TaraTypes.STRING)) {
			return STRING_KEYS;
		} else if (tokenType.equals(TaraTypes.ASSIGN)) {
			return IDENTIFIERS_KEYS;
		} else //(tokenType.equals(TaraTypes.IDENTIFIER)) {
			return IDENTIFIERS_KEYS;
//		} else {
//			return BAD_CHAR_KEYS;
//		}
	}


	private boolean isKeyword(IElementType tokenType) {
		for (IElementType elementType : KEYWORD_LIST)
			if (tokenType.equals(elementType)) return true;
		return false;
	}

	private boolean isAnnotation(IElementType tokenType) {
		for (IElementType elementType : ANNOTATION_LIST)
			if (tokenType.equals(elementType)) return true;
		return false;
	}

	private boolean isPrimitiveType(IElementType tokenType) {
		for (IElementType elementType : PRIMITIVE_TYPE_LIST)
			if (tokenType.equals(elementType)) return true;
		return false;
	}
}