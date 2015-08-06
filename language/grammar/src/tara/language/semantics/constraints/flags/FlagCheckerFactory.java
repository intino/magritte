package tara.language.semantics.constraints.flags;

import tara.language.model.Node;
import tara.language.model.Tag;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FlagCheckerFactory {

	public static final Map<String, Class<? extends AnnotationChecker>> checkers = new HashMap<>();

	static {
		checkers.put("terminal", TerminalChecker.class);
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
}
