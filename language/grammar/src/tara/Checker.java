package tara;

import tara.lang.model.Node;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class Checker {

	private final Resolver resolver;
	private final Language language;

	public Checker(Language language) {
		this.language = language;
		this.resolver = new Resolver(language);
	}

	public void check(Node node) throws SemanticException {
		resolver.resolve(node);
		checkConstraints(node);
	}

	private void checkConstraints(Node node) throws SemanticException {
		assume(node);
		checkNodeConstrains(node);
	}

	private void assume(Node node) {
		List<Assumption> assumptions = language.assumptions(node.type());
		if (assumptions != null) assume(node, assumptions);
		for (String type : node.secondaryTypes()) {
			assumptions = language.assumptions(type);
			if (assumptions != null)
				assume(node, assumptions);
		}
	}

	private void assume(Node node, Collection<Assumption> assumptions) {
		for (Assumption assumption : assumptions)
			assumption.assume(node);
	}

	private void checkNodeConstrains(Node node) throws SemanticException {
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) finish(node);
		else for (Constraint constraint : constraints)
			constraint.check(node);
	}

	private void finish(Node node) throws SemanticException {
		if (!node.isReference())
			throw new SemanticException(new SemanticNotification(ERROR, "reject.type.not.exists", node, singletonList(node.type())));
	}
}