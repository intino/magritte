package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Variable;

import java.util.*;

public class LanguageNode extends LanguageElement implements siani.tara.semantic.model.Node {

	private final Node node;
	private FacetTarget[] facetTargets;
	private List<Variable> variables = new ArrayList<>();
	private List<siani.tara.semantic.model.Node> includes = new ArrayList<>();

	public LanguageNode(Node node) {
		this.node = node;
		if (node == null) return;
		this.facetTargets = buildFacetTargets(node.getFacetTargets());
		variables.addAll(collectVariables(node.getVariables()));
		addIncludes(node);
		Node parent = node.getParentNode();
		if (parent != null) {
			variables.addAll(collectVariables(parent.getVariables()));
			addIncludes(parent);
		}
	}

	private void addIncludes(Node node) {
		for (Node inner : node.getInnerNodes())
			includes.add(new LanguageNode(inner));
		for (NodeReference nodeReference : node.getInnerNodeReferences())
			includes.add(new LanguageNodeReference(nodeReference));
	}

	@Override
	public siani.tara.semantic.model.Node context() {
		if (node == null) return null;
		if (node.isSub()) {
			Node rootOfSub = findRootOfSub(node);
			return rootOfSub == null ? null : new LanguageNode(rootOfSub);
		} else return node.container() == null ? null : new LanguageNode(node.container());
	}

	private Node findRootOfSub(Node node) {
		Node container = node.container();
		while (container != null && container.isSub())
			container = container.container();
		return container == null ? null : container.container();
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
		List<String> types = new ArrayList<>();
		for (FacetApply facetApply : node.getFacetApplies())
			types.add(facetApply.getType());
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
		Set<String> annotations = new HashSet<>();
		for (Annotation annotation : node.getAnnotations())
			annotations.add(annotation.getText());
		annotations.addAll(node.getAssumedFlags());
		return annotations.toArray(new String[annotations.size()]);
	}

	@Override
	public String[] flags() {
		Set<String> flags = new HashSet<>();
		for (Flag flag : node.getFlags())
			flags.add(flag.getText());
		flags.addAll(node.getAssumedFlags());
		return flags.toArray(new String[flags.size()]);
	}

	@Override
	public void flags(String... flags) {
		node.addInheritedFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
//		TODO node.addInheritedAnnotations(flags);
	}

	@Override
	public void moveToTheTop() {
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
		List<siani.tara.semantic.model.Parameter> parameters = wrapParameters(node.getParameters());
		parameters.addAll(wrapVarInits(node.getVarInits()));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	private List<siani.tara.semantic.model.Parameter> wrapParameters(Collection<Parameter> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		List<siani.tara.semantic.model.Parameter> parameters = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Parameter parameter : toWrap)
			parameters.add(new LanguageParameter(parameter));
		return parameters;
	}

	private List<siani.tara.semantic.model.Parameter> wrapVarInits(Collection<VarInit> toWrap) {
		if (toWrap == null || toWrap.isEmpty()) return new ArrayList<>();
		List<siani.tara.semantic.model.Parameter> parameters = new ArrayList<>();
		for (VarInit varInit : toWrap)
			parameters.add(new LanguageVarParameter(varInit));
		return parameters;
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		return includes.toArray(new siani.tara.semantic.model.Node[includes.size()]);
	}

	@Override
	public Variable[] variables() {
		return variables.toArray(new Variable[variables.size()]);
	}

	private List<Variable> collectVariables(Collection<siani.tara.intellij.lang.psi.Variable> variables) {
		List<Variable> semanticVariables = new ArrayList<>();
		for (final siani.tara.intellij.lang.psi.Variable variable : variables)
			semanticVariables.add(new LanguageVariable(variable));
		return semanticVariables;
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
