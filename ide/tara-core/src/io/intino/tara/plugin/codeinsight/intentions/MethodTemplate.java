package io.intino.tara.plugin.codeinsight.intentions;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class MethodTemplate extends Template {

	protected MethodTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new MethodTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "method & multiple"))).add(literal("public static java.util.List<")).add(mark("type")).add(literal("> ")).add(mark("name")).add(literal("(")).add(mark("scope")).add(literal(" self")).add(expression().add(literal(", ")).add(mark("parameter").multiple(","))).add(literal(") {\n\t")).add(mark("body")).add(literal("\n}")),
			rule().add((condition("type", "method"))).add(literal("public static ")).add(mark("type", "list")).add(literal(" ")).add(mark("name")).add(literal("(")).add(mark("scope")).add(literal(" self")).add(expression().add(literal(", ")).add(mark("parameter").multiple(","))).add(literal(") {\n\t")).add(mark("body")).add(literal("\n}")),
			rule().add((condition("attribute", "instant")), (condition("trigger", "type"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "date")), (condition("trigger", "type"))).add(literal("Date")),
			rule().add((condition("attribute", "time")), (condition("trigger", "type"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "Double")), (condition("trigger", "type"))).add(literal("double")),
			rule().add((condition("attribute", "INTEGER")), (condition("trigger", "type"))).add(literal("int")),
			rule().add((condition("attribute", "OBJECT")), (condition("trigger", "type"))).add(literal("Object")),
			rule().add((condition("attribute", "RESOURCE")), (condition("trigger", "type"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "resource")), (condition("trigger", "type"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "string")), (condition("trigger", "type"))).add(literal("String")),
			rule().add((condition("attribute", "boolean")), (condition("trigger", "type"))).add(literal("boolean")),
			rule().add((condition("attribute", "int")), (condition("trigger", "list"))).add(literal("Integer")),
			rule().add((condition("attribute", "instant")), (condition("trigger", "list"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "date")), (condition("trigger", "list"))).add(literal("Date")),
			rule().add((condition("attribute", "time")), (condition("trigger", "list"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "double")), (condition("trigger", "list"))).add(literal("Double")),
			rule().add((condition("attribute", "OBJECT")), (condition("trigger", "list"))).add(literal("Object")),
			rule().add((condition("attribute", "boolean")), (condition("trigger", "list"))).add(literal("Boolean")),
			rule().add((condition("attribute", "string")), (condition("trigger", "list"))).add(literal("String")),
			rule().add((condition("attribute", "resource")), (condition("trigger", "list"))).add(literal("java.net.URL")),
			rule().add((condition("attribute", "type")), (condition("trigger", "list"))).add(literal("Concept"))
		);
		return this;
	}
}