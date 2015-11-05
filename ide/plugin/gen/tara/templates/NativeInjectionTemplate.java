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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives;\n\nimport tara.magritte.Layer;\nimport tara.magritte.NativeCode;\nimport tara.magritte.Node;\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n")).add(expression().add(literal("import ")).add(mark("language", "lowercase")).add(literal(".*;"))).add(literal("\n\nimport java.util.*;\nimport java.time.LocalDateTime;\n\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".natives."))).add(mark("rule", "firstUpperCase")).add(literal(", tara.magritte.NativeCode  {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return"))
		);
		return this;
	}
}