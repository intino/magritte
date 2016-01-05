package tara.compiler.model;

import tara.Language;
import tara.lang.model.Node;
import tara.lang.model.NodeRoot;
import tara.lang.model.Variable;
import tara.lang.model.rules.CompositionRule;

import java.util.*;

public class Model implements NodeRoot {

	private String name = "";
	private String file;
	private Language language;
	private int level;
	private Map<Node, CompositionRule> components = new LinkedHashMap<>();
	private List<String> uses;
	private Map<String, Class<?>> rules;

	public Model(String file, Language language) {
		this.file = file;
		this.language = language;
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
		return components.keySet().contains(nodeContainer);
	}

	@Override
	public void remove(Node node) {
		if (node != null) components.remove(node);
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
		return Collections.unmodifiableList(new ArrayList<>(components.keySet()));
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public void add(Node node, CompositionRule size) {
		this.components.put(node, size);
	}

	@Override
	public void add(int pos, Node node, CompositionRule size) {
		this.components.put(node, size);
	}

	@Override
	public CompositionRule ruleOf(Node component) {
		return this.components.get(component);
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	public Language getLanguage() {
		return language;
	}

	@Override
	public String language() {
		return language.languageName();
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}

	public void setRules(Map<String, Class<?>> rules) {
		this.rules = rules;
	}

	public Map<String, Class<?>> getRules() {
		return rules;
	}
}
