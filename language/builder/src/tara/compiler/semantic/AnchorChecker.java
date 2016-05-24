package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

class AnchorChecker {

	private static Map<String, Element> anchorMap = new HashMap<>();

	AnchorChecker() {
	}

	void check(Node node) throws SemanticFatalException {
		if (node == null || node.isReference()) return;
		if (!node.is(Tag.Instance) && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
		if (node.anchor() != null && anchorMap.containsKey(node.anchor()) && anchorMap.get(node.anchor()).equals(node))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "duplicated.anchor", node, Arrays.asList(node.name(), anchorMap.get(node.anchor()))));
		else if (node.anchor() != null) anchorMap.put(node.anchor(), node);
//		for (Variable variable : node.variables()) check(variable);
	}
}
