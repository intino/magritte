package io.intino.tara.lang.model.rules.composition;

import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.rules.CustomRule;
import io.intino.tara.lang.model.rules.NodeRule;

import java.util.Collections;
import java.util.List;

public class NodeCustomRule implements NodeRule, CustomRule {
	private final String aClass;
	private Class<?> loadedClass;
	private NodeRule nodeRule;

	public NodeCustomRule(String aClass) {
		this.aClass = aClass;
	}

	@Override
	public boolean accept(Node value) {
		return loadedClass != null && nodeRule.accept(value);
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.emptyList();
	}

	public Class<?> getLoadedClass() {
		return loadedClass;
	}

	public String getSource() {
		return aClass;
	}

	public void setLoadedClass(Class<?> loadedClass) {
		this.loadedClass = loadedClass;
		try {
			this.nodeRule = (NodeRule) this.loadedClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
