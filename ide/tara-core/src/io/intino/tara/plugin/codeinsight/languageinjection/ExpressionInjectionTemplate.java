package io.intino.tara.plugin.codeinsight.languageinjection;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class ExpressionInjectionTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("function"))).output(literal("package ")).output(mark("generatedLanguage", "lowerCase")).output(literal(".natives;\n\n")).output(mark("imports").multiple("\n")).output(literal("\n\nclass ")).output(mark("name", "FirstUpperCase")).output(literal(" implements ")).output(expression().output(mark("scope", "lowercase")).output(literal(".functions."))).output(mark("rule", "FirstUpperCase")).output(literal(", io.intino.tara.magritte.Function {\n\t")).output(mark("nativeContainer")).output(literal(" self;\n\n\t@Override\n\t")).output(mark("signature")).output(literal(" {\n\t\t")).output(mark("return")),
				rule().condition((type("reactive"))).output(literal("package ")).output(mark("generatedLanguage", "lowerCase")).output(literal(".natives;\n\n")).output(mark("imports").multiple("\n")).output(literal("\n\nclass ")).output(mark("name", "FirstUpperCase")).output(literal(" implements io.intino.tara.magritte.Expression<")).output(mark("type", "format")).output(literal("> {\n\t")).output(mark("nativeContainer")).output(literal(" self;\n\n\tpublic ")).output(mark("type", "format")).output(literal(" value() {\n\t\t")).output(mark("return")).output(literal("\n")),
				rule().condition((type("list")), (trigger("format"))).output(literal("java.util.List<")).output(mark("value", "javaType")).output(literal(">")),
				rule().condition((trigger("format"))).output(mark("value", "javaType")),
				rule().condition((attribute("this", "instant")), (trigger("javatype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("this", "Instant")), (trigger("javatype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("this", "Date")), (trigger("javatype"))).output(literal("Date")),
				rule().condition((attribute("this", "date")), (trigger("javatype"))).output(literal("Date")),
				rule().condition((attribute("this", "time")), (trigger("javatype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("this", "Time")), (trigger("javatype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("this", "Resource")), (trigger("javatype"))).output(literal("java.net.URL")),
				rule().condition((attribute("this", "resource")), (trigger("javatype"))).output(literal("java.net.URL"))
		);
	}
}