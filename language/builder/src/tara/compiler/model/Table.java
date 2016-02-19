package tara.compiler.model;

import java.util.List;

public class Table {

	private String name;
	private List<String> header;
	private List<List<Object>> data;


	public Table(String name, List<String> header) {
		this.name = name;
		this.header = header;
	}

	public String name() {
		return name;
	}

	public List<String> header() {
		return header;
	}

	public List<List<Object>> data() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}
}
