package tara.lang.semantics.constraints.flags;

import tara.dsl.Proteo;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.HashMap;
import java.util.Map;

import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class FlagCoherenceCheckerFactory {

	public static final Map<String, FlagChecker> checkers = new HashMap<>();

	static {
		checkers.put(Tag.Private.name().toLowerCase(), new PrivateChecker());
		checkers.put(Tag.Final.name().toLowerCase(), new FinalChecker());
		checkers.put(Tag.Feature.name().toLowerCase(), new FeatureChecker());

	}

	private FlagCoherenceCheckerFactory() {
	}


	public static FlagChecker get(Object key) {
		return checkers.get(key.toString());
	}


	private static class PrivateChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.flags().contains(Tag.Final))
				throw error(node);
		}
	}

	private static class FinalChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.flags().contains(Tag.Private))
				throw error(node);
		}
	}

	private static class FeatureChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.type().equals(Proteo.METACONCEPT))
				throw error(node);
		}
	}

	public static SemanticException error(Node node) {
		return new SemanticException(new SemanticNotification(ERROR, "reject.flag.combination", node));
	}
}
