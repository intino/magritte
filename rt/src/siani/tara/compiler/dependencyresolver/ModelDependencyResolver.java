package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static siani.tara.lang.NodeObject.AnnotationType;

public class ModelDependencyResolver {
	Model model;
	List<String> toProcessNodes = new ArrayList<>();


	public ModelDependencyResolver(Model model) throws DependencyException {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		model.sortNodeTable(new NodeComparator(model.getNodeTable()));
		toProcessNodes.addAll(model.getNodeTable().keySet());
		resolveHierarchyDependencies();
		resolveInnerConceptsAsReferences();
	}

	private void resolveInnerConceptsAsReferences() {
		for (DeclaredNode node : model.getNodeTable().values()) {

		}
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		List<DeclaredNode> toAddNodes = new ArrayList<>();
		while (!toProcessNodes.isEmpty()) {
			for (int i = 0; i < toProcessNodes.size(); i++) {
				String toProcessNode = toProcessNodes.get(i);
				DeclaredNode node = model.getNodeTable().get(toProcessNode);
				NodeObject object = node.getObject();
				if (object.getParentName() != null || node.isCase()) {
					DeclaredNode parent = model.searchAncestry(node);
					if (parent == null) throwError(node);
					if (toProcessNodes.contains(parent.getQualifiedName())) continue;
					linkToParent(object, parent);
					extractInfoFromParent(toAddNodes, node, parent);
				}
				toProcessNodes.remove(node.getQualifiedName());
				i--;
			}
		}
		for (DeclaredNode toAddNode : toAddNodes) model.add(toAddNode.getQualifiedName(), toAddNode);
	}

	private void addInnerConceptsInherited(DeclaredNode parent, DeclaredNode node, List<DeclaredNode> toAddNodes) {
		for (DeclaredNode child : parent.getInnerNodes())
			if (!child.isCase()) {
				DeclaredNode element = new DeclaredNode(child.getObject(), node);
				toAddNodes.add(element);
				node.add(0, element);
				element.calculateQualifiedName();
				for (DeclaredNode innerChild : child.getInnerNodes())
					addInnerConceptsInherited(innerChild, element, toAddNodes);
			}
	}

	private void addInheritedAnnotations(NodeObject parent, DeclaredNode node) {
		for (AnnotationType annotation : parent.getAnnotations()) {
			if (annotation.equals(AnnotationType.PRIVATE)) continue;
			node.getObject().add(annotation);
		}
	}

	private void calculateInheritedVariables(NodeObject parent, DeclaredNode node) {
		for (Variable variable : parent.getVariables())
			node.getObject().add(0, variable.clone());
	}

	private void extractInfoFromParent(List<DeclaredNode> toAddNodes, DeclaredNode node, DeclaredNode parent) {
		addInnerConceptsInherited(parent, node, toAddNodes);
		calculateInheritedVariables(parent.getObject(), node);
		addInheritedAnnotations(parent.getObject(), node);
	}

	private void linkToParent(NodeObject object, DeclaredNode parent) {
		object.setParentObject(parent.getObject());
		object.setParentName(parent.getQualifiedName());
		parent.getObject().addChild(object);
	}

	private void throwError(DeclaredNode node) throws DependencyException {
		throw new DependencyException("Dependency resolution fail in: " + node.getQualifiedName() +
			". Not found ancestry: " + node.getObject().getParentName(), node);
	}


	private class NodeComparator implements Comparator<String> {
		Map<String, DeclaredNode> base;
		private Comparator<String> levelComparator = new Comparator<String>() {
			public int compare(String o1, String o2) {
				String qn1 = o1.replaceAll("\\[.*\\]", "");
				String qn2 = o2.replaceAll("\\[.*\\]", "");
				int count1 = qn1.length() - qn1.replace(".", "").length();
				int count2 = qn2.length() - qn2.replace(".", "").length();
				return count1 - count2 == 0 ? nameComparator.compare(o1, o2) : count1 - count2;
			}
		};

		private Comparator<String> nameComparator = new Comparator<String>() {
			public int compare(String o1, String o2) {
				if (o1.equals(o2)) return 0;
				return o1.compareTo(o2);
			}
		};

		private Comparator<DeclaredNode> hierarchyComparator = new Comparator<DeclaredNode>() {
			public int compare(DeclaredNode o1, DeclaredNode o2) {
				if (o1 == null) return 1;
				if (o2 == null) return -1;
				boolean parentO1 = o1.getObject().getParentName() != null;
				boolean parentO2 = o2.getObject().getParentName() != null;
				if (parentO1 && parentO2) return 0;
				if (!parentO1 && !parentO2) return 0;
				if (parentO2) return -1;
				return 1;
			}
		};

		public NodeComparator(Map<String, DeclaredNode> base) {
			this.base = base;
		}

		@Override
		public int compare(String o1, String o2) {
			int compare = hierarchyComparator.compare(base.get(o1), base.get(o2));
			if (compare != 0) return compare;
			compare = levelComparator.compare(o1, o2);
			if (compare != 0) return compare;
			compare = nameComparator.compare(o1, o2);
			return compare;
		}
	}
}
