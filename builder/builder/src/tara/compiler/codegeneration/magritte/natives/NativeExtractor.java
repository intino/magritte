package tara.compiler.codegeneration.magritte.natives;

public class NativeExtractor {

	private final String returnValue;
	private final String methodName;
	private final String parameters;

	public NativeExtractor(String methodSignature) {
		this.returnValue = getReturn(clean(methodSignature));
		this.methodName = getMethod(clean(methodSignature));
		this.parameters = getParameters(clean(methodSignature));
	}

	private String clean(String methodSignature) {
		return methodSignature.trim().replaceAll("\\s+", " ").replace(" (", "(");
	}

	private static String getReturn(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		final String returnValue = methodSignature.split(" (\\S)*\\(")[0];
		return returnValue.startsWith("public ") ? returnValue.replaceFirst("public ", "") : returnValue;
	}

	private String getMethod(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		String[] substring = methodSignature.substring(0, methodSignature.indexOf("(")).split(" ");
		return substring[substring.length - 1];
	}

	private static String getParameters(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		return methodSignature.substring(methodSignature.indexOf("(") + 1, methodSignature.length() - 1);
	}


	public String returnValue() {
		return returnValue;
	}

	public String methodName() {
		return methodName;
	}

	public String parameters() {
		return parameters;
	}

}
