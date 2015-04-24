package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Tag;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.compiler.model.Tag.READONLY;
import static siani.tara.compiler.model.Tag.TERMINAL;

public class VariableImpl extends Element implements Variable {
	private NodeContainer container;
	private String type;
	private String name;
	private boolean multiple;
	private List<Object> allowedValues = new ArrayList<>();
	private List<Object> defaultValues = new ArrayList<>();
	private String nativeName;
	private String file;
	private int line;
	private List<Tag> flags = new ArrayList<>();
	private String defaultExtension;

	public VariableImpl() {
	}

	public VariableImpl(NodeContainer container, String type, String name) {
		this.container = container;
		this.type = type;
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
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
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getNativeName() {
		return nativeName;
	}

	@Override
	public Collection<Tag> getFlags() {
		return flags;
	}

	@Override
	public void addFlags(String... flags) {
		for (String annotation : flags)
			this.flags.add(Tag.valueOf(annotation.toUpperCase().replace("+", "META_")));
	}

	@Override
	public boolean isTerminal() {
		return flags.contains(TERMINAL);
	}

	@Override
	public boolean isReadOnly() {
		return flags.contains(READONLY);
	}

	@Override
	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public Collection<Object> getAllowedValues() {
		return allowedValues;
	}

	@Override
	public void addAllowedValues(Object... values) {
		Collections.addAll(this.allowedValues, values);
	}

	@Override
	public Collection<Object> getDefaultValues() {
		return defaultValues;
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
		Variable variable = new VariableImpl(container, type, name);
		variable.setMultiple(multiple);
		variable.setDefaultExtension(defaultExtension);
		variable.setNativeName(nativeName);
		for (Tag tag : flags) variable.addFlags(tag.getName());
		variable.addAllowedValues(allowedValues.toArray(new Object[allowedValues.size()]));
		variable.addDefaultValues(defaultValues.toArray(new Object[defaultValues.size()]));
		return variable;
	}

	public Variable cloneIt(NodeContainer container) throws CloneNotSupportedException {
		Variable clone = this.clone();
		clone.setContainer(container);
		return clone;
	}
}
