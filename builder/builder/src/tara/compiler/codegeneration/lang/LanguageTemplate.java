package tara.compiler.codegeneration.lang;

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
			rule().add((condition("type", "Model"))).add(literal("package tara.dsl;\n\nimport tara.lang.model.Tag;\n\nimport java.util.Locale;\nimport static tara.lang.semantics.constraints.RuleFactory.*;\n\npublic class ")).add(mark("name", "reference", "firstUpperCase")).add(literal(" extends Tara {\n\tpublic ")).add(mark("name", "reference", "firstUpperCase")).add(literal("() {\n\t\t")).add(mark("node").multiple("\n")).add(literal("\n\t}\n\n\t@Override\n\tpublic String languageName() {\n\t\treturn \"")).add(mark("name", "firstUpperCase")).add(literal("\";\n\t}\n\n\t@Override\n    public Locale locale() {\n        return ")).add(mark("locale")).add(literal(";\n    }\n\n    @Override\n    public boolean isTerminalLanguage() {\n        return ")).add(mark("terminal")).add(literal(";\n    }\n\n\t@Override\n\tpublic String metaLanguage() {\n\t\treturn ")).add(mark("metaLanguage", "quoted")).add(literal(";\n\t}\n}")),
			rule().add((condition("type", "instance")), (condition("trigger", "node"))).add(literal("declare(")).add(mark("qn", "quoted")).add(literal(", java.util.Arrays.asList(")).add(mark("nodeType", "quoted").multiple(", ")).add(literal("), ")).add(mark("path", "quoted")).add(literal(");")),
			rule().add((condition("type", "node")), (condition("trigger", "node"))).add(literal("def(\"")).add(mark("name")).add(literal("\").with(context(")).add(expression().add(mark("nodeType"))).add(literal(")")).add(expression().add(literal(".")).add(mark("constraints"))).add(expression().add(literal(".")).add(mark("assumptions"))).add(expression().add(literal(".")).add(mark("doc"))).add(literal(");")),
			rule().add((condition("type", "nodeType")), (condition("trigger", "nodeType"))).add(mark("type", "quoted").multiple(", ")),
			rule().add((condition("trigger", "constraints"))).add(literal("has(")).add(expression().add(mark("constraint").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "component")), (condition("trigger", "constraint"))).add(literal("component(")).add(mark("type", "quoted")).add(literal(", java.util.Arrays.asList(")).add(mark("size").multiple(", ")).add(literal(")")).add(expression().add(literal(", ")).add(mark("tags").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "rule & size")), (condition("trigger", "size"))).add(literal("new tara.lang.model.rules.Size(")).add(mark("min")).add(literal(", ")).add(mark("max")).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "parameter")), (condition("type", "reference")), (condition("trigger", "constraint"))).add(literal("parameter(\"")).add(mark("name")).add(literal("\", \"")).add(mark("type")).add(literal("\", ")).add(expression().add(literal("\"")).add(mark("facet")).add(literal("\"")).or(expression().add(literal("\"\"")))).add(literal(", ")).add(mark("size")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("scope")).add(literal("\", ")).add(expression().add(mark("rule")).or(expression().add(literal("null")))).add(expression().add(literal(", ")).add(mark("tags").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "parameter")), (condition("trigger", "constraint"))).add(literal("parameter(\"")).add(mark("name")).add(literal("\", ")).add(mark("type", "primitive")).add(literal(", ")).add(expression().add(literal("\"")).add(mark("facet")).add(literal("\"")).or(expression().add(literal("\"\"")))).add(literal(", ")).add(mark("size")).add(literal(", ")).add(mark("position")).add(literal(", \"")).add(mark("scope")).add(literal("\", ")).add(expression().add(mark("rule")).or(expression().add(literal("null")))).add(expression().add(literal(", ")).add(mark("tags").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "metafacet")), (condition("trigger", "constraint"))).add(literal("metaFacet(\"")).add(mark("value")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("with", "quoted").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "facet")), (condition("trigger", "constraint"))).add(literal("facet(\"")).add(mark("value")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("terminal")).or(expression().add(literal(", false")))).add(literal(", new String[]{")).add(mark("with", "quoted").multiple(", ")).add(literal("}, new String[]{")).add(mark("without", "quoted").multiple(", ")).add(literal("})")).add(expression().add(literal(".has(")).add(mark("constraint").multiple(", ")).add(literal(")"))),
			rule().add((condition("type", "constraint")), (condition("type", "oneOf")), (condition("trigger", "constraint"))).add(literal("oneOf(java.util.Arrays.asList(")).add(mark("rule").multiple(", ")).add(literal("), ")).add(expression().add(mark("constraint").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "constraint")), (condition("type", "redefine")), (condition("trigger", "constraint"))).add(literal("redefine(")).add(mark("name", "quoted")).add(literal(", ")).add(mark("supertype", "quoted")).add(literal(")")),
			rule().add((condition("trigger", "constraint"))).add(mark("value")).add(literal("()")),
			rule().add((condition("trigger", "tags"))).add(literal("tara.lang.model.Tag.")).add(mark("value")),
			rule().add((condition("type", "customRule & metric")), (condition("trigger", "rule"))).add(mark("qn")).add(literal(".")).add(mark("default")),
			rule().add((condition("type", "customRule")), (condition("trigger", "rule"))).add(literal("new ")).add(mark("qn")).add(literal("()")),
			rule().add((condition("type", "rule & customRule")), (condition("trigger", "size"))).add(literal("new ")).add(mark("qn")).add(literal("()")),
			rule().add((condition("type", "rule & size")), (condition("trigger", "size"))).add(literal("new tara.lang.model.rules.Size(")).add(mark("min")).add(literal(", ")).add(mark("max")).add(expression().add(literal(", ")).add(mark("into", "size"))).add(literal(")")),
			rule().add((condition("type", "nativeObjectRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.NativeObjectRule(\"")).add(mark("type")).add(literal("\")")),
			rule().add((condition("type", "nativeReferenceRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.NativeReferenceRule(java.util.Arrays.asList(")).add(mark("allowedReferences", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "nativerule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.NativeRule(\"")).add(mark("interfaceClass")).add(literal("\", \"")).add(mark("signature")).add(literal("\", java.util.Arrays.asList(")).add(mark("imports", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "wordrule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList(")).add(mark("words", "quoted").multiple(", ")).add(literal(")")).add(expression().add(literal(", ")).add(mark("externalWordClass", "quoted"))).add(literal(")")),
			rule().add((condition("type", "referenceRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList(")).add(mark("allowedReferences", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "doubleRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.DoubleRule(")).add(mark("min", "cast")).add(literal(", ")).add(mark("max", "cast")).add(literal(", \"")).add(mark("metric")).add(literal("\")")),
			rule().add((condition("type", "integerRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.IntegerRule(")).add(mark("min")).add(literal(", ")).add(mark("max")).add(literal(", \"")).add(mark("metric")).add(literal("\")")),
			rule().add((condition("type", "dateRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.DateRule(")).add(expression().add(mark("parameters", "quoted")).add(literal(","))).add(literal(")")),
			rule().add((condition("type", "fileRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.FileRule(java.util.Arrays.asList(")).add(mark("extensions", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "stringRule")), (condition("trigger", "rule"))).add(literal("new tara.lang.model.rules.variable.StringRule(")).add(mark("regex", "quoted")).add(literal(")")),
			rule().add((condition("value", "Infinity")), (condition("trigger", "cast"))).add(literal("Double.POSITIVE_INFINITY")),
			rule().add((condition("value", "-Infinity")), (condition("trigger", "cast"))).add(literal("Double.NEGATIVE_INFINITY")),
			rule().add(not(condition("value", "-Infinity")), not(condition("value", "Infinity")), (condition("trigger", "cast"))).add(mark("value")),
			rule().add((condition("type", "assumptions")), (condition("trigger", "assumptions"))).add(literal("assume(")).add(mark("assumption").multiple(", ")).add(literal(")")),
			rule().add((condition("type", "doc")), (condition("trigger", "doc"))).add(literal("doc(")).add(mark("layer", "quoted")).add(literal(", ")).add(mark("file", "quoted")).add(literal(", ")).add(mark("line")).add(literal(", ")).add(mark("doc", "quoted")).add(literal(")")),
			rule().add((condition("trigger", "assumption"))).add(literal("is")).add(mark("value", "toCamelCase")).add(literal("()")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("trigger", "primitive"))).add(literal("tara.lang.model.Primitive.")).add(mark("value", "UpperCase")),
			rule().add((condition("trigger", "locale")), (condition("value", "es"))).add(literal("new Locale(\"es\", \"Spain\", \"es_ES\")")),
			rule().add((condition("trigger", "locale")), (condition("value", "en"))).add(literal("Locale.ENGLISH"))
		);
		return this;
	}
}