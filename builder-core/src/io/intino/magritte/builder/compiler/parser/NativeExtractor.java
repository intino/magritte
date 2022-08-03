package io.intino.magritte.builder.compiler.parser;

import java.util.Arrays;

public class NativeExtractor {

	private final String returnType;
	private final String methodName;
	private final String parameters;
	private final String[] exceptions;

	public NativeExtractor(String methodSignature) {
		this.returnType = returnType(clean(methodSignature));
		this.methodName = methodName(clean(methodSignature));
		this.parameters = getParameters(clean(methodSignature));
		this.exceptions = exceptions(clean(methodSignature));
	}

	private String clean(String methodSignature) {
		return methodSignature.trim().replaceAll("\\s+", " ").replace(" (", "(");
	}

	private static String returnType(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		final String returnValue = methodSignature.split(" (\\S)*\\(")[0];
		return returnValue.startsWith("public ") ? returnValue.replaceFirst("public ", "") : returnValue;
	}

	private String methodName(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		String[] substring = methodSignature.substring(0, methodSignature.indexOf("(")).split(" ");
		return substring[substring.length - 1];
	}

	private String[] exceptions(String signature) {
		final String token = " throws ";
		if (signature == null || signature.isEmpty() || !signature.contains(token))
			return new String[0];
		String[] exceptions = signature.substring(signature.indexOf(token) + token.length()).split(",");
		return Arrays.stream(exceptions).map(String::trim).toArray(String[]::new);
	}

	private static String getParameters(String methodSignature) {
		if (methodSignature == null || methodSignature.isEmpty()) return "";
		return methodSignature.substring(methodSignature.indexOf("(") + 1, methodSignature.lastIndexOf(")"));
	}

	public String returnType() {
		return returnType;
	}

	public String methodName() {
		return methodName;
	}

	public String parameters() {
		return parameters;
	}

	public String[] exceptions() {
		return exceptions;
	}

}
