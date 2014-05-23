package monet.tara.compiler.dependencyresolver;


import monet.tara.lang.ASTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class TopologicalSorter {

	public boolean hasDependency(ASTNode node) {
		return node.getParentConcept() != null;
	}

	public ArrayList<ASTNode> sort(ASTNode[] allNodes) {
		ArrayList<ASTNode> sortedList = new ArrayList<>();
		HashSet<ASTNode> independientNodes = new HashSet<>();

		for (ASTNode node : allNodes)
			if (!hasDependency(node)) independientNodes.add(node);

		while (!independientNodes.isEmpty()) {
			ASTNode n = independientNodes.iterator().next();
			independientNodes.remove(n);
			sortedList.add(n);
			for (Iterator<ASTNode> it = n.getChildren().iterator(); it.hasNext(); ) {
				ASTNode m = it.next();
				it.remove();
				m.setParentConcept(null);
				if (!hasDependency(m)) independientNodes.add(m);
			}
		}
		boolean cycle = false;
		for (ASTNode n : allNodes)
			if (!hasDependency(n)) {
				cycle = true;
				break;
			}
		if (cycle) System.out.println("Cycle present, topological sort not possible");
		else System.out.println("Topological Sort: " + Arrays.toString(sortedList.toArray()));
		return sortedList;
	}


}
