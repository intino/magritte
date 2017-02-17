package io.intino.tara.compiler.codegeneration;


import io.intino.tara.compiler.codegeneration.magritte.NamesValidator;
import org.siani.itrules.Formatter;
import org.siani.itrules.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Format {


	public static Template customize(Template template) {
		template.add("string", string());
		template.add("reference", reference());
		template.add("toCamelCase", toCamelCase());
		template.add("snakeCaseToCamelCase", snakeCaseToCamelCase());
		template.add("withDollar", withDollar());
		template.add("noPackage", noPackage());
		template.add("key", key());
		template.add("returnValue", (trigger, type) -> trigger.frame().frames("returnValue").next().value().equals(type));
		template.add("WithoutType", nativeParameterWithoutType());
		template.add("javaValidName", javaValidName());
		template.add("javaValidWord", javaValidWord());
		template.add("withoutGeneric", withoutGeneric());
		return template;
	}

	protected static final String DOT = ".";

	private Format() {
	}

	public static Formatter string() {
		return new StringFormatter();
	}

	public static Formatter reference() {
		return value -> {
			String val = value.toString();
			return !val.contains(DOT) ? referenceFormat(val) : firstLowerCase(javaValidName().format(val).toString()).replace(":", "").replace("#", "");
		};
	}

	public static Formatter qualifiedName() {
		return value -> {
			String val = value.toString();
			if (!val.contains(DOT)) return referenceFormat(val);
			else {
				final String[] split = val.split("\\.");
				String result = "";
				for (String name : split) result += "." + referenceFormat(name);
				return result.substring(1);
			}
		};
	}

	private static String firstLowerCase(String val) {
		return val.substring(0, 1).toLowerCase() + val.substring(1);
	}

	private static String referenceFormat(String val) {
		return javaValidName().format(val).toString().replace(":", "");
	}


	public static Formatter toCamelCase() {
		return s -> {
			String value = s.toString();
			if (value.isEmpty()) return "";
			if (value.contains("_")) value = value.toLowerCase();
			return snakeCaseToCamelCase().format(toCamelCase(value, "-"));
		};
	}

	public static Formatter snakeCaseToCamelCase() {
		return s -> {
			String value = s.toString();
			if (value.isEmpty()) return "";
			if (value.contains("_")) value = value.toLowerCase();
			return toCamelCase(value, "_");
		};
	}


	public static Formatter withDollar() {
		return s -> {
			final String value = s.toString();
			return value.replace(".", "$");
		};
	}

	public static Formatter noPackage() {
		return s -> {
			final String value = s.toString();
			final String[] split = value.split("\\.");
			String result = "";
			for (String word : split) {
				if (word.toLowerCase().equals(word)) continue;
				result += "." + word;
			}
			return result.isEmpty() ? result : result.substring(1);
		};
	}

	public static Formatter javaValidName() {
		return s -> {
			final String value = s.toString();
			return javaValidWord().format(toCamelCaseWithoutFirstChange(value, "-"));
		};
	}

	private static Object toCamelCaseWithoutFirstChange(String value, String regex) {
		if (value.isEmpty()) return "";
		String[] parts = value.split(regex);
		if (parts.length == 1) return value;
		String caseString = parts[0];
		for (int i = 1; i < parts.length; i++) caseString = caseString + capitalize(parts[i]);
		return caseString;
	}

	public static Formatter javaValidWord() {
		return s -> {
			final String value = s.toString();
			return NamesValidator.isKeyword(value) || NamesValidator.isTaraKeyword(value) ? value + "$" : value;
		};
	}

	public static Formatter javaClassNames() {
		return s -> {
			final List<String> names = Arrays.asList(s.toString().split("\\$"));
			final List<String> collect = names.stream().map(n -> firstUpperCase().format(javaValidName().format(n)).toString()).collect(toList());
			return String.join("$", collect);
		};
	}

	public static Formatter withoutGeneric() {
		return s -> {
			final String value = s.toString();
			return value.contains("<") ? value.substring(0, value.indexOf("<")) : value;
		};
	}

	private static String toCamelCase(String value, String regex) {
		if (value.isEmpty()) return "";
		String[] parts = value.split(regex);
		if (parts.length == 1) return value.substring(0, 1).toUpperCase() + value.substring(1);
		String caseString = "";
		for (String part : parts)
			caseString = caseString + capitalize(part);
		return caseString;
	}

	public static Formatter nativeParameterWithoutType() {
		return withType -> {
			String result = "";
			for (String parameter : split(withType.toString())) {
				String split = parameter.trim().substring(parameter.trim().lastIndexOf(" ") + 1);
				result += ", " + split;
			}
			return result.isEmpty() ? result : result.substring(2);
		};
	}

	public static String[] split(String parameters) {
		List<String> list = new ArrayList<>();
		List<Integer> commas = recollectCommas(parameters);
		for (int i = 0; i < commas.size(); i++) {
			list.add(parameters.substring(i == 0 ? 0 : commas.get(i - 1) + 1, commas.get(i)).trim());
		}
		if (!commas.isEmpty()) list.add(parameters.substring(commas.get(commas.size() - 1) + 1).trim());
		return list.isEmpty() ? new String[]{parameters.trim()} : list.toArray(new String[list.size()]);
	}

	private static List<Integer> recollectCommas(String parameters) {
		List<Integer> commas = new ArrayList<>();
		int anchor = 0;
		while (anchor >= 0) {
			anchor = parameters.indexOf(",", anchor + 1);
			if (anchor > 0 && !inType(parameters, last(commas), anchor)) commas.add(anchor);
		}
		return commas;
	}

	private static Integer last(List<Integer> commas) {
		return commas.isEmpty() ? 0 : commas.get(commas.size() - 1);
	}

	private static boolean inType(String parameters, int last, int anchor) {
		final boolean minor = parameters.substring(last, anchor).contains("<");
		final boolean greater = parameters.substring(last, anchor).contains(">");
		return minor != greater;
	}

	public static String capitalize(String value) {
		if (value.isEmpty()) return "";
		return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
	}

	private static Formatter key() {
		return value -> {
			try {
				Long.parseLong(String.valueOf(value));
				return "$(" + value.toString() + ")";
			} catch (NumberFormatException e) {
				return value;
			}
		};
	}

	public static Formatter firstUpperCase() {
		return (value) -> value.toString().isEmpty() ? "" : value.toString().substring(0, 1).toUpperCase() + value.toString().substring(1);
	}

	private static class StringFormatter implements Formatter {
		@Override
		public Object format(Object value) {
			java.lang.String val = value.toString().trim();
			if (val.isEmpty()) return val;
			if (val.startsWith("\n") || val.startsWith("---"))
				return transformMultiLineString((java.lang.String) value);
			return val;
		}

		private java.lang.String transformMultiLineString(java.lang.String value) {
			java.lang.String val = value.replace("\r", "");
			int i = value.indexOf('-');
			java.lang.String indent = value.substring(0, i).replace("\t", "    ");
			val = val.replace(indent, "\n").trim();
			if (val.startsWith("---")) {
				val = val.replaceAll("----+", "").trim();
			}
			return val.replaceAll("\n\n+", "\n").replace("\n", "\" +\n\"").replace("\"\"", "");

		}
	}
}
