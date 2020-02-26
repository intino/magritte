package io.intino.magritte.compiler.codegeneration;


import io.intino.itrules.Formatter;
import io.intino.itrules.Template;
import io.intino.magritte.compiler.codegeneration.magritte.JavaKeywords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Format {
	protected static final String DOT = ".";

	private Format() {
	}

	public static Template customize(Template template) {
		template.add("string", string());
		template.add("reference", reference());
		template.add("toCamelCase", toCamelCase());
		template.add("snakeCaseToCamelCase", snakeCaseToCamelCase());
		template.add("withDollar", withDollar());
		template.add("noPackage", noPackage());
		template.add("key", key());
//		template.add("returnType", (trigger, type) -> trigger.frame().frames("returnType").next().value().equals(type));
		template.add("WithoutType", nativeParameterWithoutType());
		template.add("javaValidName", javaValidName());
		template.add("javaValidWord", javaValidWord());
		template.add("withoutGeneric", withoutGeneric());
		return template;
	}

	public static Formatter string() {
		return new StringFormatter();
	}

	public static Formatter reference() {
		return value -> {
			String val = value.toString();
			return !val.contains(DOT) ? referenceFormat(val) : firstLowerCase(javaValidName().format(val).toString()).replace(":", "").replace("#", "").replace("$", ".");
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
		return io.intino.itrules.formatters.StringFormatters.get(Locale.getDefault()).get("firstlowercase").format(val).toString();
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

	private static Formatter snakeCaseToCamelCase() {
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
		StringBuilder caseString = new StringBuilder(parts[0]);
		for (int i = 1; i < parts.length; i++) caseString.append(capitalize(parts[i]));
		return caseString.toString();
	}

	public static Formatter javaValidWord() {
		return s -> {
			final String value = s.toString();
			return JavaKeywords.isKeyword(value) ? value + "$" : value;
		};
	}

	public static Formatter javaClassNames() {
		return s -> {
			final List<String> names = Arrays.asList(s.toString().split("\\$"));
			final List<String> collect = names.stream().map(n -> firstUpperCase().format(javaValidName().format(n)).toString()).collect(toList());
			return String.join("$", collect);
		};
	}

	private static Formatter withoutGeneric() {
		return s -> {
			final String value = s.toString();
			return value.contains("<") ? value.substring(0, value.indexOf("<")) : value;
		};
	}

	private static String toCamelCase(String value, String regex) {
		if (value.isEmpty()) return "";
		String[] parts = value.split(regex);
		if (parts.length == 1) return value.substring(0, 1).toUpperCase() + value.substring(1);
		return Arrays.stream(parts).map(Format::capitalize).collect(Collectors.joining());
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
		return list.isEmpty() ? new String[]{parameters.trim()} : list.toArray(new String[0]);
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
		return io.intino.itrules.formatters.StringFormatters.get(Locale.getDefault()).get("capitalize").format(value).toString();
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
		return io.intino.itrules.formatters.StringFormatters.get(Locale.getDefault()).get("firstuppercase");
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
