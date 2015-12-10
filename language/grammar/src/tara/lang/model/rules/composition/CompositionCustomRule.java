package tara.lang.model.rules.composition;

import tara.lang.model.Node;
import tara.lang.model.rules.CompositionRule;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

public class CompositionCustomRule implements CompositionRule {

	private final String aClass;
	private Class<?> loadedClass;

	public CompositionCustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(List<Node> value) {
		return loadedClass != null && invokeWith(value);
	}

	@Override
	public String errorMessage() {
		return "";//TODO
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.emptyList(); //TODO
	}


	public Class<?> getLoadedClass() {
		return loadedClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
	}

	public String getSource() {
		return aClass;
	}

	private boolean invokeWith(Object value) {
		try {
			return (boolean) loadedClass.getMethod("accept").invoke(value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean invokeWith(Object value, String metric) {
		try {
			return (boolean) loadedClass.getMethod("accept").invoke(value, metric);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int min() {
		try {
			return (int) loadedClass.getMethod("min").invoke(null);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int max() {
		try {
			return (int) loadedClass.getMethod("max").invoke(null);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public CompositionRule is() {
		return this;
	}

	@Override
	public void is(CompositionRule rule) {

	}

	@Override
	public CompositionRule into() {
		return null;
	}

	@Override
	public void into(CompositionRule rule) {

	}
}
