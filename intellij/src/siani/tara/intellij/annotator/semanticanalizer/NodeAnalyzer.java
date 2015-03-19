package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.FixFactory;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.semantic.LanguageElement;
import siani.tara.intellij.lang.semantic.LanguageNode;
import siani.tara.semantic.SemanticException;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class NodeAnalyzer extends TaraAnalyzer {

	private Node node;

	public NodeAnalyzer(Node node) {
		this.node = node;
	}

	@Override
	public void analyze() {
		try {
			Language language = TaraUtil.getLanguage(node);
			if (language == null) return;
			new Checker(language).check(new LanguageNode(node.resolve()));
		} catch (SemanticException e) {
			PsiElement destiny = e.getOrigin() != null ? ((LanguageElement) e.getOrigin()).element() : node.getSignature();
			if (destiny instanceof Node) destiny = ((Node) destiny).getSignature();
			results.put(destiny, annotateAndFix(e, destiny));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
