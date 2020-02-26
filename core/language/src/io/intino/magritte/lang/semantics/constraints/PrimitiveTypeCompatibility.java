package io.intino.magritte.lang.semantics.constraints;

import io.intino.magritte.lang.model.EmptyNode;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Primitive;

import java.io.File;

import static io.intino.magritte.lang.model.Primitive.*;

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
				|| integerInfersLong(type, inferredType)
				|| stringFunctionOrEmptyInfersDate(type, inferredType)
				|| stringFunctionOrEmptyInfersInstant(type, inferredType)
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
		return (inferredType.equals(BOOLEAN) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(BOOLEAN);
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

	private static boolean stringFunctionOrEmptyInfersInstant(Primitive type, Primitive inferredType) {
		return (inferredType.equals(STRING) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(INSTANT);
	}

	private static boolean integerInfersDouble(Primitive type, Primitive inferredType) {
		return (inferredType.equals(INTEGER) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(DOUBLE);
	}

	private static boolean integerInfersInteger(Primitive type, Primitive inferredType) {
		return (inferredType.equals(INTEGER) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(INTEGER);
	}

	private static boolean integerInfersLong(Primitive type, Primitive inferredType) {
		return (inferredType.equals(INTEGER) || inferredType.equals(FUNCTION) || inferredType.equals(EMPTY)) && type.equals(LONG);
	}

	private static boolean referenceInfersWord(Primitive type, Primitive inferredType) {
		return type.equals(WORD) && inferredType.equals(REFERENCE);
	}

	public static Primitive inferType(Object value) {
		if (value == null || value instanceof EmptyNode) return EMPTY;
		if (value instanceof String) return STRING;
		if (value instanceof Reference || value instanceof Node) return REFERENCE;
		if (value instanceof Double) return DOUBLE;
		if (value instanceof Boolean) return BOOLEAN;
		if (value instanceof Integer) return INTEGER;
		if (value instanceof Long) return LONG;
		if (value instanceof File) return RESOURCE;
		if (value instanceof Expression) return FUNCTION;
		if (value instanceof MethodReference) return FUNCTION;
		return null;
	}
}
