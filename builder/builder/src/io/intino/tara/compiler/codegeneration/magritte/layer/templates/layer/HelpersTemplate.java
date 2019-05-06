package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class HelpersTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((attribute("this", "datex")), (trigger("variabletype"))).output(literal("io.intino.tara.magritte.type.DateX")),
				rule().condition((attribute("this", "instant")), (trigger("variabletype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("this", "double")), (trigger("variabletype"))).output(literal("double")),
				rule().condition((attribute("this", "integer")), (trigger("variabletype"))).output(literal("int")),
				rule().condition((attribute("this", "long")), (trigger("variabletype"))).output(literal("long")),
				rule().condition((attribute("this", "object")), (trigger("variabletype"))).output(literal("java.lang.Object")),
				rule().condition((attribute("this", "type")), (trigger("variabletype"))).output(literal("Concept")),
				rule().condition((attribute("this", "string")), (trigger("variabletype"))).output(literal("java.lang.String")),
				rule().condition((attribute("this", "boolean")), (trigger("variabletype"))).output(literal("boolean")),
				rule().condition((attribute("this", "resource")), (trigger("variabletype"))).output(literal("java.net.URL")),
				rule().condition((attribute("this", "integer")), (trigger("fulltype"))).output(literal("java.lang.Integer")),
				rule().condition((attribute("this", "long")), (trigger("fulltype"))).output(literal("java.lang.Long")),
				rule().condition((attribute("this", "double")), (trigger("fulltype"))).output(literal("java.lang.Double")),
				rule().condition((attribute("this", "resource")), (trigger("fulltype"))).output(literal("java.net.URL")),
				rule().condition((attribute("this", "boolean")), (trigger("fulltype"))).output(literal("java.lang.Boolean")),
				rule().condition((attribute("this", "string")), (trigger("fulltype"))).output(literal("java.lang.String")),
				rule().condition((attribute("this", "date")), (trigger("fulltype"))).output(literal("io.intino.tara.magritte.types.Date")),
				rule().condition((attribute("this", "instant")), (trigger("fulltype"))).output(literal("java.time.Instant")),
				rule().condition((attribute("this", "time")), (trigger("fulltype"))).output(literal("java.time.LocalTime")),
				rule().condition((attribute("this", "type")), (trigger("fulltype"))).output(literal("Concept")),
				rule().condition((trigger("quoted"))).output(literal("\"")).output(mark("")).output(literal("\"")),
				rule().condition((type("nativerule")), (trigger("interfaceclass"))).output(mark("interfaceClass", "javaValidName")),
				rule().condition((type("wordrule")), (trigger("externalwordclass"))).output(mark("externalWordClass", "javaValidName")),
				rule().condition((type("nativecustomwordrule")), (trigger("externalwordclass"))).output(mark("source", "javaValidName")),
				rule().condition((type("variablecustomrule")), (trigger("externalwordclass"))).output(mark("aClass", "javaValidName")),
				rule().condition((type("customrule")), (trigger("name"))).output(mark("aClass", "javaValidName"))
		);
	}
}