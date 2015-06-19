package siani.tara.semantic;

import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Node;

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
			public List<Node> includes() {
				return Collections.emptyList();//includes;
			}
		};
	}
}
