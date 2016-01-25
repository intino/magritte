package tara.intellij.codeinsight.languageinjection.helpers;

import org.siani.itrules.engine.FormatterStore;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

import java.util.Locale;

public class QualifiedNameFormatter {

	public static final String DOT = ".";

	private QualifiedNameFormatter() {
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = facetTarget.owner();
		return container.name().toLowerCase() + "." + node.qualifiedName();
	}

	public static Object firstUpperCase(String name) {
		return new FormatterStore(Locale.ENGLISH).get("firstUpperCase").format(name);
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT + target.owner().qualifiedName();
	}

	public static String getQn(FacetTarget target, Node owner, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.owner().name().toLowerCase() + DOT + Format.qualifiedName().format(owner.qualifiedName()).toString();
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}
