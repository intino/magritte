package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Facet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageNode extends LanguageElement implements tara.language.model.Node {

	private final Node node;
	private List<tara.language.model.FacetTarget> facetTargets;
	private List<tara.language.model.Variable> variables = new ArrayList<>();
	private List<tara.language.model.Node> includes = null;

	public LanguageNode(Node node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = buildFacetTargets(node.facetTargets());
		variables.addAll(collectVariables(node.variables()));
		if (node.parent() != null) variables.addAll(collectVariables(node.parent().variables()));
	}

	@Override
	public tara.language.model.Node context() {
		if (node == null) return null;
		return node.container() == null ? null : new LanguageNode(node.container());
	}

	@Override
	public String type() {
		return node == null ? "" : node.fullType();
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public tara.language.model.Node destinyOfReference() {
		return this;
	}

	@Override
	public void type(String type) {
		node.fullType(type);
	}

	@Override
	public List<String> secondaryTypes() {
		Set<String> types = node.facets().stream().map(FacetApply::type).collect(Collectors.toSet());
		if (node.parent() != null && !parent().equals(node)) types.addAll(parent().types());
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
		return node == null || node.name() == null ? "" : node.name();
	}

	@Override
	public tara.language.model.Node parent() {
		return node.parent() == null ? null : new LanguageNode(node.parent());
	}

	@Override
	public boolean hasSubs() {
		return !node.subs().isEmpty();
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
		return unmodifiableList(node.facets().stream().map(LanguageFacet::new).collect(Collectors.toList()));
	}

	@Override
	public List<tara.language.model.FacetTarget> facetTargets() {
		return unmodifiableList(facetTargets);
	}

	@Override
	public List<tara.language.model.Parameter> parameters() {
		List<tara.language.model.Parameter> parameters = wrapParameters(node.parameters());
		return unmodifiableList(parameters);
	}

	private List<tara.language.model.Parameter> wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		return toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList());
	}

	@Override
	public List<tara.language.model.Node> components() {
		if (includes == null) {
			includes = new ArrayList<>();
			collectIncludes(node);
			if (node.parent() != null) collectIncludes(node.parent());
		}
		return unmodifiableList(includes);
	}

	private void collectIncludes(Node node) {
		includes.addAll(node.components().stream().map(LanguageNode::new).collect(Collectors.toList()));
		includes.addAll(node.referenceComponents().stream().map(LanguageNodeReference::new).collect(Collectors.toList()));
		addFacetTargetIncludes(node);
	}

	private void addFacetTargetIncludes(Node node) {
		for (tara.intellij.lang.psi.FacetTarget facetTarget : node.facetTargets())
			includes.addAll(facetTarget.components().stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	@Override
	public List<tara.language.model.Variable> variables() {
		return unmodifiableList(variables);
	}

	private List<tara.language.model.Variable> collectVariables(Collection<tara.intellij.lang.psi.Variable> variables) {
		return variables.stream().map(LanguageVariable::new).collect(Collectors.toList());
	}

	private List<tara.language.model.FacetTarget> buildFacetTargets(List<tara.intellij.lang.psi.FacetTarget> facetTargets) {
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
		return node.type() + " " + node.name();
	}

	@Override
	public PsiElement element() {
		return node;
	}
}
