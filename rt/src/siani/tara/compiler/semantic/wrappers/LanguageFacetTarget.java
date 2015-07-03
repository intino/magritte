package siani.tara.compiler.semantic.wrappers;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<siani.tara.semantic.model.Node> includes() {
		List<siani.tara.semantic.model.Node> nodes = target.getIncludedNodes().stream().
			map(include -> include instanceof NodeReference ?
				new LanguageNodeReference((NodeReference) include) : new LanguageNode((NodeImpl) include)).collect(Collectors.toList());
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public String type() {
		return target();
	}

	@Override
	public Element element() {
		return target;
	}
}
