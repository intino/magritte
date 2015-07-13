package tara.compiler.semantic.wrappers;


import tara.compiler.model.Element;
import tara.compiler.model.Facet;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFacet extends LanguageElement implements tara.semantic.model.Facet {

	Facet facet;

	public LanguageFacet(Facet facet) {
		this.facet = facet;
	}

	@Override
	public String type() {
		return facet.getFacetType();
	}

	@Override
	public List<String> nodeTypes() {
		final NodeContainer container = facet.getContainer();
		if (container instanceof Node) return Collections.unmodifiableList(collectTypes((Node) container));
		return Collections.emptyList();
	}

	private List<String> collectTypes(Node node) {
		return node.getFacets().stream().map(Facet::getFacetType).collect(Collectors.toList());
	}

	@Override
	public List<tara.semantic.model.Parameter> parameters() {
		return wrapParameters(facet.getParameters());
	}

	@Override
	public List<tara.semantic.model.Node> includes() {
		List<tara.semantic.model.Node> nodes = facet.getIncludedNodes().stream().map(include -> include instanceof NodeReference ?
			new LanguageNodeReference((NodeReference) include) :
			new LanguageNode((NodeImpl) include)).collect(Collectors.toList());
		return Collections.unmodifiableList(nodes);
	}

	private List<Parameter> wrapParameters(List<tara.compiler.model.Parameter> toWrap) {
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
