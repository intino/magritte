package io.intino.tara.lang.model.rules.composition;

import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.rules.CustomRule;
import io.intino.tara.lang.model.rules.NodeRule;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NodeCustomRule implements NodeRule, CustomRule {
	private final String aClass;
	private Class<?> loadedClass;
	private Object nodeRule;

	public NodeCustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(Node value) {
		return loadedClass != null && ((NodeRule) nodeRule).accept(value);
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.emptyList();
	}

	public Class<?> loadedClass() {
		return loadedClass;
	}

	public String externalClass() {
		return aClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
		try {
			this.nodeRule = this.loadedClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(aClass);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NodeCustomRule)) return false;
		return aClass.equals(((NodeCustomRule) obj).aClass);
	}
}
