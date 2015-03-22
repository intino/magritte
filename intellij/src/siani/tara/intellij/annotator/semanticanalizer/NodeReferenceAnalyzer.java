package siani.tara.intellij.annotator.semanticanalizer;

import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.FixFactory;
import siani.tara.intellij.lang.psi.NodeReference;
import siani.tara.intellij.lang.psi.TaraNodeReference;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.semantic.LanguageNodeReference;
import siani.tara.semantic.SemanticException;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
