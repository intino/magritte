package siani.tara.semantic.constraints;

public class PrimitiveTypeCompatibility {

	private static final String EMPTY_NODE = "emptyNode";

	public static boolean checkCompatiblePrimitives(String type, String inferredType) {
		return type.equals(inferredType)
			|| integerInferNatural(type, inferredType)
			|| doubleInfersIntegerOrNatural(type, inferredType)
			|| measureInfersDoubleNaturalInteger(type, inferredType)
			|| dateInfersString(type, inferredType) || ratioInfersIntegerNaturalDouble(type, inferredType)
			|| nativeInfersStringOrEmpty(type, inferredType)
			|| fileInfersString(type, inferredType);
	}

	private static boolean fileInfersString(String type, String inferredType) {
		return type.equalsIgnoreCase("file") && inferredType.equalsIgnoreCase("string");
	}

	private static boolean nativeInfersStringOrEmpty(String type, String inferredType) {
		return type.equalsIgnoreCase("native") && (inferredType.equalsIgnoreCase("string") || inferredType.equalsIgnoreCase(EMPTY_NODE));
	}

	private static boolean ratioInfersIntegerNaturalDouble(String type, String inferredType) {
		return type.equalsIgnoreCase("ratio") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural") || inferredType.equalsIgnoreCase("double"));
	}

	private static boolean dateInfersString(String type, String inferredType) {
		return type.equalsIgnoreCase("date") && inferredType.equalsIgnoreCase("string");
	}

	private static boolean measureInfersDoubleNaturalInteger(String type, String inferredType) {
		return type.equalsIgnoreCase("measure") && (inferredType.equalsIgnoreCase("double") || inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural"));
	}

	private static boolean doubleInfersIntegerOrNatural(String type, String inferredType) {
		return type.equalsIgnoreCase("double") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural"));
	}

	private static boolean integerInferNatural(String type, String inferredType) {
		return type.equalsIgnoreCase("integer") && (inferredType.equalsIgnoreCase("natural") || inferredType.equalsIgnoreCase("integer"));
	}

	public static String inferType(Object value) {
		if (value instanceof String) return "string";
		else if (value instanceof Double) return "double";
		else if (value instanceof Boolean) return "boolean";
		else if (value instanceof Integer) return (Integer) value < 0 ? "integer" : "natural";
		else if (value != null && value.getClass().getSimpleName().equals("EmptyNode")) return EMPTY_NODE;
		return "";
	}
}
