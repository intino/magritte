package tara.templates.dynamicLayer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Getters_referenceTemplate extends Template {

	protected Getters_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Getters_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & reference")), not(condition("type", "definition")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public List<")).add(mark("type", "variableType")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firtLowercase")).add(literal(".stream().map(r -> r.declaration().as(")).add(mark("type", "variableType")).add(literal(".class)).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Variable & reference")), not(condition("type", "definition")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firtLowercase")).add(literal(".declaration().as(")).add(mark("type", "reference")).add(literal(".class);\n}"))
		);
		return this;
	}
}