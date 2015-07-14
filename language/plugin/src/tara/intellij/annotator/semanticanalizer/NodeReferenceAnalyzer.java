package tara.intellij.annotator.semanticanalizer;

import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.NodeReference;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.semantic.LanguageNodeReference;
import tara.language.semantics.SemanticException;

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
			new Checker(language).check(new LanguageNodeReference(nodeReference));
		} catch (SemanticException e) {
			results.put(nodeReference, annotateAndFix(e, nodeReference));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, NodeReference destiny) {
		return new TaraAnnotator.AnnotateAndFix(TaraAnnotator.AnnotateAndFix.Level.ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
