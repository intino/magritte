package tara.lang.semantics.constraints;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.List;

import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;


class MetaFacetConstraint implements Constraint.MetaFacet {

	private final String type;
	private final String[] with;

	MetaFacetConstraint(String type, String[] with) {
		this.type = type;
		this.with = with;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String[] with() {
		return with;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		tara.lang.model.FacetTarget facet = facetTarget(node);
		if (facet == null && !FacetTarget.ANY.equals(type())) return;
		if (!is(node.types()))
			throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.with.no.constrains.in.context", facet, Arrays.asList(this.with)));
	}

	private tara.lang.model.FacetTarget facetTarget(Node node) {
		return node.facetTarget() != null && this.type.equals(Resolver.shortType(node.facetTarget().target())) ? node.facetTarget() : null;
	}

	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}

}
