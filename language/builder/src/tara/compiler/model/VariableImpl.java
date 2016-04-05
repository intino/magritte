package tara.compiler.model;

import tara.lang.model.*;
import tara.lang.model.rules.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static tara.lang.model.Tag.*;

public class VariableImpl implements Variable {
	private static final Logger LOG = Logger.getLogger(VariableImpl.class.getName());
	private NodeContainer container;
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
	private String anchor;
	private Rule rule;

	public VariableImpl(NodeContainer container, Primitive type, String name) {
		this.container = container;
		this.type = type;
		this.name = name;
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
	public NodeContainer container() {
		return container;
	}

	@Override
	public Node destinyOfReference() {
		return null;
	}

	@Override
	public void container(NodeContainer container) {
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
	public boolean isTerminalInstance() {
		return flags.contains(Instance);
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
	public Rule rule() {
		return this.rule;
	}

	@Override
	public void rule(Rule rule) {
		this.rule = rule;
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

	public String qualifiedNameCleaned() {
		return container().qualifiedNameCleaned() + "$" + name();
	}

	@Override
	public String getUID() {
		if (uid == null) uid = NativeCounter.next(this.container()) + "";
		return uid;
	}

	@Override
	public String anchor() {
		return anchor;
	}

	@Override
	public void anchor(String anchor) {
		this.anchor = anchor;
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
		VariableImpl variable = new VariableImpl(container, type, name);
		variable.file(file);
		variable.line(line());
		variable.column(column());
		variable.size(size());
		variable.defaultMetric(defaultExtension);
		variable.rule(rule);
		flags.forEach(variable::addFlags);
		variable.values(defaultValues);
		variable.setInherited(true);
		return variable;
	}

	@Override
	public Variable cloneIt(NodeContainer container) {
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
