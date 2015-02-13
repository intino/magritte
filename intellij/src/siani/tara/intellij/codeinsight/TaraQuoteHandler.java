package siani.tara.intellij.codeinsight;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import siani.tara.intellij.lang.psi.TaraTypes;

public class TaraQuoteHandler extends SimpleTokenSetQuoteHandler {

	public TaraQuoteHandler() {
		super(TaraTypes.QUOTE_BEGIN, TaraTypes.QUOTE_END);
	}

	@Override
	public boolean isInsideLiteral(HighlighterIterator iterator) {
		return super.isInsideLiteral(iterator);
	}
}
