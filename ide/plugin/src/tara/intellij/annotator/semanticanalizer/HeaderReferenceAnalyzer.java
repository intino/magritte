package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiReference;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.highlighting.TaraSyntaxHighlighter;
import tara.intellij.lang.psi.HeaderReference;
import tara.intellij.lang.psi.Identifier;

import java.util.List;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE.ERROR;

public class HeaderReferenceAnalyzer extends TaraAnalyzer {

	public static final String MESSAGE = MessageProvider.message("unreached.reference");
	private final HeaderReference reference;

	public HeaderReferenceAnalyzer(HeaderReference reference) {
		this.reference = reference;
	}

	@Override
	public void analyze() {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		Identifier element = identifierList.get(identifierList.size() - 1);
		PsiReference aReference = element.getReference();
		if (aReference == null || aReference.resolve() == null)
			results.put(element, new TaraAnnotator.AnnotateAndFix(ERROR, MESSAGE, TaraSyntaxHighlighter.UNRESOLVED_ACCESS));
	}
}
