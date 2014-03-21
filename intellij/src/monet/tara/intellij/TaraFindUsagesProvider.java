package monet.tara.intellij;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import monet.tara.intellij.metamodel.lexer.TaraLexerAdapter;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFindUsagesProvider implements FindUsagesProvider {
	private static final DefaultWordsScanner WORDS_SCANNER =
		new DefaultWordsScanner(new TaraLexerAdapter(),
			TokenSet.create(TaraTypes.IDENTIFIER, TaraTypes.IDENTIFIER_KEY), TokenSet.create(TaraTypes.DOC), TokenSet.EMPTY);

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
		if (element instanceof Concept) return "Tara Concept";
		else return "";
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		if (element instanceof Concept) {
			String name = ((Concept) element).getName();
			return name == null ? "Anonymous" : name;
		} else return "Error";
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof Concept) {
			String name = ((Concept) element).getName();
			return name == null ? "Anonymous" : name;
		} else return "Error";
	}
}