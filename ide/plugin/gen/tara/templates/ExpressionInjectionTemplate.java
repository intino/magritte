package tara.templates;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class ExpressionInjectionTemplate extends Template {

	protected ExpressionInjectionTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ExpressionInjectionTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "native & java"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements tara.magritte.Expression<")).add(mark("type", "javaType")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\tpublic ")).add(mark("type", "javaType")).add(literal(" value() {")).add(literal("\n")).add(expression().add(literal("\t\t")).add(mark("return"))),
			rule().add((condition("type", "function & java"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.Function {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(expression().add(literal("\t\t")).add(mark("return"))),
			rule().add((condition("type", "native & groovy"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\n@SuppressWarnings(\"UnnecessaryQualifiedReference\")\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements tara.magritte.Expression<")).add(mark("type", "javaType")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\tpublic ")).add(mark("type", "javaType")).add(literal(" value() {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add((condition("type", "function & groovy"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\n@SuppressWarnings(\"UnnecessaryQualifiedReference\")\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.Function {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add((condition("type", "native & kotlin"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" : tara.magritte.Expression<")).add(mark("type", "javaType")).add(literal("> {\n\tinternal var self: ")).add(mark("nativeContainer", "reference")).add(literal("\n\n\toverride fun value():")).add(mark("type", "javaType")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")).add(literal("\n")),
			rule().add((condition("type", "function & kotlin"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\nclass ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.Function {\n\tinternal var self: ")).add(mark("nativeContainer", "reference")).add(literal("\n\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add((condition("value", "date")), (condition("trigger", "javaType"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "javaType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "resource")), (condition("trigger", "javaType"))).add(literal("java.net.URL"))
		);
		return this;
	}
}