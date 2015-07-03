package siani.tara.compiler.semantic.wrappers;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFacet extends LanguageElement implements siani.tara.semantic.model.Facet {

	Facet facet;

	public LanguageFacet(Facet facetApply) {
		this.facet = facetApply;
	}

	@Override
	public String type() {
		return facet.getFacetType();
	}

	@Override
	public List<siani.tara.semantic.model.Parameter> parameters() {
		return wrapParameters(facet.getParameters());
	}

	@Override
	public List<siani.tara.semantic.model.Node> includes() {
		List<siani.tara.semantic.model.Node> nodes = facet.getIncludedNodes().stream().map(include -> include instanceof NodeReference ?
			new LanguageNodeReference((NodeReference) include) :
			new LanguageNode((NodeImpl) include)).collect(Collectors.toList());
		return Collections.unmodifiableList(nodes);
	}

	private List<siani.tara.semantic.model.Parameter> wrapParameters(List<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return Collections.emptyList();
		return Collections.unmodifiableList(toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList()));
	}

	@Override
	public Element element() {
		return facet;
	}

	@Override
	public String toString() {
		return facet.toString();
	}
}
