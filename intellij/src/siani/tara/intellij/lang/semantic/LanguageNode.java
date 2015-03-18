package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;
import siani.tara.model.Facet;
import siani.tara.model.FacetTarget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageNode extends LanguageElement implements siani.tara.model.Node {

	private final Node node;
	private final FacetTarget[] facetTargets;
	private List<siani.tara.model.Node> includes = new ArrayList<>();

	public LanguageNode(Node node) {
		this.node = node;
		this.facetTargets = buildFacetTargets(node.getFacetTargets());
		for (Node inner : node.getInnerConcepts()) includes.add(new LanguageNode(inner));
	}

	@Override
	public siani.tara.model.Node context() {
		return node.getContainer() != null ? new LanguageNode(node.getContainer()) : null;
	}

	@Override
	public String type() {
		return node.getFullType();
	}

	@Override
	public void type(String type) {
		node.setFullType(type);
	}

	@Override
	public String name() {
		return node.getName() == null ? "" : node.getName();
	}

	@Override
	public siani.tara.model.Node parent() {
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
		if (node.getAnnotationsNode() == null) return new String[0];
		List<? extends Annotation> annotationList = node.getAnnotationsNode().getAnnotationList();
		List<String> annotations = new ArrayList<>();
		for (Annotation annotation : annotationList) annotations.add(annotation.getText());
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
	public siani.tara.model.Parameter[] parameters() {
		return wrapParameters(node.getParameters());
	}

	private siani.tara.model.Parameter[] wrapParameters(Parameters toWrap) {
		if (toWrap == null) return new siani.tara.model.Parameter[0];
		List<siani.tara.model.Parameter> parameters = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Parameter parameter : toWrap.getParameters())
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new siani.tara.model.Parameter[parameters.size()]);
	}

	@Override
	public siani.tara.model.Node[] includes() {
		return includes.toArray(new siani.tara.model.Node[includes.size()]);
	}

	private Long toLong(String address) {
		try {
			return Long.parseLong(address.replace(".", "").replace("&", ""));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private FacetTarget[] buildFacetTargets(Collection<TaraFacetTarget> facetTargets) {
		List<FacetTarget> targets = new ArrayList<>();
		for (final TaraFacetTarget target : facetTargets) targets.add(new LanguageFacetTarget(target));
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
	public PsiElement element() {
		return node;
	}
}
