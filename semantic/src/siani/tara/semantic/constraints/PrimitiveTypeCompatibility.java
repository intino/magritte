package siani.tara.semantic.constraints;

public class PrimitiveTypeCompatibility {

	private static final String EMPTY_NODE = "emptyNode";

	public static boolean checkCompatiblePrimitives(String type, String inferredType) {
		if (type.equals(inferredType)) return true;
		if (type.equalsIgnoreCase("integer") && inferredType.equalsIgnoreCase("natural")) return true;
		if (type.equalsIgnoreCase("double") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural")))
			return true;
		if (type.equalsIgnoreCase("measure") && (inferredType.equalsIgnoreCase("double") || inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural")))
			return true;
		if (type.equalsIgnoreCase("date") && inferredType.equalsIgnoreCase("string"))
			return true;//TODO CHECK TEXT FORMAT
		if (type.equalsIgnoreCase("ratio") && (inferredType.equalsIgnoreCase("integer") || inferredType.equalsIgnoreCase("natural") || inferredType.equalsIgnoreCase("double")))
			return true;
		if (type.equalsIgnoreCase("native") && (inferredType.equalsIgnoreCase("string") || inferredType.equalsIgnoreCase(EMPTY_NODE)))
			return true;
		return type.equalsIgnoreCase("file") && inferredType.equalsIgnoreCase("string");
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
