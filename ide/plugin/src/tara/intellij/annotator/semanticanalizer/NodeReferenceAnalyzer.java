package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticException;

public class NodeReferenceAnalyzer extends TaraAnalyzer {
	private final TaraNodeReference nodeReference;

	public NodeReferenceAnalyzer(TaraNodeReference nodeReference) {
		this.nodeReference = nodeReference;
	}

	@Override
	public void analyze() {
		try {
			Language language = TaraUtil.getLanguage(nodeReference);
			if (language == null) return;
			new Checker(language).check(nodeReference);
		} catch (SemanticException e) {
			results.put(nodeReference, annotateAndFix(e, nodeReference));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, Node destiny) {
		return new TaraAnnotator.AnnotateAndFix(TaraAnnotator.AnnotateAndFix.Level.ERROR, e.getMessage(), FixFactory.get(e.key(), (PsiElement) destiny));
	}
}
