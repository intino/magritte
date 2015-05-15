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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("module")).add(literal(";\n\nimport ")).add(mark("language")).add(literal(".natives.")).add(mark("intention")).add(literal(";\n\npublic class ")).add(mark("qn")).add(literal("_")).add(mark("variable")).add(literal(" extends ")).add(mark("parent")).add(literal(" implements ")).add(mark("intention")).add(literal(" {\n\n\t@Override\n\tpublic void execute() {\n\t\t")).add(mark("body")).add(literal("\n\t}\n}"))
		);
		return this;
	}
}