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
			rule().add((condition("type", "graph"))).add(literal("package ")).add(mark("workingPackage", "LowerCase")).add(literal(";\n\nimport io.intino.tara.magritte.Graph;\n\npublic class AbstractGraph extends io.intino.tara.magritte.GraphWrapper {\n\n\tprotected io.intino.tara.magritte.Graph graph;\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("\n\n\tprivate java.util.Map<String, Indexer> _index = _fillIndex();\n\n\tpublic AbstractGraph(io.intino.tara.magritte.Graph graph) {\n\t\tthis.graph = graph;\n\t\tthis.graph.i18n().register(\"")).add(mark("name")).add(literal("\");\n\t}\n\n\tpublic AbstractGraph(io.intino.tara.magritte.Graph graph, AbstractGraph wrapper) {\n\t\tthis.graph = graph;\n\t\tthis.graph.i18n().register(\"")).add(mark("name")).add(literal("\");\n\t\t")).add(mark("node", "clone").multiple("\n")).add(literal("\n\t}\n\n\tpublic <T extends io.intino.tara.magritte.GraphWrapper> T a$(Class<T> t) {\n\t\treturn this.core$().as(t);\n\t}\n\n    @Override\n\tpublic void update() {\n\t\tthis._index.values().forEach(v -> v.clear());\n\t\tgraph.rootList().forEach(r -> addNode$(r));\n\t}\n\n\t@Override\n\tprotected void addNode$(io.intino.tara.magritte.Node node) {\n\t\tfor (io.intino.tara.magritte.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).add(node);\n\t\tif (this._index.containsKey(node.id())) this._index.get(node.id()).add(node);\n\t}\n\n\t@Override\n\tprotected void removeNode$(io.intino.tara.magritte.Node node) {\n\t\tfor (io.intino.tara.magritte.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).remove(node);\n\t\tif (this._index.containsKey(node.id())) this._index.get(node.id()).remove(node);\n\t}\n\n\tpublic java.net.URL resourceAsMessage$(String language, String key) {\n\t\treturn graph.loadResource(graph.i18n().message(language, key));\n\t}\n\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("\n\n\t")).add(mark("node", "filter").multiple("\n\n")).add(literal("\n\n\tpublic io.intino.tara.magritte.Graph core$() {\n\t\treturn graph;\n\t}\n\n\tpublic io.intino.tara.magritte.utils.I18n i18n$() {\n\t\treturn graph.i18n();\n\t}\n\n\tpublic Create create() {\n\t\treturn new Create(\"Misc\", null);\n\t}\n\n\tpublic Create create(String stash) {\n\t\treturn new Create(stash, null);\n\t}\n\n\tpublic Create create(String stash, String name) {\n\t\treturn new Create(stash, name);\n\t}\n\n\tpublic Clear clear() {\n\t\treturn new Clear();\n\t}\n\n\tpublic class Create {\n\t\tprivate final String stash;\n\t\tprivate final String name;\n\n\t\tpublic Create(String stash, String name) {\n\t\t\tthis.stash = stash;\n\t\t\tthis.name = name;\n\t\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "new").multiple("\n\n"))).add(literal("\n\t}\n\n\tpublic class Clear {")).add(expression().add(literal("\n")).add(literal("\t    ")).add(mark("node", "clear").multiple("\n\n"))).add(literal("\n\t}\n\n\n\tprivate java.util.HashMap<String, Indexer> _fillIndex() {\n\t\treturn new java.util.HashMap<String, Indexer>() {{\n\t\t\t")).add(mark("node", "put").multiple("\n")).add(literal("\n\t\t}};\n\t}\n\n\tpublic static class Indexer {\n\t\tAdd add;\n\t\tRemove remove;\n\t\tIndexClear clear;\n\n\t\tpublic Indexer(Add add, Remove remove, IndexClear clear) {\n\t\t\tthis.add = add;\n\t\t\tthis.remove = remove;\n\t\t\tthis.clear = clear;\n\t\t}\n\n\t\tvoid add(io.intino.tara.magritte.Node node) {\n\t\t\tthis.add.add(node);\n\t\t}\n\n\t\tvoid remove(io.intino.tara.magritte.Node node) {\n\t\t\tthis.remove.remove(node);\n\t\t}\n\n\t\tvoid clear() {\n\t\t\tthis.clear.clear();\n\t\t}\n\t}\n\n\tinterface Add {\n\t\tvoid add(io.intino.tara.magritte.Node node);\n\t}\n\n\tinterface Remove {\n\t\tvoid remove(io.intino.tara.magritte.Node node);\n\t}\n\n\tinterface IndexClear {\n\t\tvoid clear();\n\t}\n}")),
			rule().add((condition("type", "instance")), (condition("trigger", "put"))).add(literal("put(\"")).add(mark("generatedLanguage", "FirstUpperCase")).add(literal("#")).add(mark("name")).add(literal("\", new Indexer(node -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = node.as(")).add(mark("conceptLayer", "reference")).add(literal(".class), node -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = null, () -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = null));")),
			rule().add(not(condition("type", "instance")), (condition("type", "single")), (condition("trigger", "put"))).add(literal("put(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\", new Indexer(node -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = node.as(")).add(mark("qn", "reference")).add(literal(".class), node -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = null, () -> ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = null));")),
			rule().add(not(condition("type", "instance")), (condition("trigger", "put"))).add(literal("put(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\", new Indexer(node -> ")).add(mark("name", "FirstLowerCase")).add(literal("List.add(node.as(")).add(mark("qn", "reference")).add(literal(".class)), node -> ")).add(mark("name", "FirstLowerCase")).add(literal("List.remove(node.as(")).add(mark("qn", "reference")).add(literal(".class)), () -> ")).add(mark("name", "FirstLowerCase")).add(literal("List.clear()));")),
			rule().add((condition("trigger", "add"))),
			rule().add((condition("type", "node & instance")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("conceptLayer")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "node & single")), (condition("trigger", "declaration"))).add(literal("private ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "declaration"))).add(literal("private java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "FirstLowerCase")).add(literal("List = new java.util.ArrayList<>();")),
			rule().add((condition("type", "node & instance")), (condition("trigger", "update"))).add(literal("io.intino.tara.magritte.Node ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("Node = this.graph.load(\"")).add(mark("generatedLanguage", "FirstUpperCase")).add(literal("#")).add(mark("name")).add(literal("\");\nif (")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("Node != null) this.")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("Node.as(")).add(mark("conceptLayer")).add(literal(".class);")),
			rule().add((condition("type", "node & single")), (condition("trigger", "update"))).add(mark("name", "FirstLowerCase")).add(literal(" = this.graph.rootList(")).add(mark("qn", "reference")).add(literal(".class).stream().findFirst().orElse(null);")),
			rule().add((condition("type", "node")), (condition("trigger", "update"))).add(mark("name", "FirstLowerCase")).add(literal("List = this.graph.rootList(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "node")), (condition("type", "instance | single")), (condition("trigger", "clone"))).add(literal("this.")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(" = wrapper.")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "node")), (condition("trigger", "clone"))).add(literal("this.")).add(mark("name", "FirstLowerCase")).add(literal("List = new java.util.ArrayList<>(wrapper.")).add(mark("name", "FirstLowerCase")).add(literal("List);")),
			rule().add((condition("type", "node & instance")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("conceptLayer")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("() {\n\treturn ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(";\n}")),
			rule().add((condition("type", "node & single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("() {\n\treturn ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal(";\n}")),
			rule().add((condition("type", "node")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "FirstLowerCase")).add(literal("List() {\n\treturn ")).add(mark("name", "FirstLowerCase")).add(literal("List;\n}")),
			rule().add((condition("type", "node")), not(condition("type", "single | instance")), (condition("trigger", "filter"))).add(literal("public java.util.stream.Stream<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "FirstLowerCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> filter) {\n\treturn ")).add(mark("name", "FirstLowerCase")).add(literal("List.stream().filter(filter);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("(int index) {\n\treturn ")).add(mark("name", "FirstLowerCase")).add(literal("List.get(index);\n}")),
			rule().add((condition("type", "node & concept")), not(condition("type", "final | abstract | component | instance")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n\t")).add(mark("qn", "reference")).add(literal(" newElement = AbstractGraph.this.graph.createRoot(")).add(mark("qn", "reference")).add(literal(".class, stash, this.name).a$(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(mark("variable", "assign").multiple("\n")).add(literal("\n\treturn newElement;\n}")),
			rule().add((condition("type", "node & concept")), not(condition("type", "single | final | abstract | component | instance")), (condition("trigger", "clear"))).add(literal("public void ")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> filter) {\n\tnew java.util.ArrayList<>(AbstractGraph.this.")).add(mark("name", "FirstLowerCase", "javaValidWord")).add(literal("List()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);\n}"))
		);
		return this;
	}
}