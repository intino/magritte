package io.intino.tara.lang.semantics.constraints;

import io.intino.tara.lang.model.Element;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.Resolver;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.List;

import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;


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
		FacetTarget facet = facetTarget(node);
		if (facet == null && !FacetTarget.ANY.equals(type())) return;
		if (!is(node.types()))
			throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.with.no.constrains.in.context", facet, Arrays.asList(this.with)));
	}

	private FacetTarget facetTarget(Node node) {
		return node.facetTarget() != null && this.type.equals(Resolver.shortType(node.facetTarget().target())) ? node.facetTarget() : null;
	}

	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}

}
