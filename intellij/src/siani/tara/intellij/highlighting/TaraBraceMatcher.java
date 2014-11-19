package siani.tara.intellij.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.TaraTypes;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static siani.tara.intellij.lang.psi.TaraTypes.IDENTIFIER_KEY;

public class TaraBraceMatcher implements PairedBraceMatcher {
	private final BracePair[] PAIRS;

	public TaraBraceMatcher() {
		PAIRS = new BracePair[]{
			new BracePair(TaraTypes.LEFT_PARENTHESIS, TaraTypes.RIGHT_PARENTHESIS, false),
		};
	}

	public BracePair[] getPairs() {
		return PAIRS;
	}

	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType braceType, @Nullable IElementType tokenType) {
		return tokenType == null
			|| tokenType == WHITE_SPACE
			|| tokenType == IDENTIFIER_KEY
			|| tokenType.getLanguage() != TaraFileType.INSTANCE.getLanguage();
	}

	public int getCodeConstructStart(final PsiFile file, int openingBraceOffset) {
		return openingBraceOffset;
	}
}