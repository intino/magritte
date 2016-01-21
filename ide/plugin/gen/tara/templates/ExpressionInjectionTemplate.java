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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\nimport java.time.*;\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements tara.magritte.Expression<")).add(mark("type")).add(literal("> {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\n\t@Override\n\tpublic ")).add(mark("type")).add(literal(" value() {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return"))
		);
		return this;
	}
}