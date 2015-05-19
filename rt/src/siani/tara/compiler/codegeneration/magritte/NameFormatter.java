package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.engine.formatters.PluralInflector;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;

import java.io.File;
import java.util.Locale;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String BOX_PATH = "boxes";
	private static final String DSL = "dsl";
	public static final String DOT = ".";

	private NameFormatter() {
	}

	public static String composeMorphPackagePath(Node node, Locale locale, String generatedLanguage) {
		if (!node.isSub()) return generatedLanguage.toLowerCase();
		String aPackage = "";
		Node parent = node.getParent();
		PluralInflector inflector = new PluralFormatter(locale).getInflector();
		while (parent != null) {
			aPackage = inflector.plural(parent.getName()) + (!aPackage.isEmpty() ? DOT + aPackage : "");
			parent = parent.isSub() ? parent.getParent() : null;
		}
		return generatedLanguage.toLowerCase() + DOT + aPackage.toLowerCase();
	}

	public static String composeMorphPackagePath(FacetTarget target, Locale locale, String module) {
		PluralInflector inflector = new PluralFormatter(locale).getInflector();
		return (module.toLowerCase() + DOT + inflector.plural(((Node) target.getContainer()).getName())).toLowerCase();
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

	public static String getBoxPath(String separator) {
		return MAGRITTE_PATH + separator + DSL;
	}

	public static String buildFileName(String file) {
		return camelCase(file.substring(file.lastIndexOf(File.separator) + 1, file.lastIndexOf(DOT)), "_");
	}

	public static String createNativeReference(String qualifiedName, String variable) {
		qualifiedName = qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "").replace(".", "_");
		return qualifiedName + "_" + variable;
	}
}
