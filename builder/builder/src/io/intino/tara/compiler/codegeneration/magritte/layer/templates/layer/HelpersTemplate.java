package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class HelpersTemplate extends Template {

	protected HelpersTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new HelpersTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("value", "date")), (condition("trigger", "variableType"))).add(literal("Date")),
			rule().add((condition("value", "instant")), (condition("trigger", "variableType"))).add(literal("java.time.Instant")),
			rule().add((condition("value", "time")), (condition("trigger", "variableType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "Double")), (condition("trigger", "variableType"))).add(literal("double")),
			rule().add((condition("value", "Integer")), (condition("trigger", "variableType"))).add(literal("int")),
			rule().add((condition("value", "Object")), (condition("trigger", "variableType"))).add(literal("java.lang.Object")),
			rule().add((condition("value", "type")), (condition("trigger", "variableType"))).add(literal("Concept")),
			rule().add((condition("value", "String")), (condition("trigger", "variableType"))).add(literal("java.lang.String")),
			rule().add((condition("value", "boolean")), (condition("trigger", "variableType"))).add(literal("boolean")),
			rule().add((condition("value", "resource")), (condition("trigger", "variableType"))).add(literal("java.net.URL")),
			rule().add((condition("value", "integer")), (condition("trigger", "fulltype"))).add(literal("java.lang.Integer")),
			rule().add((condition("value", "double")), (condition("trigger", "fulltype"))).add(literal("java.lang.Double")),
			rule().add((condition("value", "resource")), (condition("trigger", "fulltype"))).add(literal("java.net.URL")),
			rule().add((condition("value", "boolean")), (condition("trigger", "fulltype"))).add(literal("java.lang.Boolean")),
			rule().add((condition("value", "string")), (condition("trigger", "fulltype"))).add(literal("java.lang.String")),
			rule().add((condition("value", "resource")), (condition("trigger", "fullType"))).add(literal("java.net.URL")),
			rule().add((condition("value", "date")), (condition("trigger", "fullType"))).add(literal("Date")),
			rule().add((condition("value", "instant")), (condition("trigger", "fullType"))).add(literal("java.time.Instant")),
			rule().add((condition("value", "time")), (condition("trigger", "fullType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "resource")), (condition("trigger", "fullType"))).add(literal("java.net.URL")),
			rule().add((condition("value", "type")), (condition("trigger", "fullType"))).add(literal("Concept")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "nativerule")), (condition("trigger", "interfaceClass"))).add(mark("interfaceClass", "javaValidName")),
			rule().add((condition("type", "wordrule")), (condition("trigger", "externalWordClass"))).add(mark("externalWordClass", "javaValidName")),
			rule().add((condition("type", "customrule")), (condition("trigger", "name"))).add(mark("aClass", "javaValidName"))
		);
		return this;
	}
}