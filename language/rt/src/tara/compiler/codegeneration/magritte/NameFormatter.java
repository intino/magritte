package tara.compiler.codegeneration.magritte;

import tara.compiler.model.Facet;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;

import java.io.File;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String BOX_PATH = "ontology";
	public static final String DOT = ".";
	private static final String DSL = "dsl";

	private NameFormatter() {
	}

	public static String composeMorphPackagePath(String language) {
		return language.toLowerCase();
	}

	public static String composeMorphPackagePath(FacetTarget target, String generatedLanguage) {
		return (generatedLanguage.toLowerCase() + DOT + ((Node) target.getContainer()).getName()).toLowerCase();
	}

	public static String getQn(Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (facetTarget == null && !node.isFacet())
			return composeMorphPackagePath(generatedLanguage) + DOT + node.getQualifiedName();
		else if (facetTarget != null) {
			return composeMorphPackagePath(generatedLanguage) + DOT + composeInFacetTargetQN(node, facetTarget);
		} else
			return composeMorphPackagePath(generatedLanguage) + DOT + node.getName().toLowerCase() + DOT + node.getQualifiedName();
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = (Node) facetTarget.getContainer();
		return container.getName().toLowerCase() + "." + capitalize(container.getName()) + "_" + capitalize(facetTarget.getTargetNode().getName()) + DOT + node.getQualifiedName();
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.getContainer();
		return null;
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return composeMorphPackagePath(generatedLanguage) + DOT + target.getTargetNode().getQualifiedName();
	}

	public static String getQnOfFacet(Node node) {
		return node.getName().toLowerCase() + DOT + capitalize(node.getName());
	}

	public static String getQn(Facet facet, String generatedLanguage) {
		return composeMorphPackagePath(generatedLanguage) + DOT + facet.getFacetType();
	}

	public static String camelCase(String value, String c) {
		String[] parts = value.split(c);
		String caseString = "";
		for (String part : parts)
			caseString = caseString + capitalize(part);
		return caseString;
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
