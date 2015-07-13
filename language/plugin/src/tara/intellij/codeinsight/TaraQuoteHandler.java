package tara.intellij.codeinsight;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import tara.intellij.lang.psi.TaraTypes;

public class TaraQuoteHandler extends SimpleTokenSetQuoteHandler {

	public TaraQuoteHandler() {
		super(TaraTypes.QUOTE_BEGIN, TaraTypes.QUOTE_END, TaraTypes.EXPRESSION_BEGIN, TaraTypes.EXPRESSION_END);
	}
}