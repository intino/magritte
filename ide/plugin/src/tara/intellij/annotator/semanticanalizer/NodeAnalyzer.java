package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraFacetTarget;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Variable;
import tara.lang.semantics.SemanticException;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class NodeAnalyzer extends TaraAnalyzer {

	private Node node;

	public NodeAnalyzer(Node node) {
		this.node = node;
	}

	@Override
	public void analyze() {
		try {
			Language language = TaraUtil.getLanguage((PsiElement) node);
			if (language == null) return;
			new Checker(language).check(node);
		} catch (SemanticException e) {
			PsiElement destiny = e.getOrigin() != null ? (PsiElement) e.getOrigin() : ((TaraNode) node);
			if (destiny instanceof TaraNode) destiny = ((TaraNode) destiny).getSignature();
			else if (destiny instanceof Facet) destiny = ((TaraFacetApply) destiny).getMetaIdentifierList().get(0);
			else if (destiny instanceof FacetTarget) destiny = ((TaraFacetTarget) destiny).getIdentifierReference();
			else if (destiny instanceof Variable) destiny = ((TaraVariable) destiny).getIdentifier();
			results.put(destiny, annotateAndFix(e, destiny));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny, e.getParameters()));
	}
}
