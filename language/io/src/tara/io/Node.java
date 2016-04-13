package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String name;
	public List<Facet> facets = new ArrayList<>();

    @Override
    public String toString() {
		return "Node{" + name + '}';
	}
}
