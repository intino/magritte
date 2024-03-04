package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class ExpressionsTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("function"))).output(literal("package ")).output(mark("workingPackage", "lowercase", "javaValidName")).output(literal(".natives")).output(expression().output(literal(".")).output(mark("package", "lowercase", "javaValidName"))).output(literal(";\n\n")).output(expression().output(mark("imports").multiple("\n"))).output(literal("\n\n/**")).output(mark("qn")).output(literal("#")).output(mark("file")).output(literal("#")).output(mark("line")).output(literal("#")).output(mark("column")).output(literal("**/\npublic class ")).output(mark("name", "FirstUpperCase", "javaValidName")).output(literal("_")).output(mark("uid")).output(literal(" implements ")).output(expression().output(mark("scope", "lowercase", "javaValidName")).output(literal(".functions."))).output(mark("rule", "FirstUpperCase")).output(literal(", io.intino.magritte.framework.Function {\n\tprivate ")).output(mark("nativeContainer", "reference")).output(literal(" self;\n\n\t@Override\n\t")).output(mark("signature")).output(literal(" {\n\t\t")).output(mark("body")).output(literal("\n\t}\n\n\t@Override\n\tpublic void self(io.intino.magritte.framework.Layer context) {\n\t\tself = (")).output(mark("nativeContainer", "reference")).output(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends io.intino.magritte.framework.Layer> selfClass() {\n\t\treturn ")).output(mark("nativeContainer", "reference")).output(literal(".class;\n\t}\n}")),
				rule().condition((type("native"))).output(literal("package ")).output(mark("workingPackage", "lowercase", "javaValidName")).output(literal(".natives")).output(expression().output(literal(".")).output(mark("package", "lowercase", "javaValidName"))).output(literal(";\n\n")).output(expression().output(mark("imports").multiple("\n"))).output(literal("\n\n/**")).output(mark("qn")).output(literal("#")).output(mark("file")).output(literal("#")).output(mark("line")).output(literal("#")).output(mark("column")).output(literal("**/\npublic class ")).output(mark("name", "FirstUpperCase", "javaValidName")).output(literal("_")).output(mark("uid")).output(literal(" implements io.intino.magritte.framework.Expression<")).output(mark("type", "format")).output(literal("> {\n\tprivate ")).output(mark("nativeContainer", "reference")).output(literal(" self;\n\n\t@Override\n\tpublic ")).output(mark("type", "format")).output(literal(" value() {\n\t\t")).output(mark("body")).output(literal("\n\t}\n\n\t@Override\n\tpublic void self(io.intino.magritte.framework.Layer context) {\n\t\tself = (")).output(mark("nativeContainer", "reference")).output(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends io.intino.magritte.framework.Layer> selfClass() {\n\t\treturn ")).output(mark("nativeContainer", "reference")).output(literal(".class;\n\t}\n}")),
				rule().condition((type("list")), (trigger("format"))).output(literal("java.util.List<")).output(mark("value", "javaType")).output(literal(">")),
				rule().condition((trigger("format"))).output(mark("value", "javaType")),
				rule().condition((attribute("", "instant")), (trigger("javatype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("", "Instant")), (trigger("javatype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("", "Date")), (trigger("javatype"))).output(literal("Date")),
				rule().condition((attribute("", "date")), (trigger("javatype"))).output(literal("Date")),
				rule().condition((attribute("", "time")), (trigger("javatype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("", "Time")), (trigger("javatype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("", "Resource")), (trigger("javatype"))).output(literal("java.net.URL")),
				rule().condition((attribute("", "resource")), (trigger("javatype"))).output(literal("java.net.URL")),
				rule().condition((trigger("javatype"))).output(mark("", "reference"))
		);
	}
}