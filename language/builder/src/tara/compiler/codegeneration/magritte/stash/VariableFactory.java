package tara.compiler.codegeneration.magritte.stash;

import tara.io.Variable;
import tara.lang.model.Primitive;

import java.util.HashMap;
import java.util.Map;

public class VariableFactory {

	private static Map<Primitive, Class<? extends tara.io.Variable>> variableMap = new HashMap();

	static {
		variableMap.put(Primitive.INTEGER, Variable.Integer.class);
		variableMap.put(Primitive.DOUBLE, Variable.Double.class);
		variableMap.put(Primitive.BOOLEAN, Variable.Boolean.class);
		variableMap.put(Primitive.STRING, Variable.String.class);
		variableMap.put(Primitive.RESOURCE, Variable.Resource.class);
		variableMap.put(Primitive.REFERENCE, Variable.Reference.class);
		variableMap.put(Primitive.WORD, Variable.Word.class);
		variableMap.put(Primitive.FUNCTION, Variable.Function.class);
		variableMap.put(Primitive.DATE, Variable.Date.class);
		variableMap.put(Primitive.TIME, Variable.Time.class);
	}

	public static tara.io.Variable get(Primitive primitive) {
		try {
			return variableMap.get(primitive).newInstance();
		} catch (InstantiationException | IllegalAccessException ignored) {
		}
		return null;
	}
}
