package tara.intellij.codeinsight.languageinjection.helpers;

import org.siani.itrules.engine.FormatterStore;
import tara.Checker;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.NodeRoot;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.Locale;

import static tara.dsl.ProteoConstants.FACET;
import static tara.dsl.ProteoConstants.METAFACET;
import static tara.intellij.codeinsight.languageinjection.helpers.Format.qualifiedName;

public class QualifiedNameFormatter {

	public static final String DOT = ".";

	private QualifiedNameFormatter() {
	}

	private static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = facetTarget.owner();
		return container.name().toLowerCase() + "." + node.qualifiedNameCleaned().replace("$", ".");
	}

	public static Object firstUpperCase(String name) {
		return new FormatterStore(Locale.ENGLISH).get("firstUpperCase").format(name);
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		if (target == null) return "";
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT +
			(!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") +
			qualifiedName().format(target.owner().name() + target.targetNode().name()).toString();
	}

	public static String getQn(FacetTarget target, Node owner, String generatedLanguage) {
		if (target == null || owner == null || target.targetNode() == null) return "";
		return generatedLanguage.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
			(!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") +
			qualifiedName().format(owner.name() + target.targetNode().name()).toString();
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
		final NodeContainer container = TaraPsiImplUtil.getContainerOf(valued);
		if (container == null || valued == null) return "";
		if (valued.name() == null || valued.name().isEmpty()) resolve(valued);
		return container.qualifiedName() + "." + valued.name();
	}

	private static void resolve(Valued valued) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(valued);
		if (node != null) try {
			final tara.Language language = TaraUtil.getLanguage(valued);
			if (language != null) new Checker(language).check(node.resolve());
		} catch (SemanticFatalException ignored) {
		}
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		if (language == null || node == null) return "";
		return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedNameCleaned().replace("$", ".") : composeInFacetTargetQN(node, facetTarget)) :
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
