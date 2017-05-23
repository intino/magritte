package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class AbstractGraphTemplate extends Template {

	protected AbstractGraphTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new AbstractGraphTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "graph"))).add(literal("package ")).add(mark("workingPackage", "LowerCase")).add(literal(";\n\nimport io.intino.tara.magritte.Graph;\n\npublic class AbstractGraph extends io.intino.tara.magritte.Graph {\n\n\tprotected io.intino.tara.magritte.Graph graph;\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tpublic GraphView(io.intino.tara.magritte.Graph graph) {\n\t\tthis.graph = graph;\n\t\tthis.graph.i18n().register(\"")).add(mark("name")).add(literal("\");\n\t}\n\n\tpublic void update() {\n\t\t")).add(mark("node", "update").multiple("\n")).add(literal("\n\t}\n\n\t@Override\n\tprotected void addNode(io.intino.tara.magritte.Node node) {\n\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n\t}\n\n\t@Override\n\tprotected void removeNode(io.intino.tara.magritte.Node node) {\n\t\t")).add(mark("node", "remove").multiple("\n")).add(literal("\n\t}\n\n\tpublic String message(String language, String key, Object... parameters) {\n\t\treturn graph.i18n().message(language, key, parameters);\n\t}\n\n\tpublic java.net.URL resourceAsMessage(String language, String key) {\n\t\treturn graph.loadResource(graph.i18n().message(language, key));\n\t}\n\n\tpublic java.util.Map<String,String> keysIn(String language) {\n\t\treturn graph.i18n().wordsIn(language);\n\t}\n\n\tpublic io.intino.tara.magritte.Concept concept(String concept) {\n\t\treturn graph.concept(concept);\n\t}\n\n\tpublic io.intino.tara.magritte.Concept concept(java.lang.Class<? extends io.intino.tara.magritte.Layer> layerClass) {\n\t\treturn graph.concept(layerClass);\n\t}\n\n\tpublic java.util.List<io.intino.tara.magritte.Concept> conceptList() {\n\t\treturn graph.conceptList();\n\t}\n\n\tpublic java.util.stream.Stream<io.intino.tara.magritte.Concept> conceptList(java.util.function.Predicate<io.intino.tara.magritte.Concept> filter) {\n\t\treturn graph.conceptList(filter);\n\t}\n\n\tpublic io.intino.tara.magritte.Node load(String node) {\n\t\treturn graph.load(node);\n\t}\n\n\tpublic void loadPaths(String... paths) {\n\t\tgraph.loadPaths(paths);\n\t}\n\n\tpublic void savePath(String... paths) {\n\t\tgraph.save(paths);\n\t}\n\n\tpublic void saveAll(String... excludedPaths) {\n\t\tgraph.saveAll(excludedPaths);\n\t}\n\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n\n\t")).add(mark("node", "filter").multiple("\n\n")).add(literal("\n\n\tpublic io.intino.tara.magritte.Graph graph() {\n\t\treturn graph;\n\t}\n\n\tpublic Create create() {\n\t\treturn new Create(\"Misc\", null);\n\t}\n\n\tpublic Create create(String path) {\n\t\treturn new Create(path, null);\n\t}\n\n\tpublic Create create(String path, String name) {\n\t\treturn new Create(path, name);\n\t}\n\n\tpublic Create clear() {\n\t\treturn new Clear();\n\t}\n\n\tpublic class Create {\n\t\tprivate final String path;\n\t\tprivate final String name;\n\n\t\tpublic Create(String path, String name) {\n\t\t\tthis.path = path;\n\t\t\tthis.name = name;\n\t\t}\n\n\t\t")).add(mark("node", "new").multiple("\n\n")).add(literal("\n\t}\n\n\tpublic class Clear {\n\t\t")).add(mark("node", "clear").multiple("\n\n")).add(literal("\n\t}\n}")),
			rule().add((condition("type", "instance")), (condition("trigger", "add"))).add(literal("if(node.id().equals(\"")).add(mark("generatedLanguage")).add(literal("#")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase", "javaValidWord")).add(literal(" = node.as(")).add(mark("conceptLayer", "reference")).add(literal(".class);")),
			rule().add((condition("type", "single")), not(condition("type", "instance")), (condition("trigger", "add"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase", "javaValidWord")).add(literal(" = node.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add(not(condition("type", "instance")), (condition("trigger", "add"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(node.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("trigger", "add"))),
			rule().add((condition("type", "instance")), (condition("trigger", "remove"))).add(literal("if(node.id().equals(\"")).add(mark("generatedLanguage")).add(literal("#")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase", "javaValidWord")).add(literal(" = null;")),
			rule().add((condition("type", "single")), not(condition("type", "instance")), (condition("trigger", "remove"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase", "javaValidWord")).add(literal(" = null;")),
			rule().add(not(condition("type", "instance")), (condition("trigger", "remove"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(node.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("trigger", "remove"))),
			rule().add((condition("type", "node & instance")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("conceptLayer")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "node & single")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List;")),
			rule().add((condition("type", "node & instance")), (condition("trigger", "update"))).add(literal("java.util.List<io.intino.tara.magritte.Node> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("List = this.graph.rootList(r -> r.id().equals(\"")).add(mark("generatedLanguage")).add(literal("#")).add(mark("name")).add(literal("\"));\nif(!")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("List.isEmpty()) this.")).add(mark("name", "firstLowercase", "javaValidWord")).add(literal(" = ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("List.get(0).as(")).add(mark("conceptLayer")).add(literal(".class);")),
			rule().add((condition("type", "node & single")), (condition("trigger", "update"))).add(mark("name", "firstLowerCase")).add(literal(" = this.graph.rootList(")).add(mark("qn", "reference")).add(literal(".class).stream().findFirst().orElse(null);")),
			rule().add((condition("type", "node")), (condition("trigger", "update"))).add(mark("name", "firstLowerCase")).add(literal("List = this.graph.rootList(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node & instance")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("conceptLayer")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";\n}")),
			rule().add((condition("type", "node & single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}")),
			rule().add((condition("type", "node")), not(condition("type", "single | instance")), (condition("trigger", "filter"))).add(literal("public java.util.stream.Stream<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> filter) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List.stream().filter(filter);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List.get(index);\n}")),
			rule().add((condition("type", "node & concept")), not(condition("type", "final | abstract | component | instance")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n\t")).add(mark("qn", "reference")).add(literal(" newElement = GraphView.this.graph.createRoot(")).add(mark("qn", "reference")).add(literal(".class, path, name).as(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(mark("variable", "assign").multiple("\n")).add(literal("\n\treturn newElement;\n}")),
			rule().add((condition("type", "node & concept")), not(condition("type", "single | final | abstract | component | instance")), (condition("trigger", "clear"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal(">... filters) {\n\tnew java.util.ArrayList<>(GraphView.this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("List()).forEach(io.intino.tara.magritte.Layer::delete);\n}"))
		);
		return this;
	}
}