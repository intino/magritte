package tara.compiler.model;

import tara.language.model.Node;
import tara.language.model.NodeRoot;
import tara.language.model.Variable;

import java.util.*;

public class Model implements NodeRoot {

	private String name = "";
	private String file;
	private String language;
	private Map<String, List<String>> metrics = new HashMap<>();
	private int level;
	private List<Node> components = new ArrayList<>();
	private List<String> uses;

	public Model(String file) {
		this.file = file;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
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

	public Map<String, List<String>> getMetrics() {
		return metrics;
	}

	public void addMetrics(Map<String, List<String>> metrics) {
		this.metrics.putAll(metrics);
	}

	@Override
	public Node container() {
		return null;
	}

	@Override
	public List<String> uses() {
		return uses;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public boolean contains(Node nodeContainer) {
		return components.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return node != null && components.remove(node);
	}

	@Override
	public String qualifiedName() {
		return "";
	}

	@Override
	public String qualifiedNameCleaned() {
		return "";
	}

	@Override
	public String doc() {
		return "";
	}

	@Override
	public List<Node> siblings() {
		return Collections.emptyList();
	}

	@Override
	public List<Node> components() {
		return Collections.unmodifiableList(components);
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public void add(Node... nodes) {
		Collections.addAll(components, nodes);
	}

	@Override
	public void add(int pos, Node... nodes) {
		components.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node component(String name) {
		return null;
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	@Override
	public String language() {
		return language;
	}

	@Override
	public void language(String language) {
		this.language = language;
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}
}
