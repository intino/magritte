package siani.tara.compiler.codegeneration;

import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String MORPH_PATH = "morphs";
	public static final String BOX_PATH = "editors";
	public static final String JAVA = "java";
	public static final String DOT = ".";

	public static String composeMorphPackagePath(Model model, Node node) {
		if (!node.isSub())
			return getMorphPath(DOT);
		String aPackage = "";
		NodeObject parent = node.getObject().getParent();
		Inflector inflector = InflectorFactory.getInflector(model.getLanguage());
		while (parent != null) {
			aPackage = inflector.plural(parent.getName()) + aPackage;
			parent = parent.isSub() ? parent.getParent() : null;
		}
		return getMorphPath(DOT) + DOT + aPackage.toLowerCase();
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

	public static String camelCase(String value) {
		return camelCase(value, "_");
	}

	public static String camelCase(String value, String c) {
		String[] parts = value.split(c);
		String caseString = "";
		for (String part : parts)
			caseString = caseString + properCase(part);
		return caseString;
	}

	public static String properCase(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String getMorphPath(String separator) {
		return MAGRITTE_PATH + separator + MORPH_PATH;
	}

	public static String getBoxPath(String separator) {
		return MAGRITTE_PATH + separator + BOX_PATH;
	}

	public static String buildFileName(String file, String model) {
		return camelCase(model.replace(DOT, "_") + "_" +
			file.substring(file.lastIndexOf(File.separator) + 1, file.lastIndexOf(DOT)), "_") + "Box";
	}
}
