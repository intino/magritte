package tara.lang.model.rules.variable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NativeObjectRule extends NativeRule {

	static Map<String, String> recognizedClasses = new HashMap<>();

	static {
		recognizedClasses.put("List", "java.util.List");
		recognizedClasses.put("ArrayList", "java.util.ArrayList");
		recognizedClasses.put("LinkedList", "java.util.LinkedList");
		recognizedClasses.put("Map", "java.util.Map");
		recognizedClasses.put("HashMap", "java.util.HashMap");
		recognizedClasses.put("LinkedHashMap", "java.util.LinkedHashMap");
		recognizedClasses.put("BufferedReader", "java.io.BufferedReader");
		recognizedClasses.put("InputStream", "java.io.InputStream");
		recognizedClasses.put("OutputStream", "java.io.OutputStream");
	}

	private final String type;

	public NativeObjectRule(String type, String language) {
		super("", "", Collections.emptyList(), language);
		this.type = type;
	}


	public String declaredType() {
		return type;
	}

	public String type() {
		final String[] split = type.split("<");
		String rest = type.substring(split[0].length());
		return recognizedClasses.containsKey(split[0]) ? recognizedClasses.get(split[0]) + rest : type;
	}


	@Override
	public String toString() {
		return "NativeObjectRule{" + type() + '}';
	}
}
