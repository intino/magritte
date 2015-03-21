package siani.tara.compiler.semantic.wrappers;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageFacet extends LanguageElement implements siani.tara.semantic.model.Facet {

	Facet facet;

	public LanguageFacet(Facet facetApply) {
		this.facet = facetApply;
	}

	@Override
	public String type() {
		return facet.type();
	}

	@Override
	public siani.tara.semantic.model.Parameter[] parameters() {
		return wrapParameters(facet.getParameters());
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		List<siani.tara.semantic.model.Node> nodes = new ArrayList<>();
		for (Node include : facet.getIncludedNodes())
			nodes.add(include instanceof NodeReference ?
				new LanguageNodeReference((NodeReference) include) :
				new LanguageNode(include));
		return nodes.toArray(new siani.tara.semantic.model.Node[nodes.size()]);
	}

	private siani.tara.semantic.model.Parameter[] wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new siani.tara.semantic.model.Parameter[0];

		List<siani.tara.semantic.model.Parameter> parameters = new ArrayList<>();
		for (Parameter parameter : toWrap)
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	@Override
	public Element element() {
		return (Element) facet;
	}
}
