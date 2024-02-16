package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class AbstractGraphTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("graph"))).output(literal("package ")).output(mark("workingPackage", "LowerCase")).output(literal(";\n\nimport io.intino.magritte.framework.Graph;\n\npublic class AbstractGraph extends io.intino.magritte.framework.GraphWrapper {\n\tprotected io.intino.magritte.framework.Graph graph;\n\t")).output(mark("node", "declaration").multiple("\n")).output(literal("\n\n\tprivate java.util.Map<String, Indexer> _index = _fillIndex();\n\n\tpublic AbstractGraph(io.intino.magritte.framework.Graph graph) {\n\t\tthis.graph = graph;\n\t\tthis.graph.i18n().register(\"")).output(mark("name")).output(literal("\");\n\t}\n\n\tpublic AbstractGraph(io.intino.magritte.framework.Graph graph, AbstractGraph wrapper) {\n\t\tthis.graph = graph;\n\t\tthis.graph.i18n().register(\"")).output(mark("name")).output(literal("\");\n\t\t")).output(mark("node", "clone").multiple("\n")).output(literal("\n\t}\n\n\tpublic <T extends io.intino.magritte.framework.GraphWrapper> T a$(Class<T> t) {\n\t\treturn this.core$().as(t);\n\t}\n\n    @Override\n\tpublic void update() {\n\t\tthis._index.values().forEach(v -> v.clear());\n\t\tgraph.rootList().forEach(r -> addNode$(r));\n\t}\n\n\t@Override\n\tprotected void addNode$(io.intino.magritte.framework.Node node) {\n\t\tfor (io.intino.magritte.framework.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).add(node);\n\t\tif (this._index.containsKey(node.id())) this._index.get(node.id()).add(node);\n\t}\n\n\t@Override\n\tprotected void removeNode$(io.intino.magritte.framework.Node node) {\n\t\tfor (io.intino.magritte.framework.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).remove(node);\n\t\tif (this._index.containsKey(node.id())) this._index.get(node.id()).remove(node);\n\t}\n\n\tpublic java.net.URL resourceAsMessage$(String language, String key) {\n\t\treturn graph.loadResource(graph.i18n().message(language, key));\n\t}\n\n\t")).output(mark("node", "getter").multiple("\n\n")).output(literal("\n\n\t")).output(mark("node", "filter").multiple("\n\n")).output(literal("\n\n\tpublic io.intino.magritte.framework.Graph core$() {\n\t\treturn graph;\n\t}\n\n\tpublic io.intino.magritte.framework.utils.I18n i18n$() {\n\t\treturn graph.i18n();\n\t}\n\n\tpublic Create create() {\n\t\treturn new Create(\"Misc\", null);\n\t}\n\n\tpublic Create create(String stash) {\n\t\treturn new Create(stash, null);\n\t}\n\n\tpublic Create create(String stash, String name) {\n\t\treturn new Create(stash, name);\n\t}\n\n\tpublic Clear clear() {\n\t\treturn new Clear();\n\t}\n\n\tpublic class Create {\n\t\tprivate final String stash;\n\t\tprivate final String name;\n\n\t\tpublic Create(String stash, String name) {\n\t\t\tthis.stash = stash;\n\t\t\tthis.name = name;\n\t\t}\n\n\t\t")).output(expression().output(mark("node", "new").multiple("\n\n"))).output(literal("\n\t}\n\n\tpublic class Clear {\n\t    ")).output(expression().output(mark("node", "clear").multiple("\n\n"))).output(literal("\n\t}\n\n\n\tprivate java.util.HashMap<String, Indexer> _fillIndex() {\n\t\tjava.util.HashMap<String, Indexer> map = new java.util.HashMap<>();\n\t\t")).output(expression().output(mark("node", "put").multiple("\n"))).output(literal("\n\t\treturn map;\n\t}\n\n\tpublic static io.intino.magritte.io.model.Stash[] _language() {\n\t\treturn new io.intino.magritte.io.model.Stash[]{")).output(expression().output(mark("parentPackage")).output(literal(".AbstractGraph._language()")).output(literal("[")).output(literal("0")).output(literal("]")).output(literal(", "))).output(literal("stash()};\n\t}\n\n\tprivate static io.intino.magritte.io.model.Stash stash() {\n\t\tString content = \"")).output(mark("stash")).output(literal("\";\n\t\treturn io.intino.magritte.io.StashDeserializer.stashFrom(java.util.Base64.getDecoder().decode(content));\n\t}\n\n\tpublic static class Indexer {\n\t\tAdd add;\n\t\tRemove remove;\n\t\tIndexClear clear;\n\n\t\tpublic Indexer(Add add, Remove remove, IndexClear clear) {\n\t\t\tthis.add = add;\n\t\t\tthis.remove = remove;\n\t\t\tthis.clear = clear;\n\t\t}\n\n\t\tvoid add(io.intino.magritte.framework.Node node) {\n\t\t\tthis.add.add(node);\n\t\t}\n\n\t\tvoid remove(io.intino.magritte.framework.Node node) {\n\t\t\tthis.remove.remove(node);\n\t\t}\n\n\t\tvoid clear() {\n\t\t\tthis.clear.clear();\n\t\t}\n\t}\n\n\tinterface Add {\n\t\tvoid add(io.intino.magritte.framework.Node node);\n\t}\n\n\tinterface Remove {\n\t\tvoid remove(io.intino.magritte.framework.Node node);\n\t}\n\n\tinterface IndexClear {\n\t\tvoid clear();\n\t}\n}")),
				rule().condition((type("instance")), (trigger("put"))).output(literal("map.put(\"")).output(mark("generatedLanguage", "FirstUpperCase")).output(literal("#")).output(mark("name")).output(literal("\", new Indexer(node -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = node.as(")).output(mark("conceptLayer", "reference")).output(literal(".class), node -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = null, () -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = null));")),
				rule().condition(not(type("instance")), (type("single")), (trigger("put"))).output(literal("map.put(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\", new Indexer(node -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = node.as(")).output(mark("qn", "reference")).output(literal(".class), node -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = null, () -> ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = null));")),
				rule().condition(not(type("instance")), (trigger("put"))).output(literal("map.put(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\", new Indexer(node -> ")).output(mark("name", "FirstLowerCase")).output(literal("List.add(node.as(")).output(mark("qn", "reference")).output(literal(".class)), node -> ")).output(mark("name", "FirstLowerCase")).output(literal("List.remove(node.as(")).output(mark("qn", "reference")).output(literal(".class)), () -> ")).output(mark("name", "FirstLowerCase")).output(literal("List.clear()));")),
				rule().condition((trigger("add"))),
				rule().condition((allTypes("node", "instance")), (trigger("declaration"))).output(literal("private ")).output(mark("conceptLayer")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(";")),
				rule().condition((allTypes("node", "single")), (trigger("declaration"))).output(literal("private ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(";")),
				rule().condition((type("node")), (trigger("declaration"))).output(literal("private java.util.List<")).output(mark("qn", "reference")).output(literal("> ")).output(mark("name", "FirstLowerCase")).output(literal("List = new java.util.ArrayList<>();")),
				rule().condition((allTypes("node", "instance")), (trigger("update"))).output(literal("io.intino.magritte.framework.Node ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("Node = this.graph.load(\"")).output(mark("generatedLanguage", "FirstUpperCase")).output(literal("#")).output(mark("name")).output(literal("\");\nif (")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("Node != null) this.")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("Node.as(")).output(mark("conceptLayer")).output(literal(".class);")),
				rule().condition((allTypes("node", "single")), (trigger("update"))).output(mark("name", "FirstLowerCase")).output(literal(" = this.graph.rootList(")).output(mark("qn", "reference")).output(literal(".class).stream().findFirst().orElse(null);")),
				rule().condition((type("node")), (trigger("update"))).output(mark("name", "FirstLowerCase")).output(literal("List = this.graph.rootList(")).output(mark("qn", "reference")).output(literal(".class);")),
				rule().condition((type("node")), (anyTypes("instance", "single")), (trigger("clone"))).output(literal("this.")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(" = wrapper.")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(";")),
				rule().condition((type("node")), (trigger("clone"))).output(literal("this.")).output(mark("name", "FirstLowerCase")).output(literal("List = new java.util.ArrayList<>(wrapper.")).output(mark("name", "FirstLowerCase")).output(literal("List);")),
				rule().condition((allTypes("node", "instance")), (trigger("getter"))).output(literal("public ")).output(mark("conceptLayer")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("() {\n\treturn ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(";\n}")),
				rule().condition((allTypes("node", "single")), (trigger("getter"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("() {\n\treturn ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal(";\n}")),
				rule().condition((type("node")), (trigger("getter"))).output(literal("public java.util.List<")).output(mark("qn", "reference")).output(literal("> ")).output(mark("name", "FirstLowerCase")).output(literal("List() {\n\treturn ")).output(mark("name", "FirstLowerCase")).output(literal("List;\n}")),
				rule().condition((type("node")), not(anyTypes("single", "instance")), (trigger("filter"))).output(literal("public java.util.stream.Stream<")).output(mark("qn", "reference")).output(literal("> ")).output(mark("name", "FirstLowerCase")).output(literal("List(java.util.function.Predicate<")).output(mark("qn", "reference")).output(literal("> filter) {\n\treturn ")).output(mark("name", "FirstLowerCase")).output(literal("List.stream().filter(filter);\n}\n\npublic ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("(int index) {\n\treturn ")).output(mark("name", "FirstLowerCase")).output(literal("List.get(index);\n}")),
				rule().condition((allTypes("node", "concept")), not(anyTypes("final", "abstract", "component", "instance")), (trigger("new"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("(")).output(expression().output(mark("variable", "parameters").multiple(", "))).output(literal(") {\n\t")).output(mark("qn", "reference")).output(literal(" newElement = AbstractGraph.this.graph.createRoot(")).output(mark("qn", "reference")).output(literal(".class, stash, this.name).a$(")).output(mark("qn", "reference")).output(literal(".class);\n\t")).output(expression().output(mark("variable", "assign").multiple("\n"))).output(literal("\n\treturn newElement;\n}")),
				rule().condition((allTypes("node", "concept")), not(anyTypes("single", "final", "abstract", "component", "instance")), (trigger("clear"))).output(literal("public void ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("(java.util.function.Predicate<")).output(mark("qn", "reference")).output(literal("> filter) {\n\tnew java.util.ArrayList<>(AbstractGraph.this.")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("List()).stream().filter(filter).forEach(io.intino.magritte.framework.Layer::delete$);\n}")),
				rule().condition((trigger("quoted"))).output(literal("\"")).output(mark("value")).output(literal("\""))
		);
	}
}