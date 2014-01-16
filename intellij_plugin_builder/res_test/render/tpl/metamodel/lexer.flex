package monet.tafat.intellij.metamodel;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tafat.intellij.psi.TafatTypes;
import com.intellij.psi.TokenType;
%%

%class TafatLexer
%implements FlexLexer
%unicode
%column
%function advance
%type IElementType

%{

	private Stack<Integer> stack = new Stack<>();

	private int transformToSpaces(CharSequence chain){
		int value = 0;
		for(int i = 0; i < chain.length(); i++){
			if (chain.charAt(i) == ('n')) continue;
			if (chain.charAt(i) == ('t')) value += 4;
			else value += 1;
		}
		return value;
	}

	private IElementType eof(){
    		if (!stack.empty()) {
                stack.pop();
                if (!stack.empty())
                    yypushback(yylength());
                return TafatTypes.DEDENT;
            }
                return TokenType.WHITE_SPACE;
        }

	private IElementType cleanstack(){
		if (!stack.empty()) {
            stack.pop();
            if (!stack.empty() && isTextDedented(transformToSpaces(yytext())))
                yypushback(yylength());
            return TafatTypes.DEDENT;
        }
            return TokenType.WHITE_SPACE;
    }

	private boolean isTextIndented(int textLength){
		if (!stack.empty())
			return textLength > (int)stack.peek();
		return false;
	}

	private boolean isTextDedented(int textLength){
		if (!stack.empty())
            return textLength < (int)stack.peek();
		return false;
    }

	private IElementType calculateIndentationToken() {
		int textLength = transformToSpaces(yytext());
        if (stack.empty() || isTextIndented(textLength)){
            stack.push(textLength);
            return TafatTypes.INDENT;
        } else if (isTextDedented(textLength)) {
            stack.pop();
            if (isTextDedented(textLength))
                yypushback(yylength());
            return TafatTypes.DEDENT;
		} else
            return TokenType.WHITE_SPACE;
	}
%}
WS = ([ ]+ | [t]+)
EOL=([rn] | [n])
INDENT=[n]+ ([ ]+ | [t]+)

//=====================
//Reserved words

THESAURUS ="Thesaurus"

TERM ="Term"

SOURCE ="Source"

OPERATION ="Operation"



