package tara.compiler.model;


import tara.lang.model.*;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class FacetImpl implements Facet {

	private String file;
	private List<String> uses;
	private int line;
	private List<Parameter> parameters = new ArrayList<>();
	private Node container;
	private String type;
	private String language;

	public FacetImpl(String type) {
		this.type = type;
	}

	@Override
	public Node container() {
		return container;
	}

	@Override
	public void container(Node container) {
		this.container = container;
	}

	public String qualifiedName() {
		return (container().container() != null ? container.container().qualifiedName() : "") +
			container().name() + shortType();
	}

	public String qualifiedNameCleaned() {
		return (container().container() != null && !container.container().qualifiedName().isEmpty() ? container.container().qualifiedName() + "." : "") +
			((Node) container()).name() + type().replace(".", "$");
	}

	private String shortType() {
		return type().contains(".") ? type().substring(type().lastIndexOf(".") + 1) : type();
	}

	@Override
	public String type() {
		return type;
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
