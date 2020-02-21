package io.intino.tara.compiler.codegeneration.magritte.stash;

import io.intino.tara.io.Variable;
import io.intino.tara.lang.model.Primitive;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

class VariableFactory {

	private static Map<Primitive, Class<? extends Variable>> variableMap = new HashMap();

	static {
		variableMap.put(Primitive.INTEGER, Variable.Integer.class);
		variableMap.put(Primitive.DOUBLE, Variable.Double.class);
		variableMap.put(Primitive.BOOLEAN, Variable.Boolean.class);
		variableMap.put(Primitive.LONG, Variable.Long.class);
		variableMap.put(Primitive.STRING, Variable.String.class);
		variableMap.put(Primitive.RESOURCE, Variable.Resource.class);
		variableMap.put(Primitive.REFERENCE, Variable.Reference.class);
		variableMap.put(Primitive.WORD, Variable.Word.class);
		variableMap.put(Primitive.FUNCTION, Variable.Function.class);
		variableMap.put(Primitive.OBJECT, Variable.Object.class);
		variableMap.put(Primitive.DATE, Variable.Date.class);
		variableMap.put(Primitive.INSTANT, Variable.Instant.class);
		variableMap.put(Primitive.TIME, Variable.Time.class);
	}

	public static Variable get(Primitive primitive) {
		try {
			return variableMap.get(primitive).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
		}
		return null;
	}
}
