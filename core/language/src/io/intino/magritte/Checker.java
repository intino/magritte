package io.intino.magritte;

import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.semantics.Assumption;
import io.intino.magritte.lang.semantics.Constraint;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticFatalException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.intino.magritte.lang.semantics.constraints.AspectConstraint.findAspect;
import static io.intino.magritte.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class Checker {

	private final Resolver resolver;
	private final Language language;
	private final List<SemanticException> exceptions = new ArrayList<>();

	public Checker(Language language) {
		this.language = language;
		this.resolver = new Resolver(language);
	}

	public void check(Node node) throws SemanticFatalException {
		exceptions.clear();
		resolver.resolve(node);
		checkConstraints(node);
		if (!exceptions.isEmpty()) {
			recoverErrors();
			if (!exceptions.isEmpty()) throw new SemanticFatalException(exceptions);
		}
	}

	private void recoverErrors() {
		final List<SemanticException> toRemove = exceptions.stream().filter(e -> e.getNotification().key().equals("required.parameter.in.context") && isParameterNotFoundRecoverable(e.getNotification().origin()[0], e.getNotification().parameters().get(0).toString(), e.getNotification().parameters().get(1).toString())).collect(toList());
		exceptions.removeAll(toRemove);
	}

	private boolean isParameterNotFoundRecoverable(Element element, String name, String type) {
		final Node node = (Node) element;
		if (language == null || node == null || node.type() == null) return false;
		final List<Constraint.Aspect> aspects = language.constraints(node.type()).stream().filter(c -> sameAspect(node, c)).map(c -> (Constraint.Aspect) c).collect(toList());
		for (Constraint.Aspect aspect : aspects)
			for (Constraint.Parameter c : aspect.constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(p -> (Constraint.Parameter) p).collect(toList()))
				if (c.type().name().equalsIgnoreCase(type) && c.name().equals(name) && !c.size().isRequired()) return true;
		return false;
	}

	private boolean sameAspect(Node node, Constraint c) {
		return c instanceof Constraint.Aspect && findAspect(node, ((Constraint.Aspect) c).type()) != null;
	}

	private void checkConstraints(Node node) throws SemanticFatalException {
		if (node == null)
			throw new SemanticFatalException(new SemanticNotification(SemanticNotification.Level.ERROR, "Node is null", (Element) null));
		assume(node);
		checkNodeConstrains(node);
	}

	private void assume(Node node) {
		if (node == null || node.type() == null || language == null) return;
		List<Assumption> assumptions = language.assumptions(node.type());
		if (assumptions != null) assume(node, assumptions);
		for (String type : node.secondaryTypes()) {
			assumptions = language.assumptions(type);
			if (assumptions != null) assume(node, assumptions);
		}
	}

	private void assume(Node node, Collection<Assumption> assumptions) {
		for (Assumption assumption : assumptions)
			assumption.assume(node);
	}

	private void checkNodeConstrains(Node node) throws SemanticFatalException {
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) finish(node);
		else for (Constraint constraint : constraints)
			try {
				constraint.check(node);
			} catch (SemanticException e) {
				if (e.level() == ERROR && e.isFatal()) throw new SemanticFatalException(singletonList(e));
				else exceptions.add(e);
			}
	}

	private void finish(Node node) throws SemanticFatalException {
		if (!node.isReference())
			throw new SemanticFatalException(new SemanticNotification(ERROR, "reject.type.not.exists", node, singletonList(presentableType(node.type()))));
	}

	private String presentableType(String type) {
		return type.replaceFirst(":", " on ");
	}
}