package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import org.siani.itrules.formatter.Inflector;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.compiler.codegeneration.FrameTags.*;
import static siani.tara.compiler.codegeneration.InflectorProvider.getInflector;
import static siani.tara.compiler.codegeneration.NameFormatter.camelCase;
import static siani.tara.compiler.codegeneration.NameFormatter.composeMorphPackagePath;

public class MorphFrameCreator extends FrameCreator {


	private Node initNode;
	Set<String> imports = new HashSet<>();

	public MorphFrameCreator(String project, Model model) {
		super(project, model);
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		final Frame frame = new Frame(MORPH);
		String packagePath = composeMorphPackagePath(model, node);
		if (!packagePath.isEmpty()) frame.addFrame(PACKAGE, packagePath);
		nodeToFrame(node, frame);
		addImports(frame);
		initNode = null;
		return new AbstractMap.SimpleEntry<>(packagePath + DOT + node.getName(), frame);
	}

	private void nodeToFrame(final Node node, Frame frame) {
		if (node.is(Annotation.CASE) || node.is(Annotation.PROPERTY)) return;
		if (node.is(LinkNode.class) && ((LinkNode) node).isReference())
			linkNodeToFrame((LinkNode) node, frame);
		else declaredNodeToFrame(node, frame);
	}

	private void linkNodeToFrame(LinkNode linkNode, Frame frame) {
		Frame newFrame = new Frame(getLinkNodeTypes(linkNode));
		newFrame.addFrame(PARENT, linkNode.getDestinyName()).
			addFrame(TYPE, linkNode.getDestiny().getObject().getType()).
			addFrame(QN, linkNode.getDestiny().getObject().getDeclaredNodeQN()).
			addFrame(NAME, linkNode.getDestiny().getObject().getName());
		frame.addFrame(NODE, newFrame);
		addImports(linkNode.getDestiny().getContainer());
	}

	private void declaredNodeToFrame(Node node, Frame frame) {
		List<String> types = getTypes(node);
		final Frame newFrame = new Frame(types.toArray(new String[types.size()]));
		frame.addFrame(NODE, newFrame);
		addAnnotations(node, newFrame);
		addNodeInfo(node, newFrame);
		addInner(node, newFrame);
		if (!node.isSub() && !node.equals(initNode))
			addSubs(node, frame);
		addImports(node.getContainer());
	}

	private void addImports(DeclaredNode container) {
		if (container != null) {
			String containerPackage = composeMorphPackagePath(model, container);
			if (!containerPackage.equals(MAGRITTE_MORPHS))
				imports.add(containerPackage + DOT + container.getName());
		}
	}

