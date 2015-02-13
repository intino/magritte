package siani.tara.intellij.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.TaraTypes;

public class TaraBraceMatcher implements PairedBraceMatcher {
	private final BracePair[] PAIRS;

	public TaraBraceMatcher() {
		PAIRS = new BracePair[]{
			new BracePair(TaraTypes.LEFT_PARENTHESIS, TaraTypes.RIGHT_PARENTHESIS, false),
			new BracePair(TaraTypes.LEFT_SQUARE, TaraTypes.RIGHT_SQUARE, false),
			new BracePair(TaraTypes.QUOTE_BEGIN, TaraTypes.QUOTE_END, false),
		};
	}

	public BracePair[] getPairs() {
		return PAIRS;
	}

	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType braceType, @Nullable IElementType tokenType) {
		return true;
	}

	public int getCodeConstructStart(final PsiFile file, int openingBraceOffset) {
		return openingBraceOffset;
	}
}