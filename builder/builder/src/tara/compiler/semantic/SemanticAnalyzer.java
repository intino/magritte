package tara.compiler.semantic;

import tara.Checker;
import tara.Resolver;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
	private final Model model;
	private final boolean persistent;
	private final Resolver resolver;
	private Checker checker;
	private TableChecker tableChecker;
	private AnchorChecker anchorChecker;
	private List<SemanticException> notifications;

	public SemanticAnalyzer(Model model, File resources, boolean persistent) {
		this.model = model;
		this.persistent = persistent;
		tableChecker = new TableChecker(model.getLanguage(), resources);
		resolver = new Resolver(model.getLanguage());
		checker = new Checker(model.getLanguage());
		anchorChecker = new AnchorChecker();
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
			if (persistent) anchorChecker.check(node);
			if (node instanceof NodeImpl && ((NodeImpl) node).table() != null) {
				tableChecker.check((NodeImpl) node);
				return;
			} else checker.check(node);
			if (node instanceof NodeImpl) check(node);
		} catch (SemanticFatalException e) {
			notifications.addAll(e.exceptions());
		}
	}

}
