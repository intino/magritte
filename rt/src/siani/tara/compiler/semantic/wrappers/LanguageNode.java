package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;
import siani.tara.semantic.model.Variable;

import java.util.*;
import java.util.stream.Collectors;

public class LanguageNode extends LanguageElement implements siani.tara.semantic.model.Node {

	private final NodeImpl node;
	private siani.tara.semantic.model.FacetTarget[] facetTargets;
	private List<Variable> variables = new ArrayList<>();
	private List<siani.tara.semantic.model.Node> includes = new ArrayList<>();

	public LanguageNode(NodeImpl node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = collectFacetTargets(node.getFacetTargets());
		variables.addAll(collectVariables(node.getVariables()));
		addIncludes(node.getIncludedNodes());
	}

	private void addIncludes(Collection<Node> inners) {
		for (Node inner : inners)
			includes.add(inner instanceof NodeReference ?
				new LanguageNodeReference((NodeReference) inner) :
				new LanguageNode((NodeImpl) inner));
	}

	private List<Variable> collectVariables(Collection<siani.tara.compiler.model.Variable> variables) {
		List<siani.tara.semantic.model.Variable> semanticVariables = new ArrayList<>();
		for (final siani.tara.compiler.model.Variable variable : variables)
			semanticVariables.add(new LanguageVariable(variable));
		return semanticVariables;
	}

	@Override
	public siani.tara.semantic.model.Node context() {
		if (node == null || node.getContainer() == null || node.getContainer() instanceof Model) return null;
		return getContainerNode();
	}

	public siani.tara.semantic.model.Node getContainerNode() {
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
	public String[] secondaryTypes() {
		List<String> types = new ArrayList<>();
		for (Facet facet : node.getFacets())
			types.add(facet.getType());
		return types.toArray(new String[types.size()]);
	}

	@Override
	public void type(String type) {
		node.setType(type);
		node.setFullType(type);
	}

	@Override
	public String[] types() {
		List<String> types = new ArrayList<>();
		types.add(type());
		Collections.addAll(types, secondaryTypes());
		return types.toArray(new String[types.size()]); //TODO Add language types
	}

	@Override
	public String name() {
		return node.getName() == null ? "" : node.getName();
	}

	@Override
	public siani.tara.semantic.model.Node parent() {
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
	public String[] annotations() {
		Set<String> annotations = node.getAnnotations().stream().map(Tag::name).collect(Collectors.toSet());
		return annotations.toArray(new String[annotations.size()]);
	}

	@Override
	public String[] flags() {
		Set<String> flags = node.getFlags().stream().map(Tag::name).collect(Collectors.toSet());
		return flags.toArray(new String[flags.size()]);
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
	public siani.tara.semantic.model.Facet[] facets() {
		List<siani.tara.semantic.model.Facet> facets = new ArrayList<>();
		for (final Facet facet : node.getFacets())
			facets.add(new LanguageFacet(facet));
		return facets.toArray(new siani.tara.semantic.model.Facet[facets.size()]);
	}

	@Override
	public siani.tara.semantic.model.FacetTarget[] facetTargets() {
		return this.facetTargets;
	}

	@Override
	public siani.tara.semantic.model.Parameter[] parameters() {
		return wrapParameters(node.getParameters());
	}

	private siani.tara.semantic.model.Parameter[] wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null) return new siani.tara.semantic.model.Parameter[0];
		List<siani.tara.semantic.model.Parameter> parameters = new ArrayList<>();
		for (Parameter parameter : toWrap)
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		return includes.toArray(new siani.tara.semantic.model.Node[includes.size()]);
	}

	@Override
	public siani.tara.semantic.model.Variable[] variables() {
		return variables.toArray(new Variable[variables.size()]);
	}


	private siani.tara.semantic.model.FacetTarget[] collectFacetTargets(Collection<FacetTarget> facetTargets) {
		List<siani.tara.semantic.model.FacetTarget> targets = new ArrayList<>();
		for (final FacetTarget target : facetTargets)
			targets.add(new LanguageFacetTarget(target));
		return targets.toArray(new siani.tara.semantic.model.FacetTarget[targets.size()]);
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
