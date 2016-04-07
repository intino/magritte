package tara.lang.semantics.constraints;

import tara.lang.model.EmptyNode;
import tara.lang.model.Primitive;

import java.io.File;

import static tara.lang.model.Primitive.*;

public class PrimitiveTypeCompatibility {

	private PrimitiveTypeCompatibility() {
	}

	public static boolean checkCompatiblePrimitives(Primitive type, Primitive inferredType, boolean multiple) {
		return type.equals(inferredType)
			|| emptyInfersEmptyList(type, inferredType, multiple)
			|| integerInfersInteger(type, inferredType)
			|| booleanOrFunctionInfersBoolean(type, inferredType)
			|| nativeOrEmptyInfersObject(type, inferredType)
			|| stringInfersString(type, inferredType)
			|| integerInfersDouble(type, inferredType)
			|| stringFunctionOrEmptyInfersDate(type, inferredType)
			|| stringInfersTime(type, inferredType)
			|| nativeOrEmptyInfersNative(type, inferredType)
			|| emptyInfersReference(type, inferredType)
			|| referenceInfersWord(type, inferredType)
			|| stringOrEmptyInfersFile(type, inferredType);
	}

	private static boolean stringInfersTime(Primitive type, Primitive inferredType) {
		return (inferredType.equals(STRING) || inferredType.equals(EMPTY)) && type.equals(TIME);
	}

	private static boolean stringOrEmptyInfersFile(Primitive type, Primitive inferredType) {
		return (inferredType.equals(STRING) || inferredType.equals(EMPTY) || inferredType.equals(FUNCTION)) && type.equals(RESOURCE);
	}

	private static boolean booleanOrFunctionInfersBoolean(Primitive type, Primitive inferredType) {
		return (inferredType.equals(BOOLEAN) || inferredType.equals(FUNCTION)) && type.equals(BOOLEAN);
	}

	private static boolean nativeOrEmptyInfersObject(Primitive type, Primitive inferredType) {
		return (inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(OBJECT);
	}

	private static boolean stringInfersString(Primitive type, Primitive inferredType) {
		return (inferredType.equals(STRING) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(STRING);
	}

	private static boolean emptyInfersReference(Primitive type, Primitive inferredType) {
		return (inferredType.equals(EMPTY) || inferredType.equals(FUNCTION)) && type.equals(REFERENCE);
	}

	private static boolean nativeOrEmptyInfersNative(Primitive type, Primitive inferredType) {
		return (inferredType.equals(FUNCTION)) && type.equals(FUNCTION);
	}

	private static boolean emptyInfersEmptyList(Primitive type, Primitive inferredType, boolean multiple) {
		return inferredType.equals(EMPTY) && !type.equals(REFERENCE) && multiple;
	}

	private static boolean stringFunctionOrEmptyInfersDate(Primitive type, Primitive inferredType) {
		return (inferredType.equals(STRING) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(DATE);
	}

	private static boolean integerInfersDouble(Primitive type, Primitive inferredType) {
		return (inferredType.equals(INTEGER) || inferredType.equals(FUNCTION)) && type.equals(DOUBLE);
	}

	private static boolean integerInfersInteger(Primitive type, Primitive inferredType) {
		return (inferredType.equals(INTEGER) || inferredType.equals(FUNCTION)) && type.equals(INTEGER);
	}

	private static boolean referenceInfersWord(Primitive type, Primitive inferredType) {
		return type.equals(WORD) && inferredType.equals(REFERENCE);
	}

	public static Primitive inferType(Object value) {
		if (value instanceof String) return STRING;
		if (value instanceof Reference) return REFERENCE;
		else if (value instanceof Double) return DOUBLE;
		else if (value instanceof Boolean) return BOOLEAN;
		else if (value instanceof Integer) return INTEGER;
		else if (value instanceof File) return RESOURCE;
		else if (value instanceof Expression) return FUNCTION;
		else if (value instanceof MethodReference) return FUNCTION;
		else if (value == null || value instanceof EmptyNode) return EMPTY;
		return null;
	}
}
