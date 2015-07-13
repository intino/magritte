package tara.compiler.semantic.wrappers;

import tara.compiler.model.Element;
import tara.compiler.model.Facet;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.FacetTarget;
import tara.semantic.model.Tag;
import tara.semantic.model.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageNodeReference extends LanguageNode implements tara.semantic.model.Node {

	private final NodeReference reference;

	public LanguageNodeReference(NodeReference reference) {
		super(null);
		this.reference = reference;
	}

	@Override
	public tara.semantic.model.Node context() {
		if (reference == null || reference.getContainer() == null || reference.getContainer() instanceof Model)
			return null;
		return getContainerNode();
	}

	public tara.semantic.model.Node getContainerNode() {
		NodeContainer container = reference.getContainer();
		while (!(container instanceof Node))
			container = container.getContainer();
		return new LanguageNode((NodeImpl) container);
	}

	@Override
	public tara.semantic.model.Node destinyOfReference() {
		return new LanguageNode(reference.getDestiny());
	}

	@Override
	public String type() {
		return reference.getType();
	}

	@Override
	public boolean isReference() {
		return true;
	}

	@Override
	public void type(String type) {
	}

	@Override
	public List<String> secondaryTypes() {
		return unmodifiableList(reference.getDestiny().getFacets().stream().map(Facet::getFacetType).collect(Collectors.toList()));
	}

	@Override
	public String name() {
		return "";
	}

	@Override
	public tara.semantic.model.Node parent() {
		return null;
	}

	@Override
	public boolean hasSubs() {
		return false;
	}

	@Override
	public String plate() {
		return "";
	}

	@Override
	public List<String> annotations() {
		return unmodifiableList(reference.getAnnotations().stream().map(Tag::name).collect(Collectors.toList()));
	}

	@Override
	public List<String> flags() {
		return unmodifiableList(reference.getFlags().stream().map(Tag::name).collect(Collectors.toList()));
	}

	@Override
	public void moveToTheTop() {
	}

	@Override
	public void flags(String... flags) {
		reference.addFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
		reference.addAnnotations(annotations);
	}

	@Override
	public List<tara.semantic.model.Facet> facets() {
		return Collections.emptyList();
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	@Override
	public List<tara.semantic.model.Node> includes() {
		List<tara.semantic.model.Node> includes = reference.getIncludedNodes().stream().map(inner -> inner instanceof NodeReference ?
			new LanguageNodeReference((NodeReference) inner) :
			new LanguageNode((NodeImpl) inner)).collect(Collectors.toList());
		return unmodifiableList(includes);
	}

	@Override
	public String toString() {
		return "reference " + reference.getType();
	}

	@Override
	public Element element() {
		return reference;
	}
}
