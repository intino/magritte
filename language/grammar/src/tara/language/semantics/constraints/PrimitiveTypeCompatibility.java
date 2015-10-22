package tara.language.semantics.constraints;

import tara.language.model.EmptyNode;
import tara.language.model.Parameter;
import tara.language.model.Primitive;

import java.util.AbstractMap;

import static tara.language.model.Primitive.*;

public class PrimitiveTypeCompatibility {

	private PrimitiveTypeCompatibility() {
	}

	public static boolean checkCompatiblePrimitives(Primitive type, Primitive inferredType, boolean multiple) {
		return type.equals(inferredType)
			|| emptyInfersEmptyList(type, inferredType, multiple)
			|| naturalInfersInteger(type, inferredType)
			|| stringInfersString(type, inferredType)
			|| integerOrNaturalInfersDouble(type, inferredType)
			|| stringInfersDate(type, inferredType)
			|| stringInfersTime(type, inferredType)
			|| nativeOrEmptyInfersNative(type, inferredType)
			|| stringOrEmptyInfersReference(type, inferredType)
			|| stringInfersFile(type, inferredType);
	}

	private static boolean stringInfersTime(Primitive type, Primitive inferredType) {
		return type.equals(TIME) && inferredType.equals(STRING);
	}

	private static boolean stringInfersFile(Primitive type, Primitive inferredType) {
		return type.equals(FILE) && inferredType.equals(STRING);
	}

	private static boolean nativeOrEmptyInfersNative(Primitive type, Primitive inferredType) {
		return type.equals(NATIVE) && (inferredType.equals(NATIVE));
	}

	private static boolean stringInfersString(Primitive type, Primitive inferredType) {
		return type.equals(STRING) && (inferredType.equals(STRING));
	}

	private static boolean stringOrEmptyInfersReference(Primitive type, Primitive inferredType) {
		return type.equals(REFERENCE) && inferredType.equals(EMPTY);
	}

	private static boolean emptyInfersEmptyList(Primitive type, Primitive inferredType, boolean multiple) {
		return !type.equals(REFERENCE) && inferredType.equals(EMPTY) && multiple;
	}

	private static boolean stringInfersDate(Primitive type, Primitive inferredType) {
		return type.equals(DATE) && inferredType.equals(STRING);
	}

	private static boolean integerOrNaturalInfersDouble(Primitive type, Primitive inferredType) {
		return type.equals(DOUBLE) && (inferredType.equals(INTEGER) || inferredType.equals(NATIVE));
	}

	private static boolean naturalInfersInteger(Primitive type, Primitive inferredType) {
		return type.equals(INTEGER) && (inferredType.equals(INTEGER) || inferredType.equals(NATIVE));
	}

	public static Primitive inferType(Object value) {
		if (value instanceof String && !((String) value).startsWith(Parameter.REFERENCE_PREFIX)) return STRING;
		if (value instanceof String && ((String) value).startsWith(Parameter.REFERENCE_PREFIX)) return REFERENCE;
		else if (value instanceof Double) return DOUBLE;
		else if (value instanceof Boolean) return BOOLEAN;
		else if (value instanceof Integer) return INTEGER;
		else if (value instanceof Expression) return NATIVE;
		else if (value instanceof AbstractMap.SimpleEntry) return TUPLE;
		else if (value != null && value instanceof EmptyNode) return EMPTY;
		return null;
	}
}
