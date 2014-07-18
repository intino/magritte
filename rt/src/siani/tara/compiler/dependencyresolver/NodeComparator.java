package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.LinkNode;
import siani.tara.lang.Node;

import java.util.Comparator;
import java.util.Map;

class NodeComparator implements Comparator<String> {
	Map<String, Node> base;
	private Comparator<String> levelComparator = new Comparator<String>() {
		public int compare(String o1, String o2) {
			String qn1 = o1.replaceAll("\\[.*\\]", "");
			String qn2 = o2.replaceAll("\\[.*\\]", "");
			int count1 = qn1.length() - qn1.replace(".", "").length();
			int count2 = qn2.length() - qn2.replace(".", "").length();
			return count1 - count2;
		}
	};
	private Comparator<String> nameComparator = new Comparator<String>() {
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
	private Comparator<Node> hierarchyComparator = new Comparator<Node>() {
		public int compare(Node o1, Node o2) {
			if (o1 == null) return 0;
			if (o2 == null) return 0;
			if (o1 instanceof LinkNode | o2 instanceof LinkNode)
				return 0;
			boolean parentO1 = o1.getObject().getParentName() != null;
			boolean parentO2 = o2.getObject().getParentName() != null;
			if (parentO1 && parentO2) return 0;
			if (!parentO1 && !parentO2) return 0;
			if (parentO2) return -1;
			return 1;
		}
	};

	public NodeComparator(Map<String, Node> base) {
		this.base = base;
	}

	@Override
	public int compare(String o1, String o2) {
		int compare;
		compare = nameComparator.compare(o1, o2);
		if (compare == 0) return compare;
//		compare = hierarchyComparator.compare(base.get(o1), base.get(o2));
//		if (compare != 0) return compare;
		compare = levelComparator.compare(o1, o2);
		if (compare != 0) return compare;
		compare = nameComparator.compare(o1, o2);
		return compare;
	}
}
