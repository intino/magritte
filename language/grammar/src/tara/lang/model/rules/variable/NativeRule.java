package tara.lang.model.rules.variable;

import tara.lang.model.Primitive.Expression;
import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class NativeRule implements Rule<Expression> {

	private final String interfaceClass;
	private String signature;
	private String language;
	private List<String> imports = new ArrayList<>();

	public NativeRule(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public NativeRule(String interfaceClass, String signature) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
	}

	public NativeRule(String interfaceClass, String signature, List<String> imports, String language) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
		this.imports = imports;
		this.language = language;
	}

	@Override
	public boolean accept(Expression value) {
		return true;
	}

	public String signature() {
		return signature;
	}

	public String interfaceClass() {
		return interfaceClass;
	}

	public String getLanguage() {
		return language;
	}

	public void signature(String signature) {
		this.signature = signature;
	}

	public void language(String language) {
		this.language = language;
	}

	public List<String> imports() {
		return imports;
	}

	public void imports(List<String> imports) {
		this.imports = imports;
	}

	@Override
	public String toString() {
		return "NativeRule{" + interfaceClass + '}';
	}
}
