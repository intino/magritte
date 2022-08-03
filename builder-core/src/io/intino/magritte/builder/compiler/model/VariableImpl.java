package io.intino.magritte.builder.compiler.model;

import io.intino.magritte.lang.model.*;
import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static io.intino.magritte.lang.model.Tag.*;

public class VariableImpl implements Variable {
	private static final Logger LOG = Logger.getGlobal();
	private Node container;
	private Primitive type;
	private String name;
	private List<Object> defaultValues = new ArrayList<>();
	private String file;
	private int line;
	private int column;
	private List<Tag> flags = new ArrayList<>();
	private String defaultExtension;
	private boolean inherited;
	private boolean overriden;
	private Size size = new Size(1, 1);
	private String uid;
	private VariableRule rule;
	private String scope;

	public VariableImpl(Node container, Primitive type, String name, String scope) {
		this.container = container;
		this.type = type;
		this.name = name;
		this.scope = scope;
	}

	@Override
	public Primitive type() {
		return type;
	}

	@Override
	public void type(Primitive type) {
		this.type = type;
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
	public Node container() {
		return container;
	}

	@Override
	public Node destinyOfReference() {
		return null;
	}

	@Override
	public void container(Node container) {
		this.container = container;
	}

	@Override
	public List<Tag> flags() {
		return flags;
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public void addFlags(Tag... flags) {
		Collections.addAll(this.flags, flags);
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(Terminal);
	}

	@Override
	public boolean isFinal() {
		return flags.contains(Final);
	}

	@Override
	public boolean isPrivate() {
		return flags.contains(Private);
	}

	@Override
	public boolean isInherited() {
		return inherited;
	}

	protected void setInherited(boolean inherited) {
		this.inherited = inherited;
	}

	@Override
	public boolean isMultiple() {
		return size().max() > 1;
	}

	@Override
	public Size size() {
		return this.size;
	}

	@Override
	public void size(Size size) {
		this.size = size;
	}

	@Override
	public VariableRule rule() {
		return this.rule;
	}

	@Override
	public void rule(VariableRule rule) {
		this.rule = rule;
	}

	public String scope() {
		return scope;
	}

	@Override
	public void values(List<Object> values) {
		this.defaultValues.clear();
		this.defaultValues.addAll(values);
	}

	@Override
	public List<Object> values() {
		return Collections.unmodifiableList(makeUp(model().resourcesRoot(), type(), defaultValues));
	}

	private NodeRoot model() {
		NodeContainer container = container();
		while (!(container instanceof NodeRoot))
			container = container.container();
		return (NodeRoot) container;
	}

	@Override
	public String defaultMetric() {
		return defaultExtension;
	}

	@Override
	public void defaultMetric(String defaultExtension) {
		this.defaultExtension = defaultExtension;
	}

	@Override
	public String getUID() {
		if (uid == null) uid = NativeCounter.next(this.container(), name()) + "";
		return uid;
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
	public Variable clone() throws CloneNotSupportedException {
		super.clone();
		VariableImpl clone = new VariableImpl(container, type, name, scope);
		clone.file(file);
		clone.line(line());
		clone.column(column());
		clone.size(size());
		clone.defaultMetric(defaultExtension);
		clone.rule(rule);
		flags.forEach(clone::addFlags);
		clone.values(defaultValues);
		clone.setInherited(true);
		return clone;
	}

	@Override
	public Variable cloneIt(Node container) {
		try {
			Variable clone = this.clone();
			clone.container(container);
			return clone;
		} catch (CloneNotSupportedException ignored) {
			LOG.severe("Error cloning variable: " + name());
			return null;
		}
	}

	@Override
	public String toString() {
		return type + ":" + name;
	}

	@Override
	public boolean isOverriden() {
		return overriden;
	}

	@Override
	public void overriden(boolean overriden) {
		this.overriden = overriden;
	}

}
