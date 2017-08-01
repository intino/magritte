package io.intino.tara.plugin.codeinsight.languageinjection;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ExpressionInjectionTemplate extends Template {

	protected ExpressionInjectionTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ExpressionInjectionTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "java")), not(condition("type", "function"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements io.intino.tara.magritte.Expression<")).add(mark("type")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\tpublic ")).add(mark("type")).add(literal(" value() {")).add(literal("\n")).add(expression().add(literal("\t\t")).add(mark("return"))),
			rule().add((condition("type", "function & java"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("scope", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", io.intino.tara.magritte.Function {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(expression().add(literal("\t\t")).add(mark("return"))),
			rule().add((condition("type", "groovy")), not(condition("type", "function"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\n@SuppressWarnings(\"UnnecessaryQualifiedReference\")\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements io.intino.tara.magritte.Expression<")).add(mark("type")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\tpublic ")).add(mark("type")).add(literal(" value() {")).add(literal("\n")).add(expression().add(literal("\n\n")).add(mark("return"))),
			rule().add((condition("type", "function & groovy"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\n@SuppressWarnings(\"UnnecessaryQualifiedReference\")\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("scope", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", io.intino.tara.magritte.Function {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add((condition("type", "kotlin")), not(condition("type", "function"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" : io.intino.tara.magritte.Expression<")).add(mark("type")).add(literal("> {\n\tinternal var self: ")).add(mark("nativeContainer", "reference")).add(literal("\n\n\toverride fun value():")).add(mark("type")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")).add(literal("\n")),
			rule().add((condition("type", "function & kotlin"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("scope", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", io.intino.tara.magritte.Function {\n\tinternal var self: ")).add(mark("nativeContainer", "reference")).add(literal("\n\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add(not(condition("type", "list")), (condition("trigger", "type"))).add(mark("value", "javaType")),
			rule().add((condition("type", "list")), (condition("trigger", "type"))).add(literal("java.util.List<")).add(mark("value", "javaType")).add(literal(">")),
			rule().add((condition("attribute", "instant")), (condition("trigger", "javaType"))).add(literal("java.time.Instant")),
			rule().add((condition("attribute", "date")), (condition("trigger", "javaType"))).add(literal("Date")),
			rule().add((condition("attribute", "time")), (condition("trigger", "javaType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("attribute", "resource")), (condition("trigger", "javaType"))).add(literal("java.net.URL"))
		);
		return this;
	}
}