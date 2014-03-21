package monet.tara.intellij.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static monet.tara.intellij.metamodel.psi.TaraTypes.IDENTIFIER_KEY;

public class TaraBraceMatcher implements PairedBraceMatcher {
	private final BracePair[] PAIRS;

	public TaraBraceMatcher() {
		PAIRS = new BracePair[]{new BracePair(TaraTypes.LEFT_SQUARE, TaraTypes.RIGHT_SQUARE, false)};
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