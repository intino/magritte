package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Parameter;

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
	private String inferredType;
	private String[] annotations = new String[0];


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
	public String getInferredType() {
		return inferredType;
	}

	@Override
	public void setInferredType(String type) {
		this.inferredType = type;
	}

	@Override
	public String[] getAnnotations() {
		return annotations;
	}

	@Override
	public void setAnnotations(String[] annotations) {
		this.annotations = annotations;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
