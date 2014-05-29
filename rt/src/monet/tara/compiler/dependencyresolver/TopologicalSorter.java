package monet.tara.compiler.dependencyresolver;


import monet.tara.lang.AbstractNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class TopologicalSorter {

	public boolean hasDependency(AbstractNode node) {
		return node.getParentConcept() != null;
	}

	public ArrayList<AbstractNode> sort(AbstractNode[] allNodes) {
		ArrayList<AbstractNode> sortedList = new ArrayList<>();
		HashSet<AbstractNode> independientNodes = new HashSet<>();

		for (AbstractNode node : allNodes)
			if (!hasDependency(node)) independientNodes.add(node);

		while (!independientNodes.isEmpty()) {
			AbstractNode n = independientNodes.iterator().next();
			independientNodes.remove(n);
			sortedList.add(n);
			for (Iterator<AbstractNode> it = n.getChildren().iterator(); it.hasNext(); ) {
				AbstractNode m = it.next();
				it.remove();
				m.setParentConcept(null);
				if (!hasDependency(m)) independientNodes.add(m);
			}
		}
		boolean cycle = false;
		for (AbstractNode n : allNodes)
			if (!hasDependency(n)) {
				cycle = true;
				break;
			}
		if (cycle) System.out.println("Cycle present, topological sort not possible");
		else System.out.println("Topological Sort: " + Arrays.toString(sortedList.toArray()));
		return sortedList;
	}


}
