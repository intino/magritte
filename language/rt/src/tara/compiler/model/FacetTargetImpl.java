package tara.compiler.model;

import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class FacetTargetImpl implements FacetTarget {

	private String file;
	private int line;
	private String doc;
	private String destiny;
	private List<String> constraint;
	private Node targetNode;
	private NodeContainer container;
	private List<Node> includes = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();

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
		return constraint;
	}

	public void constraints(List<String> constraints) {
		this.constraint = constraints;
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
		return unmodifiableList(includes);
	}

	@Override
	public String type() {
		return targetNode.qualifiedName();
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
	public Node components(String name) {
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return includes.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return includes.remove(node);
	}

	@Override
	public void moveToTheTop() {

	}

	@Override
	public List<Node> siblings() {
		ArrayList<Node> objects = new ArrayList<>();
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
	public void container(NodeContainer container) {
		this.container = container;
	}

	@Override
	public String qualifiedName() {
		return "";
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
}
