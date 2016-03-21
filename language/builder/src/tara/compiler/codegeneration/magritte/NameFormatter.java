package tara.compiler.codegeneration.magritte;

import tara.compiler.codegeneration.Format;
import tara.compiler.model.Model;
import tara.lang.model.*;

import java.util.Arrays;
import java.util.List;

import static tara.compiler.codegeneration.Format.javaClassNames;
import static tara.compiler.codegeneration.Format.qualifiedName;

public class NameFormatter {

	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String composeLayerPackagePath(FacetTarget target, String generatedLanguage) {
		return (generatedLanguage.toLowerCase() + DOT + target.owner().name()).toLowerCase() + (!(target.targetNode().container() instanceof Model) ? DOT + target.targetNode().container().qualifiedName().toLowerCase() : "");
	}

	public static String getQn(Node node, String generatedLanguage) {
		if (node.facetTarget() != null) return getQn(node.facetTarget(), generatedLanguage).replace(":", "");
		final FacetTarget facetTarget = isInFacet(node);
		return generatedLanguage.toLowerCase() + DOT +
			(facetTarget != null ? composeInFacetTargetQN(node, facetTarget) : qualifiedName().format(node.qualifiedName()));
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget target) {
		return target.owner().name().toLowerCase() + DOT +
			(!(target.targetNode().container() instanceof NodeRoot) ?
				target.targetNode().container().qualifiedName().toLowerCase() + DOT :
				"") +
			qualifiedName().format(target.owner().name() + target.targetNode().name()).toString() + DOT +
			qnInsideFacet(node.qualifiedName());
	}

	private static String qnInsideFacet(String qn) {
		final List<String> names = Arrays.asList(qn.split(":"));
		final List<String> qnValues = Arrays.asList(names.get(names.size() - 1).split("\\."));
		return String.join(".", qnValues.subList(1, qnValues.size()));
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT + (!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") + qualifiedName().format(target.owner().name() + target.targetNode().name()).toString();
	}

	public static String getQn(FacetTarget target, Node owner, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT + (!(target.targetNode().container() instanceof NodeRoot) ? target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") + qualifiedName().format(owner.name() + target.targetNode().name()).toString();
	}

	public static String getQn(Facet facet, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + qualifiedName().format(facet.type());
	}

	public static String getJavaQN(String generatedLanguage, Node node) {
		final FacetTarget facet = isInFacet(node);
		final String name = facet != null ?
			composeLayerPackagePath(facet, generatedLanguage) + DOT + javaClassNames().format(node.qualifiedNameCleaned()).toString() :
			generatedLanguage.toLowerCase() + DOT + javaClassNames().format(node.qualifiedNameCleaned()).toString();
		return name.replace(":", "");
	}

	public static String getJavaQN(String generatedLanguage, FacetTarget facetTarget, Node owner) {
		String aPackage = NameFormatter.composeLayerPackagePath(facetTarget, generatedLanguage);
		return aPackage + DOT + Format.javaValidName().format(owner.name() + facetTarget.targetNode().name());
	}

	private static FacetTarget isInFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null && (container instanceof Node) && ((Node) container).facetTarget() == null)
			container = container.container();
		return container != null && container instanceof Node ? ((Node) container).facetTarget() : null;
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANONYMOUS, "").replace("[", "").replace("]", "").replace(":", "");
	}
}
