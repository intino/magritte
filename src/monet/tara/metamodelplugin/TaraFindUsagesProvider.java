package monet.tara.metamodelplugin;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lexer.FlexAdapter;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;
import monet.tara.metamodelplugin.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;

public class TaraFindUsagesProvider implements FindUsagesProvider {
	private static final DefaultWordsScanner WORDS_SCANNER =
			new DefaultWordsScanner(new FlexAdapter(new TaraLexer((Reader) null)),
					                       TokenSet.create(TaraTypes.IDENTIFIER), TokenSet.create(TaraTypes.COMMENT), TokenSet.EMPTY);

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
		return HelpID.FIND_OTHER_USAGES;
	}

	@NotNull
	@Override
	public String getType(@NotNull PsiElement element) {
		if (element instanceof TaraConceptDefinition) {
			return "Tara Concept";
		} else {
			return "";
		}
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		if (element instanceof TaraConceptDefinition) {
			return ((TaraConceptDefinition) element).getIdentifier();
		} else {
			return "";
		}
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		if (element instanceof TaraConceptDefinition )
			return ((TaraConceptDefinition) element).getIdentifier();
		else return "";
	}
}