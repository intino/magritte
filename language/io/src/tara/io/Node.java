package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String name;
	public List<String> facets = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();

    @Override
    public String toString() {
		return "Node{" + name + '}';
	}
}
