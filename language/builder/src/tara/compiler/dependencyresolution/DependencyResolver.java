package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.model.rules.CustomRule;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.model.rules.WordRule;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.REFERENCE;

public class DependencyResolver {
	Model model;
	ReferenceManager manager;
	private Map<String, Class<?>> loadedRules = new HashMap();
	private String generatedLanguage;
	private final File rulesDirectory;
	private final File semanticLib;

	public DependencyResolver(Model model, String generatedLanguage, File rulesDirectory, File semanticLib) throws DependencyException {
		this.model = model;
		this.generatedLanguage = generatedLanguage;
		this.rulesDirectory = rulesDirectory;
		this.semanticLib = semanticLib;
		this.manager = new ReferenceManager(this.model);
		model.setRules(loadedRules);
	}

	public void resolve() throws DependencyException {
		resolveInNodes(model);
		resolveFacets(model);
	}

	private void resolveInNodes(Node node) throws DependencyException {
		for (Node inner : node.components())
			resolve(inner);
	}

	private void resolveFacets(Node node) throws DependencyException {
		if (node instanceof NodeReference) return;
		resolveInFacets(node);
		resolveInTargets(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveParent(node);
		resolveInnerReferenceNodes(node);
		resolveVariables(node);
		resolveParametersReference(node);
		for (Node include : node.components())
			resolve(include);
	}

	private void resolveParametersReference(Parametrized parametrized) throws DependencyException {
		for (Parameter parameter : parametrized.parameters())
			resolveParameterValue((NodeContainer) parametrized, parameter);
	}

	private void resolveParameterValue(NodeContainer node, Parameter parameter) throws DependencyException {
		if (!areReferenceValues(parameter)) return;
		List<Node> nodes = new ArrayList<>();
		for (Object value : parameter.values()) {
			Node reference = resolveReferenceParameter(node, (Primitive.Reference) value);
			if (reference != null) nodes.add(reference);
		}
		if (!nodes.isEmpty()) {
			parameter.inferredType(REFERENCE);
			parameter.substituteValues(nodes);
		}
	}

	private Node resolveReferenceParameter(NodeContainer node, Primitive.Reference reference) throws DependencyException {
		return manager.resolve(reference.get(), node);
	}

	private boolean areReferenceValues(Parameter parameter) {
		return parameter.values().get(0) instanceof Primitive.Reference;
	}

	private void resolveParent(Node node) throws DependencyException {
		if (node.parent() == null && node.parentName() != null) {
			Node parent = manager.resolveParent(node.parentName(), getNodeContainer(node.container()));
			if (parent == null) throw new DependencyException("reject.dependency.parent.node.not.found", node);
			else {
				((NodeImpl) node).setParent(parent);
				parent.addChild(node);
			}
		}
	}

	private void resolveInnerReferenceNodes(Node node) throws DependencyException {
		for (Node nodeReference : node.referenceComponents())
			resolveNodeReference((NodeReference) nodeReference);
	}

	private void resolveNodeReference(NodeReference nodeReference) throws DependencyException {
		if (nodeReference.getDestiny() != null) return;
		NodeImpl destiny = manager.resolve(nodeReference);
		if (destiny == null) throw new DependencyException("reject.dependency.reference.node.not.found", nodeReference);
		else nodeReference.setDestiny(destiny);
	}

	private void resolveInFacets(Node node) throws DependencyException {
		facets(node);
		for (Node inner : node.components()) resolveFacets(inner);
	}

	private void facets(Node node) throws DependencyException {
		for (Facet facet : node.facets()) {
			resolveVariables(facet);
			resolveParametersReference(facet);
			for (Node include : facet.components())
				if (include instanceof NodeReference) resolveNodeReference((NodeReference) include);
				else resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.facetTargets()) {
			resolveVariables(facet);
			resolveFacetTarget(facet);
			resolveConstraints(facet);
			for (Node include : facet.components())
				if (include instanceof NodeImpl) resolve(include);
				else resolveNodeReference((NodeReference) include);
			for (Node inner : node.components()) resolveFacets(inner);
		}
		for (Node inner : node.components()) resolveFacets(inner);

	}

	private void resolveConstraints(FacetTarget facet) throws DependencyException {
		List<Node> constraintNodes = new ArrayList<>();
		for (String constraintQN : facet.constraints()) {
			Node destiny = manager.resolve(constraintQN, facet.container());
			if (destiny == null) throw new DependencyException("reject.facet.target.not.found", facet);
			else constraintNodes.add(destiny);
		}
		facet.constraintNodes(constraintNodes);
	}

	private void resolveVariables(NodeContainer container) throws DependencyException {
		for (Variable variable : container.variables()) {
			if (variable instanceof VariableReference) resolveVariable((VariableReference) variable, container);
			if (variable.rule() instanceof CustomRule) loadCustomRule(variable);
		}
	}

	private void loadCustomRule(Variable variable) {
		final CustomRule rule = (CustomRule) variable.rule();
		final String source = rule.getSource();
		final Class<?> aClass = loadedRules.containsKey(source) ?
			loadedRules.get(source) :
			RuleLoader.compileAndLoad(rule, generatedLanguage, rulesDirectory, semanticLib);
		if (aClass != null) loadedRules.put(source, aClass);
		if (variable.type().equals(Primitive.WORD)) updateRule(aClass, variable);
		else rule.setLoadedClass(aClass);
	}

	private void updateRule(Class<?> aClass, Variable variable) {
		if (aClass != null)
			variable.rule(new WordRule(collectEnums(Arrays.asList(aClass.getDeclaredFields())), aClass.getSimpleName()));
	}

	private List<String> collectEnums(List<Field> fields) {
		return fields.stream().filter(Field::isEnumConstant).map(Field::getName).collect(Collectors.toList());
	}

	private void resolveFacetTarget(FacetTarget facet) throws DependencyException {
		Node destiny = manager.resolve(facet, facet.container());
		if (destiny == null) throw new DependencyException("reject.facet.target.not.found", facet);
		else facet.targetNode(destiny);
	}

	private void resolveVariable(VariableReference variable, NodeContainer container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny == null)
			throw new DependencyException("reject.variable.not.found", container, variable.type().getName());
		else variable.setDestiny(destiny);
		variable.rule(createReferenceRule(variable));
	}

	private ReferenceRule createReferenceRule(VariableReference variable) {
		return new ReferenceRule(collectTypes(variable.destinyOfReference()));
	}

	private Set<String> collectTypes(Node node) {
		Set<String> set = new HashSet<>();
		if (!node.isAbstract()) set.add(node.qualifiedName());
		for (Node child : node.children())
			set.addAll(collectTypes(child));
		return set;
	}

	private Node getNodeContainer(NodeContainer reference) {
		NodeContainer container = reference;
		while (!(container instanceof NodeImpl)) {
			if (container.container() == null) break;
			container = container.container();
		}
		return (Node) container;
	}
}