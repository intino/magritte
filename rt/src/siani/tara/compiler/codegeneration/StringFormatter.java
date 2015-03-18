package siani.tara.compiler.codegeneration;


import org.siani.itrules.formatter.Formatter;

public class StringFormatter implements Formatter {
	@Override
	public Object format(Object value) {
		String val = value.toString().trim();
		if (val.isEmpty()) return val;
		if (val.startsWith("\n") || val.startsWith("---")) return transformMultiLineString((String) value);
		return val;
	}

	private String transformMultiLineString(String value) {
		String val = value.replace("\r", "");
		int i = value.indexOf('-');
		String indent = value.substring(0, i).replace("\t", "    ");
		val = val.replace(indent, "\n").trim();
		if (val.startsWith("---")) {
			val = val.replaceAll("----+", "").trim();
		}
		return val.replaceAll("\n\n+","\n").replace("\n", "\" +\n\"").replace("\"\"","");

	}
}
