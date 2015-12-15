package tara.intellij.lang.psi.resolve;

import com.intellij.lang.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.lexer.TaraLexerAdapter;
import tara.intellij.lang.psi.*;
import tara.lang.model.Node;

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
		return psiElement instanceof Identifier || psiElement instanceof IdentifierReference;
	}

	@Nullable
	@Override
	public String getHelpId(@NotNull PsiElement psiElement) {
		return HelpID.FIND_OTHER_USAGES;
	}

	@NotNull
	@Override
	public String getType(@NotNull PsiElement element) {
		return "";
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		return getDescriptiveName(element);
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		if (element instanceof TaraNode) {
			String name = ((Node) element).name();
			return name == null ? ANONYMOUS : name;
		} else if (element instanceof Identifier)
			return element.getText();
		else if (element instanceof TaraModel)
			return ((TaraModel) element).getName();
		return element.getText();
	}
}