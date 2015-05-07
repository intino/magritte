package siani.tara.compiler.codegeneration;


import org.siani.itrules.Formatter;

public class Format {

	protected static final String DOT = ".";


	public static Formatter string() {
		return new StringFormatter();
	}

	public static Formatter reference() {
		return new Formatter() {
			@Override
			public Object format(Object value) {
				java.lang.String val = value.toString();
				if (!val.contains(DOT)) return val.substring(0, 1).toUpperCase() + val.substring(1);
				return val;
			}
		};
	}

	public static Formatter date() {
		return new Formatter() {
			@Override
			public Object format(Object value) {
				java.lang.String val = value.toString();
				if (!val.contains("/")) return value;
				return val.replace("/", ", ");
			}
		};
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
