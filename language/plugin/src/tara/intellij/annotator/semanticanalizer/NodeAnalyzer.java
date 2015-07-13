package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.FacetApply;
import tara.intellij.lang.psi.FacetTarget;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.semantic.LanguageElement;
import tara.intellij.lang.semantic.LanguageNode;
import tara.semantic.SemanticException;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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
			new Checker(language).check(new LanguageNode(node));
		} catch (SemanticException e) {
			PsiElement destiny = e.getOrigin() != null ? ((LanguageElement) e.getOrigin()).element() : node.getSignature();
			if (destiny instanceof Node) destiny = ((Node) destiny).getSignature();
			if (destiny instanceof FacetApply) destiny = ((TaraFacetApply) destiny).getMetaIdentifierList().get(0);
			if (destiny instanceof FacetTarget) destiny = ((FacetTarget) destiny).getIdentifierReference();
			results.put(destiny, annotateAndFix(e, destiny));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
