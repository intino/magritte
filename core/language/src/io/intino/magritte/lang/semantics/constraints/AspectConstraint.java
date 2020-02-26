package io.intino.magritte.lang.semantics.constraints;

import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.semantics.Constraint;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.magritte.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class AspectConstraint implements Constraint.Aspect {
	private final String type;
	private final boolean terminal;
	private final boolean required;
	private final String[] with;
	private final String[] withOut;
	private final List<Constraint> constraints;

	AspectConstraint(String type, boolean terminal, boolean required, String[] with, String[] withOut) {
		this.type = type;
		this.terminal = terminal;
		this.required = required;
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
	public boolean isRequired() {
		return required;
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
	public Aspect has(Constraint... requires) {
		this.constraints.addAll(asList(requires));
		return this;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		io.intino.magritte.lang.model.Aspect aspect = findAspect(node, this.type);
		if (aspect == null && required)
			throw new SemanticException(new SemanticNotification(ERROR, "reject.node.with.required.aspect.not.found", node, singletonList(this.type)));
		if (aspect == null) return;
		final boolean hasType = is(node.types(), with);
		final boolean hasIncompatibles = isAny(node.types(), withOut);
		if (!hasType || hasIncompatibles || !checkAspectConstrains(node)) {
			if (!hasType)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.aspect.with.no.constrains.in.context", aspect, Arrays.asList(this.with)));
			else if (hasIncompatibles)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.incompatible.aspects.in.context", aspect, singletonList(String.join(", ", Arrays.asList(this.withOut)))));
		}
	}

	public static io.intino.magritte.lang.model.Aspect findAspect(Node node, String type) {
		return node.appliedAspects().stream().filter(aspect -> type.equals(aspect.fullType())).findFirst().orElse(null);
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

	private boolean checkAspectConstrains(Node node) throws SemanticException {
		List<SemanticException> messages = new ArrayList<>();
		for (Constraint c : constraints) {
			try {
				c.check(node);
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
		return "Aspect " + type;
	}
}
