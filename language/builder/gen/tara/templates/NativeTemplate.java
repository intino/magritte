package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class NativeTemplate extends Template {

	protected NativeTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new NativeTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("generatedLanguage", "javaValidName", "lowercase")).add(literal(".natives")).add(expression().add(literal(".")).add(mark("package", "javaValidName", "lowercase"))).add(literal(";\n\n")).add(expression().add(mark("imports").multiple("\n"))).add(literal("\n\n/**")).add(mark("qn")).add(literal("#")).add(mark("file")).add(literal("#")).add(mark("line")).add(literal("#")).add(mark("column")).add(literal("**/\npublic class ")).add(mark("name", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(" implements tara.magritte.Expression<")).add(mark("type", "javaType")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" self;\n\n\t@Override\n\tpublic ")).add(mark("type", "javaType")).add(literal(" value() {\n\t\t")).add(expression().add(mark("return")).add(literal(" "))).add(mark("body")).add(literal("\n\t}\n\n\t@Override\n\tpublic void self(tara.magritte.Layer context) {\n\t\tself = (")).add(mark("nativeContainer", "reference")).add(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends tara.magritte.Layer> selfClass() {\n\t\treturn ")).add(mark("nativeContainer", "reference")).add(literal(".class;\n\t}\n}")),
			rule().add((condition("value", "date")), (condition("trigger", "javaType"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "javaType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "resource")), (condition("trigger", "javaType"))).add(literal("java.net.URL"))
		);
		return this;
	}
}