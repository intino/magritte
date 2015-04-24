package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String BOX_PATH = "store";
	private static final String DSL = "dsl";
	public static final String DOT = ".";

	private NameFormatter() {
	}

	public static String composeMorphPackagePath(Node node, Locale locale, String module) {
		if (!node.isSub())
			return module.toLowerCase();
		String aPackage = "";
		Node parent = node.getParent();
		Inflector inflector = InflectorFactory.getInflector(locale);
		while (parent != null) {
			aPackage = inflector.plural(parent.getName()) + (!aPackage.isEmpty() ? DOT + aPackage : "");
			parent = parent.isSub() ? parent.getParent() : null;
		}
		return module.toLowerCase() + DOT + aPackage.toLowerCase();
	}

	public static String composeMorphPackagePath(FacetTarget target, Locale locale, String module) {
		Inflector inflector = InflectorFactory.getInflector(locale);
		return (module.toLowerCase() + DOT + inflector.plural(((Node) target.getContainer()).getName())).toLowerCase();
	}

	public static String buildMorphPath(String morph) {
		String[] split = morph.split("\\.");
		if (split.length <= 3) return "";
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(split).subList(2, split.length - 1));
		list.add(camelCase(split[split.length - 1], " "));
		String aPackage = "";
		for (String s : list) aPackage += DOT + s;
		return aPackage.substring(1);
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
}
