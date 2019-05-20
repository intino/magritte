package io.intino.tara.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Serializable {
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
		String name = nameOf(this.name);
		return Objects.hash(isUUID(name) ? "" : name, facets, variables, nodes);
	}

	public static String nameOf(String id) {
		String shortName = id.contains(".") ? id.substring(id.lastIndexOf(".") + 1) : id;
		shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
		shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
		return shortName;
	}

	private static boolean isUUID(String str) {
		return str.length() == 36 && str.charAt(8) == '-' && str.charAt(13) == '-' && str.charAt(18) == '-' && str.charAt(23) == '-';
	}
}
