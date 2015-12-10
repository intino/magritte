package tara.templates.layer;

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
			rule().add((condition("value", "date")), (condition("trigger", "variableType"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "variableType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "Double")), (condition("trigger", "variableType"))).add(literal("double")),
			rule().add((condition("value", "INTEGER")), (condition("trigger", "variableType"))).add(literal("int")),
			rule().add((condition("value", "file")), (condition("trigger", "variableType"))).add(literal("java.io.File")),
			rule().add((condition("value", "integer")), (condition("trigger", "variableTypeList"))).add(literal("Integer")),
			rule().add((condition("value", "double")), (condition("trigger", "variableTypeList"))).add(literal("Double")),
			rule().add((condition("value", "boolean")), (condition("trigger", "variableTypeList"))).add(literal("Boolean")),
			rule().add((condition("value", "string")), (condition("trigger", "variableTypeList"))).add(literal("String")),
			rule().add((condition("value", "file")), (condition("trigger", "variableTypeList"))).add(literal("java.io.File")),
			rule().add((condition("value", "type")), (condition("trigger", "variableTypeList"))).add(literal("tara.magritte.Concept")),
			rule().add((condition("value", "type")), (condition("trigger", "variableType"))).add(literal("tara.magritte.Concept")),
			rule().add((condition("value", "String")), (condition("trigger", "variableType"))).add(literal("String")),
			rule().add((condition("value", "boolean")), (condition("trigger", "variableType"))).add(literal("boolean")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "nativerule")), (condition("trigger", "interfaceClass"))).add(mark("interfaceClass", "javaValidName")),
			rule().add((condition("type", "wordrule")), (condition("trigger", "externalWordClass"))).add(mark("externalWordClass", "javaValidName")),
			rule().add((condition("type", "customrule")), (condition("trigger", "name"))).add(mark("aClass", "javaValidName"))
		);
		return this;
	}
}