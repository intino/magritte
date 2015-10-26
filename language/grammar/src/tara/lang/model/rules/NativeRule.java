package tara.lang.model.rules;

import tara.lang.model.Rule;

public class NativeRule implements Rule<String> {

	private final String interfaceClass;
	private String signature;
	private String language;

	public NativeRule(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public NativeRule(String interfaceClass, String signature) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
	}

	public NativeRule(String interfaceClass, String signature, String language) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
		this.language = language;
	}

	@Override
	public boolean accept(String value) {
		return true;
	}

	public String getSignature() {
		return signature;
	}

	public String interfaceClass() {
		return interfaceClass;
	}

	public String getLanguage() {
		return language;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
