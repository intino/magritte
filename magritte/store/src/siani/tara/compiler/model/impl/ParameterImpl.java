package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.util.WordGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterImpl implements Parameter {

	private final List<Object> values = new ArrayList<>();
	private String name;
	private int position;
	private List<String> allowedValues = new ArrayList<>();
	private String file;
	private int line;
	private String metric;
	private String contract;
	private String inferredType;
	private boolean multiple;
	private boolean hasReferenceValue = false;
	private List<String> annotations = new ArrayList<>();
	private NodeContainer owner;
	private final String uid;


	public ParameterImpl(String name, int position, String metric, Object... values) {
		this.name = name;
		this.position = position;
		this.metric = metric;
		uid = WordGenerator.generate();
		addValues(values);
	}

	public ParameterImpl(int position, String metric, Object... values) {
		this("", position, metric, values);
	}

	private void addValues(Object[] values) {
		if (values[0].toString().startsWith(REFERENCE)) {
			hasReferenceValue = true;
			for (Object value : values) this.values.add(value.toString().replace(REFERENCE, ""));
		} else Collections.addAll(this.values, values);
	}

	@Override
	public NodeContainer getOwner() {
		return owner;
	}

	public void setOwner(NodeContainer owner) {
		this.owner = owner;
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
	public boolean isMultiple() {
		return multiple;
	}

	@Override
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public List<String> getAnnotations() {
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public void setAnnotations(List<String> annotations) {
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
	public List<Object> getValues() {
		return Collections.unmodifiableList(values);
	}

	@Override
	public String getContract() {
		return contract;
	}

	@Override
	public void setContract(String contract) {
		this.contract = contract;
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

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	@Override
	public boolean hasReferenceValue() {
		return hasReferenceValue;
	}

	@Override
	public String toString() {
		return name + ":" + position + ":" + values;
	}

	public List<String> getAllowedValues() {
		return allowedValues;
	}

	public void addAllowedValues(List<String> allowedValues) {
		this.allowedValues.addAll(allowedValues);
	}

	@Override
	public void substituteValues(List<?> newValues) {
		this.values.clear();
		this.values.addAll(newValues);
	}

	@Override
	public String getUID() {
		return uid;
	}

}
