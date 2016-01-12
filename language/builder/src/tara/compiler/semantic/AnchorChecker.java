package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class AnchorChecker {

	private Map<String, Node> anchorMap = new HashMap<>();

	public AnchorChecker() {
	}

	public void check(Node node) throws SemanticFatalException {
		if (node == null) return;
		if (!node.is(Tag.Instance) && !node.isReference() && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
		if (anchorMap.containsKey(node.anchor()) && anchorMap.get(node.anchor()) != node)
			throw new SemanticFatalException(new SemanticNotification(ERROR, "duplicated.anchor", node, singletonList(node.type())));
		else anchorMap.put(node.anchor(), node);
	}
}
