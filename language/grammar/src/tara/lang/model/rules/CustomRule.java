package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.lang.reflect.InvocationTargetException;

public class CustomRule implements Rule<Object> {

	private final String aClass;
	private String languageName;

	public CustomRule(String aClass) {
		this.aClass = aClass;
	}

	public boolean execute(Object value) {
		final Class<?> aClass = loadClass();
		if (aClass != null && !aClass.getInterfaces()[0].getName().contains("Metric")) {
			try {
				return (boolean) aClass.getMethod("accept").invoke(value);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Class<?> loadClass() {
		try {
			return Class.forName(languageName + ".rules." + aClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTheClass() {
		return aClass;
	}

	@Override
	public boolean accept(Object value) {
		return false;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
}
