package io.intino.magritte.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
	public String name;
	public List<String> layers = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();

	@Override
	public String toString() {
		return "Node{" + name + '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, layers, variables, nodes);
	}
}
