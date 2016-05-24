package tara.lang.model.rules.variable;

import tara.lang.model.Primitive.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NativeRule implements VariableRule<Expression> {

	private String interfaceClass;
	private String signature;
	private List<String> imports = new ArrayList<>();

	public NativeRule(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public NativeRule(String interfaceClass, String signature) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
	}

	public NativeRule(String interfaceClass, String signature, List<String> imports) {
		this.interfaceClass = interfaceClass;
		this.signature = signature;
		this.imports.addAll(imports);
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

	public void interfaceClass(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public void signature(String signature) {
		this.signature = signature;
	}

	public List<String> imports() {
		return new ArrayList<>(imports);
	}

	public void imports(List<String> imports) {
		this.imports.addAll(imports.stream().filter(s -> !s.isEmpty() && !this.imports.contains(s)).collect(Collectors.toList()));
	}

	@Override
	public String toString() {
		return "NativeRule{" + interfaceClass + '}';
	}
}
