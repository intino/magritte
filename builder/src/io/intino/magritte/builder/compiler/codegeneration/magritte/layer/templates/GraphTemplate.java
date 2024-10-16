package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.allTypes;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class GraphTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(allTypes("wrapper")).output(literal("package ")).output(placeholder("workingPackage", "lowercase")).output(literal(";\n\nimport io.intino.magritte.framework.Graph;\n\npublic class ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph extends ")).output(placeholder("workingPackage", "lowercase")).output(literal(".AbstractGraph {\n\n\tpublic ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(Graph graph) {\n\t\tsuper(graph);\n\t}\n\n\tpublic ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph(io.intino.magritte.framework.Graph graph, ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph wrapper) {\n\t    super(graph, wrapper);\n\t}\n\n\n\tpublic static ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(io.intino.magritte.io.model.Stash... startingModel) {\n\t\treturn new Graph().loadLanguage(\"")).output(placeholder("generatedLanguage", "firstUpperCase")).output(literal("\", _language()).loadStashes(startingModel).as(")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n\n\tpublic static ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(io.intino.magritte.framework.Store store, io.intino.magritte.io.model.Stash... startingModel) {\n\t\treturn new Graph(store).loadLanguage(\"")).output(placeholder("generatedLanguage", "firstUpperCase")).output(literal("\", _language()).loadStashes(startingModel).as(")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n\n\tpublic static ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(String... startingModel) {\n\t\treturn new Graph().loadLanguage(\"")).output(placeholder("generatedLanguage", "firstUpperCase")).output(literal("\", _language()).loadStashes(startingModel).as(")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n\n\tpublic static ")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph load(io.intino.magritte.framework.Store store, String... startingModel) {\n\t\treturn new Graph(store).loadLanguage(\"")).output(placeholder("generatedLanguage", "firstUpperCase")).output(literal("\", _language()).loadStashes(startingModel).as(")).output(placeholder("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n}")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}