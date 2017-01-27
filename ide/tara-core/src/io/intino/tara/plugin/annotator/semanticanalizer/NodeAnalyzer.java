package io.intino.tara.plugin.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import io.intino.tara.Checker;
import io.intino.tara.Language;
import io.intino.tara.lang.model.*;
import io.intino.tara.plugin.annotator.TaraAnnotator;
import io.intino.tara.plugin.annotator.fix.FixFactory;
import io.intino.tara.plugin.lang.psi.TaraFacetApply;
import io.intino.tara.plugin.lang.psi.TaraFacetTarget;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		} catch (SemanticFatalException fatal) {
			for (SemanticException e : fatal.exceptions()) {
				List<PsiElement> origins = e.origin() != null ? cast(e.origin()) : Collections.singletonList((TaraNode) node);
				for (PsiElement destiny : origins) {
					if (destiny instanceof TaraNode) destiny = ((TaraNode) destiny).getSignature();
					else if (destiny instanceof NodeRoot) return;
					else if (destiny instanceof Facet) destiny = ((TaraFacetApply) destiny).getMetaIdentifier();
					else if (destiny instanceof FacetTarget) destiny = ((TaraFacetTarget) destiny).getIdentifierReference();
					results.put(destiny, annotateAndFix(e, destiny));
				}
			}
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(e.level(), e.getMessage(), FixFactory.get(e.key(), destiny, e.getParameters()));
	}

	private List<PsiElement> cast(Element[] elements) {
		List<PsiElement> list = new ArrayList<>();
		for (Element element : elements) list.add((PsiElement) element);
		return list;
	}
}
