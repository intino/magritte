package tara.compiler.dependencyresolution;

import tara.Language;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.CustomRule;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.model.rules.variable.VariableCustomRule;
import tara.lang.model.rules.variable.WordRule;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.REFERENCE;

public class DependencyResolver {
	private final File rulesDirectory;
	private final File semanticLib;
	private final File tempDirectory;
	private Model model;
	private ReferenceManager manager;
	private Map<String, Class<?>> loadedRules = new HashMap();
	private String scope;

	public DependencyResolver(Model model, String generatedLanguage, File rulesDirectory, File semanticLib, File tempDirectory) throws DependencyException {
		this.model = model;
		this.scope = generatedLanguage;
		this.rulesDirectory = rulesDirectory;
		this.semanticLib = semanticLib;
		this.tempDirectory = tempDirectory;
		this.manager = new ReferenceManager(this.model);
		model.setRules(loadedRules);
	}

	public void resolve() throws DependencyException {
		resolveParentReference(model);
		resolveInNodes(model);
		resolveFacetTargets(model);
	}

	private void resolveParentReference(Node node) throws DependencyException {
		if (node instanceof NodeReference) return;
		resolveParent(node);
		if (node.facetTarget() != null) resolveParent(node.facetTarget());
		for (Node component : node.components())
			resolveParentReference(component);
	}

	private void resolveParent(FacetTarget facetTarget) {
		final Node owner = facetTarget.owner();
		for (Node component : owner.container().components())
			if (component.name() != null && component.name().equals(owner.name()) && component.isAbstract() && !component.equals(owner) && component.facetTarget() == null) {
				facetTarget.parent(component);
				component.addChild(facetTarget.owner());
				return;
			}
	}

	private void resolveInNodes(Node node) throws DependencyException {
		resolveCustomRules(node);
		for (Node component : node.components()) resolve(component);
	}

