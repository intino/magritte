package monet.tara.compiler.dependencyresolver;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.DependencyException;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

import java.util.*;

public class ASTDependencyResolver {
	ASTWrapper ast;
	List<ASTNode> nodes = new ArrayList<>();

	public ASTDependencyResolver(Collection<SourceUnit> sources) throws DependencyException {
		ast = mergeAST(sources);
		for (Collection o : ast.getNodeNameLookUpTable().values()) nodes.addAll(o);
		resolveHierarchyDependencies();
	}

	public ASTWrapper getAst() {
		return ast;
	}


	public ASTNode[] resolve() throws DependencyException {
		for (ASTNode node : nodes) {
			if (node.getParentConcept() == null) continue;
			List<ASTNode> innerConcepts = new ArrayList<>();
			List<ASTNode.Variable> vars = new ArrayList<>();
			Set<ASTNode.AnnotationType> annotations = new HashSet<>();
			collectHierarchyData(node.getParentConcept(), innerConcepts, vars, annotations);
			for (ASTNode innerConcept : innerConcepts)
				if (isImportable(node, innerConcept)) node.getInnerConcepts().add(innerConcept);
			node.getVariables().addAll(vars);
			for (ASTNode.AnnotationType annotation : annotations) node.add(annotation);
		}
		return nodes.toArray(new ASTNode[nodes.size()]);
	}

	private boolean isImportable(ASTNode node, ASTNode innerConcept) {
		return !innerConcept.isCase() && !node.equals(innerConcept) && innerConcept.isAbstract() && !node.isBase();
	}

	private void collectHierarchyData(ASTNode parentConcept,
	                                  List<ASTNode> innerConcepts,
	                                  List<ASTNode.Variable> vars,
	                                  Set<ASTNode.AnnotationType> annotations) {
		innerConcepts.addAll(parentConcept.getInnerConcepts());
		vars.addAll(parentConcept.getVariables());
		Collections.addAll(annotations, parentConcept.getAnnotations());
		if (parentConcept.getParentConcept() != null)
			collectHierarchyData(parentConcept.getParentConcept(), innerConcepts, vars, annotations);
	}

	public void resolveHierarchyDependencies() throws DependencyException {
		for (ASTNode node : nodes)
			if (node.getParentName() != null) {
				ASTNode parent = ast.searchAncestry(node);
				if (parent == null)
					throw new DependencyException("Dependency resolution fail in: " + node + "doesn't find" + node.getParentName(), node);
				parent.addChild(node);
				node.setParentConcept(parent);
			}
	}


	private ASTWrapper mergeAST(Collection<SourceUnit> units) {
		ASTWrapper newAst = new ASTWrapper();
		for (SourceUnit unit : units) {
			newAst.addAll(unit.getAST().getAST());
			newAst.putAllIdentifiers(unit.getAST().getIdentifiers());
			newAst.putAllInNodeNameTable(unit.getAST().getNodeNameLookUpTable());
		}
		return newAst;
	}
}
