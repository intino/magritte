package tara.semantic;

import tara.semantic.model.FacetTarget;
import tara.semantic.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ScriptTargetDefinition implements TargetDefinition {
	String target;
	List<ScriptNode> includes = new ArrayList<>();

	@Override
	public TargetDefinition on(String target) {
		this.target = target;
		return this;
	}

	@Override
	public TargetDefinition include(Node node) {
		includes.add((ScriptNode) node);
		return this;
	}

	@Override
	public FacetTarget target() {
		return new FacetTarget() {
			@Override
			public String target() {
				return target;
			}

			@Override
			public List<String> constraints() {
				return null;
			}

			@Override
			public List<Node> components() {
				return Collections.emptyList();//components;
			}

			@Override
			public String type() {
				return null;
			}
		};
	}
}
