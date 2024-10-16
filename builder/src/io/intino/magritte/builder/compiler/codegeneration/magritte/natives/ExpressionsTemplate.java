package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.*;

public class ExpressionsTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(allTypes("function")).output(literal("package ")).output(placeholder("workingPackage", "lowercase", "javaValidName")).output(literal(".natives")).output(expression().output(literal(".")).output(placeholder("package", "lowercase", "javaValidName"))).output(literal(";\n\n")).output(expression().output(placeholder("imports").multiple("\n"))).output(literal("\n\n/**")).output(placeholder("qn")).output(literal("#")).output(placeholder("file")).output(literal("#")).output(placeholder("line")).output(literal("#")).output(placeholder("column")).output(literal("**/\npublic class ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal("_")).output(placeholder("uid")).output(literal(" implements ")).output(expression().output(placeholder("scope", "lowercase", "javaValidName")).output(literal(".functions."))).output(placeholder("rule", "FirstUpperCase")).output(literal(", io.intino.magritte.framework.Function {\n\tprivate ")).output(placeholder("nativeContainer", "reference")).output(literal(" self;\n\n\t@Override\n\t")).output(placeholder("signature")).output(literal(" {\n\t\t")).output(placeholder("body")).output(literal("\n\t}\n\n\t@Override\n\tpublic void self(io.intino.magritte.framework.Layer context) {\n\t\tself = (")).output(placeholder("nativeContainer", "reference")).output(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends io.intino.magritte.framework.Layer> selfClass() {\n\t\treturn ")).output(placeholder("nativeContainer", "reference")).output(literal(".class;\n\t}\n}")));
		rules.add(rule().condition(allTypes("native")).output(literal("package ")).output(placeholder("workingPackage", "lowercase", "javaValidName")).output(literal(".natives")).output(expression().output(literal(".")).output(placeholder("package", "lowercase", "javaValidName"))).output(literal(";\n\n")).output(expression().output(placeholder("imports").multiple("\n"))).output(literal("\n\n/**")).output(placeholder("qn")).output(literal("#")).output(placeholder("file")).output(literal("#")).output(placeholder("line")).output(literal("#")).output(placeholder("column")).output(literal("**/\npublic class ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal("_")).output(placeholder("uid")).output(literal(" implements io.intino.magritte.framework.Expression<")).output(placeholder("type", "format")).output(literal("> {\n\tprivate ")).output(placeholder("nativeContainer", "reference")).output(literal(" self;\n\n\t@Override\n\tpublic ")).output(placeholder("type", "format")).output(literal(" value() {\n\t\t")).output(placeholder("body")).output(literal("\n\t}\n\n\t@Override\n\tpublic void self(io.intino.magritte.framework.Layer context) {\n\t\tself = (")).output(placeholder("nativeContainer", "reference")).output(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends io.intino.magritte.framework.Layer> selfClass() {\n\t\treturn ")).output(placeholder("nativeContainer", "reference")).output(literal(".class;\n\t}\n}")));
		rules.add(rule().condition(all(allTypes("list"), trigger("format"))).output(literal("java.util.List<")).output(placeholder("value", "javaType")).output(literal(">")));
		rules.add(rule().condition(trigger("format")).output(placeholder("value", "javaType")));
		rules.add(rule().condition(all(attribute("", "instant"), trigger("javatype"))).output(literal("java.time.Instant")));
		rules.add(rule().condition(all(attribute("", "Instant"), trigger("javatype"))).output(literal("java.time.Instant")));
		rules.add(rule().condition(all(attribute("", "Date"), trigger("javatype"))).output(literal("Date")));
		rules.add(rule().condition(all(attribute("", "date"), trigger("javatype"))).output(literal("Date")));
		rules.add(rule().condition(all(attribute("", "time"), trigger("javatype"))).output(literal("java.time.LocalTime")));
		rules.add(rule().condition(all(attribute("", "Time"), trigger("javatype"))).output(literal("java.time.LocalTime")));
		rules.add(rule().condition(all(attribute("", "Resource"), trigger("javatype"))).output(literal("java.net.URL")));
		rules.add(rule().condition(all(attribute("", "resource"), trigger("javatype"))).output(literal("java.net.URL")));
		rules.add(rule().condition(trigger("javatype")).output(placeholder("", "reference")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}