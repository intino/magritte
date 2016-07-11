package tara;

import tara.lang.model.Node;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
		if (!exceptions.isEmpty()) throw new SemanticFatalException(exceptions);
	}

	private boolean hasFatal() {
		for (SemanticException exception : exceptions) if (exception.level() == ERROR) return true;
		return false;
	}

	private void checkConstraints(Node node) throws SemanticFatalException {
		assume(node);
		checkNodeConstrains(node);
	}

	private void assume(Node node) {
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
		else for (Constraint constraint : constraints) {
			try {
				constraint.check(node);
			} catch (SemanticException e) {
				if (e.level() == ERROR && e.isFatal()) throw new SemanticFatalException(Collections.singletonList(e));
				else exceptions.add(e);
			}
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