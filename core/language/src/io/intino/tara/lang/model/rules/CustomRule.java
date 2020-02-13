package io.intino.tara.lang.model.rules;

public interface CustomRule {

	Class<?> loadedClass();

	void setLoadedClass(Class<?> loadedClass);

	String externalClass();
}
