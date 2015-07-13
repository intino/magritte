package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Parameter;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.semantic.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageNode extends LanguageElement implements tara.semantic.model.Node {

	private final Node node;
	private List<tara.semantic.model.FacetTarget> facetTargets;
	private List<tara.semantic.model.Variable> variables = new ArrayList<>();
	private List<tara.semantic.model.Node> includes = null;

	public LanguageNode(Node node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = buildFacetTargets(node.getFacetTargets());
		variables.addAll(collectVariables(node.getVariables()));
		if (node.getParentNode() != null) variables.addAll(collectVariables(node.getParentNode().getVariables()));
	}

	@Override
	public tara.semantic.model.Node context() {
		if (node == null) return null;
		return node.getContainer() == null ? null : new LanguageNode(node.getContainer());
	}

	@Override
	public String type() {
		return node == null ? "" : node.getFullType();
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
	public void type(String type) {
		node.setFullType(type);
	}

	@Override
	public List<String> secondaryTypes() {
		Set<String> types = node.getFacetApplies().stream().map(FacetApply::getType).collect(Collectors.toSet());
		if (node.getParentNode() != null && !parent().equals(node)) types.addAll(parent().types());
		return new ArrayList<>(types);
	}

	@Override
	public List<String> types() {
		List<String> types = new ArrayList<>();
		types.add(type());
		types.addAll(secondaryTypes());
		types.addAll(TaraUtil.getTypesOf(node));
		return unmodifiableList(types);
	}

	@Override
	public String name() {
		return node == null || node.getName() == null ? "" : node.getName();
	}

	@Override
	public tara.semantic.model.Node parent() {
		return node.getParentNode() == null ? null : new LanguageNode(node.getParentNode());
	}

	@Override
	public boolean hasSubs() {
		return !node.getSubNodes().isEmpty();
	}

	@Override
	public String plate() {
		if (node.getAddress() == null) return "";
		return node.getAddress().getText().substring(1);
	}

	@Override
	public List<String> annotations() {
		Set<String> annotations = node.getAnnotations().stream().map(Annotation::getText).collect(Collectors.toSet());
		annotations.addAll(node.getInheritedFlags());
		return unmodifiableList(new ArrayList<>(annotations));
	}

	@Override
	public List<String> flags() {
		Set<String> flags = node.getFlags().stream().map(Flag::getText).collect(Collectors.toSet());
		flags.addAll(node.getInheritedFlags());
		return unmodifiableList(new ArrayList<>(flags));
	}

	@Override
	public void flags(String... flags) {
		node.addInheritedFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
//		TODO node.addInheritedAnnotations(annotations);
	}

	@Override
	public void moveToTheTop() {
	}

	@Override
	public List<Facet> facets() {
		return unmodifiableList(node.getFacetApplies().stream().map(LanguageFacet::new).collect(Collectors.toList()));
	}

	@Override
	public List<tara.semantic.model.FacetTarget> facetTargets() {
		return unmodifiableList(facetTargets);
	}

	@Override
	public List<tara.semantic.model.Parameter> parameters() {
		List<tara.semantic.model.Parameter> parameters = wrapParameters(node.getParameterList());
		return unmodifiableList(parameters);
	}

	private List<tara.semantic.model.Parameter> wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		return toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList());
	}

	@Override
	public List<tara.semantic.model.Node> includes() {
		if (includes == null) {
			includes = new ArrayList<>();
			collectIncludes(node);
			if (node.getParentNode() != null) collectIncludes(node.getParentNode());
		}
		return unmodifiableList(includes);
	}

	private void collectIncludes(Node node) {
		includes.addAll(node.getIncludes().stream().map(LanguageNode::new).collect(Collectors.toList()));
		includes.addAll(node.getInnerNodeReferences().stream().map(LanguageNodeReference::new).collect(Collectors.toList()));
		addFacetTargetIncludes(node);
	}

	private void addFacetTargetIncludes(Node node) {
		for (tara.intellij.lang.psi.FacetTarget facetTarget : node.getFacetTargets())
			includes.addAll(facetTarget.getIncludes().stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	@Override
	public List<tara.semantic.model.Variable> variables() {
		return unmodifiableList(variables);
	}

	private List<tara.semantic.model.Variable> collectVariables(Collection<tara.intellij.lang.psi.Variable> variables) {
		return variables.stream().map(LanguageVariable::new).collect(Collectors.toList());
	}

	private List<tara.semantic.model.FacetTarget> buildFacetTargets(List<tara.intellij.lang.psi.FacetTarget> facetTargets) {
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
	public PsiElement element() {
		return node;
	}
}
