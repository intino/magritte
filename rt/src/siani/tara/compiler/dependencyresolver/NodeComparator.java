package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.Node;

import java.util.Comparator;

class NodeComparator implements Comparator<Node> {
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


	@Override
	public int compare(Node o1, Node o2) {
		int compare;
		compare = nameComparator.compare(o1.getQualifiedName(), o2.getQualifiedName());
		if (compare == 0) return compare;
		compare = levelComparator.compare(o1.getQualifiedName(), o2.getQualifiedName());
		if (compare != 0) return compare;
		compare = nameComparator.compare(o1.getQualifiedName(), o2.getQualifiedName());
		return compare;
	}
}
