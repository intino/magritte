package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.ArrayList;
import java.util.List;

public class LanguageNodeReference extends LanguageNode implements siani.tara.semantic.model.Node {

	private final NodeReference reference;

	public LanguageNodeReference(NodeReference reference) {
		super(null);
		this.reference = reference;
	}

	@Override
	public siani.tara.semantic.model.Node context() {
		return new LanguageNode((NodeImpl) reference.getContainer());
	}

	@Override
	public String type() {
		return reference.getType();
	}

	@Override
	public boolean isReference() {
		return true;
	}

	@Override
	public void type(String type) {
	}

	@Override
	public String[] secondaryTypes() {
		List<String> types = new ArrayList<>();
		for (Facet facet : reference.getDestiny().getFacets())
			types.add(facet.type());
		return types.toArray(new String[types.size()]);
	}

	@Override
	public String name() {
		return "";
	}

	@Override
	public siani.tara.semantic.model.Node parent() {
		return null;
	}

	@Override
	public boolean hasSubs() {
		return false;
	}

	@Override
	public Long address() {
		return Long.MIN_VALUE;
	}

	@Override
	public String[] annotations() {
		List<String> values = new ArrayList<>();
		for (Annotation annotation : reference.getAnnotations()) values.add(annotation.getName());
		return values.toArray(new String[values.size()]);
	}

	@Override
	public void moveToTheTop() {
	}

	@Override
	public void annotations(String... annotations) {
		reference.addAnnotations(annotations);
	}

	@Override
	public siani.tara.semantic.model.Facet[] facets() {
		return new siani.tara.semantic.model.Facet[0];
	}

	@Override
	public siani.tara.semantic.model.FacetTarget[] facetTargets() {
		return new siani.tara.semantic.model.FacetTarget[0];
	}

	@Override
	public siani.tara.semantic.model.Parameter[] parameters() {
		return new siani.tara.semantic.model.Parameter[0];
	}

	@Override
	public siani.tara.semantic.model.Node[] includes() {
		return new siani.tara.semantic.model.Node[0];
	}

	@Override
	public String toString() {
		return "reference " + reference.getType();
	}

	@Override
	public Element element() {
		return reference;
	}
}
