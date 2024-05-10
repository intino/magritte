package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class GraphLoaderTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("graphLoader"))).output(literal("package ")).output(mark("workingPackage", "lowercase")).output(literal(";\n\nimport ")).output(mark("language_workingPackage")).output(literal(".")).output(mark("language", "firstUpperCase")).output(literal("Graph;\nimport io.intino.magritte.framework.Store;\nimport io.intino.magritte.io.model.Stash;\n\npublic class GraphLoader {\n\tprivate static final String[] serialized = new String[] {\n\t\t")).output(mark("stash", "call").multiple(",\n")).output(literal("\n\t};\n\n\tpublic static ")).output(mark("language", "firstUpperCase")).output(literal("Graph load() {\n\t\treturn ")).output(mark("language", "firstUpperCase")).output(literal("Graph.load(stashes());\n\t}\n\n\tpublic static ")).output(mark("language", "firstUpperCase")).output(literal("Graph load(Store store) {\n\t\treturn ")).output(mark("language", "firstUpperCase")).output(literal("Graph.load(store, stashes());\n\t}\n\n\t")).output(mark("stash", "stashMethod").multiple("\n\n")).output(literal("\n\n\tprivate static Stash[] stashes() {\n\t\treturn java.util.Arrays.stream(serialized)\n\t\t\t\t.map(s -> java.util.Base64.getDecoder().decode(s))\n\t\t\t\t.map(s -> io.intino.magritte.io.StashDeserializer.stashFrom(s))\n\t\t\t\t.toArray(Stash[]::new);\n\t}\n}")),
				rule().condition((type("stash")), (trigger("call"))).output(mark("part", "methodCall").multiple(" + ")),
				rule().condition((trigger("methodcall"))).output(literal("stash")).output(mark("name", "firstUpperCase")).output(mark("index")).output(literal("()")),
				rule().condition((type("stash")), (trigger("stashmethod"))).output(mark("part", "method").multiple("\n\n")),
				rule().condition((trigger("method"))).output(literal("private static String stash")).output(mark("name", "firstUpperCase")).output(mark("index")).output(literal("() {\n\treturn \"")).output(mark("code")).output(literal("\";\n}")),
				rule().condition((trigger("quoted"))).output(literal("\"")).output(mark("")).output(literal("\""))
		);
	}
}