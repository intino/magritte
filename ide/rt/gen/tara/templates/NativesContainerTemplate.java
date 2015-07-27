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
			rule().add((condition("type", "nativeContainer"))).add(literal("package magritte.natives;\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\nimport ")).add(mark("language", "lowercase")).add(literal(".*;\n\nimport java.util.*;\nimport java.time.LocalDateTime;\n\npublic  class ")).add(mark("name")).add(literal(" {\n\t")).add(mark("native").multiple("\n\n")).add(literal("\n}"))
		);
		return this;
	}
}