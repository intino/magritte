package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.Language;
import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.model.*;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.CustomRule;
import io.intino.tara.lang.model.rules.variable.ReferenceRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Primitive.REFERENCE;

public class DependencyResolver {
	private final File rulesDirectory;
	private final File semanticLib;
	private final File tempDirectory;
	private Model model;
	private ReferenceManager manager;
	private Map<String, Class<?>> loadedRules = new HashMap();
	private String workingPackage;
	private List<DependencyException> rulesNotLoaded = new ArrayList<>();

	public DependencyResolver(Model model, String workingPackage, File rulesDirectory, File semanticLib, File tempDirectory) throws DependencyException {
		this.model = model;
		this.workingPackage = workingPackage;
		this.rulesDirectory = rulesDirectory;
		this.semanticLib = semanticLib;
		this.tempDirectory = tempDirectory;
		this.manager = new ReferenceManager(this.model);
		model.setRules(loadedRules);
	}

	public void resolve() throws DependencyException {
		resolveParentReference(model);
		resolveInNodes(model);
	}

	public List<DependencyException> rulesNotLoaded() {
		return rulesNotLoaded;
	}

	private void resolveParentReference(Node node) throws DependencyException {
		if (node instanceof NodeReference) return;
		resolveParent(node);
		for (Node component : node.components())
			resolveParentReference(component);
	}

	private void resolveInNodes(Node node) throws DependencyException {
		resolveCustomRules(node);
		for (Node component : node.components()) resolve(component);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveNodesReferences(node);
		resolveVariables(node);
		resolveParametersReference(node);
		resolveInNodes(node);
	}

	private void resolveCustomRules(Node node) throws DependencyException {
		if (node.container() == null) return;
		for (Rule rule : node.container().rulesOf(node))
			if (rule instanceof CustomRule) loadCustomRule(node, (CustomRule) rule);
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
		final Language language = model.language();
		if (language != null && language.instances().containsKey(value.get())) {
			value.setToInstance(true);
			value.instanceTypes(language.instances().get(value.get()).types());
			value.path(language.instances().get(value.get()).path());
			return true;
		}
		return false;
	}

	private Node resolveReferenceParameter(Node node, Primitive.Reference value) throws DependencyException {
		return manager.resolveParameterReference(value, node);
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

	private void resolveNodesReferences(Node node) throws DependencyException {
		for (Node nodeReference : node.referenceComponents()) {
			resolveNodeReference((NodeReference) nodeReference);
			resolveCustomRules(nodeReference);
		}
	}

	private void resolveNodeReference(NodeReference nodeReference) throws DependencyException {
		if (nodeReference.destination() != null) return;
		NodeImpl destiny = manager.resolve(nodeReference);
		if (destiny == null) throw new DependencyException("reject.dependency.reference.node.not.found", nodeReference);
		else nodeReference.destination(destiny);
	}


	private void resolveVariables(Node container) throws DependencyException {
		for (Variable variable : container.variables()) {
			if (variable instanceof VariableReference) resolveVariable((VariableReference) variable, container);
			if (variable.rule() instanceof VariableCustomRule) loadCustomRule(variable);
		}
	}

	private void loadCustomRule(Variable variable) {
		final VariableCustomRule rule = (VariableCustomRule) variable.rule();
		final String source = rule.getExternalWordClass();
		Class<?> aClass = null;
		try {
			aClass = loadedRules.containsKey(source) ?
					loadedRules.get(source) :
					CustomRuleLoader.compileAndLoad(rule, workingPackage, rulesDirectory, semanticLib, tempDirectory);
		} catch (TaraException e) {
			rulesNotLoaded.add(new DependencyException("impossible.load.rule.class", variable, rule.getExternalWordClass(), e.getMessage()));
			rule.qualifiedName(CustomRuleLoader.composeQualifiedName(workingPackage, rule.getExternalWordClass()));
		}
		if (aClass == null) {
			rulesNotLoaded.add(new DependencyException("impossible.load.rule.class", variable, rule.getExternalWordClass()));
			return;
		} else loadedRules.put(source, aClass);
		if (variable.type().equals(Primitive.WORD)) updateRule(aClass, variable);
		else rule.setLoadedClass(aClass);
	}

	private void loadCustomRule(Node node, CustomRule rule) throws DependencyException {
		final String source = rule.getExternalWordClass();
		final Class<?> aClass;
		try {
			aClass = loadedRules.containsKey(source) ?
					loadedRules.get(source) : CustomRuleLoader.compileAndLoad(rule, workingPackage, rulesDirectory, semanticLib, tempDirectory);
		} catch (TaraException e) {
			throw new DependencyException("impossible.load.rule.class", node, rule.getExternalWordClass(), e.getMessage().split("\n")[0]);
		}
		if (aClass != null) loadedRules.put(source, aClass);
		else throw new DependencyException("impossible.load.rule.class", node, rule.getExternalWordClass());
		rule.setLoadedClass(aClass);
	}

	private void updateRule(Class<?> aClass, Variable variable) {
		if (aClass != null)
			variable.rule(new WordRule(collectEnums(Arrays.asList(aClass.getDeclaredFields())), aClass.getSimpleName()));
	}

	private List<String> collectEnums(List<Field> fields) {
		return fields.stream().filter(Field::isEnumConstant).map(Field::getName).collect(Collectors.toList());
	}

	private void resolveVariable(VariableReference variable, Node container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny != null) variable.setDestiny(destiny);
		else if (!tryAsLanguageReference(variable))
			throw new DependencyException("reject.reference.variable.not.found", container, variable.destinyName());
		variable.rule(createReferenceRule(variable));
		resolveVariableDefaultValue(variable, container);
	}

	private void resolveVariableDefaultValue(VariableReference variable, Node container) throws DependencyException {
		if (variable.values().isEmpty() || !(variable.values().get(0) instanceof Primitive.Reference)) return;
		final List<Primitive.Reference> collect = variable.values().stream().map(v -> ((Primitive.Reference) v)).collect(Collectors.toList());
		for (Primitive.Reference v : collect) {
			Node destiny = manager.resolve(v.get(), container);
			if (destiny == null)
				throw new DependencyException("reject.reference.variable.not.found", container, variable.destinyName());
			v.reference(destiny);
		}
	}

	private boolean tryAsLanguageReference(VariableReference variable) {
		final Language language = model.language();
		if (language == null) return false;
		final List<String> types = language.types(variable.destinyName());
		if (types != null) {
			variable.setTypeReference();
			variable.setDestiny(new LanguageNodeReference(types, variable.destinyName()));
			return true;
		}
		return false;
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