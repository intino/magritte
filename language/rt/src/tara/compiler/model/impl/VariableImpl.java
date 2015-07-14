package tara.compiler.model.impl;

import tara.compiler.model.NodeContainer;
import tara.compiler.model.Variable;
import tara.semantic.model.Tag;
import tara.util.WordGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static tara.semantic.model.Tag.*;

public class VariableImpl implements Variable {
	private NodeContainer container;
	private String type;
	private String name;
	private List<Object> allowedValues = new ArrayList<>();
	private List<Object> defaultValues = new ArrayList<>();
	private String contract;
	private String file;
	private int line;
	private List<Tag> flags = new ArrayList<>();
	private String defaultExtension;
	private boolean inherited;
	private boolean overriden;
	private int size = 1;
	private final String uid;

	public VariableImpl(NodeContainer container, String type, String name) {
		this.container = container;
		this.type = type;
		this.name = name;
		uid = WordGenerator.generate();
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public void type(String type) {
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
	public void setContainer(NodeContainer container) {
		this.container = container;
	}

	@Override
	public String contract() {
		return contract;
	}

	@Override
	public void contract(String contract) {
		this.contract = contract;
	}

	@Override
	public Collection<Tag> flags() {
		return flags;
	}

	@Override
	public void addFlags(String... flags) {
		for (String annotation : flags)
			this.flags.add(Tag.valueOf(annotation.toUpperCase()));
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(TERMINAL);
	}

	@Override
	public boolean isTerminalInstance() {
		return flags.contains(TERMINAL_INSTANCE);
	}

	@Override
	public boolean isFinal() {
		return flags.contains(FINAL);
	}

	@Override
	public boolean isPrivate() {
		return flags.contains(PRIVATE);
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
		return size != 1;
	}


	@Override
	public List<Object> allowedValues() {
		return Collections.unmodifiableList(allowedValues);
	}

	@Override
	public void addAllowedValues(Object... values) {
		Collections.addAll(this.allowedValues, values);
	}

	@Override
	public List<Object> getDefaultValues() {
		return Collections.unmodifiableList(defaultValues);
	}

	@Override
	public void addDefaultValues(Object... values) {
		Collections.addAll(this.defaultValues, values);
	}

	@Override
	public String defaultExtension() {
		return defaultExtension;
	}

	@Override
	public void defaultExtension(String defaultExtension) {
		this.defaultExtension = defaultExtension;
	}

	@Override
	public String getUID() {
		return uid;
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

	@Override
	public Variable clone() throws CloneNotSupportedException {
		super.clone();
		VariableImpl variable = new VariableImpl(container, type, name);
		variable.size(size);
		variable.defaultExtension(defaultExtension);
		variable.contract(contract);
		for (Tag tag : flags) variable.addFlags(tag.name());
		variable.addAllowedValues(allowedValues.toArray(new Object[allowedValues.size()]));
		variable.addDefaultValues(defaultValues.toArray(new Object[defaultValues.size()]));
		variable.setInherited(true);
		return variable;
	}

	public Variable cloneIt(NodeContainer container) {
		try {
			Variable clone = this.clone();
			clone.container(container);
			return clone;
		} catch (CloneNotSupportedException ignored) {
			return null;
		}
	}

	@Override
	public String toString() {
		return type + ":" + name;
	}

	public boolean isOverriden() {
		return overriden;
	}

	@Override
	public void overriden(boolean overriden) {
		this.overriden = overriden;
	}

	public int size() {
		return size;
	}

	public void size(int tupleSize) {
		this.size = tupleSize;
	}
}
