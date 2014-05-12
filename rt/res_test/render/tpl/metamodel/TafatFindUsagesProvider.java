package monet.tafat.intellij.metamodel;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import monet.tafat.intellij.psi.TafatDefinition;
import monet.tafat.intellij.psi.TafatIdentifier;
import monet.tafat.intellij.psi.TafatTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TafatFindUsagesProvider implements FindUsagesProvider {
	private static final DefaultWordsScanner WORDS_SCANNER =
		new DefaultWordsScanner(new TafatLexerAdapter(),
			TokenSet.create(TafatTypes.IDENTIFIER), TokenSet.create(TafatTypes.DOC), TokenSet.EMPTY);

	@Nullable
	@Override
	public com.intellij.lang.cacheBuilder.WordsScanner getWordsScanner() {
		return WORDS_SCANNER;
	}

	@Override
	public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
		return psiElement instanceof PsiNamedElement;
	}

	@Nullable
	@Override
	public String getHelpId(@NotNull PsiElement psiElement) {
		return HelpID.FIND_PACKAGE_USAGES;
	}

	@NotNull
	@Override
	public String getType(@NotNull PsiElement element) {
		if (element instanceof TafatDefinition) {
			return "Tafat Definition";
		} else {
			return "";
		}
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		if (element instanceof TafatIdentifier) {
			return ((TafatIdentifier) element).getIdentifier();
		} else {
			return "Error, no text";
		}
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof TafatIdentifier)
			return ((TafatIdentifier) element).getIdentifier();
		else return "Error, no text";
	}
}