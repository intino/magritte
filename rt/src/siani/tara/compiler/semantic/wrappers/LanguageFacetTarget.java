package siani.tara.compiler.semantic.wrappers;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.ArrayList;
import java.util.List;

public class LanguageFacetTarget extends LanguageElement implements siani.tara.semantic.model.FacetTarget {

	FacetTarget target;

	public LanguageFacetTarget(FacetTarget target) {
		this.target = target;
	}

	@Override
	public String target() {
		return target.getTarget();
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		List<siani.tara.semantic.model.Node> nodes = new ArrayList<>();
		for (Node include : target.getIncludedNodes())
			nodes.add(include instanceof NodeReference ?
				new LanguageNodeReference((NodeReference) include) :
				new LanguageNode(include));
		return nodes.toArray(new siani.tara.semantic.model.Node[nodes.size()]);
	}

	@Override
	public Element element() {
		return (Element) target;
	}
}
