package tara.lang.semantics.constraints;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static tara.lang.semantics.constraints.ConstraintHelper.componentConstrains;
import static tara.lang.semantics.constraints.ConstraintHelper.parameterConstrains;
import static tara.lang.semantics.constraints.RuleFactory.rejectOtherComponents;
import static tara.lang.semantics.constraints.RuleFactory.rejectOtherParameters;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

class FacetConstraint implements Constraint.Facet {
	private final String type;
	private final String[] with;
	private final boolean terminal;
	private final List<Constraint> constraints;

	public FacetConstraint(String type, boolean terminal, String[] with) {
		this.type = type;
		this.terminal = terminal;
		this.with = with.clone();
		constraints = new ArrayList<>();
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String[] with() {
		return with;
	}

	public boolean terminal() {
		return terminal;
	}

	@Override
	public List<Constraint> constraints() {
		return constraints;
	}

	@Override
	public Facet has(Constraint... requires) {
		this.constraints.addAll(asList(requires));
		this.constraints().add(rejectOtherComponents(componentConstrains(this.constraints())));
		this.constraints().add(rejectOtherParameters(parameterConstrains(this.constraints())));
		return this;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		tara.lang.model.Facet facet = findFacet(node);
		if (facet == null && !FacetTarget.ANY.equals(type())) return;
		final boolean hasType = is(node.types());
		if (!hasType || !checkFacetConstrains(facet)) {
			if (!hasType)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.with.no.constrains.in.context", facet, Arrays.asList(this.with)));
		}
	}

	private tara.lang.model.Facet findFacet(Node node) {
		for (tara.lang.model.Facet facet : node.facets())
			if (this.type.equals(Resolver.shortType(facet.type()))) return facet;
		return null;
	}

	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}

	private boolean checkFacetConstrains(tara.lang.model.Facet facet) throws SemanticException {
		for (Constraint require : constraints) require.check(facet);
		return true;
	}

}
