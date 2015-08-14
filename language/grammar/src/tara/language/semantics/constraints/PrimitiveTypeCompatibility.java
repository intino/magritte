package tara.language.semantics.constraints;

import tara.language.model.EmptyNode;
import tara.language.model.Primitives;

import java.util.AbstractMap;

public class PrimitiveTypeCompatibility {

	private PrimitiveTypeCompatibility() {
	}

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
		return type.equalsIgnoreCase(Primitives.FILE) && inferredType.equalsIgnoreCase(Primitives.STRING);
	}

	private static boolean nativeOrEmptyInfersNative(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.NATIVE) && (inferredType.equalsIgnoreCase(Primitives.NATIVE) || inferredType.equalsIgnoreCase(Primitives.REFERENCE));
	}

	private static boolean stringInfersString(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.STRING) && (inferredType.equalsIgnoreCase(Primitives.STRING));
	}

	private static boolean stringOrEmptyInfersReference(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.REFERENCE) && (inferredType.equalsIgnoreCase(Primitives.STRING) || inferredType.equalsIgnoreCase(Primitives.REFERENCE));
	}

	private static boolean integerNaturalDoubleInfersRatio(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.RATIO) && (inferredType.equalsIgnoreCase(Primitives.INTEGER) || inferredType.equalsIgnoreCase(Primitives.NATURAL) || inferredType.equalsIgnoreCase(Primitives.DOUBLE) || inferredType.equalsIgnoreCase(Primitives.NATIVE));
	}

	private static boolean stringInfersDate(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.DATE) && inferredType.equalsIgnoreCase(Primitives.STRING);
	}

	private static boolean doubleNaturalIntegerInfersMeasure(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.MEASURE) && (inferredType.equalsIgnoreCase(Primitives.DOUBLE) || inferredType.equalsIgnoreCase(Primitives.INTEGER) || inferredType.equalsIgnoreCase(Primitives.NATURAL));
	}

	private static boolean integerOrNaturalInfersDouble(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.DOUBLE) && (inferredType.equalsIgnoreCase(Primitives.INTEGER) || inferredType.equalsIgnoreCase(Primitives.NATURAL) || inferredType.equalsIgnoreCase(Primitives.NATIVE));
	}

	private static boolean naturalInfersInteger(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.INTEGER) && (inferredType.equalsIgnoreCase(Primitives.NATURAL) || inferredType.equalsIgnoreCase(Primitives.INTEGER) || inferredType.equalsIgnoreCase(Primitives.NATIVE));
	}

	private static boolean naturalInfersNatural(String type, String inferredType) {
		return type.equalsIgnoreCase(Primitives.NATURAL) && (inferredType.equalsIgnoreCase(Primitives.NATURAL) || inferredType.equalsIgnoreCase(Primitives.NATIVE));
	}

	public static String inferType(Object value) {
		if (value instanceof String) return Primitives.STRING;
		else if (value instanceof Double) return Primitives.DOUBLE;
		else if (value instanceof Boolean) return Primitives.BOOLEAN;
		else if (value instanceof Integer) return (Integer) value < 0 ? Primitives.INTEGER : Primitives.NATURAL;
		else if (value instanceof Primitives.Expression) return Primitives.NATIVE;
		else if (value instanceof AbstractMap.SimpleEntry) return Primitives.TUPLE;
		else if (value != null && value instanceof EmptyNode) return Primitives.REFERENCE;
		return "";
	}
}
