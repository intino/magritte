package monet.tara.compiler.codegeneration.render;

public class RenderUtils {

	public static String underscoreToCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return camelCaseString;
	}

	public static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
