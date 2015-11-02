package tara;

import tara.lang.model.Node;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
		Collection<Constraint> hases = language.constraints(node.type());
		if (hases == null) finish(node);
		else for (Constraint constraint : hases)
			constraint.check(node);
	}

	private void finish(Node node) throws SemanticException {
		if (!node.isReference())
			throw new SemanticException(new SemanticError("reject.type.not.exists", node, Collections.singletonList(node.type())));
	}
}