	private void addImports(Frame frame) {
		for (String anImport : imports)
			frame.addFrame(IMPORTS, IMPORT + anImport + SEMICOLON);
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (initNode != null && node != initNode) {
			newFrame.addFrame(INNER, "");
			if (node.is(Annotation.AGGREGATED)) newFrame.addFrame(RELATION, AGGREGATION);
			else newFrame.addFrame(RELATION, COMPOSITION);
		}
		NodeObject object = node.getObject();
		if (object.getDoc() != null) newFrame.addFrame(DOC, object.getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		newFrame.addFrame(QN, node.getQualifiedName()).addFrame(PROJECT, project);
		addParent(node, newFrame, object);
		addTargets(node, newFrame, object);
		addFacets(node, newFrame, object);
		addAddress(newFrame, object);
		addVariables(node, newFrame);
		addTargets(node, newFrame);
		addFacets(node, newFrame);
	}

	private void addParent(Node node, Frame newFrame, NodeObject object) {
		if (object.getParent() != null) {
			newFrame.addFrame(PARENT, object.getParent().getName().equals(node.getName()) ?
				MAGRITTE_MORPHS + DOT + object.getParent().getDeclaredNodeQN() :
				object.getParent().getDeclaredNodeQN());
		} else newFrame.addFrame(PARENT, MORPH);
	}

	private void addTargets(Node node, Frame newFrame, NodeObject object) {
		if (object.getType() != null) {
			Frame typeFrame = new Frame(NODE_TYPE).addFrame(NAME, object.getType());
			addFacetTargets(node, typeFrame);
			newFrame.addFrame(NODE_TYPE, typeFrame);
		}
	}

	private void addFacets(Node node, Frame newFrame, NodeObject object) {
		for (Facet facet : object.getFacets()) {
			Frame facetFrame = new Frame(FrameTags.FACET).addFrame(NAME, facet.getName()).addFrame(FrameTags.FACET, getFacetDestinies(node));
			newFrame.addFrame(FrameTags.FACET, facetFrame);
		}
	}

	private void addAddress(Frame newFrame, NodeObject object) {
		if (object.getAddress() != null) newFrame.addFrame(ADDRESS, object.getAddress().replace(DOT, ""));
	}

	protected void addVariables(Node node, final Frame frame) {
		super.addVariables(node, frame);
		for (final Variable variable : node.getObject().getVariables())
			if (variable.hasValue() && variable instanceof Reference)
				addImports((DeclaredNode) model.getParentModel().searchNode(variable.getType()));
	}

	private String[] getFacetDestinies(Node node) {
		List<String> list = new ArrayList<>();
		for (Facet facet : node.getObject().getFacets()) list.add(buildFacetPath(node, facet.getName()));
		return list.toArray(new String[list.size()]);
	}

	private String buildFacetPath(Node node, String name) {
		Node aNode = node;
		String path = node.getName() + node.getType() + name + DOT + CLASS;
		while (aNode.getContainer() != null) {
			aNode = aNode.getContainer();
			path = addToPath(name, aNode, path);
		}
		return path;
	}

	private String addToPath(String name, Node aNode, String path) {
		for (Facet facet : aNode.getObject().getFacets())
			if (facet.getName().equals(name))
				path = aNode.getName() + aNode.getType() + name + DOT + path;
		return path;
	}

	private void addFacetTargets(Node node, Frame typeFrame) {
		if (node.getObject().getFacetTargets().isEmpty()) return;
		Frame targetFrame = new Frame(TARGET, node.is(Annotation.INTENTION) ? INTENTION : "");
		targetFrame.addFrame(TARGET, project.toLowerCase() + DOT + EXTENSIONS + DOT + camelCase(node.getName()) + node.getType() + DOT + CLASS);
		Inflector inflector = getInflector(model.getLanguage());
		for (FacetTarget target : node.getObject().getFacetTargets()) {
			targetFrame.addFrame(TARGET, project.toLowerCase() + DOT + EXTENSIONS + DOT + inflector.plural(node.getType()).toLowerCase() + DOT +
				inflector.plural(node.getName()).toLowerCase() + DOT + camelCase(target.getDestinyName()) + node.getType() + DOT + CLASS);
		}
		typeFrame.addFrame(TARGET, targetFrame);
	}

	private void addSubs(Node node, Frame frame) {
		Set<Node> subs = new HashSet<>();
		collectSubs(node.getSubNodes(), subs);
		for (Node sub : subs)
			nodeToFrame(sub, frame);
	}

	private void collectSubs(Node[] subs, Collection<Node> list) {
		for (Node sub : subs) {
			list.add(sub);
			Node[] innerSubs = sub.getSubNodes();
			if (innerSubs.length > 0) {
				Collections.addAll(list, innerSubs);
				collectSubs(innerSubs, list);
			}
		}
	}

	private void addInner(Node node, Frame newFrame) {
		for (Node inner : filterMorphCandidates(node))
			nodeToFrame(inner, newFrame);
	}

	private NodeTree filterMorphCandidates(Node node) {
		NodeTree tree = new NodeTree();
		for (Node inner : node.getInnerNodes())
			if ((inner.is(LinkNode.class) && !isInherited((LinkNode) inner, node)) || (inner.is(DeclaredNode.class) && !inner.isSub()) && !isIncluded(tree, inner))
				tree.add(inner);
		return tree;
	}

	private boolean isIncluded(NodeTree tree, Node inner) {
		for (Node node : tree)
			if (node.getType().equals(inner.getType()) && node.getName().equals(inner.getName())) return true;
		return false;
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getObject().getFacets())
			newFrame.addFrame(FACETS, new Frame(getTypes(facet)).addFrame(NAME, facet.getName()));
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getObject().getFacetTargets())
			newFrame.addFrame(TARGETS, new Frame(getTypes(target)).addFrame(NAME, target.getDestinyName()));
	}

	private String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add(FACET);
		list.add(facet.getName());
		return list.toArray(new String[list.size()]);
	}

	private String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add(FACET_TARGET);
		if (facetTarget.getDestinyQN() != null)
			list.add(facetTarget.getDestinyQN());
		if (facetTarget.isAlways())
			list.add(ALWAYS);
		return list.toArray(new String[list.size()]);
	}


	public boolean isInherited(LinkNode inner, Node node) {
		NodeObject destiny = inner.getDestiny().getObject();
		NodeObject parent = node.getObject();
		while ((parent = parent.getParent()) != null)
			if (((DeclaredNode) model.get(parent.getDeclaredNodeQN())).contains(destiny.getName()))
				return true;
		return false;
	}
}
