package tara.compiler.model;

import tara.lang.model.*;

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
	private int column;
	private String metric;
	private Rule rule;
	private Primitive inferredType;
	private boolean multiple;
	private boolean hasReferenceValue = false;
	private List<String> annotations = new ArrayList<>();
	private NodeContainer owner;
	private String uid;


	public ParameterImpl(String name, int position, String metric, Object... values) {
		this.name = name;
		this.position = position;
		this.metric = metric;
		addValues(values);
	}

	public ParameterImpl(int position, String metric, Object... values) {
		this("", position, metric, values);
	}

	private void addValues(Object[] values) {
		if (values[0].toString().startsWith(REFERENCE_PREFIX)) {
			hasReferenceValue = true;
			for (Object value : values) this.values.add(value.toString().replace(REFERENCE_PREFIX, ""));
		} else Collections.addAll(this.values, values);
	}

	@Override
	public NodeContainer container() {
		return owner;
	}

	public void owner(NodeContainer owner) {
		this.owner = owner;
	}

	@Override
	public Primitive inferredType() {
		return inferredType;
	}

	@Override
	public void inferredType(Primitive type) {
		this.inferredType = type;
		hasReferenceValue = Primitive.REFERENCE.equals(inferredType);
	}

	@Override
	public boolean isMultiple() {
		return multiple;
	}

	@Override
	public void multiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public List<String> flags() {
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public void flags(List<String> annotations) {
		this.annotations = annotations;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void name(String name) {
		this.name = name;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public List<Object> values() {
		return Collections.unmodifiableList(values);
	}

	@Override
	public Rule rule() {
		return rule;
	}

	@Override
	public void rule(Rule rule) {
		this.rule = rule;
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

	public int column() {
		return column;
	}

	public void column(int column) {
		this.column = column;
	}

	@Override
	public String metric() {
		return metric;
	}

	@Override
	public void metric(String metric) {
		this.metric = metric;
	}

	@Override
	public boolean isVariableInit() {
		return false;
	}

	@Override
	public void addAllowedParameters(List<String> values) {
	}

	@Override
	public boolean hasReferenceValue() {
		return hasReferenceValue;
	}

	@Override
	public String toString() {
		return name + ":" + position + ":" + values;
	}


	@Override
	public List<String> getAllowedValues() {
		return allowedValues;
	}

	@Override
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
		if (uid == null) uid = Variable.NativeCounter.next() + "";
		return uid;
	}

}
