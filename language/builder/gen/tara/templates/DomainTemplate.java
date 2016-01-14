package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class DomainTemplate extends Template {

	protected DomainTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new DomainTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "domain"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Domain extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelWrapper implements tara.magritte.Domain {\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Domain(Model model) {\n\t\tsuper(model);\n\t\t// Insert code here\n\t}\n}"))
		);
		return this;
	}
}