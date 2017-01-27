package io.intino.tara.compiler.semantic;

import io.intino.tara.Checker;
import io.intino.tara.Resolver;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
	private final Model model;
	private final Resolver resolver;
	private Checker checker;
	private List<SemanticException> notifications;

	public SemanticAnalyzer(Model model) {
		this.model = model;
		resolver = new Resolver(model.getLanguage());
		checker = new Checker(model.getLanguage());
		notifications = new ArrayList<>();
	}

	public void analyze() throws SemanticFatalException {
		resolveTypes(model);
		checkNode(model);
		check(model);
		if (!notifications.isEmpty()) throw new SemanticFatalException(notifications);
	}

	private void resolveTypes(Node node) {
		node.components().forEach(this::resolveNode);
	}

	private void check(Node node) {
		node.components().forEach(this::checkNode);
	}

	private void resolveNode(Node node) {
		resolver.resolve(node);
		if (node instanceof NodeImpl) resolveTypes(node);
	}

	private void checkNode(Node node) {
		try {
			checker.check(node);
			if (node instanceof NodeImpl) check(node);
		} catch (SemanticFatalException e) {
			notifications.addAll(e.exceptions());
			if (!hasFatal(e.exceptions()) && node instanceof NodeImpl) check(node);
		}
	}

	private boolean hasFatal(List<SemanticException> exceptions) {
		for (SemanticException exception : exceptions)
			if (exception.isFatal()) return true;
		return false;
	}

}