	private void resolveFacetTargets(Node node) throws DependencyException {
		if (node instanceof NodeReference) return;
		resolveInTargets(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveComponentReferenceNodes(node);
		resolveVariables(node);
		resolveParametersReference(node);
		resolveInNodes(node);
	}

	private void resolveCustomRules(Node node) throws DependencyException {
		if (node.container() == null) return;
		final CompositionRule compositionRule = node.container().ruleOf(node);
		if (compositionRule instanceof CustomRule) loadCustomRule(node, (CustomRule) compositionRule);
	}

	private void resolveParametersReference(Parametrized parametrized) throws DependencyException {
		for (Parameter parameter : parametrized.parameters())
			resolveParameterValue((Node) parametrized, parameter);
	}

	private void resolveParameterValue(Node node, Parameter parameter) throws DependencyException {
		if (parameter.values().isEmpty() || !areReferenceValues(parameter)) return;
		List<Object> nodes = new ArrayList<>();
		for (Object value : parameter.values()) {
			Node reference = resolveReferenceParameter(node, (Primitive.Reference) value);
			if (reference != null) nodes.add(reference);
			else if (tryWithAnInstance((Primitive.Reference) value)) nodes.add(value);
		}
		if (!nodes.isEmpty()) {
			parameter.type(REFERENCE);
			parameter.substituteValues(nodes);
		}
	}

	private boolean tryWithAnInstance(Primitive.Reference value) {
		final Language language = model.getLanguage();
		if (language != null && language.instances().keySet().contains(value.get())) {
			value.setToInstance(true);
			value.instanceTypes(language.instances().get(value.get()).types());
			value.path(language.instances().get(value.get()).path());
			return true;
		}
		return false;
	}

	private Node resolveReferenceParameter(Node node, Primitive.Reference reference) throws DependencyException {
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

	private void resolveComponentReferenceNodes(Node node) throws DependencyException {
		for (Node nodeReference : node.referenceComponents()) {
			resolveNodeReference((NodeReference) nodeReference);
			resolveCustomRules(nodeReference);
		}
	}

	private void resolveNodeReference(NodeReference nodeReference) throws DependencyException {
		if (nodeReference.getDestiny() != null) return;
		NodeImpl destiny = manager.resolve(nodeReference);
		if (destiny == null) throw new DependencyException("reject.dependency.reference.node.not.found", nodeReference);
		else nodeReference.setDestiny(destiny);
	}

	private void resolveInTargets(Node node) throws DependencyException {
		if (node.facetTarget() != null) {
			resolveFacetTarget(node.facetTarget());
			resolveConstraints(node.facetTarget());
		}
		for (Node component : node.components()) resolveFacetTargets(component);
	}

	private void resolveConstraints(FacetTarget facet) throws DependencyException {
		for (FacetTarget.Constraint constraint : facet.constraints()) {
			Node destiny = resolve(facet, constraint);
			if (destiny == null) throw new DependencyException("reject.facet.target.not.found", facet);
			else constraint.node(destiny);
		}
	}

	private Node resolve(FacetTarget facet, FacetTarget.Constraint constraint) {
		final Node resolve = manager.resolveFacetConstraint(constraint.name(), facet.targetNode().qualifiedName());
		return resolve == null ? manager.resolve(constraint.name(), facet.owner()) : resolve;
	}

	private void resolveVariables(Node container) throws DependencyException {
		for (Variable variable : container.variables()) {
			if (variable instanceof VariableReference) resolveVariable((VariableReference) variable, container);
			if (variable.rule() instanceof VariableCustomRule) loadCustomRule(variable);
		}
	}

	private void loadCustomRule(Variable variable) throws DependencyException {
		final VariableCustomRule rule = (VariableCustomRule) variable.rule();
		final String source = rule.getSource();
		final Class<?> aClass;
		try {
			aClass = loadedRules.containsKey(source) ?
				loadedRules.get(source) :
				CustomRuleLoader.compileAndLoad(rule, scope, rulesDirectory, semanticLib, tempDirectory);
		} catch (TaraException e) {
			throw new DependencyException("impossible.load.rule.class", variable, rule.getSource(), e.getMessage());
		}
		if (aClass != null) loadedRules.put(source, aClass);
		else throw new DependencyException("impossible.load.rule.class", variable, rule.getSource());
		if (variable.type().equals(Primitive.WORD)) updateRule(aClass, variable);
		else rule.setLoadedClass(aClass);
	}

	private void loadCustomRule(Node node, CustomRule rule) throws DependencyException {
		final String source = rule.getSource();
		final Class<?> aClass;
		try {
			aClass = loadedRules.containsKey(source) ?
				loadedRules.get(source) : CustomRuleLoader.compileAndLoad(rule, scope, rulesDirectory, semanticLib, tempDirectory);
		} catch (TaraException e) {
			throw new DependencyException("impossible.load.rule.class", node, rule.getSource(), e.getMessage());
		}
		if (aClass != null) loadedRules.put(source, aClass);
		else throw new DependencyException("impossible.load.rule.class", node, rule.getSource());
		rule.setLoadedClass(aClass);
	}

	private void updateRule(Class<?> aClass, Variable variable) {
		if (aClass != null) variable.rule(new WordRule(collectEnums(Arrays.asList(aClass.getDeclaredFields())), aClass.getSimpleName()));
	}

	private List<String> collectEnums(List<Field> fields) {
		return fields.stream().filter(Field::isEnumConstant).map(Field::getName).collect(Collectors.toList());
	}

	private void resolveFacetTarget(FacetTarget target) throws DependencyException {
		Node destiny = manager.resolve(target, target.owner());//TODO poner parent
		if (destiny == null) throw new DependencyException("reject.facet.target.not.found", target);
		else target.targetNode(destiny);
	}

	private void resolveVariable(VariableReference variable, Node container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny == null)
			throw new DependencyException("reject.reference.variable.not.found", container, variable.destinyName());
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