package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Parameter;

public class ParameterImpl implements Parameter {

	private String name;
	private int position;
	private final Object[] values;


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
}
