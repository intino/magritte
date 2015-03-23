package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ParameterImpl extends Element implements Parameter {

	private String name;
	private int position;
	private final List<Object> values = new ArrayList<>();
	private String file;
	private int line;
	private String extension;
	private Variable inferredVariable;


	public ParameterImpl(String name, int position, String extension, Object... values) {
		this.name = name;
		this.position = position;
		this.extension = extension;
		Collections.addAll(this.values, values);
	}

	public ParameterImpl(int position, String extension, Object... values) {
		this("", position, extension, values);
	}

	@Override
	public Variable inferredVariable() {
		return inferredVariable;
	}

	@Override
	public void setInferredVariable(Variable variable) {
		this.inferredVariable = variable;
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
	public Collection<Object> getValues() {
		return values;
	}

	@Override
	public String getExtension() {
		return extension;
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
