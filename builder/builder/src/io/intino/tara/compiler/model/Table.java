package io.intino.tara.compiler.model;

import java.util.List;

public class Table {

	private String name;
	private List<String> parameters;
	private List<List<Object>> data;
	private List<String> header;


	public Table(String name, List<String> parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	public String name() {
		return name;
	}

	public List<String> parameters() {
		return parameters;
	}

	public List<List<Object>> data() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	public List<String> header() {
		return header;
	}

	public void header(List<String> header) {
		this.header = header;
	}
}
