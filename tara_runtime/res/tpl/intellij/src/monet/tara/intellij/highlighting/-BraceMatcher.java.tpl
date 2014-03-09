package monet.::projectName::.intellij.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types.IDENTIFIER_KEY;

public class ::projectProperName::BraceMatcher implements PairedBraceMatcher {
	private final BracePair[] PAIRS;

	public ::projectProperName::BraceMatcher() {
		PAIRS = new BracePair[]{new BracePair(::projectProperName::Types.LEFT_SQUARE, ::projectProperName::Types.RIGHT_SQUARE, false)};
	}

	public BracePair[] getPairs() {
		return PAIRS;
	}

	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType braceType, @Nullable IElementType tokenType) {
		return tokenType == null
			|| tokenType == WHITE_SPACE
			|| tokenType == IDENTIFIER_KEY
			|| tokenType.getLanguage() != ::projectProperName::FileType.INSTANCE.getLanguage();
	}

	public int getCodeConstructStart(final PsiFile file, int openingBraceOffset) {
		return openingBraceOffset;
	}
}