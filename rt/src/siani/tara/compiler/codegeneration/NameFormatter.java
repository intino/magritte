package siani.tara.compiler.codegeneration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameFormatter {

	public static final String MAGRITTE_PATH = "magritte";
	public static final String MORPH_PATH = "morphs";
	public static final String BOX_PATH = "editors";
	public static final String JAVA = "java";

	public static String composeMorphPackagePath(String box) {
		String[] split = box.split("\\.");
		if (split.length <= 3) return "";
		String aPackage = "";
		for (int i = 2; i < split.length - 1; i++)
			aPackage += i < split.length - 2 ? split[i] + "." : camelCase(split[i], " ");
		return aPackage;
	}

	public static String buildMorphPath(String box) {
		String[] split = box.split("\\.");
		if (split.length <= 3) return "";
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(split).subList(2, split.length - 1));
		list.add(camelCase(split[split.length - 1], " "));
		String aPackage = "";
		for (String s : list) aPackage += "." + s;
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

	public static String getMorphPath(String projectName, String separator) {
		return projectName + separator + MORPH_PATH + separator + JAVA;
	}

	public static String getBoxPath(String separator) {
		return MAGRITTE_PATH + separator + BOX_PATH;
	}

	public static String buildFileName(String file, String model) {
		return camelCase(model.replace(".", "_") + "_" +
			file.substring(file.lastIndexOf(File.separator) + 1, file.lastIndexOf(".")), "_") + "Box";
	}
}
