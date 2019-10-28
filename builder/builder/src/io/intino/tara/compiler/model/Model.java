package io.intino.tara.compiler.model;

import io.intino.tara.Language;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeRoot;
import io.intino.tara.lang.model.Rule;

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
	private List<Node> facets;

	public Model(String file) {
		this.file = file;
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
		return components.containsKey(nodeContainer);
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

	public Language language() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String languageName() {
		return language != null ? language.languageName() : "";
	}

	public List<Node> allFacets() {
		if (this.facets == null) collectFacet();
		return facets;
	}

	private void collectFacet() {
		this.facets = new ArrayList<>();
		for (Node node : components.keySet()) {
			if (node.isAspect()) facets.add(node);
			facets(node);
		}
	}

	private List<Node> facets(Node node) {
		List<Node> list = new ArrayList<>();
		node.components().forEach(n -> {
			if (n.isReference()) return;
			if (n.isAspect()) list.add(n);
			list.addAll(facets(n));
		});
		return list;
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}

	public void setRules(Map<String, Class<?>> rules) {
		this.rules = rules;
	}

	public Map<String, Class<?>> rules() {
		return rules;
	}

	public void setResourcesRoot(File resourceRoot) {
		this.resourcesRoot = resourceRoot;
	}

	public File resourcesRoot() {
		return resourcesRoot;
	}

	@Override
	public boolean isMetaAspect() {
		return false;
	}

	@Override
	public void stashNodeName(String name) {

	}
}
