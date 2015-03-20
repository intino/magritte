package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Node;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.FacetTarget;

import java.util.*;

public class LanguageNode extends LanguageElement implements siani.tara.semantic.model.Node {

	private final Node node;
	private FacetTarget[] facetTargets;
	private List<siani.tara.semantic.model.Node> includes = new ArrayList<>();

	public LanguageNode(Node node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = buildFacetTargets(node.getObject().getFacetTargets());
		for (Node inner : node.getInnerNodes())
			includes.add(new LanguageNode(inner));

	}

	@Override
	public siani.tara.semantic.model.Node context() {
		if (node == null) return null;
		if (node.isSub()) {
			Node rootOfSub = findRootOfSub(node.getContainer());
			return rootOfSub == null ? null : new LanguageNode(rootOfSub);
		} else return node.getContainer() == null ? null : new LanguageNode(node.getContainer());
	}

	private Node findRootOfSub(Node container) {
		while (container != null && container.isSub())
			container = container.getContainer();
		return container.getContainer();
	}

	@Override
	public String type() {
		return node == null ? "" : node.getQualifiedName();
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public void type(String type) {
		node.setMeta(type);
	}

	@Override
	public String[] secondaryTypes() {
		List<String> types = new ArrayList<>();
		for (FacetApply facetApply : node.getFacetApplies())
			types.add(facetApply.getFacetName());
		return types.toArray(new String[types.size()]);
	}

	@Override
	public String name() {
		return node.getName() == null ? "" : node.getName();
	}

	@Override
	public siani.tara.semantic.model.Node parent() {
		return node.getParentNode() == null ? null : new LanguageNode(node.getParentNode());
	}

	@Override
	public boolean hasSubs() {
		return !node.getSubNodes().isEmpty();
	}

	@Override
	public Long address() {
		if (node.getAddress() == null) return Long.MIN_VALUE;
		return toLong(node.getAddress().getText());
	}

	@Override
	public String[] annotations() {
		Set<String> annotations = new HashSet<>();
		for (Annotation annotation : node.getAnnotations())
			annotations.add(annotation.getText());
		annotations.addAll(node.getAssumedAnnotations());
		return annotations.toArray(new String[annotations.size()]);
	}

	@Override
	public void annotations(String... strings) {
		node.addInheritedAnnotations(strings);
	}

	@Override
	public Facet[] facets() {
		List<Facet> facets = new ArrayList<>();
		for (final FacetApply facetApply : node.getFacetApplies()) facets.add(new LanguageFacet(facetApply));
		return facets.toArray(new Facet[facets.size()]);
	}

	@Override
	public FacetTarget[] facetTargets() {
		return this.facetTargets;
	}

	@Override
	public siani.tara.semantic.model.Parameter[] parameters() {
		return wrapParameters(node.getParameters());
	}

	private siani.tara.semantic.model.Parameter[] wrapParameters(Parameters toWrap) {
		if (toWrap == null) return new siani.tara.semantic.model.Parameter[0];
		List<siani.tara.semantic.model.Parameter> parameters = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Parameter parameter : toWrap.getParameters())
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		return includes.toArray(new siani.tara.semantic.model.Node[includes.size()]);
	}

	private Long toLong(String address) {
		try {
			return Long.parseLong(address.replace(".", "").replace("&", ""));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private FacetTarget[] buildFacetTargets(Collection<siani.tara.compiler.model.FacetTarget> facetTargets) {
		List<FacetTarget> targets = new ArrayList<>();
		for (final siani.tara.compiler.model.FacetTarget target : facetTargets)
			targets.add(new LanguageFacetTarget(target));
		return targets.toArray(new FacetTarget[targets.size()]);
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
