package tara.lang.semantics.constraints.flags;

import tara.lang.model.Node;
import tara.lang.model.NodeRoot;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.dsl.ProteoConstants.META_CONCEPT;
import static tara.lang.model.Tag.*;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class FlagCoherenceCheckerFactory {

	private static final Map<String, FlagChecker> checkers = new HashMap<>();

	static {
		checkers.put(Tag.Private.name().toLowerCase(), new PrivateChecker());
		checkers.put(Feature.name().toLowerCase(), new FeatureChecker());
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
			if (node.flags().contains(Final)) throw error(node, asList(Final.name(), Private.name()));
		}
	}

	private static class FeatureChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.type().equals(META_CONCEPT)) throw error("metaconcept.cannot.be", node, singletonList(Feature.name()));
			if (node.isReference() && !node.destinyOfReference().is(Feature))
				throw error("declared.node.must.be", node, singletonList(Feature.name()));
		}
	}

	private static class ComponentChecker implements FlagChecker {
		@Override
		public void check(Node node) throws SemanticException {
			if (node.isReference() || !(node.container() instanceof NodeRoot)) return;
			if (node.isReference() && !node.destinyOfReference().is(Component))
				throw error("declared.node.must.be", node, singletonList(Component.name()));
			if (node.flags().contains(Feature)) throw error(node, asList(Feature.name(), Component.name()));
			final Size size = node.container().sizeOf(node);
			if (size == null) return;
			if (size.min() != 0 || size.max() != Integer.MAX_VALUE) throw error("reject.root.component.size", node);
		}
	}

	public static SemanticException error(Node node, List<String> flags) {
		return new SemanticException(new SemanticNotification(ERROR, "reject.flag.combination", node, flags));
	}

	public static SemanticException error(String message, Node node) {
		return new SemanticException(new SemanticNotification(ERROR, message, node));
	}

	public static SemanticException error(String message, Node node, List<String> parameters) {
		return new SemanticException(new SemanticNotification(ERROR, message, node, parameters));
	}
}
