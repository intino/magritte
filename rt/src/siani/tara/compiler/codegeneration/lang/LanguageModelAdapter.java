package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.model.Context;

import java.util.*;

import static siani.tara.compiler.model.Annotation.*;

class LanguageModelAdapter implements Adapter<Model> {
	private static final String ALLOW = "allow";
	private static final String REQUIRE = "require";

	private Frame root;
	private Model model;
	private Set<String> processed = new HashSet<>();
	private String languageName;
	private Locale locale;
	private siani.tara.Language language;

	public LanguageModelAdapter(String languageName, Locale locale, Language language) {
		this.languageName = languageName;
		this.locale = locale;
		this.language = language;
	}

	@Override
	public void adapt(Frame root, Model model, BuilderContext context) {
		this.root = root;
		this.model = model;
		root.addFrame("name", languageName);
		root.addFrame("terminal", Boolean.toString(model.isTerminal()));
		root.addFrame("locale", locale.equals(Locale.ENGLISH) ? "Locale.ENGLISH" : createLocale());
		addInheritedRules();
		buildNode(model);
	}

	private void addInheritedRules() {
		List<String> cases = collectCaseRules();
		LanguageInheritanceFiller filler = new LanguageInheritanceFiller(root, cases, language);
		filler.fill();
	}

	private List<String> collectCaseRules() {
		List<String> cases = new ArrayList<>();
		for (Map.Entry<String, Context> entry : language.catalog().entrySet())
			if (isCase(entry.getValue())) cases.add(entry.getKey());
		return cases;
	}

	private boolean isCase(Context value) {
		for (Assumption assumption : value.assumptions())
			if (assumption instanceof Assumption.Case) return true;
		return false;
	}

	private String createLocale() {
		return "new Locale(" + locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() + ")";
	}

