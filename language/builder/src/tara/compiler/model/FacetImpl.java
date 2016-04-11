package tara.compiler.model;


import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class FacetImpl implements Facet {

	private String file;
	private List<String> uses;
	private int line;
	private List<Parameter> parameters = new ArrayList<>();
	private NodeContainer container;
	private Map<Node, CompositionRule> components = new LinkedHashMap<>();
	private String facet;
	private String doc;
	private String language;

	public FacetImpl(String facet) {
		this.facet = facet;
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(new ArrayList<>(components.keySet()));
	}

	@Override
	public void add(Node node, CompositionRule size) {
		this.components.put(node, size);
	}

	@Override
	public void add(int pos, Node node, CompositionRule size) {
		components.put(node, size);
	}

	@Override
	public CompositionRule ruleOf(Node component) {
		return components.get(component);
	}

	@Override
	public boolean contains(Node node) {
		return components.keySet().contains(node);
	}

	@Override
	public void remove(Node node) {
		components.remove(node);
	}

	@Override
	public List<Node> siblings() {
		List<Node> siblings = new ArrayList<>();
		siblings.addAll(container().components());
		return unmodifiableList(siblings);
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	@Override
	public void add(Variable... variables) {

	}

	@Override
	public void add(int pos, Variable... variables) {
	}

	@Override
	public NodeContainer container() {
		return container;
	}

	@Override
	public List<String> uses() {
		return uses;
	}

	@Override
	public void container(NodeContainer container) {
		this.container = container;
	}

	@Override
	public String qualifiedName() {
		return (container().container() != null ? container.container().qualifiedName() : "") +
			((Node) container()).name() + shortType();
	}

	@Override
	public String qualifiedNameCleaned() {
		return (container().container() != null ? container.container().qualifiedName() : "") +
			((Node) container()).name() + type().replace(".", "$");
	}

	private String shortType() {
		return type().contains(".") ? type().substring(type().lastIndexOf(".") + 1) : type();
	}

	@Override
	public String doc() {
		return doc;
	}

	@Override
	public void addDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public String type() {
		return facet;
	}

	@Override
	public List<Parameter> parameters() {
		return unmodifiableList(parameters);
	}

	@Override
	public void addParameter(String name, int position, String metric, int line, int column, List<Object> values) {
		ParameterImpl parameter = new ParameterImpl(name, position, metric, values);
		parameter.file(file);
		parameter.line(line);
		parameter.column(column);
		parameter.owner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, int line, int column, List<Object> values) {
		addParameter("", position, extension, line, column, values);
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
	public int line() {
		return line;
	}

	@Override
	public void line(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return type();
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}

	@Override
	public String language() {
		return language;
	}

	@Override
	public void language(String language) {
		this.language = language;
	}
}
