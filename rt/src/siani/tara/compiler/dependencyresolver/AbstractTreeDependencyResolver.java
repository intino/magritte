package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AbstractTreeDependencyResolver {
	TreeWrapper tree;
	NodeTree nodes = new NodeTree();
	NodeTree clones = new NodeTree();
	TreeWrapper newTree;

	public AbstractTreeDependencyResolver(Collection<SourceUnit> sources) throws DependencyException {
		tree = mergeTrees(sources);
		newTree = new TreeWrapper();
		for (Collection o : tree.getNodeNameLookUpTable().values()) nodes.addAll(o);
		resolveHierarchyDependencies();
	}

	public TreeWrapper getTree() {
		return tree;
	}

	public NodeObject[] resolve() throws DependencyException {
//		for (NodeObject node : nodes) {
//			List<NodeObject> innerConcepts = new ArrayList<>();
//			List<Variable> vars = new ArrayList<>();
//			Set<NodeObject.AnnotationType> annotations = new HashSet<>();
//			collectHierarchyData(node.getParent(), innerConcepts, vars, annotations);
//			for (NodeObject innerConcept : innerConcepts)
//				if (isImportable(node, innerConcept)) {
//					innerConcept.setContainer(node);
//					innerConcept.updateQulifiedName();
//					node.getInnerConcepts().add(0, innerConcept);
//				}
//			node.getVariables().addAll(0, vars);
//			for (NodeObject.AnnotationType annotation : annotations) node.add(annotation);
//		}
		return nodes.toArray(new NodeObject[nodes.size()]);
	}

	private boolean isImportable(NodeObject node, NodeObject innerConcept) {
		return !innerConcept.isCase() && !node.equals(innerConcept) && innerConcept.isAbstract() && !node.isBase();
	}

	private void collectHierarchyData(NodeObject parentConcept,
	                                  List<NodeObject> innerConcepts,
	                                  List<Variable> vars,
	                                  Set<NodeObject.AnnotationType> annotations) {
//		for (NodeObject innerConcept : innerConcepts)
//			innerConcepts.add(0, innerConcept.getClone());
//		vars.addAll(0, parentConcept.getVariables());
//		Collections.addAll(annotations, parentConcept.getAnnotations());
//		if (parentConcept.getParent() != null)
//			collectHierarchyData(parentConcept.getParent(), innerConcepts, vars, annotations);
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		for (Node node : nodes)
			if (node.getObject().getParentName() != null || node.isCase()) {
				Node parent = tree.searchAncestry(node);
				if (parent == null)
					throw new DependencyException("Dependency resolution fail in: " + node + "doesn't find" + node.getObject().getParentName(), node);
				parent.getObject().addChild(node.getObject());
				node.getObject().setParentConcept(parent.getObject());
			}
	}


	private TreeWrapper mergeTrees(Collection<SourceUnit> units) {
		TreeWrapper newAst = new TreeWrapper();
		for (SourceUnit unit : units) {
			newAst.addAll(unit.getAbstractTree().getTree());
			newAst.putAllIdentifiers(unit.getAbstractTree().getIdentifiers());
			newAst.putAllInNodeNameTable(unit.getAbstractTree().getNodeNameLookUpTable());
		}
		return newAst;
	}
}
