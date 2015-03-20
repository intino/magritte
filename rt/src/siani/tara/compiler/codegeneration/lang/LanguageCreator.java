package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.Document;
import org.siani.itrules.ItrRulesReader;
import org.siani.itrules.RuleEngine;
import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.model.*;

import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.model.Annotation.*;

public class LanguageCreator {
	private static final Logger LOG = Logger.getLogger(LanguageCreator.class.getName());
	private static final String LANGUAGE_ITR = "Language.itr";
	private static final String ALLOW = "allow";
	private static final String REQUIRE = "require";

	public static String create(String language, Model model) {
		try {
			Document document = new Document();
			RuleEngine ruleEngine = new RuleEngine(ItrRulesReader.read(loadRules(LANGUAGE_ITR)));
			ruleEngine.render(createFrame(language, model), document);
			System.out.println(document.content());
			return document.content();
		} catch (TaraException e) {
			return null;
		}
	}

	private static Frame createFrame(final String language, final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new Adapter<Model>() {
			private Frame root;

			@Override
			public void adapt(Frame root, Model model, BuilderContext context) {
				this.root = root;
				root.addFrame("name", language);
				root.addFrame("terminal", Boolean.toString(model.isTerminal()));
				root.addFrame("locale", model.getLocale().equals(Locale.ENGLISH) ? "Locale.ENGLISH" : createLocale(model.getLocale()));
				buildModel(model);
				buildNodes(model.getNodeTree());
			}

			private String createLocale(Locale locale) {
				return "new Locale(" + locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() + ")";
			}

			private void buildModel(Model model) {
				Frame rootFrame = new Frame("node").addFrame("name", "");
				addAllows(model, rootFrame);
				addRequires(model, rootFrame);
				root.addFrame("node", rootFrame);
			}

			private void buildNodes(NodeTree nodeTree) {
				for (Node node : nodeTree)
					buildNode(node);
			}

			private void buildNode(Node node) {
				Frame frame = new Frame("node");
				if (!node.isAbstract()) {
					frame.addFrame("name", getName(node));
					addAllows(node, frame);
					addRequires(node, frame);
					addAssumptions(node, frame);
					root.addFrame("node", frame);
					for (Node inner : filter(node.getInnerNodes())) {
						if (inner.is(LinkNode.class) && ((LinkNode) inner).isReference()) continue;
						buildNode(inner);
					}
				}
				for (Node inner : node.getSubNodes())
					buildNode(inner);
			}

			private void addAllows(Model model, Frame rootFrame) {
				Frame allows = buildAllowedNodes(model.getNodeTree());
				if (allows.getSlots().length != 0) rootFrame.addFrame("allows", allows);
			}

			private void addRequires(Model model, Frame rootFrame) {
				Frame requires = buildRequiredNodes(model.getNodeTree());
				if (requires.getSlots().length != 0) rootFrame.addFrame("requires", requires);
			}

			private void addAllows(Node node, Frame frame) {
				Frame allows = buildAllowedNodes(filter(node.getInnerNodes()));
				addContextAllows(node, allows);
				if (allows.getSlots().length != 0) frame.addFrame("allows", allows);
			}

			private void addRequires(Node node, Frame frame) {
				Frame requires = buildRequiredNodes(filter(node.getInnerNodes()));
				addContextRequires(node, requires);
				if (requires.getSlots().length != 0) frame.addFrame("requires", requires);
			}

			private void addContextAllows(Node node, Frame allows) {
				for (int i = 0; i < node.getObject().getVariables().size(); i++) {
					Variable variable = node.getObject().getVariables().get(i);
					if (!variable.hasValue()) continue;
					addParameter(allows, i, variable, ALLOW);
				}
				if (!node.is(NAMED) && !node.is(PROPERTY))
					allows.addFrame("allow", "name");
				addFacetAllows(node, allows);
			}

			private void addFacetAllows(Node node, Frame allows) {
				for (Map.Entry<String, List<FacetTarget>> facet : node.getObject().getAllowedFacets().entrySet())
					allows.addFrame("allow", new Frame("allow", "facet").addFrame("value", facet.getKey()));
			}

			private void addContextRequires(Node node, Frame requires) {
				for (int i = 0; i < node.getObject().getVariables().size(); i++) {
					Variable variable = node.getObject().getVariables().get(i);
					if (variable.hasValue() ||
						(variable.isTerminal() && !node.is(TERMINAL))) continue;
					addParameter(requires, i, variable, REQUIRE);
				}
				if (node.is(NAMED))
					requires.addFrame(REQUIRE, "name");
				if (node.is(ADDRESSED))
					requires.addFrame(REQUIRE, "address");
			}

			private void addParameter(Frame allows, int i, Variable variable, String relation) {
				if (variable instanceof Word)
					allows.addFrame(relation, new Frame(relation, "parameter", "word").
						addFrame("name", variable.getName() + ":word").
						addFrame("words", renderWord((Word) variable)).
						addFrame("multiple", variable.isList()).
						addFrame("position", i));
				else if (variable instanceof Reference)
					allows.addFrame(relation, new Frame(relation, "parameter", "reference").
						addFrame("name", variable.getName()).
						addFrame("types", renderReference((Reference) variable)).
						addFrame("multiple", variable.isList()).
						addFrame("position", i));
				else allows.addFrame(relation, new Frame(relation, "parameter").
						addFrame("name", variable.getName()).
						addFrame("type", variable.getType()).
						addFrame("multiple", variable.isList()).
						addFrame("position", i));
			}

			private String[] renderWord(Word variable) {
				return variable.getWordTypes().toArray(new String[variable.getWordTypes().size()]);
			}

			private String[] renderReference(Reference reference) {
				Node node = model.searchNode(reference.getType());
				if (node == null) return new String[0];
				if (!node.isAbstract()) return new String[]{node.getQualifiedName()};
				List<String> types = new ArrayList<>();
				for (DeclaredNode declaredNode : node.getDeepSubNodes()) //TODO search extends too
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

			private NodeTree filter(NodeTree nodes) {
				NodeTree nodeTree = new NodeTree();
				for (Node inner : nodes) {
					if (inner.is(DeclaredNode.class) && inner.isSub()) continue;
					nodeTree.add(inner);
				}
				return nodeTree;
			}

			private Frame buildRequiredNodes(NodeTree nodeTree) {
				Frame requires = new Frame("requires");
				addRequiredInnerNodes(requires, nodeTree);
				return requires;
			}

			private Frame buildAllowedNodes(NodeTree tree) {
				Frame allows = new Frame("allows");
				addAllowedInnerNodes(allows, tree);
				return allows;
			}

			private void addAllowedInnerNodes(Frame allows, NodeTree tree) {
				List<Frame> multipleNodes = new ArrayList<>();
				List<Frame> singleNodes = new ArrayList<>();
				for (Node node : collectCandidates(tree)) {
					if (node.is(Annotation.REQUIRED)) continue;
					if (node.isSingle()) singleNodes.add(createAllowedSingle(node));
					else multipleNodes.add(createAllowedMultiple(node));
				}
				addAllowedNodes(allows, multipleNodes, singleNodes);
			}

			private void addRequiredInnerNodes(Frame requires, NodeTree tree) {
				List<Frame> multipleNodes = new ArrayList<>();
				List<Frame> singleNodes = new ArrayList<>();
				for (Node node : collectCandidates(tree)) {
					if (!node.is(Annotation.REQUIRED)) continue;
					if (node.isSingle()) singleNodes.add(createRequiredSingle(node));
					else multipleNodes.add(createRequiredMultiple(node));
				}
				addRequiredNodes(requires, multipleNodes, singleNodes);
			}

			private Collection<Node> collectCandidates(NodeTree tree) {
				List<Node> nodes = new ArrayList<>();
				for (Node node : tree)
					if (node.isAbstract()) nodes.addAll(node.getDeepSubNodes());
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
				if (node.is(LinkNode.class))
					if (((LinkNode) node).isReference())
						return ((LinkNode) node).getDestinyQN();
					else return node.getContainer().getQualifiedName() + "." + node.getName();
				else return node.getQualifiedName();
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
