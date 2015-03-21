package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Parameter;

public class ParameterImpl extends Element implements Parameter {

	private String name;
	private int position;
	private final Object[] values;
	private String file;
	private int line;


	public ParameterImpl(String name, int position, Object... values) {
		this.name = name;
		this.position = position;
		this.values = values;
	}

	public ParameterImpl(int position, Object... values) {
		this("", position, values);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public Object[] getValues() {
		return values;
	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}
}
