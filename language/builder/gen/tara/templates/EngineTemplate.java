package tara.templates;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class EngineTemplate extends Template {

	protected EngineTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new EngineTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "engine"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Engine extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelWrapper implements tara.magritte.Engine {\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Engine(Model model) {\n\t\tsuper(model);\n\t}\n\n\tpublic void init() {\n\t\t// Insert init code here\n\t}\n\n\tpublic void execute() {\n\t\t// Insert execute code here\n\t}\n}"))
		);
		return this;
	}
}