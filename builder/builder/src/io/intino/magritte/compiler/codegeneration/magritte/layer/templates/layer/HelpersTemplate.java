package io.intino.magritte.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class HelpersTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((attribute("", "datex")), (trigger("variabletype"))).output(literal("io.intino.magritte.framework.type.DateX")),
				rule().condition((attribute("", "instant")), (trigger("variabletype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("", "double")), (trigger("variabletype"))).output(literal("double")),
				rule().condition((attribute("", "integer")), (trigger("variabletype"))).output(literal("int")),
				rule().condition((attribute("", "long")), (trigger("variabletype"))).output(literal("long")),
				rule().condition((attribute("", "object")), (trigger("variabletype"))).output(literal("java.lang.Object")),
				rule().condition((attribute("", "time")), (trigger("variabletype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("", "type")), (trigger("variabletype"))).output(literal("Concept")),
				rule().condition((attribute("", "string")), (trigger("variabletype"))).output(literal("java.lang.String")),
				rule().condition((attribute("", "boolean")), (trigger("variabletype"))).output(literal("boolean")),
				rule().condition((attribute("", "resource")), (trigger("variabletype"))).output(literal("java.net.URL")),
				rule().condition((attribute("", "integer")), (trigger("fulltype"))).output(literal("java.lang.Integer")),
				rule().condition((attribute("", "long")), (trigger("fulltype"))).output(literal("java.lang.Long")),
				rule().condition((attribute("", "double")), (trigger("fulltype"))).output(literal("java.lang.Double")),
				rule().condition((attribute("", "resource")), (trigger("fulltype"))).output(literal("java.net.URL")),
				rule().condition((attribute("", "boolean")), (trigger("fulltype"))).output(literal("java.lang.Boolean")),
				rule().condition((attribute("", "time")), (trigger("fulltype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("", "string")), (trigger("fulltype"))).output(literal("java.lang.String")),
				rule().condition((attribute("", "date")), (trigger("fulltype"))).output(literal("io.intino.magritte.framework.types.Date")),
				rule().condition((attribute("", "instant")), (trigger("fulltype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("", "time")), (trigger("fulltype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("", "type")), (trigger("fulltype"))).output(literal("Concept")),
				rule().condition((trigger("quoted"))).output(literal("\"")).output(mark("")).output(literal("\"")),
				rule().condition((type("nativerule")), (trigger("interfaceclass"))).output(mark("interfaceClass", "javaValidName")),
				rule().condition((type("wordrule")), (trigger("externalwordclass"))).output(mark("aClass", "javaValidName")),
				rule().condition((type("nativeCustomWordRule")), (trigger("externalwordclass"))).output(mark("source", "javaValidName")),
				rule().condition((anyTypes("variablecustomrule", "customrule")), (trigger("externalwordclass"))).output(mark("aClass", "javaValidName")),
				rule().condition((type("customrule")), (trigger("name"))).output(mark("aClass", "javaValidName"))
		);
	}
}