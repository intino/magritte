package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class NativesContainerTemplate extends Template {

	protected NativesContainerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new NativesContainerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "nativeContainer"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(".natives;\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n")).add(expression().add(literal("import ")).add(mark("language", "lowercase")).add(literal(".*;"))).add(literal("\n\nimport java.util.*;\nimport java.time.LocalDateTime;\n\npublic class ")).add(mark("name")).add(literal(" {\n\tpublic static ")).add(mark("generatedLanguage", "firstUpperCase")).add(literal("Viewer ")).add(mark("generatedLanguage", "firstLowerCase")).add(literal("Viewer;\n\n\t")).add(mark("native").multiple("\n\n")).add(literal("\n}"))
		);
		return this;
	}
}