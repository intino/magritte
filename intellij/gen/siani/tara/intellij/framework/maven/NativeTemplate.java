package siani.tara.intellij.framework.maven;

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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("languageGenerated")).add(literal(";\n\nimport ")).add(mark("language")).add(literal(".natives.*;\nimport ")).add(mark("language")).add(literal(".*;\nimport java.util.*;\n\npublic class ")).add(mark("qn")).add(expression().add(literal("_")).add(mark("variable"))).add(literal(" ")).add(expression().add(literal("extends ")).add(mark("parent"))).add(literal(" ")).add(expression().add(literal("implements ")).add(mark("interface"))).add(literal(" {\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return"))
		);
		return this;
	}
}