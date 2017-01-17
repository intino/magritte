package io.intino.tara.lang.semantics.constraints;

import io.intino.tara.lang.model.Element;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.Resolver;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

class FacetConstraint implements Constraint.Facet {
	private final String type;
	private final boolean terminal;
	private final String[] with;
	private final String[] withOut;
	private final List<Constraint> constraints;

	FacetConstraint(String type, boolean terminal, String[] with, String[] withOut) {
		this.type = type;
		this.terminal = terminal;
		this.with = with.clone();
		this.withOut = withOut.clone();
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

	@Override
	public String[] withOut() {
		return withOut;
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
		return this;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		io.intino.tara.lang.model.Facet facet = findFacet(node);
		if (facet == null && !FacetTarget.ANY.equals(type())) return;
		final boolean hasType = is(node.types(), with);
		final boolean hasIncompatibles = isAny(node.types(), withOut);
		if (!hasType || hasIncompatibles || !checkFacetConstrains(node)) {
			if (!hasType)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.with.no.constrains.in.context", facet, Arrays.asList(this.with)));
			else if (hasIncompatibles)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.incompatible.facets.in.context", facet, singletonList(String.join(", ", Arrays.asList(this.withOut)))));
		}
	}

	private io.intino.tara.lang.model.Facet findFacet(Node node) {
		for (io.intino.tara.lang.model.Facet facet : node.facets())
			if (this.type.equals(Resolver.shortType(facet.type()))) return facet;
		return null;
	}

	private boolean is(List<String> nodeTypes, String[] constraints) {
		List<String> types = nodeTypes.stream().map(s -> s.split(":")[0]).collect(Collectors.toList());
		if (constraints == null) return true;
		for (String aType : constraints)
			if (!types.contains(aType)) return false;
		return true;
	}

	private boolean isAny(List<String> nodeTypes, String[] constraints) {
		List<String> types = nodeTypes.stream().map(s -> s.split(":")[0]).collect(Collectors.toList());
		if (constraints == null) return false;
		for (String aType : constraints)
			if (types.contains(aType) && !aType.equals(this.type)) return true;
		return false;
	}

	private boolean checkFacetConstrains(Node node) throws SemanticException {
		List<SemanticException> messages = new ArrayList<>();
		for (Constraint require : constraints) {
			try {
				require.check(node);
			} catch (SemanticException e) {
				if (e.level() == ERROR) throw e;
				else messages.add(e);
			}
		}
		if (!messages.isEmpty()) throw messages.get(0);
		return true;
	}

	@Override
	public String toString() {
		return "Facet " + type;
	}
}
