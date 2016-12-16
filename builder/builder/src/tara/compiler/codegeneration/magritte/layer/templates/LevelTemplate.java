package tara.compiler.codegeneration.magritte.layer.templates;

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
			rule().add((condition("type", "platform"))).add(literal("package ")).add(mark("workingPackage", "lowercase")).add(literal(";\n\nimport tara.magritte.Graph;\n\npublic class ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal("Platform extends ")).add(mark("workingPackage", "lowercase")).add(literal(".GraphWrapper implements tara.magritte.Platform {\n\n\tpublic ")).add(mark("generatedLanguage", "javaValidName")).add(literal("Platform(Graph graph) {\n\t\tsuper(graph);\n\t}\n\n\tpublic void execute(String... args) {\n\t\t// Insert execute code here\n\t}\n}")),
			rule().add((condition("type", "application & ontology"))).add(literal("package ")).add(mark("workingPackage", "lowercase")).add(literal(";\n\nimport tara.magritte.Graph;\n\npublic class ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal("Application extends ")).add(mark("workingPackage", "lowercase")).add(literal(".GraphWrapper implements tara.magritte.Application {\n\n\tpublic ")).add(mark("generatedLanguage", "javaValidName")).add(literal("Application(Graph graph) {\n\t\tsuper(graph);\n\t\t// Insert code here\n\t}\n\n\tpublic void execute(String... args) {\n\t\t// Insert code here\n\t}\n}")),
			rule().add((condition("type", "application"))).add(literal("package ")).add(mark("workingPackage", "lowercase")).add(literal(";\n\nimport tara.magritte.Graph;\n\npublic class ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal("Application extends ")).add(mark("workingPackage", "lowercase")).add(literal(".GraphWrapper implements tara.magritte.Application {\n\n\tpublic ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal("Application(Graph graph) {\n\t\tsuper(graph);\n\t\t// Insert code here\n\t}\n}")),
			rule().add((condition("type", "launcher & ontology"))).add(literal("import tara.magritte.Graph;\n\npublic class Main {\n\n\tpublic static void main(String[] args) {\n\t\tGraph graph = ")).add(mark("dynamic")).add(literal("Graph.load().wrap(")).add(mark("language", "lowerCase")).add(literal(".")).add(mark("language", "firstUpperCase")).add(literal("Application.class);\n\t\tgraph.application().execute(args);\n\t}\n}")),
			rule().add((condition("type", "launcher"))).add(literal("import tara.magritte.Graph;\n\npublic class Main {\n\n\tpublic static void main(String[] args) {\n\t\tGraph graph = ")).add(mark("dynamic")).add(literal("Graph.load().wrap(")).add(mark("language", "lowerCase")).add(literal(".")).add(mark("language", "firstUpperCase")).add(literal("Application.class, ")).add(mark("metaLanguage", "lowerCase")).add(literal(".")).add(mark("metaLanguage", "firstUpperCase")).add(literal("Platform.class);\n\t\tgraph.platform().execute(args);\n\t}\n}"))
		);
		return this;
	}
}