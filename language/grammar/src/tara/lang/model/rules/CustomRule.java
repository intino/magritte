package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.lang.reflect.InvocationTargetException;

public class CustomRule implements Rule<Object> {

	private final String aClass;
	private Class<?> loadedClass;

	public CustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(Object value) {
		if (loadedClass != null && !loadedClass.getInterfaces()[0].getName().contains("Metric")) {
			try {
				return (boolean) loadedClass.getMethod("accept").invoke(value);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Class<?> getLoadedClass() {
		return loadedClass;
	}

	public String getSource() {
		return aClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
	}
}
