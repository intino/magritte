package tara.intellij.codeinsight;

import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.intellij.spellchecker.tokenizer.TokenizerBase;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Identifier;

public class TaraSpellcheckerStrategy extends SpellcheckingStrategy {

	private final Tokenizer<Identifier> myIdentifierTokenizer = TokenizerBase.create(PlainTextSplitter.getInstance());

	@NotNull
	@Override
	public Tokenizer getTokenizer(PsiElement element) {
		if (element instanceof Identifier) return myIdentifierTokenizer;
		return super.getTokenizer(element);
	}
}
