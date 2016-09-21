package tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraFacetTarget;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.*;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
			if (isTableInstance()) {
				node.resolve();
				return;
			}
			new Checker(language).check(node);
			checkAnchor(node);
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

	private boolean isTableInstance() {
		return !node.tableName().isEmpty();
	}

	private void checkAnchor(Node node) throws SemanticFatalException {
		if (node == null) return;
		if (!node.isReference() && !node.is(Tag.Instance) && isPersistentModel(node) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(e.level(), e.getMessage(), FixFactory.get(e.key(), destiny, e.getParameters()));
	}


	//TODO
	private boolean isPersistentModel(Node node) {
		final Module module = ModuleProvider.moduleOf((PsiElement) node);
		if (!TaraModuleType.isTara(module)) return false;
		return TaraUtil.configurationOf(module).isPersistent();
	}

	private List<PsiElement> cast(Element[] elements) {
		List<PsiElement> list = new ArrayList<>();
		for (Element element : elements) list.add((PsiElement) element);
		return list;
	}
}
