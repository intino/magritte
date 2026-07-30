package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class HelpersTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(attribute("", "datex"), trigger("variabletype"))).output(literal("io.intino.magritte.framework.type.DateX")));
		rules.add(rule().condition(all(attribute("", "instant"), trigger("variabletype"))).output(literal("java.time.Instant")));
		rules.add(rule().condition(all(attribute("", "double"), trigger("variabletype"))).output(literal("double")));
		rules.add(rule().condition(all(attribute("", "integer"), trigger("variabletype"))).output(literal("int")));
		rules.add(rule().condition(all(attribute("", "long"), trigger("variabletype"))).output(literal("long")));
		rules.add(rule().condition(all(attribute("", "object"), trigger("variabletype"))).output(literal("java.lang.Object")));
		rules.add(rule().condition(all(attribute("", "time"), trigger("variabletype"))).output(literal("java.time.LocalTime")));
		rules.add(rule().condition(all(attribute("", "type"), trigger("variabletype"))).output(literal("Concept")));
		rules.add(rule().condition(all(attribute("", "string"), trigger("variabletype"))).output(literal("java.lang.String")));
		rules.add(rule().condition(all(attribute("", "boolean"), trigger("variabletype"))).output(literal("boolean")));
		rules.add(rule().condition(all(attribute("", "resource"), trigger("variabletype"))).output(literal("java.net.URL")));
		rules.add(rule().condition(all(attribute("", "integer"), trigger("fulltype"))).output(literal("java.lang.Integer")));
		rules.add(rule().condition(all(attribute("", "long"), trigger("fulltype"))).output(literal("java.lang.Long")));
		rules.add(rule().condition(all(attribute("", "double"), trigger("fulltype"))).output(literal("java.lang.Double")));
		rules.add(rule().condition(all(attribute("", "resource"), trigger("fulltype"))).output(literal("java.net.URL")));
		rules.add(rule().condition(all(attribute("", "boolean"), trigger("fulltype"))).output(literal("java.lang.Boolean")));
		rules.add(rule().condition(all(attribute("", "time"), trigger("fulltype"))).output(literal("java.time.LocalTime")));
		rules.add(rule().condition(all(attribute("", "string"), trigger("fulltype"))).output(literal("java.lang.String")));
		rules.add(rule().condition(all(attribute("", "date"), trigger("fulltype"))).output(literal("io.intino.magritte.framework.types.Date")));
		rules.add(rule().condition(all(attribute("", "instant"), trigger("fulltype"))).output(literal("java.time.Instant")));
		rules.add(rule().condition(all(attribute("", "time"), trigger("fulltype"))).output(literal("java.time.LocalTime")));
		rules.add(rule().condition(all(attribute("", "type"), trigger("fulltype"))).output(literal("Concept")));
		rules.add(rule().condition(trigger("quoted")).output(literal("\"")).output(placeholder("")).output(literal("\"")));
		rules.add(rule().condition(all(allTypes("nativerule"), trigger("interfaceclass"))).output(placeholder("interfaceClass", "javaValidName")));
		rules.add(rule().condition(all(allTypes("wordrule"), trigger("externalwordclass"))).output(placeholder("aClass", "javaValidName")));
		rules.add(rule().condition(all(allTypes("nativeCustomWordRule"), trigger("externalwordclass"))).output(placeholder("source", "javaValidName")));
		rules.add(rule().condition(all(any(allTypes("variablecustomrule"), allTypes("customrule")), trigger("externalwordclass"))).output(placeholder("aClass", "javaValidName")));
		rules.add(rule().condition(all(allTypes("customrule"), trigger("name"))).output(placeholder("aClass", "javaValidName")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}