package pandora.codegeneration.action;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ActionTemplate extends Template {

	protected ActionTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ActionTemplate(Locale.ENGLISH, CRLF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "action"))).add(literal("package ")).add(mark("package", "validname")).add(literal(".actions;\n\nimport tara.magritte.Graph;\nimport java.time.*;\nimport java.util.*;\n")).add(mark("formatImport")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal("Action {\n\n\tpublic Graph graph;\n\t")).add(expression().add(mark("parameter", "type").multiple("\n"))).add(literal("\n\n\tpublic ")).add(mark("returnType")).add(literal(" execute() ")).add(expression().add(literal("throws ")).add(mark("throws").multiple(", ")).add(literal(" "))).add(literal("{\n\t\t")).add(mark("returnType", "return")).add(literal("\n\t}\n}")),
			rule().add((condition("type", "parameter")), (condition("trigger", "type"))).add(literal("public ")).add(mark("type")).add(literal(" ")).add(mark("name", "SnakeCaseToCamelCase", "FirstLowerCase")).add(literal(";")),
			rule().add((condition("attribute", "void")), (condition("trigger", "return"))),
			rule().add((condition("trigger", "return"))).add(literal("return null;")),
			rule().add((condition("type", "formatImport")), (condition("trigger", "formatImport"))).add(literal("import ")).add(mark("package")).add(literal(".formats.*;"))
		);
		return this;
	}
}