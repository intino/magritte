package io.intino.magritte.builder.compiler.model;

import io.intino.magritte.lang.model.*;
import io.intino.magritte.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterImpl implements Parameter {

	private final List<Object> values = new ArrayList<>();
	private final List<Object> originalValues = new ArrayList<>();
	private String name;
	private int position;
	private String aspect = "";
	private String scope;
	private String file;
	private int line;
	private int column;
	private String metric;
	private VariableRule rule;
	private Primitive inferredType;
	private boolean multiple;
	private boolean hasReferenceValue = false;
	private List<Tag> flags = new ArrayList<>();
	private Node container;
	private String uid;


	public ParameterImpl(String name, int position, String metric, List<Object> values) {
		this.name = name;
		this.position = position;
		this.metric = (metric == null ? "" : metric);
		addOriginalValues(values);
		addValues(values);
	}

	public ParameterImpl(int position, String metric, List<Object> values) {
		this("", position, metric, values);
	}

	@Override
	public Node container() {
		return container;
	}

	public void owner(Node owner) {
		this.container = owner;
	}

	@Override
	public Primitive type() {
		return inferredType;
	}

	@Override
	public void type(Primitive type) {
		this.inferredType = type;
		hasReferenceValue = Primitive.REFERENCE.equals(inferredType);
	}

	@Override
	public String aspect() {
		return aspect;
	}

	@Override
	public void aspect(String aspect) {
		this.aspect = aspect;
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
	public List<Tag> flags() {
		return Collections.unmodifiableList(flags);
	}

	@Override
	public void flags(List<Tag> flags) {
		this.flags = flags;
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
		return Collections.unmodifiableList(makeUp(model().resourcesRoot(), inferredType, values));
	}

	public List<Object> originalValues() {
		return originalValues;
	}

	private NodeRoot model() {
		Node container = this.container;
		while (!(container instanceof NodeRoot))
			container = container.container();
		return (NodeRoot) container;
	}

	@Override
	public void values(List<Object> objects) {
		addValues(objects);
	}

	@Override
	public VariableRule rule() {
		return rule;
	}

	@Override
	public void rule(VariableRule rule) {
		this.rule = rule;
	}

	@Override
	public void scope(String scope) {
		this.scope = scope;
	}

	@Override
	public String scope() {
		return this.scope;
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
		this.metric = (metric == null ? "" : metric);
	}

	@Override
	public boolean isVariableInit() {
		return false;
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
	public void substituteValues(List<?> newValues) {
		addValues((List<Object>) newValues);
	}

	@Override
	public String getUID() {
		if (uid == null) uid = Variable.NativeCounter.next(this.container(), name()) + "";
		return uid;
	}

	private void addValues(List<Object> values) {
		this.values.clear();
		this.values.addAll(values);
	}

	private void addOriginalValues(List<Object> values) {
		this.originalValues.clear();
		this.originalValues.addAll(values);
	}

}
