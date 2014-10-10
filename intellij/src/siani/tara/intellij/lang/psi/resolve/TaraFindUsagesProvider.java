package siani.tara.intellij.lang.psi.resolve;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.lexer.TaraLexerAdapter;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraFindUsagesProvider implements FindUsagesProvider {
	public static final String ANONYMOUS = "Anonymous";
	private static final DefaultWordsScanner WORDS_SCANNER = new DefaultWordsScanner(new TaraLexerAdapter(),
		TokenSet.create(TaraTypes.IDENTIFIER),
		TokenSet.create(TaraTypes.DOC, TaraTypes.DOC_LINE), TokenSet.EMPTY);

	@Nullable
	@Override
	public WordsScanner getWordsScanner() {
		return WORDS_SCANNER;
	}

	@Override
	public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
		return psiElement instanceof TaraConcept || psiElement instanceof Identifier || psiElement instanceof IdentifierReference;
	}

	@Nullable
	@Override
	public String getHelpId(@NotNull PsiElement psiElement) {
		return com.intellij.lang.HelpID.FIND_OTHER_USAGES;
	}

	@NotNull
	@Override
	public String getType(@NotNull PsiElement element) {
		return "In child models:";
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		if (element instanceof Concept) {
			String name = ((Concept) element).getName();
			return name == null ? ANONYMOUS : name;
		} else if (element instanceof Identifier)
			return TaraPsiImplUtil.getConceptContextOf(element).getType() + " " + element.getText();
		return element.getText();
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		return getDescriptiveName(element);
	}
}