package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class EngineTemplate extends Template {

	protected EngineTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new EngineTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "engine"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Engine extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelHandler implements tara.magritte.Engine {\n\n\tprivate Model model;\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Engine(Model model) {\n\t\tsuper(model);\n\t\t// Insert start code here\n\t}\n\n\t@Override\n\tpublic void execute() {\n\t\t// Insert execute code here\n\t}\n}"))
		);
		return this;
	}
}