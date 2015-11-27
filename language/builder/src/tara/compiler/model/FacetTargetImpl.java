package tara.compiler.model;

import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class FacetTargetImpl implements FacetTarget {

	private String file;
	private int line;
	private String doc;
	private String destiny;
	private List<String> constraints = new ArrayList<>();
	private Node targetNode;
	private NodeContainer container;
	private Map<Node, CompositionRule> components = new LinkedHashMap<>();
	private List<Variable> variables = new ArrayList<>();
	private List<Parameter> parameters = new ArrayList<>();
	private List<String> uses;
	private String language;
	private List<Node> constraintNodes = new ArrayList<>();

	@Override
	public String target() {
		return destiny;
	}

	@Override
	public void target(String destiny) {
		this.destiny = destiny;
	}

	@Override
	public List<String> constraints() {
		return constraints;
	}

	@Override
	public List<Node> constraintNodes() {
		return constraintNodes;
	}

	@Override
	public void constraints(List<String> constraints) {
		this.constraints = constraints;
	}

	@Override
	public void constraintNodes(List<Node> constraints) {
		constraintNodes = constraints;
	}

	@Override
	public Node targetNode() {
		return targetNode;
	}

	@Override
	public void targetNode(Node destiny) {
		this.targetNode = destiny;
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(new ArrayList<>(components.keySet()));
	}

	@Override
	public String type() {
		return targetNode.qualifiedName();
	}

	@Override
	public void add(Node node, CompositionRule size) {
		components.put(node, size);
	}

	@Override
	public void add(int pos, Node node, CompositionRule size) {
		components.put(node, size);
	}

	@Override
	public Node component(String name) {
		return null;
	}

	@Override
	public CompositionRule ruleOf(Node component) {
		return components.get(component);
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return components.keySet().contains(nodeContainer);
	}

	@Override
	public void remove(Node node) {
		components.remove(node);
	}

	@Override
	public void moveToTheTop() {
	}

	@Override
	public List<Node> siblings() {
		List<Node> objects = new ArrayList<>();
		objects.addAll(container().components());
		return unmodifiableList(objects);
	}

	@Override
	public List<Variable> variables() {
		return unmodifiableList(variables);
	}

	@Override
	public void add(Variable... variables) {
		Collections.addAll(this.variables, variables);
	}

	@Override
	public void add(int pos, Variable... variables) {
		this.variables.addAll(pos, Arrays.asList(variables));
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
			((Node) container()).name() + "_" + shortType();
	}

	@Override
	public String qualifiedNameCleaned() {
		return (container().container() != null ? container.container().qualifiedName() : "") +
			((Node) container()).name() + "_" + type().replace(".", "#");
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
		return "on " + destiny;
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

	@Override
	public List<Parameter> parameters() {
		return unmodifiableList(parameters);
	}

	@Override
	public void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
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
}
