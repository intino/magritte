package tara.lang.semantics.constraints.flags;

import tara.dsl.Proteo;
import tara.lang.model.Node;
import tara.lang.model.NodeRoot;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
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
		checkers.put(Tag.Component.name().toLowerCase(), new ComponentChecker());
	}

	private FlagCoherenceCheckerFactory() {
	}


	public static FlagChecker get(Object key) {
		return checkers.get(key.toString());
	}


	private static class PrivateChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.flags().contains(Tag.Final)) throw error(node);
		}
	}

	private static class FinalChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.flags().contains(Tag.Private)) throw error(node);
		}
	}

	private static class FeatureChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.type().equals(Proteo.METACONCEPT)) throw error(node);
		}
	}

	private static class ComponentChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.isReference() || node.container() instanceof NodeRoot) return;
			final CompositionRule rule = node.container().ruleOf(node);
			if (rule == null) return;
			if (rule.min() != 0 || rule.max() != Integer.MAX_VALUE) throw error("reject.root.component.size", node);
		}
	}

	public static SemanticException error(Node node) {
		return new SemanticException(new SemanticNotification(ERROR, "reject.flag.combination", node));
	}

	public static SemanticException error(String message, Node node) {
		return new SemanticException(new SemanticNotification(ERROR, message, node));
	}
}
