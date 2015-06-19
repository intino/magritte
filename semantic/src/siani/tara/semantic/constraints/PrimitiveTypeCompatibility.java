package siani.tara.semantic.constraints;

import siani.tara.semantic.model.Primitives;

import static siani.tara.semantic.model.Primitives.*;

public class PrimitiveTypeCompatibility {

	public static boolean checkCompatiblePrimitives(String type, String inferredType) {
		return type.equals(inferredType)
			|| naturalInfersInteger(type, inferredType)
			|| stringInfersString(type, inferredType)
			|| integerOrNaturalInfersDouble(type, inferredType)
			|| doubleNaturalIntegerInfersMeasure(type, inferredType)
			|| stringInfersDate(type, inferredType) || integerNaturalDoubleInfersRatio(type, inferredType)
			|| nativeOrEmptyInfersNative(type, inferredType)
			|| stringOrEmptyInfersReference(type, inferredType)
			|| stringInfersFile(type, inferredType)
			|| naturalInfersNatural(type, inferredType);
	}

	private static boolean stringInfersFile(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.FILE) && inferredType.equalsIgnoreCase(STRING);
	}

	private static boolean nativeOrEmptyInfersNative(String type, String inferredType) {
		return type.equalsIgnoreCase(NATIVE) && (inferredType.equalsIgnoreCase(NATIVE)|| inferredType.equalsIgnoreCase(REFERENCE)) ;
	}

	private static boolean stringInfersString(String type, String inferredType) {
		return type.equalsIgnoreCase(STRING) && (inferredType.equalsIgnoreCase(STRING));
	}

	private static boolean stringOrEmptyInfersReference(String type, String inferredType) {
		return type.equalsIgnoreCase(REFERENCE) && (inferredType.equalsIgnoreCase(STRING) || inferredType.equalsIgnoreCase(REFERENCE));
	}

	private static boolean integerNaturalDoubleInfersRatio(String type, String inferredType) {
		return type.equalsIgnoreCase(RATIO) && (inferredType.equalsIgnoreCase(INTEGER) || inferredType.equalsIgnoreCase(NATURAL) || inferredType.equalsIgnoreCase(DOUBLE) || inferredType.equalsIgnoreCase(NATIVE));
	}

	private static boolean stringInfersDate(String type, String inferredType) {
		return type.equalsIgnoreCase(DATE) && inferredType.equalsIgnoreCase(STRING);
	}

	private static boolean doubleNaturalIntegerInfersMeasure(String type, String inferredType) {
		return type.equalsIgnoreCase(MEASURE) && (inferredType.equalsIgnoreCase(DOUBLE) || inferredType.equalsIgnoreCase(INTEGER) || inferredType.equalsIgnoreCase(NATURAL));
	}

	private static boolean integerOrNaturalInfersDouble(String type, String inferredType) {
		return type.equalsIgnoreCase(DOUBLE) && (inferredType.equalsIgnoreCase(INTEGER) || inferredType.equalsIgnoreCase(NATURAL) || inferredType.equalsIgnoreCase(NATIVE));
	}

	private static boolean naturalInfersInteger(String type, String inferredType) {
		return type.equalsIgnoreCase(INTEGER) && (inferredType.equalsIgnoreCase(NATURAL) || inferredType.equalsIgnoreCase(INTEGER) || inferredType.equalsIgnoreCase(NATIVE));
	}

	private static boolean naturalInfersNatural(String type, String inferredType) {
		return type.equalsIgnoreCase(NATURAL) && (inferredType.equalsIgnoreCase(NATURAL) || inferredType.equalsIgnoreCase(NATIVE));
	}

	public static String inferType(Object value) {
		if (value instanceof String) return Primitives.STRING;
		else if (value instanceof Double) return Primitives.DOUBLE;
		else if (value instanceof Boolean) return Primitives.BOOLEAN;
		else if (value instanceof Integer) return (Integer) value < 0 ? INTEGER : NATURAL;
		else if (value instanceof Primitives.Expression) return NATIVE;
		else if (value != null && value.getClass().getSimpleName().equals("EmptyNode")) return REFERENCE;
		return "";
	}
}
