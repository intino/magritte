package io.intino.tara.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
	public String name;
	public List<String> facets = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();

    @Override
    public String toString() {
		return "Node{" + name + '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(isUUID(name) ? "" : name, facets, variables, nodes);
	}

	private static boolean isUUID(String str) {
		return str.charAt(8) == '-' && str.charAt(13) == '-' && str.charAt(18) == '-' && str.charAt(23) == '-';
	}
}
