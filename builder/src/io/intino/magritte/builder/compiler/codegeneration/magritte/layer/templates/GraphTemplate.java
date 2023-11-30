package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class GraphTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("wrapper"))).output(literal("package ")).output(mark("workingPackage", "lowercase")).output(literal(";\n\nimport io.intino.magritte.framework.Graph;\n\npublic class ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph extends ")).output(mark("workingPackage", "lowercase")).output(literal(".AbstractGraph {\n\n\tpublic ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(Graph graph) {\n\t\tsuper(graph);\n\t}\n\n\tpublic ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(io.intino.magritte.framework.Graph graph, ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph wrapper) {\n\t    super(graph, wrapper);\n\t}\n\n\n\tpublic static ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(io.intino.magritte.io.Stash... startingModel) {\n\t\treturn new Graph().loadStashes(stashes()).loadStashes(startingModel).as(")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n\n\tpublic static ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(io.intino.magritte.framework.Store store, io.intino.magritte.io.Stash... startingModel) {\n\t\treturn new Graph(store).loadStashes(stashes()).loadStashes(startingModel).as(")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n}"))
		);
	}
}