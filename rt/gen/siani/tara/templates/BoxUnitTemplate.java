package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class BoxUnitTemplate extends Template {

	protected BoxUnitTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new BoxUnitTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "model"))).add(literal("package magritte.boxes;\n\nimport java.text.SimpleDateFormat;\nimport java.text.ParseException;\nimport java.util.Arrays;\n\nimport static magritte.editors.BoxCore.*;\nimport static magritte.Tag.*;\nimport magritte.primitives.Date;\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n\n")).add(mark("importMetric").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name")).add(literal(" extends magritte.editors.Box.")).add(mark("terminal")).add(literal(" {\n\tpublic static final magritte.editors.Box box = new ")).add(mark("name")).add(literal("();\n\n\t@Override\n\tpublic magritte.editors.Box[] dependencies() {\n\t\treturn new magritte.editors.Box[]{")).add(expression().add(literal("magritte.dsl.")).add(mark("language", "firstUpperCase")).add(literal(".box"))).add(literal("};\n\t}\n\n\t@Override\n\tpublic void doWrite() {\n\t\t")).add(mark("node").multiple("\n")).add(literal("\n\t}\n\n\tprivate Date asDate(String date){\n\t\ttry {\n\t\t\treturn Date.date(new SimpleDateFormat(\"dd/MM/yyyy hh:mm:ss\").parse(date).getTime());\n\t\t} catch (ParseException e) {\n\t\t\treturn null;\n\t\t}\n\t}\n\n\t")).add(mark("intention").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("trigger", "terminal")), (condition("value", "true"))).add(literal("Terminal")),
			rule().add((condition("trigger", "terminal")), (condition("value", "false"))).add(literal("Unit")),
			rule().add((condition("type", "nodecontainer")), (condition("trigger", "node"))).add(expression().add(literal("def(\"")).add(mark("name")).add(mark("plate")).add(literal("\")"))).add(expression().add(literal("def(")).add(mark("key")).add(literal(")"))).add(expression().add(literal(".name()"))).add(expression().add(mark("nodeType").multiple(""))).add(expression().add(mark("facet").multiple(""))).add(expression().add(mark("annotation").multiple(""))).add(expression().add(literal(".")).add(mark("parent"))).add(expression().add(mark("variable").multiple(""))).add(expression().add(mark("include").multiple(""))).add(literal(";")),
			rule().add((condition("type", "intention")), (condition("trigger", "intention"))).add(literal("public static class ")).add(mark("path")).add(literal(" ")).add(expression().add(literal("extends ")).add(mark("container"))).add(literal(" implements ")).add(mark("parentIntention", "lowercase")).add(literal(".natives.")).add(mark("interface", "FirstUppercase")).add(literal(" {\n\t@Override\n\t")).add(mark("signature")).add(literal(" {\n\t\t")).add(mark("body")).add(literal("\n\t}\n}")),
			rule().add((condition("trigger", "nodeType"))).add(literal(".type(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "facetapply")), (condition("trigger", "facet"))).add(literal(".type(\"")).add(mark("name")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("apply"))).add(literal(")")),
			rule().add((condition("trigger", "parent"))).add(literal("parent(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "include")), (condition("type", "key")), (condition("type", "terminal")), (condition("trigger", "include"))).add(literal(".has($(")).add(mark("value")).add(literal("))")),
			rule().add((condition("type", "include")), not(condition("type", "key")), (condition("type", "terminal")), (condition("trigger", "include"))).add(literal(".has(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "include")), (condition("type", "required")), not(condition("type", "single")), (condition("trigger", "include"))).add(literal(".requires(")).add(mark("value")).add(literal(").holds(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "include")), (condition("type", "single")), not(condition("type", "required")), (condition("trigger", "include"))).add(literal(".allows(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "include")), (condition("type", "required")), (condition("trigger", "include"))).add(literal(".requires(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "include")), not(condition("type", "single")), not(condition("type", "required")), (condition("trigger", "include"))).add(literal(".holds(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(ref(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "file")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(resource((")).add(mark("variableValue", "file").multiple(", ")).add(literal("))))")),
			rule().add((condition("trigger", "file"))).add(literal("in(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("variableValue", "native")).add(literal(")")),
			rule().add((condition("trigger", "native")), (condition("value", "null"))).add(literal("nullBehavior()")),
			rule().add((condition("type", "variable")), (condition("type", "word")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(new String[]{")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("}))")),
			rule().add((condition("type", "Variable")), (condition("type", "string")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "integer")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "double")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "date")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "date").multiple(", ")).add(literal("))")),
			rule().add((condition("trigger", "date"))).add(literal("asDate(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "Variable")), (condition("type", "boolean")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("private")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("trigger", "variable"))),
			rule().add((condition("type", "Annotation")), (condition("trigger", "annotation"))).add(literal(".set(")).add(mark("value", "CamelCase").multiple(", ")).add(literal(")")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("trigger", "multiple")), (condition("value", "true"))).add(literal("multiple")),
			rule().add((condition("trigger", "multiple")), (condition("value", "false")))
		);
		return this;
	}
}