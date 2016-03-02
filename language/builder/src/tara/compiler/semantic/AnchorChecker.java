package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.model.Variable;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class AnchorChecker {

	private static Map<String, Element> anchorMap = new HashMap<>();

	public AnchorChecker() {
	}

	public void check(Node node) throws SemanticFatalException {
		if (node == null || node.isReference()) return;
		if (!node.is(Tag.Instance) && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
		if (node.anchor() != null && anchorMap.containsKey(node.anchor()) && anchorMap.get(node.anchor()).equals(node))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "duplicated.anchor", node, Arrays.asList(node.name(), anchorMap.get(node.anchor()))));
		else if (node.anchor() != null) anchorMap.put(node.anchor(), node);
//		for (Variable variable : node.variables()) check(variable);
	}

	public void check(Variable variable) throws SemanticFatalException {
		if (variable == null || variable.isReference()) return;
		if ((variable.anchor() == null || variable.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", variable, singletonList(variable.type())));
		if (variable.anchor() != null && anchorMap.containsKey(variable.anchor()) && anchorMap.get(variable.anchor()).equals(variable))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "duplicated.anchor", variable, Arrays.asList(variable.name(), anchorMap.get(variable.anchor()))));
		else if (variable.anchor() != null) anchorMap.put(variable.anchor(), variable);
	}
}
