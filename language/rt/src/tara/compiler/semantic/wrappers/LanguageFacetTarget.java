package tara.compiler.semantic.wrappers;


import tara.compiler.model.Element;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFacetTarget extends LanguageElement implements tara.semantic.model.FacetTarget {

	FacetTarget target;

	public LanguageFacetTarget(FacetTarget target) {
		this.target = target;
	}

	@Override
	public String target() {
		return target.target();
	}

	@Override
	public List<String> constraints() {
		return target.constraints();
	}

	@Override
	public List<Node> components() {
		List<Node> nodes = target.components().stream().
			map(include -> include instanceof NodeReference ?
				new LanguageNodeReference((NodeReference) include) : new LanguageNode((NodeImpl) include)).collect(Collectors.toList());
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public String type() {
		return target();
	}

	@Override
	public Element element() {
		return target;
	}
}
