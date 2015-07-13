package tara.compiler.semantic.wrappers;

import tara.semantic.model.Tag;
import tara.compiler.model.Element;
import tara.compiler.model.Facet;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.Parameter;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageNode extends LanguageElement implements tara.semantic.model.Node {

	private final NodeImpl node;
	private List<tara.semantic.model.FacetTarget> facetTargets;
	private List<tara.semantic.model.Variable> variables = new ArrayList<>();
	private List<tara.semantic.model.Node> includes = new ArrayList<>();

	public LanguageNode(NodeImpl node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = collectFacetTargets(node.getFacetTargets());
		variables.addAll(collectVariables(node.getVariables()));
		addIncludes(node.getIncludedNodes());
		addFacetTargetIncludes();
	}

	private void addFacetTargetIncludes() {
		for (FacetTarget facetTarget : node.getFacetTargets())
			addIncludes(facetTarget.getIncludedNodes());
	}

	private void addIncludes(Collection<Node> inners) {
		includes.addAll(inners.stream().map(inner -> inner instanceof NodeReference ?
			new LanguageNodeReference((NodeReference) inner) :
			new LanguageNode((NodeImpl) inner)).collect(Collectors.toList()));
	}

	private List<tara.semantic.model.Variable> collectVariables(Collection<tara.compiler.model.Variable> variables) {
		return variables.stream().map(LanguageVariable::new).collect(Collectors.toList());
	}

	@Override
	public tara.semantic.model.Node context() {
		if (node == null || node.getContainer() == null || node.getContainer() instanceof Model) return null;
		return getContainerNode();
	}

	public tara.semantic.model.Node getContainerNode() {
		NodeContainer container = node.getContainer();
		while (!(container instanceof Node))
			container = container.getContainer();
		return new LanguageNode((NodeImpl) container);
	}

	@Override
	public String type() {
		return node.getType();
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public tara.semantic.model.Node destinyOfReference() {
		return this;
	}

	@Override
	public List<String> secondaryTypes() {
		Set<String> types = node.getFacets().stream().map(Facet::getFacetType).collect(Collectors.toSet());
		if (parent() != null) types.addAll(parent().types());
		return unmodifiableList(new ArrayList(types));
	}

	@Override
	public void type(String type) {
		node.setType(type);
		node.setFullType(type);
	}

	@Override
	public List<String> types() {
		List<String> types = new ArrayList<>();
		types.add(type());
		types.addAll(secondaryTypes());
		return unmodifiableList(types); //TODO Add language types
	}

	@Override
	public String name() {
		return node.getName() == null ? "" : node.getName();
	}

	@Override
	public tara.semantic.model.Node parent() {
		return node.getParent() == null ? null : new LanguageNode((NodeImpl) node.getParent());
	}

	@Override
	public boolean hasSubs() {
		return !node.getSubNodes().isEmpty();
	}

	@Override
	public String plate() {
		if (node.getPlate() == null) return "";
		return node.getPlate();
	}

	@Override
	public List<String> annotations() {
		return unmodifiableList(new ArrayList<>(node.getAnnotations().stream().map(Tag::name).collect(Collectors.toList())));
	}

	@Override
	public List<String> flags() {
		Set<String> flags = node.getFlags().stream().map(Tag::name).collect(Collectors.toSet());
		return unmodifiableList(new ArrayList<>(flags));
	}

	@Override
	public void flags(String... flags) {
		node.addFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
		node.addAnnotations(annotations);
	}

	@Override
	public void moveToTheTop() {
		node.moveToTheTop();
	}

	@Override
	public List<tara.semantic.model.Facet> facets() {
		List<tara.semantic.model.Facet> facets = node.getFacets().stream().map(LanguageFacet::new).collect(Collectors.toList());
		return unmodifiableList(facets);
	}

	@Override
	public List<tara.semantic.model.FacetTarget> facetTargets() {
		return this.facetTargets;
	}

	@Override
	public List<tara.semantic.model.Parameter> parameters() {
		return wrapParameters(node.getParameters());
	}

	private List<tara.semantic.model.Parameter> wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null) return Collections.emptyList();
		return unmodifiableList(toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList()));
	}

	@Override
	public List<tara.semantic.model.Node> includes() {
		return unmodifiableList(includes);
	}

	@Override
	public List<tara.semantic.model.Variable> variables() {
		return unmodifiableList(variables);
	}


	private List<tara.semantic.model.FacetTarget> collectFacetTargets(Collection<FacetTarget> facetTargets) {
		return facetTargets.stream().map(LanguageFacetTarget::new).collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		LanguageNode that = (LanguageNode) object;
		return node.equals(that.node);
	}

	@Override
	public int hashCode() {
		return node.hashCode();
	}

	@Override
	public String toString() {
		return node.getType() + " " + node.getName();
	}

	@Override
	public Element element() {
		return node;
	}
}
