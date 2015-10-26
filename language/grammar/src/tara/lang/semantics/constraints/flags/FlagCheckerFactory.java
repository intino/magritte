package tara.lang.semantics.constraints.flags;

import tara.lang.model.Tag;
import tara.lang.model.Node;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

public class FlagCheckerFactory {

	public static final Map<String, Class<? extends AnnotationChecker>> checkers = new HashMap<>();

	static {
		checkers.put("terminal", TerminalChecker.class);
		checkers.put("prototype", PrototypeChecker.class);
	}

	private FlagCheckerFactory() {
	}


	public static Class<? extends AnnotationChecker> get(Object key) {
		return checkers.get(key.toString());
	}

	public static class TerminalChecker implements AnnotationChecker {
		@Override
		public void check(Node node) throws SemanticException {
			for (Tag annotation : node.annotations()) {
				if (Tag.FACET.equals(annotation))
					throw new SemanticException(new SemanticError("reject.flag.combination", node, Arrays.asList(annotation, node.type())));
			}
		}
	}

	public static class PrototypeChecker implements AnnotationChecker {
		@Override
		public void check(Node node) throws SemanticException {
			for (Tag annotation : node.flags())
				if (Tag.FEATURE.equals(annotation)) return;
			throw new SemanticException(new SemanticError("reject.prototype.non.feature", node, singletonList(node.type())));
		}
	}
}
