package monet.tara.compiler.codegeneration.render.grammarcomponents;

public class AttributeSimplifier {

	public static String reduce(String attributesImplicit) {
		String reducedAttributes = "";
		String[] attributes = attributesImplicit.split("\\|");
		for (String attribute : attributes)
			reducedAttributes = append(reducedAttributes, attribute);
		return deleteLastPipe(reducedAttributes);
	}

	private static String deleteLastPipe(String reducedAttributes) {
		if (!reducedAttributes.isEmpty())
			reducedAttributes = reducedAttributes.substring(0, reducedAttributes.length() - 3);
		return reducedAttributes;
	}

	private static String append(String reducedAttributes, String attribute) {
		if (attribute.contains("LEFT_SQUARE")) attribute = attribute.replaceFirst("LEFT_SQUARE", "LEFT_SQUARE ");
		if (!reducedAttributes.contains(attribute)) reducedAttributes += attribute + " | ";
		return reducedAttributes;
	}
}
