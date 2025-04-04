package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class GraphLoaderTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(allTypes("graphLoader")).output(literal("package ")).output(placeholder("workingPackage", "lowercase")).output(literal(";\n\nimport ")).output(placeholder("language_workingPackage")).output(literal(".")).output(placeholder("language", "firstUpperCase")).output(literal("Graph;\nimport io.intino.magritte.framework.Store;\nimport io.intino.magritte.io.model.Stash;\n\npublic class GraphLoader {\n\tprivate static final String[] serialized = new String[] {\n\t\t")).output(placeholder("stash", "call").multiple(",\n")).output(literal("\n\t};\n\n\tpublic static ")).output(placeholder("language", "firstUpperCase")).output(literal("Graph load() {\n\t\treturn ")).output(placeholder("language", "firstUpperCase")).output(literal("Graph.load(stashes());\n\t}\n\n\tpublic static ")).output(placeholder("language", "firstUpperCase")).output(literal("Graph load(Store store) {\n\t\treturn ")).output(placeholder("language", "firstUpperCase")).output(literal("Graph.load(store, stashes());\n\t}\n\n\t")).output(placeholder("stash", "stashMethod").multiple("\n\n")).output(literal("\n\n\tprivate static Stash[] stashes() {\n\t\treturn java.util.Arrays.stream(serialized)\n\t\t\t\t.map(s -> java.util.Base64.getDecoder().decode(s))\n\t\t\t\t.map(s -> io.intino.magritte.io.StashDeserializer.stashFrom(s))\n\t\t\t\t.toArray(Stash[]::new);\n\t}\n}")));
		rules.add(rule().condition(all(allTypes("stash"), trigger("call"))).output(placeholder("part", "methodCall").multiple(" + ")));
		rules.add(rule().condition(trigger("methodcall")).output(literal("stash")).output(placeholder("name", "firstUpperCase")).output(placeholder("index")).output(literal("()")));
		rules.add(rule().condition(all(allTypes("stash"), trigger("stashmethod"))).output(placeholder("part", "method").multiple("\n\n")));
		rules.add(rule().condition(trigger("method")).output(literal("private static String stash")).output(placeholder("name", "firstUpperCase")).output(placeholder("index")).output(literal("() {\n\treturn \"")).output(placeholder("code")).output(literal("\";\n}")));
		rules.add(rule().condition(trigger("quoted")).output(literal("\"")).output(placeholder("")).output(literal("\"")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}