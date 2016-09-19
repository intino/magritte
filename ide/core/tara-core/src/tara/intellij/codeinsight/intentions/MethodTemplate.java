package tara.intellij.codeinsight.intentions;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class MethodTemplate extends Template {

	protected MethodTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new MethodTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "method & multiple"))).add(literal("public static List<")).add(mark("type")).add(literal("> ")).add(mark("name")).add(literal("(")).add(mark("scope")).add(literal(" self")).add(expression().add(literal(", ")).add(mark("parameter").multiple(","))).add(literal(") {\n\t")).add(mark("body")).add(literal("\n}")),
			rule().add((condition("type", "method"))).add(literal("public static ")).add(mark("type", "list")).add(literal(" ")).add(mark("name")).add(literal("(")).add(mark("scope")).add(literal(" self")).add(expression().add(literal(", ")).add(mark("parameter").multiple(","))).add(literal(") {\n\t")).add(mark("body")).add(literal("\n}")),
			rule().add((condition("value", "date")), (condition("trigger", "type"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "type"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "Double")), (condition("trigger", "type"))).add(literal("double")),
			rule().add((condition("value", "INTEGER")), (condition("trigger", "type"))).add(literal("int")),
			rule().add((condition("value", "OBJECT")), (condition("trigger", "type"))).add(literal("Object")),
			rule().add((condition("value", "RESOURCE")), (condition("trigger", "type"))).add(literal("java.net.URL")),
			rule().add((condition("value", "string")), (condition("trigger", "type"))).add(literal("String")),
			rule().add((condition("value", "boolean")), (condition("trigger", "type"))).add(literal("boolean")),
			rule().add((condition("value", "int")), (condition("trigger", "list"))).add(literal("Integer")),
			rule().add((condition("value", "date")), (condition("trigger", "list"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "list"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "double")), (condition("trigger", "list"))).add(literal("Double")),
			rule().add((condition("value", "OBJECT")), (condition("trigger", "list"))).add(literal("Object")),
			rule().add((condition("value", "boolean")), (condition("trigger", "list"))).add(literal("Boolean")),
			rule().add((condition("value", "string")), (condition("trigger", "list"))).add(literal("String")),
			rule().add((condition("value", "resource")), (condition("trigger", "list"))).add(literal("java.net.URL")),
			rule().add((condition("value", "type")), (condition("trigger", "list"))).add(literal("tara.magritte.Concept"))
		);
		return this;
	}
}