package tara.compiler.model;

import tara.language.model.Node;
import tara.language.model.NodeRoot;
import tara.language.model.Variable;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class Model implements NodeRoot {

	private String name = "";
	private String file;
	private String language;
	private Map<String, List<SimpleEntry<String, String>>> metrics = new HashMap<>();
	private int level;
	private List<Node> includes = new ArrayList<>();

	public Model(String file) {
		this.file = file;
	}


	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	@Override
	public String file() {
		return file;
	}

	@Override
	public void file(String file) {
		this.file = file;
	}

	public Map<String, List<SimpleEntry<String, String>>> getMetrics() {
		return metrics;
	}

	public void addMetrics(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics.putAll(metrics);
	}

	@Override
	public Node container() {
		return null;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public boolean contains(Node nodeContainer) {
		return includes.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return node != null && includes.remove(node);
	}

	@Override
	public String qualifiedName() {
		return "";
	}

	@Override
	public String doc() {
		return "";
	}

	@Override
	public List<Node> siblings() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<Node> components() {
		return Collections.unmodifiableList(includes);
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public void add(Node... nodes) {
		Collections.addAll(includes, nodes);
	}

	@Override
	public void add(int pos, Node... nodes) {
		includes.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node component(String name) {
		return null;
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	public String language() {
		return language;
	}

	public void language(String language) {
		this.language = language;
	}
}
