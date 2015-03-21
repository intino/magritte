package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.Document;
import org.siani.itrules.ItrRulesReader;
import org.siani.itrules.RuleEngine;
import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;

import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.compiler.model.Annotation.*;

public class LanguageCreator {
	private static final Logger LOG = Logger.getLogger(LanguageCreator.class.getName());
	private static final String LANGUAGE_ITR = "Language.itr";
	private static final String ALLOW = "allow";
	private static final String REQUIRE = "require";
	private static String language;
	private static Locale locale;
	private static Set<Node> processed = new HashSet<>();

	public static String create(CompilerConfiguration configuration, Model model) {
		try {
			language = configuration.getGeneratedLanguage();
			locale = configuration.getLocale();
			Document document = new Document();
			RuleEngine ruleEngine = new RuleEngine(ItrRulesReader.read(loadRules(LANGUAGE_ITR)));
			ruleEngine.render(createFrame(model), document);
			System.out.println(document.content());
			return document.content();
		} catch (TaraException e) {
			return null;
		}
	}

	private static Frame createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new Adapter<Model>() {
			private Frame root;

			@Override
			public void adapt(Frame root, Model model, BuilderContext context) {
				this.root = root;
				root.addFrame("name", language);
				root.addFrame("terminal", Boolean.toString(model.isTerminal()));
				root.addFrame("locale", locale.equals(Locale.ENGLISH) ? "Locale.ENGLISH" : createLocale());
				buildNode(model);
			}

			private String createLocale() {
				return "new Locale(" + locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() + ")";
			}

			private void buildNode(Node node) {
				if (!processed.add(node)) return;
				Frame frame = new Frame("node");
				if (!node.isAbstract()) {
					frame.addFrame("name", getName(node));
					addAllows(node, frame);
					addRequires(node, frame);
					addAssumptions(node, frame);
					root.addFrame("node", frame);
					for (Node inner : node.getIncludedNodes()) {
						if (inner instanceof NodeReference && ((NodeReference) inner).isHas()) continue;
						buildNode(inner);
					}
				}
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
				if (node instanceof NodeImpl) {
					List<Variable> variables = (List<Variable>) node.getVariables();
					for (int i = 0; i < node.getVariables().size(); i++) {
						Variable variable = variables.get(i);
						if (variable.getDefaultValues().isEmpty()) continue;
						addParameter(allows, i, variable, ALLOW);
					}
				}
				if (!node.isNamed() && !node.isProperty())
					allows.addFrame("allow", "name");
				addFacetAllows(node, allows);
			}

			private void addFacetAllows(Node node, Frame allows) {
				for (String facet : node.getAllowedFacets())
					allows.addFrame("allow", new Frame("allow", "facet").addFrame("value", facet));
			}

			private void addContextRequires(Node node, Frame requires) {
				if (node instanceof NodeImpl) {
					List<Variable> variables = (List<Variable>) node.getVariables();
					for (int i = 0; i < variables.size(); i++) {
						Variable variable = variables.get(i);
						if (!variable.getDefaultValues().isEmpty() ||
							(variable.isTerminal() && !node.isTerminal())) continue;
						addParameter(requires, i, variable, REQUIRE);
					}
				}
				if (node.isNamed())
					requires.addFrame(REQUIRE, "name");
				if (node.isAddressed())
					requires.addFrame(REQUIRE, "address");
			}

			private void addParameter(Frame frame, int i, Variable variable, String relation) {
				if (variable.getType().equals("word"))
					frame.addFrame(relation, new Frame(relation, "parameter", "word").
						addFrame("name", variable.getName() + ":word").
						addFrame("words", renderWord(variable)).
						addFrame("multiple", variable.isMultiple()).
						addFrame("position", i));
				else if (variable instanceof VariableReference)
					frame.addFrame(relation, new Frame(relation, "parameter", "reference").
						addFrame("name", variable.getName()).
						addFrame("types", renderReference((VariableReference) variable)).
						addFrame("multiple", variable.isMultiple()).
						addFrame("position", i));
				else frame.addFrame(relation, new Frame(relation, "parameter").
						addFrame("name", variable.getName()).
						addFrame("type", variable.getType()).
						addFrame("multiple", variable.isMultiple()).
						addFrame("position", i));
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
				Frame requires = buildAssumptions(node);
				if (requires.getSlots().length != 0) frame.addFrame("assumptions", requires);
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
					else if (annotation.equals(FACET)) assumptions.addFrame("assumption", "FacetInstance");
					else if (annotation.equals(Annotation.INTENTION))
						assumptions.addFrame("assumption", "IntentionInstance");
				}
			}

			private Frame buildRequiredNodes(Collection<Node> nodeTree) {
				Frame requires = new Frame("requires");
				addRequiredInnerNodes(requires, nodeTree);
				return requires;
			}

			private Frame buildAllowedNodes(Collection<Node> tree) {
				Frame allows = new Frame("allows");
				addAllowedInnerNodes(allows, tree);
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
				for (Node node : tree)
					if (node.isAbstract()) nodes.addAll(node.getChildren());
					else nodes.add(node);
				return nodes;
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
		});
		return builder.createFrame(model);
	}


	private static InputStream loadRules(String itr) throws TaraException {
		InputStream stream = getRulesFromResources(itr);
		if (stream == null) {
			LOG.log(Level.SEVERE, itr + ".itr rules file not found.");
			throw new TaraException(itr + ".itr rules file not found.");
		}
		return stream;
	}

	public static InputStream getRulesFromResources(String rules) throws TaraException {
		return ResourceManager.getStream("rules/" + rules);
	}
}
