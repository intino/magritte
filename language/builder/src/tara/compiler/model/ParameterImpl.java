package tara.compiler.model;

import tara.lang.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterImpl implements Parameter {

	private final List<Object> values = new ArrayList<>();
	private String name;
	private int position;
	private String file;
	private int line;
	private int column;
	private String metric = "";
	private Rule rule;
	private Primitive inferredType;
	private boolean multiple;
	private boolean hasReferenceValue = false;
	private List<String> flags = new ArrayList<>();
	private NodeContainer owner;
	private String uid;


	public ParameterImpl(String name, int position, String metric, List<Object> values) {
		this.name = name;
		this.position = position;
		this.metric = (metric == null ? "" : metric);
		addValues(values);
	}

	public ParameterImpl(int position, String metric, List<Object> values) {
		this("", position, metric, values);
	}

	private void addValues(List<Object> values) {
		this.values.clear();
		this.values.addAll(values);
	}

	@Override
	public NodeContainer container() {
		return owner;
	}

	public void owner(NodeContainer owner) {
		this.owner = owner;
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
	public boolean isMultiple() {
		return multiple;
	}

	@Override
	public void multiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public List<String> flags() {
		return Collections.unmodifiableList(flags);
	}

	@Override
	public void flags(List<String> flags) {
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

	private NodeRoot model() {
		NodeContainer container = owner;
		while (!(container instanceof NodeRoot))
			container = container.container();
		return (NodeRoot) container;
	}

	@Override
	public void values(List<Object> objects) {
		addValues(objects);
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
		this.values.clear();
		this.values.addAll(newValues);
	}

	@Override
	public String getUID() {
		if (uid == null) uid = Variable.NativeCounter.next() + "";
		return uid;
	}

}
