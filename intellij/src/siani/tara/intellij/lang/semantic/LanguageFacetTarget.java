package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifierReference;
import siani.tara.semantic.model.*;

import java.util.ArrayList;
import java.util.List;

public class LanguageFacetTarget extends LanguageElement implements FacetTarget {

	TaraFacetTarget target;

	public LanguageFacetTarget(TaraFacetTarget target) {
		this.target = target;
	}

	@Override
	public String target() {
		TaraIdentifierReference reference = target.getIdentifierReference();
		return reference != null ? reference.getText() : null;
	}

	@Override
	public Node[] includes() {
		Body body = target.getBody();
		if (body == null) return new siani.tara.semantic.model.Node[0];
		List<siani.tara.intellij.lang.psi.Node> concepts = (List<siani.tara.intellij.lang.psi.Node>) body.getConceptList();
		List<siani.tara.semantic.model.Node> nodes = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Node inner : concepts)
			nodes.add(new LanguageNode(inner));
		return nodes.toArray(new siani.tara.semantic.model.Node[nodes.size()]);
	}

	@Override
	public PsiElement element() {
		return target;
	}
}
