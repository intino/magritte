package monet.tara.compiler.dependencyresolver;


import monet.tara.lang.NodeObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class TopologicalSorter {

	public boolean hasDependency(NodeObject node) {
		return node.getParent() != null;
	}

	public ArrayList<NodeObject> sort(NodeObject[] allNodes) {
		ArrayList<NodeObject> sortedList = new ArrayList<>();
		HashSet<NodeObject> independientNodes = new HashSet<>();

		for (NodeObject node : allNodes)
			if (!hasDependency(node)) independientNodes.add(node);

		while (!independientNodes.isEmpty()) {
			NodeObject n = independientNodes.iterator().next();
			independientNodes.remove(n);
			sortedList.add(n);
			for (Iterator<NodeObject> it = n.getChildren().iterator(); it.hasNext(); ) {
				NodeObject m = it.next();
				it.remove();
				m.setParentConcept(null);
				if (!hasDependency(m)) independientNodes.add(m);
			}
		}
		boolean cycle = false;
		for (NodeObject n : allNodes)
			if (!hasDependency(n)) {
				cycle = true;
				break;
			}
		if (cycle) System.out.println("Cycle present, topological sort not possible");
		else System.out.println("Topological Sort: " + Arrays.toString(sortedList.toArray()));
		return sortedList;
	}


}
