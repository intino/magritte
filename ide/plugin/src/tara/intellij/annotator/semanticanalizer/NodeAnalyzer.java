package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraFacetTarget;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.*;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import static java.util.Collections.singletonList;

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
			checkAnchor(node);
		} catch (SemanticException e) {
			PsiElement destiny = e.getOrigin() != null ? (PsiElement) e.getOrigin() : ((TaraNode) node);
			if (destiny instanceof TaraNode) destiny = ((TaraNode) destiny).getSignature();
			else if (destiny instanceof NodeRoot) return;
			else if (destiny instanceof Facet) destiny = ((TaraFacetApply) destiny).getMetaIdentifierList().get(0);
			else if (destiny instanceof FacetTarget) destiny = ((TaraFacetTarget) destiny).getIdentifierReference();
			else if (destiny instanceof Variable) destiny = ((TaraVariable) destiny).getIdentifier();
			results.put(destiny, annotateAndFix(e, destiny));
		}
	}

	private void checkAnchor(Node node) throws SemanticException {
		if (node == null) return;
		if (!node.isReference() && !node.isDeclaration() && isDynamicLoaded(node) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticException(new SemanticNotification(SemanticNotification.ERROR, "required.anchor", node, singletonList(node.type())));
	}


	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(TYPE.values()[e.level()], e.getMessage(), FixFactory.get(e.key(), destiny, e.getParameters()));
	}

	public boolean isDynamicLoaded(Node node) {
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf((PsiElement) node));
		return facet != null && facet.getConfiguration().isDynamicLoad();
	}
}
