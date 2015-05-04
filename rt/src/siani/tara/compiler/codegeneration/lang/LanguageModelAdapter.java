package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Tag;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Assumption;

import java.util.*;

import static siani.tara.compiler.model.Tag.*;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model> {
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
	public void execute(Frame root, Model model, FrameContext<Model> context) {
		this.root = root;
		this.model = model;
		root.addFrame("name", languageName);
		root.addFrame("terminal", Boolean.toString(model.isTerminal()));
		root.addFrame("locale", locale.equals(Locale.ENGLISH) ? "Locale.ENGLISH" : createLocale());
		buildNode(model);
		addInheritedRules();
	}

	private void addInheritedRules() {
		List<String> cases = collectCaseRules();
		new LanguageInheritanceFiller(root, cases, language).fill();
	}

	private List<String> collectCaseRules() {
		List<String> cases = new ArrayList<>();
		for (Map.Entry<String, siani.tara.semantic.model.Context> entry : language.catalog().entrySet())
			if (isTerminalInstance(entry.getValue())) cases.add(entry.getKey());
		return cases;
	}

	private boolean isTerminalInstance(siani.tara.semantic.model.Context value) {
		for (Assumption assumption : value.assumptions())
			if (assumption instanceof Assumption.TerminalInstance) return true;
		return false;
	}

	private String createLocale() {
		return "new Locale(" + locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() + ")";
	}

	private void buildNode(Node node) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame(root).addTypes("node");
		if (!node.isAbstract() && !node.isAnonymous() && !node.isTerminalInstance()) {
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
		Frame typesFrame = new Frame(frame).addTypes("nodeType");
		Set<String> typeSet = new LinkedHashSet<>();
		typeSet.add(node.getType());
		Collection<String> languageTypes = getLanguageTypes(node);
		if (languageTypes != null)
			typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrame.addFrame("type", type);
		if (typesFrame.slots().length > 0)
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
		Frame allows = buildAllowedNodes(frame, node.getIncludedNodes());
		for (Frame allowFrame : getCasesConstrains(allows, collectCaseRules()))
			allows.addFrame("allow", allowFrame);
		addContextAllows(node, allows);
		if (allows.slots().length != 0) frame.addFrame("allows", allows);
	}

	private Collection<Frame> getCasesConstrains(Frame allows, List<String> nodes) {
		List<Frame> frames = new ArrayList<>();
		for (String node : nodes)
			frames.add(new Frame(allows).addTypes("multiple", ALLOW).addFrame("type", node).addFrame("relation", COMPONENT.getName()));
		return frames;
	}

	private void addRequires(Node node, Frame frame) {
		Frame requires = buildRequiredNodes(frame, node.getIncludedNodes());
		addContextRequires(node, requires);
		if (requires.slots().length != 0) frame.addFrame("requires", requires);
	}

	private void addContextAllows(Node node, Frame allows) {
		if (node instanceof NodeImpl) addParameterAllows((List<Variable>) node.getVariables(), allows);
		if (!node.isNamed() && !node.isPropertyInstance()) allows.addFrame("allow", "name");
		addFacetAllows(node, allows);
	}

	private void addParameterAllows(List<Variable> variables, Frame allows) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (variable.getDefaultValues().isEmpty() && !variable.isTerminal()) continue;
			new LanguageParameterAdapter(language).addParameter(allows, i, variable, ALLOW);
		}
	}

	private void addFacetAllows(Node node, Frame allows) {
		for (String facet : node.getAllowedFacets()) {
			Frame frame = new Frame(allows).addTypes("allow", "facet").addFrame("value", facet);
			allows.addFrame("allow", frame);
			FacetTarget facetNode = findFacetTarget(node, facet);
			if (facetNode == null) continue;
			addParameterAllows((List<Variable>) facetNode.getVariables(), frame);
			addParameterRequires((List<Variable>) facetNode.getVariables(), frame, true, 0);//TRUE? añadir terminales
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
		if (node instanceof NodeImpl) {
			int index = new LanguageParameterAdapter(language).addTerminalParameters(node, requires);
			addParameterRequires((List<Variable>) node.getVariables(), requires, node.isTerminal(), index);
		}
		if (node.isNamed()) requires.addFrame(REQUIRE, "name");
		if (node.isAddressed()) requires.addFrame(REQUIRE, "address");
	}

	private void addParameterRequires(List<Variable> variables, Frame requires, boolean terminalNode, int index) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (!variable.getDefaultValues().isEmpty() ||
				(variable.isTerminal() && !terminalNode)) continue;
			new LanguageParameterAdapter(language).addParameter(requires, index + i, variable, REQUIRE);
		}
	}


	private void addAssumptions(Node node, Frame frame) {
		Frame assumptions = buildAssumptions(frame, node);
		if (assumptions.slots().length != 0) frame.addFrame("assumptions", assumptions);
	}

	private Frame buildAssumptions(Frame frame, Node node) {
		Frame assumptions = new Frame(frame).addTypes("assumptions");
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, Frame assumptions) {
		for (Tag tag : node.getAnnotations())
			if (!tag.equals(SINGLE) || tag.equals(REQUIRED) || tag.equals(MULTIPLE) || tag.equals(OPTIONAL))
				assumptions.addFrame("assumption", tag.getName());
		for (Tag tag : node.getFlags()) {
			if (tag.equals(TERMINAL)) assumptions.addFrame("assumption", "TerminalInstance");
			else if (tag.equals(PROPERTY)) assumptions.addFrame("assumption", "PropertyInstance");
			else if (tag.equals(FEATURE)) assumptions.addFrame("assumption", "FeatureInstance");
			else if (tag.equals(FACET)) assumptions.addFrame("assumption", "FacetInstance");
		}
	}

	private Frame buildRequiredNodes(Frame frame, Collection<Node> nodeTree) {
		Frame requires = new Frame(frame).addTypes("requires");
		addRequiredInnerNodes(requires, nodeTree);
		return requires;
	}

	private Frame buildAllowedNodes(Frame frame, Collection<Node> nodes) {
		Frame allows = new Frame(frame).addTypes("allows");
		if (!nodes.isEmpty())
			addAllowedInnerNodes(allows, nodes);
		return allows;
	}

	private void addAllowedInnerNodes(Frame allows, Collection<Node> tree) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node node : tree) {
			if (node.isRequired()) continue;
			for (Node candidate : collectCandidates(node))
				if (node.isSingle()) singleNodes.add(createAllowedSingle(allows, candidate, node.getAnnotations()));
				else multipleNodes.add(createAllowedMultiple(allows, candidate, node.getAnnotations()));
		}
		addAllowedNodes(allows, multipleNodes, singleNodes);
	}

	private void addRequiredInnerNodes(Frame requires, Collection<Node> tree) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node node : tree) {
			if (!node.isRequired()) continue;
			Collection<Node> candidates = collectCandidates(node);
			if (candidates.size() > 1 && node.isSingle())
				requires.addFrame("require", createOneOf(requires, candidates, node.getAnnotations()));
			else for (Node candidate : candidates)
				if (node.isSingle()) singleNodes.add(createRequiredSingle(requires, candidate, node.getAnnotations()));
				else multipleNodes.add(createRequiredMultiple(requires, candidate, node.getAnnotations()));
		}
		addRequiredNodes(requires, multipleNodes, singleNodes);
	}

	private Collection<Node> collectCandidates(Node node) {
		List<Node> nodes = new ArrayList<>();
		if (node.isAnonymous() || node.isTerminalInstance()) return nodes;
		if (node.isAbstract()) getNonAbstractChildren(node, nodes);
		else nodes.add(node);
		return nodes;
	}

	private void getNonAbstractChildren(Node node, List<Node> nodes) {
		for (Node child : node.getChildren()) {
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else nodes.add(child);
		}
	}

	private void addAllowedNodes(Frame allows, List<Frame> multipleNodeFrames, List<Frame> singleNodes) {
		if (!multipleNodeFrames.isEmpty())
			allows.addFrame(ALLOW, multipleNodeFrames.toArray(new Frame[multipleNodeFrames.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(ALLOW, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private void addRequiredNodes(Frame allows, List<Frame> multipleNodes, List<Frame> singleNodes) {
		if (!multipleNodes.isEmpty())
			allows.addFrame(REQUIRE, multipleNodes.toArray(new Frame[multipleNodes.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(REQUIRE, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private Frame createAllowedSingle(Frame owner, Node node, Collection<Tag> annotations) {
		Frame frame = new Frame(owner).addTypes("single", ALLOW).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
		for (Tag tag : annotations)
			frame.addFrame("tags", tag.name());
		return frame;
	}

	private Frame createAllowedMultiple(Frame owner, Node node, Collection<Tag> annotations) {
		Frame frame = new Frame(owner).addTypes("multiple", ALLOW).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
		for (Tag tag : annotations)
			frame.addFrame("tags", tag.name());
		return frame;
	}

	private String getName(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().getQualifiedName() : node.getQualifiedName();
	}

	private Frame createOneOf(Frame owner, Collection<Node> candidates, Collection<Tag> annotations) {
		Frame frame = new Frame(owner).addTypes("oneOf", REQUIRE);
		for (Node candidate : candidates)
			frame.addFrame("require", createRequiredSingle(frame, candidate, annotations));
		return frame;
	}

	private Frame createRequiredSingle(Frame owner, Node node, Collection<Tag> annotations) {
		Frame frame = new Frame(owner).addTypes("single", REQUIRE).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
		for (Tag tag : annotations) frame.addFrame("tags", tag.name());
		return frame;
	}

	private Frame createRequiredMultiple(Frame owner, Node node, Collection<Tag> annotations) {
		Frame frame = new Frame(owner).addTypes("multiple", REQUIRE).addFrame("type", getName(node)).addFrame("relation", getRelation(node));
		for (Tag tag : annotations) frame.addFrame("tags", tag.name());
		return frame;
	}

	private String getRelation(Node node) {
		if (node.isAggregated()) return "AGGREGATED";
		if (node.isAssociated()) return "ASSOCIATED";
		return "COMPONENT";
	}


}