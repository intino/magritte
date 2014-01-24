package monet.::projectName::.intellij.metamodel;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import monet.::projectName::.intellij.psi.::projectProperName::Definition;
import monet.::projectName::.intellij.psi.::projectProperName::Identifier;
import monet.::projectName::.intellij.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::FindUsagesProvider implements FindUsagesProvider {
	private static final DefaultWordsScanner WORDS_SCANNER =
		new DefaultWordsScanner(new ::projectProperName::LexerAdapter(),
			TokenSet.create(::projectProperName::Types.IDENTIFIER), TokenSet.create(::projectProperName::Types.DOC), TokenSet.EMPTY);

	\@Nullable
	\@Override
	public com.intellij.lang.cacheBuilder.WordsScanner getWordsScanner() {
		return WORDS_SCANNER;
	}

	\@Override
	public boolean canFindUsagesFor(\@NotNull PsiElement psiElement) {
		return psiElement instanceof PsiNamedElement;
	}

	\@Nullable
	\@Override
	public String getHelpId(\@NotNull PsiElement psiElement) {
		return HelpID.FIND_OTHER_USAGES;
	}

	\@NotNull
	\@Override
	public String getType(\@NotNull PsiElement element) {
		if (element instanceof ::projectProperName::Definition) {
			return "::projectProperName:: Definition";
		} else {
			return "";
		}
	}

	\@NotNull
	\@Override
	public String getDescriptiveName(\@NotNull PsiElement element) {
		if (element instanceof ::projectProperName::Identifier) {
			return ((::projectProperName::Identifier) element).getIdentifier();
		} else {
			return "Error, no text";
		}
	}

	\@NotNull
	\@Override
	public String getNodeText(\@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof ::projectProperName::Identifier)
			return ((::projectProperName::Identifier) element).getIdentifier();
		else return "Error, no text";
	}
}