package tara.compiler.model.impl;

import tara.compiler.model.NodeContainer;
import tara.compiler.model.Tag;
import tara.compiler.model.Variable;
import tara.compiler.util.WordGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VariableImpl implements Variable {
	private NodeContainer container;
	private String type;
	private String name;
	private boolean multiple;
	private List<Object> allowedValues = new ArrayList<>();
	private List<Object> defaultValues = new ArrayList<>();
	private String contract;
	private String file;
	private int line;
	private List<Tag> flags = new ArrayList<>();
	private String defaultExtension;
	private boolean inherited;
	private boolean overriden;
	private int tupleSize;
	private final String uid;

	public VariableImpl(NodeContainer container, String type, String name) {
		this.container = container;
		this.type = type;
		this.name = name;
		uid = WordGenerator.generate();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
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
	public NodeContainer getContainer() {
		return container;
	}

	@Override
	public void setContainer(NodeContainer container) {
		this.container = container;
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
	public Collection<Tag> getFlags() {
		return flags;
	}

	@Override
	public void addFlags(String... flags) {
		for (String annotation : flags)
			this.flags.add(Tag.valueOf(annotation.toUpperCase()));
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(Tag.TERMINAL);
	}

	@Override
	public boolean isTerminalInstance() {
		return flags.contains(Tag.TERMINAL_INSTANCE);
	}

	@Override
	public boolean isFinal() {
		return flags.contains(Tag.FINAL);
	}

	@Override
	public boolean isPrivate() {
		return flags.contains(Tag.PRIVATE);
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
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public List<Object> getAllowedValues() {
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
	public String getDefaultExtension() {
		return defaultExtension;
	}

	@Override
	public void setDefaultExtension(String defaultExtension) {
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
		variable.setMultiple(multiple);
		variable.setDefaultExtension(defaultExtension);
		variable.setContract(contract);
		for (Tag tag : flags) variable.addFlags(tag.name());
		variable.addAllowedValues(allowedValues.toArray(new Object[allowedValues.size()]));
		variable.addDefaultValues(defaultValues.toArray(new Object[defaultValues.size()]));
		variable.setInherited(true);
		return variable;
	}

	public Variable cloneIt(NodeContainer container) {
		try {
			Variable clone = this.clone();
			clone.setContainer(container);
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
	public void setOverriden(boolean overriden) {
		this.overriden = overriden;
	}

	public int getTupleSize() {
		return tupleSize;
	}

	public void setTupleSize(int tupleSize) {
		this.tupleSize = tupleSize;
	}
}
