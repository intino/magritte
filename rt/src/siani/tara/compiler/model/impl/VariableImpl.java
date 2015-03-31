package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.compiler.model.Annotation.READONLY;
import static siani.tara.compiler.model.Annotation.TERMINAL;

public class VariableImpl extends Element implements Variable {
	private NodeContainer container;
	private String type;
	private String name;
	private boolean multiple;
	private List<Object> allowedValues = new ArrayList<>();
	private List<Object> defaultValues = new ArrayList<>();
	private String extension;
	private String file;
	private int line;
	private List<Annotation> annotations = new ArrayList<>();
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
	public String getExtension() {
		return extension;
	}

	@Override
	public Collection<Annotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void addAnnotations(String... annotations) {
		for (String annotation : annotations)
			this.annotations.add(Annotation.valueOf(annotation.toUpperCase().replace("+", "META_")));
	}

	@Override
	public boolean isTerminal() {
		return annotations.contains(TERMINAL);
	}

	@Override
	public boolean isReadOnly() {
		return annotations.contains(READONLY);
	}

	@Override
	public void setExtension(String extension) {
		this.extension = extension;
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
		variable.setExtension(extension);
		for (Annotation annotation : annotations) variable.addAnnotations(annotation.getName());
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
