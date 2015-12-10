package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class NativeInjectionTemplate extends Template {

	protected NativeInjectionTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new NativeInjectionTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "function"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n")).add(mark("imports").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".natives."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.Function {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return"))
		);
		return this;
	}
}