	private void buildNode(Node node) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame("node");
		if (!node.isAbstract() && !node.isAnonymous() && !node.isCase()) {
			frame.addFrame("name", getName(node));
			addTypes(node, frame);
			addAllows(node, frame);
			addRequires(node, frame);
			addAssumptions(node, frame);
			root.addFrame("node", frame);
		}
		for (Node inner : node.getIncludedNodes()) {
			if (inner instanceof NodeReference && ((NodeReference) inner).isHas()) continue;
			buildNode(inner);
		}
	}

	private void addTypes(Node node, Frame frame) {
		if (node.getType() == null) return;
		Frame typesFrame = new Frame("nodeType");
		Set<String> typeSet = new LinkedHashSet<>();
		typeSet.add(node.getType());
		Collection<String> languageTypes = getLanguageTypes(node);
		if (languageTypes != null)
			typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrame.addFrame("type", type);
		if (typesFrame.getSlots().length > 0)
			frame.addFrame("nodeType", typesFrame);
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.getType());
	}

	private boolean alreadyProcessed(Node node) {
		String qn = node instanceof NodeReference ? ((NodeReference) node).getDestiny().getQualifiedName() : node.getQualifiedName();
		return !processed.add(qn);
	}

	private void addAllows(Node node, Frame frame) {
		Frame allows = buildAllowedNodes(node.getIncludedNodes());
		addContextAllows(node, allows);
		if (allows.getSlots().length != 0) frame.addFrame("allows", allows);
	}

	private void addRequires(Node node, Frame frame) {
		Frame requires = buildRequiredNodes(node.getIncludedNodes());
		addContextRequires(node, requires);
		if (requires.getSlots().length != 0) frame.addFrame("requires", requires);
	}

	private void addContextAllows(Node node, Frame allows) {
		if (node instanceof NodeImpl) addParameterAllows((List<Variable>) node.getVariables(), allows);
		if (!node.isNamed() && !node.isProperty()) allows.addFrame("allow", "name");
		addFacetAllows(node, allows);
	}

	private void addParameterAllows(List<Variable> variables, Frame allows) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (variable.getDefaultValues().isEmpty()) continue;
			addParameter(allows, i, variable, ALLOW);
		}
	}

	private void addFacetAllows(Node node, Frame allows) {
		for (String facet : node.getAllowedFacets()) {
			Frame frame = new Frame("allow", "facet").addFrame("value", facet);
			allows.addFrame("allow", frame);
			FacetTarget facetNode = findFacetTarget(node, facet);
			if (facetNode == null) continue;
			addParameterAllows((List<Variable>) facetNode.getVariables(), frame);
			addParameterRequires((List<Variable>) facetNode.getVariables(), frame, true);//TRUE?
		}

	}

	private FacetTarget findFacetTarget(Node target, String facet) {
		for (Node node : model.getIncludedNodes())
			if (facet.equals(node.getName())) return correspondingTarget(node, target);
		return null;
	}

	private FacetTarget correspondingTarget(Node node, Node target) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			if (facetTarget.getTargetNode().equals(target)) //refactor to node hierarchy
				return facetTarget;
		return null;
	}

	private void addContextRequires(Node node, Frame requires) {
		if (node instanceof NodeImpl)
			addParameterRequires((List<Variable>) node.getVariables(), requires, node.isTerminal());
		if (node.isNamed()) requires.addFrame(REQUIRE, "name");
		if (node.isAddressed()) requires.addFrame(REQUIRE, "address");
	}


	private void addParameterRequires(List<Variable> variables, Frame requires, boolean terminal) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (!variable.getDefaultValues().isEmpty() ||
				(variable.isTerminal() && !terminal)) continue;
			addParameter(requires, i, variable, REQUIRE);
		}
	}

	private void addParameter(Frame frame, int i, Variable variable, String relation) {
		if (variable.getType().equals("word"))
			frame.addFrame(relation, new Frame(relation, "parameter", "word").
				addFrame("name", variable.getName() + ":word").
				addFrame("words", renderWord(variable)).
				addFrame("multiple", variable.isMultiple()).
				addFrame("position", i).
				addFrame("annotations", getAnnotations(variable)).
				addFrame("metric", variable.getMetric() == null ? "" : variable.getMetric()));
		else if (variable instanceof VariableReference)
			frame.addFrame(relation, new Frame(relation, "parameter", "reference").
				addFrame("name", variable.getName()).
				addFrame("types", renderReference((VariableReference) variable)).
				addFrame("multiple", variable.isMultiple()).
				addFrame("position", i).
				addFrame("annotations", getAnnotations(variable)).
				addFrame("metric", variable.getMetric() == null ? "" : variable.getMetric()));
		else frame.addFrame(relation, new Frame(relation, "parameter").
				addFrame("name", variable.getName()).
				addFrame("type", variable.getType()).
				addFrame("multiple", variable.isMultiple()).
				addFrame("position", i).
				addFrame("annotations", getAnnotations(variable)).
				addFrame("metricBo", variable.getMetric() == null ? "" : variable.getMetric()));
	}

	private String[] getAnnotations(Variable variable) {
		List<String> annotations = new ArrayList<>();
		for (Annotation annotation : variable.getAnnotations()) annotations.add(annotation.getName());
		return annotations.toArray(new String[annotations.size()]);
	}

	private String[] renderWord(Variable variable) {
		return variable.getAllowedValues().toArray(new String[variable.getAllowedValues().size()]);
	}

	private String[] renderReference(VariableReference reference) {
		Node node = reference.getDestiny();
		if (node == null) return new String[0];
		if (!node.isAbstract()) return new String[]{node.getQualifiedName()};
		List<String> types = new ArrayList<>();
		for (Node declaredNode : node.getChildren()) //TODO search extends too
			types.add(declaredNode.getQualifiedName());
		return types.toArray(new String[types.size()]);
	}

	private void addAssumptions(Node node, Frame frame) {
		Frame assumptions = buildAssumptions(node);
		if (assumptions.getSlots().length != 0) frame.addFrame("assumptions", assumptions);
	}

	private Frame buildAssumptions(Node node) {
		Frame assumptions = new Frame("assumptions");
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, Frame assumptions) {
		for (Annotation annotation : node.getAnnotations()) {
			if (annotation.equals(SINGLE) || annotation.equals(REQUIRED)) continue;
			if (annotation.isMeta()) assumptions.addFrame("assumption", annotation.getName().substring(1));
			else if (annotation.equals(TERMINAL)) assumptions.addFrame("assumption", "Case");
			else if (annotation.equals(PROPERTY)) assumptions.addFrame("assumption", "PropertyInstance");
			else if (annotation.equals(FACET)) assumptions.addFrame("assumption", "FacetInstance");
			else if (annotation.equals(Annotation.INTENTION)) assumptions.addFrame("assumption", "IntentionInstance");
		}
	}

	private Frame buildRequiredNodes(Collection<Node> nodeTree) {
		Frame requires = new Frame("requires");
		addRequiredInnerNodes(requires, nodeTree);
		return requires;
	}

	private Frame buildAllowedNodes(Collection<Node> nodes) {
		Frame allows = new Frame("allows");
		if (!nodes.isEmpty())
			addAllowedInnerNodes(allows, nodes);
		return allows;
	}

	private void addAllowedInnerNodes(Frame allows, Collection<Node> tree) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node node : collectCandidates(tree)) {
			if (node.isRequired()) continue;
			if (node.isSingle()) singleNodes.add(createAllowedSingle(node));
			else multipleNodes.add(createAllowedMultiple(node));
		}
		addAllowedNodes(allows, multipleNodes, singleNodes);
	}

	private void addRequiredInnerNodes(Frame requires, Collection<Node> tree) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node node : collectCandidates(tree)) {
			if (!node.isRequired()) continue;
			if (node.isSingle()) singleNodes.add(createRequiredSingle(node));
			else multipleNodes.add(createRequiredMultiple(node));
		}
		addRequiredNodes(requires, multipleNodes, singleNodes);
	}

	private Collection<Node> collectCandidates(Collection<Node> tree) {
		List<Node> nodes = new ArrayList<>();
		for (Node node : tree) {
			if (node.isAnonymous() || node.isCase()) continue;
			if (node.isAbstract())
				getNonAbstractChildren(node, nodes);
			else nodes.add(node);
		}

		return nodes;
	}

	private void getNonAbstractChildren(Node node, List<Node> nodes) {
		for (Node child : node.getChildren()) {
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else nodes.add(child);
		}
	}

	private void addAllowedNodes(Frame allows, List<Frame> multipleNodes, List<Frame> singleNodes) {
		if (!multipleNodes.isEmpty())
			allows.addFrame(ALLOW, multipleNodes.toArray(new Frame[multipleNodes.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(ALLOW, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private void addRequiredNodes(Frame allows, List<Frame> multipleNodes, List<Frame> singleNodes) {
		if (!multipleNodes.isEmpty())
			allows.addFrame(REQUIRE, multipleNodes.toArray(new Frame[multipleNodes.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(REQUIRE, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private Frame createAllowedSingle(Node node) {
		return new Frame("single", ALLOW).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
	}

	private Frame createAllowedMultiple(Node node) {
		return new Frame("multiple", ALLOW).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
	}

	private String getName(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().getQualifiedName() : node.getQualifiedName();
	}

	private Frame createRequiredSingle(Node node) {
		return new Frame("single", REQUIRE).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
	}

	private Frame createRequiredMultiple(Node node) {
		return new Frame("multiple", REQUIRE).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
	}

	private String getRelation(Node node) {
		if (node.isAggregated()) return "AGGREGATED";
		if (node.isAssociated()) return "ASSOCIATED";
		return "COMPOSED";
	}
}