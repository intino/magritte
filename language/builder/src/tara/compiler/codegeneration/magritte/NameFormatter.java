package tara.compiler.codegeneration.magritte;

import tara.compiler.codegeneration.Format;
import tara.compiler.model.Model;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;

public class NameFormatter {

	public static final String DOT = ".";

	private NameFormatter() {
	}

	public static String composeLayerPackagePath(FacetTarget target, String generatedLanguage) {
		return (generatedLanguage.toLowerCase() + DOT + target.owner().name()).toLowerCase() + (!(target.targetNode().container() instanceof Model) ? DOT + target.targetNode().container().qualifiedName().toLowerCase() : "");
	}

	public static String getQn(Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		return generatedLanguage.toLowerCase() + DOT +
			(facetTarget != null ? composeInFacetTargetQN(node, facetTarget) : Format.qualifiedName().format(node.qualifiedName()));
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node owner = facetTarget.owner();
		return owner.name().toLowerCase() + "." + Format.qualifiedName().format(node.qualifiedName());
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.container();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.container();
		return null;
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT + Format.qualifiedName().format(target.owner().qualifiedName()).toString().replace(":", "");
	}

	public static String getQn(Facet facet, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + Format.qualifiedName().format(facet.type());
	}

	public static String getJavaQN(String generatedLanguage, Node node) {
		final FacetTarget facet = isInFacet(node);
		return facet != null ?
			composeLayerPackagePath(facet, generatedLanguage) + DOT + node.qualifiedName().replace(".", "$") : generatedLanguage.toLowerCase() + DOT +
			Format.javaValidName().format(node.qualifiedName()).toString().replace(".", "$");
	}

	public static String getJavaQN(String generatedLanguage, FacetTarget facetTarget) {
		String aPackage = NameFormatter.composeLayerPackagePath(facetTarget, generatedLanguage);
		return aPackage + DOT + Format.javaValidName().format(facetTarget.owner().name() + facetTarget.targetNode().name());
	}

	private static FacetTarget isInFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null && !(container instanceof FacetTarget))
			container = container.container();
		return container != null ? (FacetTarget) container : null;
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}
