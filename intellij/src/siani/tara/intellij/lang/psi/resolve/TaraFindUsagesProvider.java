package siani.tara.intellij.lang.psi.resolve;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.lexer.TaraLexerAdapter;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraConcept;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraFindUsagesProvider implements FindUsagesProvider {
	public static final String ANONYMOUS = "Anonymous";
	private static final DefaultWordsScanner WORDS_SCANNER = new DefaultWordsScanner(new TaraLexerAdapter(),
		TokenSet.create(TaraTypes.CONCEPT), TokenSet.create(TaraTypes.DOC), TokenSet.create(TaraTypes.IDENTIFIER_KEY));

	@Nullable
	@Override
	public WordsScanner getWordsScanner() {
		return WORDS_SCANNER;
	}

	@Override
	public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
		return psiElement instanceof TaraConcept || psiElement instanceof Identifier;
	}

	@Nullable
	@Override
	public String getHelpId(@NotNull PsiElement psiElement) {
		return HelpID.FIND_IN_PROJECT;
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
			return TaraPsiImplUtil.getContextOf(element).getType() + " " + element.getText();
		return element.getText();
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof Concept) {
			String name = ((Concept) element).getName();
			return name == null ? ANONYMOUS : name;
		} else if (element instanceof Identifier)
			return TaraPsiImplUtil.getContextOf(element).getType() + " " + element.getText();
		return element.getText();
	}


}