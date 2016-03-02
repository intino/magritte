package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class FunctionTemplate extends Template {

	protected FunctionTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new FunctionTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "function"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(".natives")).add(expression().add(literal(".")).add(mark("package"))).add(literal(";\n\n")).add(expression().add(mark("imports").multiple("\n"))).add(literal("\n\n/**")).add(mark("qn")).add(literal("#")).add(mark("file")).add(literal("#")).add(mark("line")).add(literal("#")).add(mark("column")).add(literal("**/\npublic class ")).add(mark("name", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(" implements ")).add(expression().add(mark("language", "lowercase")).add(literal(".functions."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.Function {\n\t")).add(mark("nativeContainer", "reference")).add(literal(" $;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {\n\t\t")).add(mark("body")).add(literal("\n\t}\n\n\t@Override\n\tpublic void $(tara.magritte.Layer context) {\n\t\t$ = (")).add(mark("nativeContainer", "reference")).add(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends tara.magritte.Layer> $Class() {\n\t\treturn ")).add(mark("nativeContainer", "reference")).add(literal(".class;\n\t}\n}"))
		);
		return this;
	}
}