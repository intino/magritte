package io.intino.tara.lang.model.rules.variable;

import io.intino.tara.lang.model.Metric;
import io.intino.tara.lang.model.rules.CustomRule;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

public class VariableCustomRule implements VariableRule<List<Object>>, CustomRule {

	private final String aClass;
	private Class<?> loadedClass;
	private VariableRule object;

	public VariableCustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(List<Object> value) {
		return loadedClass != null && invokeWith(value) && !(isMetric() && getDefaultUnit() == null);
	}

	@Override
	public boolean accept(List<Object> value, String metric) {
		return isMetric() && (invokeWith(value, metric) || getDefaultUnit() != null);
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
		for (Class<?> aClass : loadedClass.getInterfaces()) if (aClass.getName().equals(Metric.class.getName())) return true;
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

	private boolean invokeWith(Object value) {
		try {
			return (boolean) loadedClass.getMethod("accept").invoke(object, value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean invokeWith(Object value, String metric) {
		try {
			return (boolean) loadedClass.getMethod("accept").invoke(object, value, metric);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			return true;
		}
	}
}
