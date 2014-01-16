package monet.tara.intellij.plugingeneration.render;

/**
 * Created by oroncal on 15/01/14.
 */
public class RenderUtils {

	public static String UnderscoreToCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return camelCaseString;
	}

	public static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() +
			s.substring(1);
	}
}
