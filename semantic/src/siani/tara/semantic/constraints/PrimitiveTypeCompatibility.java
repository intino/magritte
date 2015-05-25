package siani.tara.semantic.constraints;

public class PrimitiveTypeCompatibility {

	private static final String EMPTY_NODE = "emptyNode";

	public static boolean checkCompatiblePrimitives(String type, String inferredType) {
		return type.equals(inferredType)
			|| naturalInfersInteger(type, inferredType)
			|| integerOrNaturalInfersDouble(type, inferredType)
			|| doubleNaturalIntegerInfersMeasure(type, inferredType)
			|| stringInfersDate(type, inferredType) || integerNaturalDoubleInfersRatio(type, inferredType)
			|| stringOrEmptyInfersNative(type, inferredType)
			|| stringOrEmptyInfersReference(type, inferredType)
			|| stringInfersFile(type, inferredType);
	}

	private static boolean stringInfersFile(String type, String inferredType) {
		return type.equalsIgnoreCase("file") && inferredType.equalsIgnoreCase("string");
	}

	private static boolean stringOrEmptyInfersNative(String type, String inferredType) {
		return type.equalsIgnoreCase("native") && (inferredType.equalsIgnoreCase("string") || inferredType.equalsIgnoreCase(EMPTY_NODE));
	}

	private static boolean stringOrEmptyInfersReference(String type, String inferredType) {
		return type.equalsIgnoreCase("reference") && (inferredType.equalsIgnoreCase("string") || inferredType.equalsIgnoreCase(EMPTY_NODE));
	}

	private static boolean integerNaturalDoubleInfersRatio(String type, String inferredType) {
		return type.equalsIgnoreCase("ratio") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural") || inferredType.equalsIgnoreCase("double"));
	}

	private static boolean stringInfersDate(String type, String inferredType) {
		return type.equalsIgnoreCase("date") && inferredType.equalsIgnoreCase("string");
	}

	private static boolean doubleNaturalIntegerInfersMeasure(String type, String inferredType) {
		return type.equalsIgnoreCase("measure") && (inferredType.equalsIgnoreCase("double") || inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural"));
	}

	private static boolean integerOrNaturalInfersDouble(String type, String inferredType) {
		return type.equalsIgnoreCase("double") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural"));
	}

	private static boolean naturalInfersInteger(String type, String inferredType) {
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
