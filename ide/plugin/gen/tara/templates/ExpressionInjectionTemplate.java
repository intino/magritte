package tara.templates;

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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\n")).add(mark("imports").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements tara.magritte.Expression<")).add(mark("type", "javaType")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t@Override\n\tpublic ")).add(mark("type", "javaType")).add(literal(" value() {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return")),
			rule().add((condition("value", "date")), (condition("trigger", "javaType"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "javaType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "resource")), (condition("trigger", "javaType"))).add(literal("java.net.URL"))
		);
		return this;
	}
}