package tara;

import tara.semantic.Assumption;
import tara.semantic.Constraint;
import tara.semantic.SemanticError;
import tara.semantic.SemanticException;
import tara.semantic.model.Node;

import java.util.Collection;
import java.util.Collections;

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

	private void checkConstraintsDeep(Node node) throws SemanticException {
		assume(node);
		if (!node.isReference())
			for (Node content : node.components()) check(content);
		if (node.type() != null) checkNodeConstrains(node);
	}

	private void assume(Node node) {
		Collection<Assumption> assumptions = language.assumptions(node.type());
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
			throw new SemanticException(new SemanticError("reject.type.not.exists", node, Collections.singletonList(node.type())));
	}
}
