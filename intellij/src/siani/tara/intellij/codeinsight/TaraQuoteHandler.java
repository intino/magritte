package siani.tara.intellij.codeinsight;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import siani.tara.intellij.lang.psi.TaraTypes;

public class TaraQuoteHandler extends SimpleTokenSetQuoteHandler {

	public TaraQuoteHandler() {
		super(TaraTypes.QUOTE_BEGIN,TaraTypes.CHARACTER, TaraTypes.QUOTE_END);
	}
}
