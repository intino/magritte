package io.intino.tara.plugin.codeinsight.languageinjection.helpers;

import io.intino.tara.Checker;
import io.intino.tara.Language;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeRoot;
import io.intino.tara.lang.semantics.errorcollector.SemanticFatalException;
import io.intino.tara.plugin.lang.psi.Valued;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;

import static io.intino.tara.dsl.ProteoConstants.FACET;
import static io.intino.tara.dsl.ProteoConstants.METAFACET;

public class QualifiedNameFormatter {

	public static final String DOT = ".";

	private QualifiedNameFormatter() {
	}

	private static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = facetTarget.owner();
		return container.name().toLowerCase() + "." + node.cleanQn().replace("$", ".");
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		if (target == null) return "";
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT +
				(!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") +
				Format.qualifiedName().format(target.owner().name() + target.targetNode().name()).toString();
	}

	public static String getQn(FacetTarget target, Node owner, String generatedLanguage) {
		if (target == null || owner == null || target.targetNode() == null) return "";
		return generatedLanguage.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
				(!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") +
				Format.qualifiedName().format(owner.name() + target.targetNode().name()).toString();
	}

	public static String getQn(Node owner, String language, boolean m0) {
		return asNode(owner, language, m0, null);
	}

	public static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.type() != null && (owner.type().equals(FACET) || owner.metaTypes().contains(METAFACET)) && facetTarget != null)
			return asFacetTarget(owner, language, facetTarget);
		else return asNode(owner, language, m0, facetTarget);
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANONYMOUS, "").replace("[", "").replace("]", "");
	}


	public static String qnOf(Valued valued) {
		final Node container = TaraPsiImplUtil.getContainerNodeOf(valued);
		if (container == null || valued == null) return "";
		if (valued.name() == null || valued.name().isEmpty()) resolve(valued);
		return container.qualifiedName() + "." + valued.name();
	}

	private static void resolve(Valued valued) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(valued);
		if (node != null) try {
			final Language language = TaraUtil.getLanguage(valued);
			if (language != null) new Checker(language).check(node.resolve());
		} catch (SemanticFatalException ignored) {
		}
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		if (language == null || node == null) return "";
		return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.cleanQn().replace("$", ".") : composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + DOT + node.type();
	}

	private static String asFacetTarget(Node owner, String language, FacetTarget facetTarget) {
		return language.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
				Format.reference().format(owner.name()) + Format.reference().format(facetTarget.target());
	}

	private static FacetTarget facetTargetContainer(Node node) {
		Node container = node.container();
		while (container != null)
			if (container.facetTarget() != null)
				return container.facetTarget();
			else container = container.container();
		return null;
	}


}
