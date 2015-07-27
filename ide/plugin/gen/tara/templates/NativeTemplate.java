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
			rule().add((condition("type", "native"))).add(literal("package ")).add(mark("generatedLanguage")).add(literal(";\n\nimport tara.magritte.Morph;\nimport tara.magritte.NativeCode;\nimport tara.magritte.Node;\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n\nimport java.util.*;\nimport java.time.LocalDateTime;\n\n\npublic class ")).add(mark("name", "firstUpperCase")).add(literal(" implements ")).add(expression().add(mark("language", "lowercase")).add(literal(".natives."))).add(mark("contract", "firstUpperCase")).add(literal(", NativeCode  {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {")).add(literal("\n")).add(literal("\t")).add(literal("\t")).add(mark("return"))
		);
		return this;
	}
}