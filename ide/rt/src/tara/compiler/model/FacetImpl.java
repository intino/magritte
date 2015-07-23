package tara.compiler.model;


import tara.language.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class FacetImpl implements Facet {

	private String file;
	private List<String> uses;
	private int line;
	private List<Parameter> parameters = new ArrayList<>();
	private NodeContainer container;
	private List<Node> includes = new ArrayList<>();
	private String facet;
	private String doc;
	private String language;

	public FacetImpl(String facet) {
		this.facet = facet;
	}

	@Override
	public List<Node> components() {
		return unmodifiableList(includes);
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
		for (Node include : includes)
			if (name.equals(include.name()))
				return include;
		return null;
	}

	@Override
	public boolean contains(Node node) {
		return includes.contains(node);
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
		ArrayList<Node> siblings = new ArrayList<>();
		siblings.addAll(container().components());
		return unmodifiableList(siblings);
	}

	@Override
	public List<Variable> variables() {
		return Collections.EMPTY_LIST;
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
	public String type() {
		return facet;
	}

	@Override
	public List<Parameter> parameters() {
		return unmodifiableList(parameters);
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.file(file);
		parameter.line(line);
		parameter.owner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, Object... values) {
		addParameter("", position, extension, values);
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
