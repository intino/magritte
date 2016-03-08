package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class LevelTemplate extends Template {

	protected LevelTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new LevelTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "platform"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Platform extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelWrapper implements tara.magritte.Platform {\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Platform(Model model) {\n\t\tsuper(model);\n\t}\n\n\tpublic void init(String... args) {\n\t\t// Insert init code here\n\t}\n\n\tpublic void execute() {\n\t\t// Insert execute code here\n\t}\n}")),
			rule().add((condition("type", "application & ontology"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Application extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelWrapper implements tara.magritte.Application {\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Application(Model model) {\n\t\tsuper(model);\n\t\t// Insert code here\n\t}\n\n\tpublic void execute() {\n\t\t// Insert code here\n\t}\n}")),
			rule().add((condition("type", "application"))).add(literal("package ")).add(mark("generatedLanguage", "lowercase")).add(literal(";\n\nimport tara.magritte.Model;\n\npublic class ")).add(mark("generatedLanguage")).add(literal("Application extends ")).add(mark("generatedLanguage", "lowercase")).add(literal(".ModelWrapper implements tara.magritte.Application {\n\n\tpublic ")).add(mark("generatedLanguage")).add(literal("Application(Model model) {\n\t\tsuper(model);\n\t\t// Insert code here\n\t}\n}")),
			rule().add((condition("type", "launcher & ontology"))).add(literal("import tara.magritte.Model;\n\npublic class Main {\n\n\tpublic static void main(String[] args) {\n\t\tModel model = ")).add(mark("dynamic")).add(literal("Model.load().init(")).add(mark("language", "lowerCase")).add(literal(".")).add(mark("language", "firstUpperCase")).add(literal("Application.class);\n\t\tmodel.application().init();\n\t\tmodel.application().execute();\n\t}\n}")),
			rule().add((condition("type", "launcher"))).add(literal("import tara.magritte.Model;\n\npublic class Main {\n\n\tpublic static void main(String[] args) {\n\t\tModel model = ")).add(mark("dynamic")).add(literal("Model.load().init(")).add(mark("language", "lowerCase")).add(literal(".")).add(mark("language", "firstUpperCase")).add(literal("Application.class, ")).add(mark("metaLanguage", "lowerCase")).add(literal(".")).add(mark("metaLanguage", "firstUpperCase")).add(literal("Platform.class);\n\t\tmodel.platform().init();\n\t\tmodel.platform().execute();\n\t}\n}"))
		);
		return this;
	}
}