package monet.::projectName::.intellij;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import monet.::projectName::.intellij.lang.lexer.::projectProperName::LexerAdapter;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::FindUsagesProvider implements FindUsagesProvider {
	public static final String ANONYMOUS = "Anonymous";
	public static final String ERROR = "Error";
	private static final DefaultWordsScanner WORDS_SCANNER =
		new DefaultWordsScanner(new ::projectProperName::LexerAdapter(),
			TokenSet.create(::projectProperName::Types.IDENTIFIER, ::projectProperName::Types.IDENTIFIER_KEY), TokenSet.create(::projectProperName::Types.DOC), TokenSet.EMPTY);

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
		return HelpID.FIND_PACKAGE_USAGES;
	}

	\@NotNull
	\@Override
	public String getType(\@NotNull PsiElement element) {
		if (element instanceof Definition) return "::projectProperName:: Definition";
		else return "";
	}

	\@NotNull
	\@Override
	public String getDescriptiveName(\@NotNull PsiElement element) {
		if (element instanceof Definition) {
			String name = ((Definition) element).getName();
			return name == null ? ANONYMOUS \: name;
		} else return ERROR;
	}

	\@NotNull
	\@Override
	public String getNodeText(\@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof Definition) {
			String name = ((Definition) element).getName();
			return name == null ? ANONYMOUS \: name;
		} else return ERROR;
	}
}