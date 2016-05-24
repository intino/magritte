package tara.compiler.codegeneration.magritte.layer.templates.lazy;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class Getters_referenceTemplate extends Template {

	protected Getters_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Getters_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & reference & owner")), not(condition("type", "target")), not(condition("type", "concept")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public List<")).add(mark("type", "variableType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("() {\n\treturn new tara.magritte.utils.ReferenceList<>(")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(", ")).add(mark("type", "variableType")).add(literal(".class);\n}\n\npublic List<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.util.function.Predicate<")).add(mark("type", "reference")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Variable & reference & owner")), not(condition("type", "target")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("() {\n\treturn ")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(".node() == null ? null : ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(".node().as(")).add(mark("type", "reference")).add(literal(".class);\n}"))
		);
		return this;
	}
}