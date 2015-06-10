package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Variable;

import java.util.*;
import java.util.stream.Collectors;

public class LanguageNode extends LanguageElement implements siani.tara.semantic.model.Node {

	private final Node node;
	private List<FacetTarget> facetTargets;
	private List<Variable> variables = new ArrayList<>();
	private List<siani.tara.semantic.model.Node> includes = null;

	public LanguageNode(Node node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = buildFacetTargets(node.getFacetTargets());
		variables.addAll(collectVariables(node.getVariables()));
		if (node.getParentNode() != null) variables.addAll(collectVariables(node.getParentNode().getVariables()));
	}

	@Override
	public siani.tara.semantic.model.Node context() {
		if (node == null) return null;
		return node.container() == null ? null : new LanguageNode(node.container());
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
	public void type(String type) {
		node.setFullType(type);
	}

	@Override
	public String[] secondaryTypes() {
		Set<String> types = node.getFacetApplies().stream().map(FacetApply::getType).collect(Collectors.toSet());
		if (node.getParentNode() != null) Collections.addAll(types, parent().types());
		return types.toArray(new String[types.size()]);
	}

	@Override
	public String[] types() {
		List<String> types = new ArrayList<>();
		types.add(type());
		Collections.addAll(types, secondaryTypes());
		Collection<String> typesOf = TaraUtil.getTypesOf(node);
		if (typesOf != null) types.addAll(typesOf);
		return types.toArray(new String[types.size()]); //TODO Add language types
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
	public String plate() {
		if (node.getAddress() == null) return "";
		return node.getAddress().getText().substring(1);
	}

	@Override
	public String[] annotations() {
		Set<String> annotations = node.getAnnotations().stream().map(Annotation::getText).collect(Collectors.toSet());
		annotations.addAll(node.getAssumedFlags());
		return annotations.toArray(new String[annotations.size()]);
	}

	@Override
	public String[] flags() {
		Set<String> flags = node.getFlags().stream().map(Flag::getText).collect(Collectors.toSet());
		flags.addAll(node.getAssumedFlags());
		return flags.toArray(new String[flags.size()]);
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
	public Facet[] facets() {
		List<Facet> facets = node.getFacetApplies().stream().map(LanguageFacet::new).collect(Collectors.toList());
		return facets.toArray(new Facet[facets.size()]);
	}

	@Override
	public FacetTarget[] facetTargets() {
		return facetTargets.toArray(new FacetTarget[facetTargets.size()]);
	}

	@Override
	public siani.tara.semantic.model.Parameter[] parameters() {
		List<siani.tara.semantic.model.Parameter> parameters = wrapParameters(node.getParameters());
		parameters.addAll(wrapVarInits(node.getVarInits()));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	private List<siani.tara.semantic.model.Parameter> wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		return toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList());
	}

	private List<siani.tara.semantic.model.Parameter> wrapVarInits(Collection<VarInit> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		return toWrap.stream().map(LanguageVarParameter::new).collect(Collectors.toList());
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		if (includes == null) {
			includes = new ArrayList<>();
			collectIncludes(node);
			if (node.getParentNode() != null) collectIncludes(node.getParentNode());
		}
		return includes.toArray(new siani.tara.semantic.model.Node[includes.size()]);
	}

	private void collectIncludes(Node node) {
		includes.addAll(node.getIncludes().stream().map(LanguageNode::new).collect(Collectors.toList()));
		includes.addAll(node.getInnerNodeReferences().stream().map(LanguageNodeReference::new).collect(Collectors.toList()));
		addFacetTargetIncludes(node);
	}

	private void addFacetTargetIncludes(Node node) {
		for (siani.tara.intellij.lang.psi.FacetTarget facetTarget : node.getFacetTargets())
			includes.addAll(facetTarget.includes().stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	@Override
	public Variable[] variables() {
		return variables.toArray(new Variable[variables.size()]);
	}

	private List<Variable> collectVariables(Collection<siani.tara.intellij.lang.psi.Variable> variables) {
		return variables.stream().map(LanguageVariable::new).collect(Collectors.toList());
	}

	private List<FacetTarget> buildFacetTargets(List<siani.tara.intellij.lang.psi.FacetTarget> facetTargets) {
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
