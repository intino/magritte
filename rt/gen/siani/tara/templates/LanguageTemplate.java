package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class LanguageTemplate extends Template {

	protected LanguageTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new LanguageTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Model"))).add(literal("package siani.tara.dsls;\n\nimport siani.tara.semantic.model.Tara;\nimport java.util.Locale;\nimport siani.tara.semantic.model.Tag;\nimport static siani.tara.semantic.Relation.*;\nimport static siani.tara.semantic.constraints.RuleFactory.*;\n\npublic class ")).add(mark("name")).add(literal(" extends Tara {\n\tpublic ")).add(mark("name")).add(literal("() {\n\t\t")).add(mark("node").multiple("\n")).add(literal("\n\t}\n\n\t@Override\n\tpublic String languageName() {\n\t\treturn \"")).add(mark("name")).add(literal("\";\n\t}\n\n\t@Override\n    public Locale locale() {\n        return ")).add(mark("locale")).add(literal(";\n    }\n\n    @Override\n    public boolean isTerminalLanguage() {\n        return ")).add(mark("terminal")).add(literal(";\n    }\n}\n")),
			rule().add((condition("type", "node")), (condition("trigger", "node"))).add(literal("in(\"")).add(mark("name")).add(literal("\").def(context(")).add(expression().add(mark("nodeType"))).add(literal(")")).add(expression().add(literal(".")).add(mark("allows"))).add(expression().add(literal(".")).add(mark("requires"))).add(expression().add(literal(".")).add(mark("assumptions"))).add(literal(");")),
			rule().add((condition("type", "nodeType")), (condition("trigger", "nodeType"))).add(mark("type", "quoted").multiple(", ")),
			rule().add((condition("type", "allows")), (condition("trigger", "allows"))).add(literal("allow(")).add(expression().add(mark("allow").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "multiple")), (condition("trigger", "allow"))).add(literal("multiple(")).add(mark("type", "quoted")).add(expression().add(literal(", ")).add(mark("relation"))).add(expression().add(literal(", ")).add(mark("tags", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "single")), (condition("trigger", "allow"))).add(literal("single(")).add(mark("type", "quoted")).add(expression().add(literal(", ")).add(mark("relation"))).add(expression().add(literal(", ")).add(mark("tags", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "parameter")), (condition("type", "word")), (condition("trigger", "allow"))).add(literal("parameter(\"")).add(mark("name")).add(literal("\", new String[]{")).add(mark("words", "quoted").multiple(", ")).add(literal("}, ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "parameter")), (condition("type", "reference")), (condition("trigger", "allow"))).add(literal("parameter(\"")).add(mark("name")).add(literal("\", new String[]{")).add(mark("types", "quoted").multiple(", ")).add(literal("}, ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "parameter")), (condition("trigger", "allow"))).add(literal("parameter(\"")).add(mark("name")).add(literal("\", \"")).add(mark("type")).add(literal("\", ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "allow")), (condition("type", "facet")), (condition("trigger", "allow"))).add(literal("facet(\"")).add(mark("value")).add(literal("\")")).add(expression().add(literal(".require(")).add(mark("require").multiple(", ")).add(literal(")"))).add(expression().add(literal(".allow(")).add(mark("allow").multiple(", ")).add(literal(")"))),
			rule().add((condition("trigger", "allow"))).add(mark("value")).add(literal("()")),
			rule().add((condition("type", "requires")), (condition("trigger", "requires"))).add(literal("require(")).add(expression().add(mark("require").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "require")), (condition("type", "oneOf")), (condition("trigger", "require"))).add(literal("oneOf(")).add(expression().add(mark("require").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "require")), (condition("type", "multiple")), (condition("trigger", "require"))).add(literal("_multiple(")).add(mark("type", "quoted")).add(expression().add(literal(", ")).add(mark("relation"))).add(expression().add(literal(", ")).add(mark("tags", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "require")), (condition("type", "single")), (condition("trigger", "require"))).add(literal("_single(")).add(mark("type", "quoted")).add(expression().add(literal(", ")).add(mark("relation"))).add(expression().add(literal(", ")).add(mark("tags", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("trigger", "tags"))).add(literal("Tag.")).add(mark("value")),
			rule().add((condition("type", "require")), not(condition("type", "parameter")), (condition("type", "none")), (condition("trigger", "require"))).add(literal("_noneIncludes()")),
			rule().add((condition("type", "require")), (condition("type", "parameter")), (condition("type", "none")), (condition("trigger", "require"))).add(literal("_noneParameter()")),
			rule().add((condition("type", "require")), (condition("type", "parameter")), (condition("type", "word")), (condition("trigger", "require"))).add(literal("_parameter(\"")).add(mark("name")).add(literal("\", new String[]{")).add(mark("words", "quoted").multiple(", ")).add(literal("}, ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "require")), (condition("type", "parameter")), (condition("type", "reference")), (condition("trigger", "require"))).add(literal("_parameter(\"")).add(mark("name")).add(literal("\", new String[]{")).add(mark("types", "quoted").multiple(", ")).add(literal("}, ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "require")), (condition("type", "parameter")), (condition("trigger", "require"))).add(literal("_parameter(\"")).add(mark("name")).add(literal("\", \"")).add(mark("type")).add(literal("\", ")).add(mark("multiple")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("metric")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("annotations", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("trigger", "require"))).add(literal("_")).add(mark("value")).add(literal("()")),
			rule().add((condition("type", "assumptions")), (condition("trigger", "assumptions"))).add(literal("assume(")).add(mark("assumption").multiple(", ")).add(literal(")")),
			rule().add((condition("trigger", "assumption"))).add(literal("is")).add(mark("value", "firstUpperCase")).add(literal("()")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\""))
		);
		return this;
	}
}