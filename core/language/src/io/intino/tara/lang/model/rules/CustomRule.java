package io.intino.tara.lang.model.rules;

public interface CustomRule {

	Class<?> getLoadedClass();

	void setLoadedClass(Class<?> loadedClass);

	String getExternalWordClass();
}
