package tara.compiler.codegeneration.magritte;

import org.siani.itrules.engine.FormatterStore;
import tara.compiler.codegeneration.Format;
import tara.language.model.Facet;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

import java.util.Locale;

public class NameFormatter {

	public static final String DOT = ".";

	private NameFormatter() {
	}

	public static String composeMorphPackagePath(FacetTarget target, String generatedLanguage) {
		return (generatedLanguage.toLowerCase() + DOT + ((Node) target.container()).name()).toLowerCase();
	}

	public static String getQn(Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		return generatedLanguage.toLowerCase() + DOT +
			(facetTarget != null ? composeInFacetTargetQN(node, facetTarget) : node.qualifiedName());
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = (Node) facetTarget.container();
		return container.name().toLowerCase() + "." + node.qualifiedName();
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.container();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.container();
		return null;
	}

	public static Object firstUpperCase(String name) {
		return new FormatterStore(Locale.ENGLISH).get("firstUpperCase").format(name);
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + ((Node) target.container()).name().toLowerCase() + DOT + target.qualifiedName();
	}

	public static String getQn(Facet facet, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + facet.type();
	}

	public static String getJavaQN(String generatedLanguage, NodeContainer container) {
		if (container instanceof Node) {
			Node node = (Node) container;
			final FacetTarget facet = isInFacet(node);
			String aPackage = generatedLanguage.toLowerCase();
			return aPackage + DOT + (facet != null ?
				composeInFacetTargetQN(node, facet).replace(".", "$").replaceFirst("\\$", ".") :
				Format.javaValidName().format(node.qualifiedName()).toString().replace(".", "$"));
		} else if (container instanceof FacetTarget) {
			FacetTarget facetTarget = (FacetTarget) container;
			String aPackage = NameFormatter.composeMorphPackagePath(facetTarget, generatedLanguage);
			return aPackage + DOT + Format.javaValidName().format(((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name());
		} else return "";
	}

	private static FacetTarget isInFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null && !(container instanceof FacetTarget))
			container = container.container();
		return container != null ? (FacetTarget) container : null;
	}

	public static String capitalize(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}
