package io.intino.tara.lang.model.rules.variable;

import io.intino.tara.lang.model.Metric;
import io.intino.tara.lang.model.Rule;
import io.intino.tara.lang.model.rules.CustomRule;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class VariableCustomRule implements VariableRule<List<Object>>, CustomRule {
	private static final Logger LOG = Logger.getGlobal();

	private final String aClass;
	private Class<?> loadedClass;
	private VariableRule object;

	public VariableCustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(List<Object> values) {
		return loadedClass != null && invokeWith(values) && (!isMetric() && getDefaultUnit() == null);
	}

	@Override
	public boolean accept(List<Object> values, String metric) {
		return (isMetric() && (invokeWith(values, metric) || getDefaultUnit() != null)) || accept(values);
	}

	@Override
	public String errorMessage() {
		return "";//TODO
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.emptyList(); //TODO
	}


	public String getDefaultUnit() {
		for (Field field : loadedClass.getDeclaredFields()) if (field.isEnumConstant()) return field.getName();
		return null;
	}

	public boolean isMetric() {
		if (!loadedClass.isEnum()) return false;
		for (Class<?> aClass : loadedClass.getInterfaces())
			if (aClass.getName().equals(Metric.class.getName())) return true;
		return false;
	}

	public Class<?> getLoadedClass() {
		return loadedClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
		try {
			this.object = (VariableRule) this.loadedClass.newInstance();
		} catch (InstantiationException | IllegalAccessException ignored) {
		}
	}

	public String getSource() {
		return aClass;
	}

	private boolean invokeWith(List<Object> values) {
		try {
			for (Object value : values) if (!((Rule) loadedClass.newInstance()).accept(value)) return false;
			return true;
		} catch (IllegalAccessException | InstantiationException e) {
			LOG.severe(e.getMessage());
		}
		return false;
	}

	private boolean invokeWith(List<Object> values, String metric) {
		try {
			for (Object value : values)
				if (!((Rule) loadedClass.newInstance()).accept(value, metric)) return false;
			return true;
		} catch (IllegalAccessException | InstantiationException e) {
			LOG.severe(e.getMessage());
		}
		return false;
	}
}
