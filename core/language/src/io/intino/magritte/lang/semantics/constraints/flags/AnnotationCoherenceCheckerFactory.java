package io.intino.magritte.lang.semantics.constraints.flags;

import io.intino.magritte.dsl.ProteoConstants;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Tag;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;
import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

import java.util.HashMap;
import java.util.Map;

import static io.intino.magritte.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static java.util.Arrays.asList;

public class AnnotationCoherenceCheckerFactory {

	private static final Map<String, FlagChecker> checkers = new HashMap<>();

	static {
		checkers.put(Tag.Feature.name().toLowerCase(), new FeatureChecker());
	}

	private AnnotationCoherenceCheckerFactory() {
	}


	public static FlagChecker get(Object key) {
		return checkers.get(key.toString());
	}


	private static class FeatureChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (ProteoConstants.CONCEPT.equals(node.type())) throw error(node);
		}
	}

	public static SemanticException error(Node node) {
		return new SemanticException(new SemanticNotification(ERROR, "reject.flag.combination", node, asList(Tag.Concept, Tag.Feature)));
	}
}
