package monet.tara.compiler.dependencyresolver;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.DependencyException;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.TreeWrapper;
import monet.tara.lang.Variable;

import java.util.*;

public class AbstractTreeDependencyResolver {
	TreeWrapper tree;
	List<AbstractNode> nodes = new ArrayList<>();

	public AbstractTreeDependencyResolver(Collection<SourceUnit> sources) throws DependencyException {
		tree = mergeTrees(sources);
		for (Collection o : tree.getNodeNameLookUpTable().values()) nodes.addAll(o);
		resolveHierarchyDependencies();
	}

	public TreeWrapper getTree() {
		return tree;
	}


	public AbstractNode[] resolve() throws DependencyException {
		for (AbstractNode node : nodes) {
			if (node.getParentConcept() == null) continue;
			List<AbstractNode> innerConcepts = new ArrayList<>();
			List<Variable> vars = new ArrayList<>();
			Set<AbstractNode.AnnotationType> annotations = new HashSet<>();
			collectHierarchyData(node.getParentConcept(), innerConcepts, vars, annotations);
			for (AbstractNode innerConcept : innerConcepts)
				if (isImportable(node, innerConcept)) node.getInnerConcepts().add(0, innerConcept);
			node.getVariables().addAll(0,vars);
			for (AbstractNode.AnnotationType annotation : annotations) node.add(annotation);
		}
		return nodes.toArray(new AbstractNode[nodes.size()]);
	}

	private boolean isImportable(AbstractNode node, AbstractNode innerConcept) {
		return !innerConcept.isCase() && !node.equals(innerConcept) && innerConcept.isAbstract() && !node.isBase();
	}

	private void collectHierarchyData(AbstractNode parentConcept,
	                                  List<AbstractNode> innerConcepts,
	                                  List<Variable> vars,
	                                  Set<AbstractNode.AnnotationType> annotations) {
		innerConcepts.addAll(0, parentConcept.getInnerConcepts());
		vars.addAll(0, parentConcept.getVariables());
		Collections.addAll(annotations, parentConcept.getAnnotations());
		if (parentConcept.getParentConcept() != null)
			collectHierarchyData(parentConcept.getParentConcept(), innerConcepts, vars, annotations);
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		for (AbstractNode node : nodes)
			if (node.getParentName() != null || node.isCase()) {
				AbstractNode parent = tree.searchAncestry(node);
				if (parent == null)
					throw new DependencyException("Dependency resolution fail in: " + node + "doesn't find" + node.getParentName(), node);
				parent.addChild(node);
				node.setParentConcept(parent);
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
