package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import org.siani.itrules.formatter.Inflector;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.compiler.codegeneration.InflectorProvider.getInflector;
import static siani.tara.compiler.codegeneration.NameFormatter.camelCase;
import static siani.tara.compiler.codegeneration.NameFormatter.composeMorphPackagePath;

public class MorphFrameCreator extends FrameCreator {

	private static final String SEPARATOR = ".";
	private Node initNode;
	private String packagePath;
	Set<String> imports = new HashSet<>();

	public MorphFrameCreator(String project, Model model) {
		super(project, model);
	}

	public Map.Entry<String, Frame> create(Node node) {
		this.initNode = node;
		final Frame frame = new Frame("Morph");
		packagePath = composeMorphPackagePath(model, node);
		if (!packagePath.isEmpty()) frame.addFrame("package", packagePath);
		nodeToFrame(node, frame);
		addImports(frame);
		initNode = null;
		return new AbstractMap.SimpleEntry<>(packagePath + SEPARATOR + node.getName(), frame);
	}

	private void nodeToFrame(final Node node, Frame frame) {
		if (node.is(Annotation.CASE)) return;
		if (node.is(LinkNode.class) && ((LinkNode) node).isReference()) {
			LinkNode linkNode = (LinkNode) node;
			Frame newFrame = new Frame(getLinkNodeTypes(linkNode));
			newFrame.addFrame("parent", linkNode.getDestinyName());
			newFrame.addFrame("type", linkNode.getDestiny().getObject().getType());
			newFrame.addFrame("qn", linkNode.getDestiny().getObject().getDeclaredNodeQN());
			newFrame.addFrame("name", linkNode.getDestiny().getObject().getName());
			frame.addFrame("node", newFrame);
			DeclaredNode container = linkNode.getDestiny().getContainer();
			if (container != null) {
				String containerPackage = composeMorphPackagePath(model, container);
				if (!containerPackage.equals("magritte.morphs"))
					imports.add(containerPackage + "." + container.getName());
			}
		} else {
			List<String> types = getTypes(node);
			final Frame newFrame = new Frame(types.toArray(new String[types.size()]));
			frame.addFrame("node", newFrame);
			addAnnotations(node, newFrame);
			addNodeInfo(node, newFrame);
			addInner(node, newFrame);
			if (!node.isSub() && !node.equals(initNode))
				addSubs(node, frame);
			if (node.getContainer() != null) {
				String containerPackage = composeMorphPackagePath(model, node.getContainer());
				if (!containerPackage.equals("magritte.morphs"))
					imports.add(containerPackage + "." + node.getContainer().getName());
			}
		}

	}

	private void addImports(Frame frame) {
		for (String anImport : imports)
			frame.addFrame("imports", "import " + anImport + ";");
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (initNode != null && node != initNode) {
			newFrame.addFrame("inner", "");
			if (node.is(Annotation.AGGREGATED)) newFrame.addFrame("relation", "add");
			else newFrame.addFrame("relation", "has");
		}
		NodeObject object = node.getObject();
		if (object.getDoc() != null)
			newFrame.addFrame("doc", object.getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addFrame("name", node.isAnonymous() ? node.getType() : node.getName());
		newFrame.addFrame("qn", node.getQualifiedName());
		newFrame.addFrame("project", project);
		if (node.getObject().getParent() != null)
			newFrame.addFrame("parent", node.getObject().getParent().getDeclaredNodeQN());
		else newFrame.addFrame("parent", "Morph");
		if (node.getObject().getType() != null) {
			Frame typeFrame = new Frame("nodeType").addFrame("name", node.getObject().getType());
			addFacetTargets(node, typeFrame);
			newFrame.addFrame("nodeType", typeFrame);
		}
		for (Facet facet : node.getObject().getFacets()) {
			Frame facetFrame = new Frame("facet").addFrame("name", facet.getName()).addFrame("facet", getFacetDestinies(node));
			newFrame.addFrame("facet", facetFrame);
		}
		if (object.getAddress() != null) newFrame.addFrame("address", object.getAddress().replace(SEPARATOR, ""));
		addVariables(node, newFrame);
		addTargets(node, newFrame);
		addFacets(node, newFrame);
	}

	private String[] getFacetDestinies(Node node) {
		List<String> list = new ArrayList<>();
		for (Facet facet : node.getObject().getFacets()) list.add(buildFacetPath(node, facet.getName()));
		return list.toArray(new String[list.size()]);
	}

	private String buildFacetPath(Node node, String name) {
		Node aNode = node;
		String path = node.getName() + node.getType() + name + ".class";
		while (aNode.getContainer() != null) {
			aNode = aNode.getContainer();
			path = addToPath(name, aNode, path);
		}
		return path;
	}

	private String addToPath(String name, Node aNode, String path) {
		for (Facet facet : aNode.getObject().getFacets())
			if (facet.getName().equals(name))
				path = aNode.getName() + aNode.getType() + name + SEPARATOR + path;
		return path;
	}

	private void addFacetTargets(Node node, Frame typeFrame) {
		if (node.getObject().getFacetTargets().isEmpty()) return;
		Frame targetFrame = new Frame("target", node.is(Annotation.INTENTION) ? "intention" : "");
		targetFrame.addFrame("target", project.toLowerCase() + ".extensions." + camelCase(node.getName()) + node.getType() + ".class");
		Inflector inflector = getInflector(model.getLanguage());
		for (FacetTarget target : node.getObject().getFacetTargets()) {
			targetFrame.addFrame("target", project.toLowerCase() + ".extensions." + inflector.plural(node.getType()).toLowerCase() + SEPARATOR +
				inflector.plural(node.getName()).toLowerCase() + SEPARATOR + camelCase(target.getDestinyName()) + node.getType() + ".class");
		}
		typeFrame.addFrame("target", targetFrame);
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
			newFrame.addFrame("facets", new Frame(getTypes(facet)).addFrame("name", facet.getName()));
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getObject().getFacetTargets())
			newFrame.addFrame("targets", new Frame(getTypes(target)).addFrame("name", target.getDestinyName()));
	}

	private String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add("Facet");
		list.add(facet.getName());
		return list.toArray(new String[list.size()]);
	}

	private String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add("FacetTarget");
		if (facetTarget.getDestinyQN() != null)
			list.add(facetTarget.getDestinyQN());
		if (facetTarget.isAlways())
			list.add("always");
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