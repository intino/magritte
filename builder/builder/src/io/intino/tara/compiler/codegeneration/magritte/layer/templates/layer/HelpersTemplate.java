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
			rule().add((condition("attribute", "datex")), (condition("trigger", "variableType"))).add(literal("io.intino.tara.magritte.type.DateX")),
			rule().add((condition("attribute", "Instant")), (condition("trigger", "variableType"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "instant")), (condition("trigger", "variableType"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "Time")), (condition("trigger", "variableType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "Double")), (condition("trigger", "variableType"))).add(literal("double")),
			rule().add((condition("attribute", "Integer")), (condition("trigger", "variableType"))).add(literal("int")),
			rule().add((condition("attribute", "Object")), (condition("trigger", "variableType"))).add(literal("java.lang.Object")),
			rule().add((condition("attribute", "type")), (condition("trigger", "variableType"))).add(literal("Concept")),
			rule().add((condition("attribute", "String")), (condition("trigger", "variableType"))).add(literal("java.lang.String")),
			rule().add((condition("attribute", "string")), (condition("trigger", "variableType"))).add(literal("java.lang.String")),
			rule().add((condition("attribute", "Boolean")), (condition("trigger", "variableType"))).add(literal("boolean")),
			rule().add((condition("attribute", "boolean")), (condition("trigger", "variableType"))).add(literal("boolean")),
			rule().add((condition("attribute", "Resource")), (condition("trigger", "variableType"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "resource")), (condition("trigger", "variableType"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "Integer")), (condition("trigger", "fulltype"))).add(literal("java.lang.Integer")),
			rule().add((condition("attribute", "integer")), (condition("trigger", "fulltype"))).add(literal("java.lang.Integer")),
			rule().add((condition("attribute", "Double")), (condition("trigger", "fulltype"))).add(literal("java.lang.Double")),
			rule().add((condition("attribute", "double")), (condition("trigger", "fulltype"))).add(literal("java.lang.Double")),
			rule().add((condition("attribute", "Resource")), (condition("trigger", "fulltype"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "resource")), (condition("trigger", "fulltype"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "boolean")), (condition("trigger", "fulltype"))).add(literal("java.lang.Boolean")),
			rule().add((condition("attribute", "Boolean")), (condition("trigger", "fulltype"))).add(literal("java.lang.Boolean")),
			rule().add((condition("attribute", "string")), (condition("trigger", "fulltype"))).add(literal("java.lang.String")),
			rule().add((condition("attribute", "String")), (condition("trigger", "fulltype"))).add(literal("java.lang.String")),
			rule().add((condition("attribute", "Date")), (condition("trigger", "fullType"))).add(literal("io.intino.tara.magritte.types.Date")),
			rule().add((condition("attribute", "date")), (condition("trigger", "fullType"))).add(literal("io.intino.tara.magritte.types.Date")),
			rule().add((condition("attribute", "Instant")), (condition("trigger", "fullType"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "instant")), (condition("trigger", "fullType"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "Time")), (condition("trigger", "fullType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "time")), (condition("trigger", "fullType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "type")), (condition("trigger", "fullType"))).add(literal("Concept")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "nativerule")), (condition("trigger", "interfaceClass"))).add(mark("interfaceClass", "javaValidName")),
			rule().add((condition("type", "wordrule")), (condition("trigger", "externalWordClass"))).add(mark("externalWordClass", "javaValidName")),
			rule().add((condition("type", "nativeCustomWordRule")), (condition("trigger", "externalWordClass"))).add(mark("source", "javaValidName")),
			rule().add((condition("type", "variablecustomrule")), (condition("trigger", "externalWordClass"))).add(mark("aClass", "javaValidName")),
			rule().add((condition("type", "customrule")), (condition("trigger", "name"))).add(mark("aClass", "javaValidName"))
		);
		return this;
	}
}