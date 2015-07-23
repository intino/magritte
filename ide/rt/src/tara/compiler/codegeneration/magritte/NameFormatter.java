package tara.compiler.codegeneration.magritte;

import org.siani.itrules.engine.FormatterStore;
import tara.compiler.codegeneration.Format;
import tara.language.model.Facet;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

import java.io.File;
import java.util.Locale;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String BOX_PATH = "ontology";
	public static final String DOT = ".";
	private static final String DSL = "dsl";

	private NameFormatter() {
	}

	public static String composeMorphPackagePath(FacetTarget target, String generatedLanguage) {
		return (generatedLanguage.toLowerCase() + DOT + ((Node) target.container()).name()).toLowerCase();
	}

	public static String getQn(Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (facetTarget == null && !node.isFacet())
			return generatedLanguage.toLowerCase() + DOT + node.qualifiedName();
		else if (facetTarget != null) {
			return generatedLanguage.toLowerCase() + DOT + composeInFacetTargetQN(node, facetTarget);
		} else
			return generatedLanguage.toLowerCase() + DOT + node.name().toLowerCase() + DOT + node.qualifiedName();
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = (Node) facetTarget.container();
		return container.name().toLowerCase() + "." + capitalize(container.name()) + "_" + capitalize(facetTarget.targetNode().name()) + DOT + node.qualifiedName();
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
		return generatedLanguage.toLowerCase() + DOT + target.targetNode().qualifiedName();
	}

	public static String getQnOfFacet(Node node) {
		return node.name().toLowerCase() + DOT + capitalize(node.name());
	}

	public static String getQn(Facet facet, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + facet.type();
	}

	public static String camelCase(String value, String c) {
		String[] parts = value.split(c);
		String caseString = "";
		for (String part : parts)
			caseString = caseString + capitalize(part);
		return caseString;
	}

	public static String getJavaQN(String generatedLanguage, NodeContainer container) {
		if (container instanceof Node) {
			Node node = (Node) container;
			String aPackage = generatedLanguage.toLowerCase() + (node.isFacet() ? DOT + node.name().toLowerCase() : "");
			return aPackage + DOT + Format.javaValidName().format(node.name()).toString();
		} else if (container instanceof FacetTarget) {
			FacetTarget facetTarget = (FacetTarget) container;
			String aPackage = NameFormatter.composeMorphPackagePath(facetTarget, generatedLanguage);
			aPackage + DOT + Format.javaValidName().format(((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name()
		}
	}

	public static String capitalize(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String getBoxUnitPath(String separator) {
		return MAGRITTE_PATH + separator + BOX_PATH;
	}

	public static String getBoxDSLPath(String separator) {
		return MAGRITTE_PATH + separator + DSL;
	}

	public static String buildFileName(String file) {
		return camelCase(file.substring(file.lastIndexOf(File.separator) + 1, file.lastIndexOf(DOT)), "_");
	}

	public static String cleanNativeReference(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "").replace(".", "_");
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}
