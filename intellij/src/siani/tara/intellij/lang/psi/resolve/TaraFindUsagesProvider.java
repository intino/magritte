package siani.tara.intellij.lang.psi.resolve;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.lexer.TaraLexerAdapter;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraTypes;

public class TaraFindUsagesProvider implements FindUsagesProvider {
	public static final String ANONYMOUS = "Anonymous";
	public static final String ERROR = "Error";
	private static final DefaultWordsScanner WORDS_SCANNER = new DefaultWordsScanner(new TaraLexerAdapter(ProjectManager.getInstance().getOpenProjects()[0]),
		TokenSet.create(TaraTypes.IDENTIFIER, TaraTypes.IDENTIFIER_KEY), TokenSet.create(TaraTypes.DOC), TokenSet.EMPTY);

	@Nullable
	@Override
	public WordsScanner getWordsScanner() {
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
			return name == null ? ANONYMOUS : name;
		} else return ERROR;
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof Concept) {
			String name = ((Concept) element).getName();
			return name == null ? ANONYMOUS : name;
		} else return ERROR;
	}
}