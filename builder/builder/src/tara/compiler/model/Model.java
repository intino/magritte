package tara.compiler.model;

import tara.Language;
import tara.compiler.shared.Configuration.Level;
import tara.lang.model.Node;
import tara.lang.model.NodeRoot;
import tara.lang.model.Rule;

import java.io.File;
import java.util.*;

public class Model implements NodeRoot {

	private String file;
	private Language language;
	private Level level;
	private Map<Node, List<Rule>> components = new LinkedHashMap<>();
	private List<String> uses;
	private Map<String, Class<?>> rules;
	private File resourcesRoot;

	public Model(String file, Language language) {
		this.file = file;
		this.language = language;
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
	public List<String> uses() {
		return uses;
	}

	public Level level() {
		return level;
	}

	public void setLevel(Level level) {
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
	public String doc() {
		return "";
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
	public void add(Node node, List<Rule> rule) {
		this.components.put(node, new ArrayList<>(rule));
	}

	@Override
	public List<Rule> rulesOf(Node component) {
		return this.components.get(component);
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

	public void setResourcesRoot(File resourceRoot) {
		this.resourcesRoot = resourceRoot;
	}

	public File resourcesRoot() {
		return resourcesRoot;
	}
}
