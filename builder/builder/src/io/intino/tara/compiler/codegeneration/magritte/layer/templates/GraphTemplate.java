package io.intino.tara.compiler.codegeneration.magritte.layer.templates;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class GraphTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("wrapper"))).output(literal("package ")).output(mark("workingPackage", "lowercase")).output(literal(";\n\nimport io.intino.tara.magritte.Graph;\n\npublic class ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph extends ")).output(mark("workingPackage", "lowercase")).output(literal(".AbstractGraph {\n\n\tpublic ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(Graph graph) {\n\t\tsuper(graph);\n\t}\n\n\tpublic ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(io.intino.tara.magritte.Graph graph, ")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph wrapper) {\n\t    super(graph, wrapper);\n\t}\n}"))
		);
	}
}