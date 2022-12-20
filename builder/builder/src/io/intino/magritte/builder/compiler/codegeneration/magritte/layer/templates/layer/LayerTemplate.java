package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class LayerTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("Layer", "decorable"))).output(literal("package ")).output(mark("package", "javaValidName", "lowercase")).output(expression().output(literal(".")).output(mark("container", "lowercase"))).output(literal(";\n\npublic ")).output(expression().output(mark("abstract"))).output(literal(" class ")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal(" extends Abstract")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal(" {\n\tpublic ")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal("(io.intino.magritte.framework.Node node) {\n\t\tsuper(node);\n\t}\n\n\t")).output(expression().output(mark("decorableNode").multiple("\n\n"))).output(literal("\n}")),
				rule().condition((trigger("decorablenode"))).output(literal("public static ")).output(expression().output(mark("abstract"))).output(literal(" class ")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal(" extends Abstract")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal(" {\n\tpublic ")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal("(io.intino.magritte.framework.Node node) {\n\t\tsuper(node);\n\t}\n\n\t")).output(expression().output(mark("decorableNode").multiple("\n\n"))).output(literal("\n}")),
				rule().condition((type("Layer"))).output(literal("package ")).output(mark("package", "javaValidName", "lowercase")).output(literal(";\n\nimport ")).output(mark("workingPackage", "javaValidName", "lowercase")).output(literal(".*;\n")).output(expression().output(mark("imports").multiple("\n"))).output(literal("\n\n")).output(mark("node")),
				rule().condition((allTypes("single", "owner")), not(anyTypes("overriden", "instance")), (trigger("add"))).output(literal("if (node.is(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\")) this.")).output(mark("name", "FirstLowerCase", "javaValidName")).output(literal(" = node.as(")).output(mark("qn", "reference")).output(literal(".class);")),
				rule().condition((type("owner")), not(anyTypes("overriden", "instance")), (trigger("add"))).output(literal("if (node.is(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\")) this.")).output(mark("name", "toCamelCase", "FirstLowerCase")).output(literal("List.add(node.as(")).output(mark("qn", "reference")).output(literal(".class));")),
				rule().condition((type("overriden")), (trigger("add"))),
				rule().condition((allTypes("single", "owner")), not(anyTypes("overriden", "instance")), (trigger("remove"))).output(literal("if (node.is(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\")) this.")).output(mark("name", "FirstLowerCase", "javaValidName")).output(literal(" = null;")),
				rule().condition((type("owner")), not(anyTypes("overriden", "instance")), (trigger("remove"))).output(literal("if (node.is(\"")).output(mark("stashQn", "noPackage", "withDollar")).output(literal("\")) this.")).output(mark("name", "toCamelCase", "FirstLowerCase")).output(literal("List.remove(node.as(")).output(mark("qn", "reference")).output(literal(".class));")),
				rule().condition((type("overriden")), (trigger("remove"))),
				rule().condition((type("node")), not(anyTypes("aspect", "final", "instance", "abstract")), (trigger("new"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidName")).output(literal("(")).output(mark("variable", "parameters").multiple(", ")).output(literal(") {\n\t")).output(mark("qn", "reference")).output(literal(" newElement = core$().graph().concept(")).output(mark("qn", "reference")).output(literal(".class).createNode(this.name, core$()).as(")).output(mark("qn", "reference")).output(literal(".class);\n\t")).output(mark("variable", "assign").multiple("\n")).output(literal("\n\treturn newElement;\n}")),
				rule().condition((type("node")), not(anyTypes("aspect", "single", "final", "instance", "abstract", "overriden")), (trigger("clear"))).output(literal("public void ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("(java.util.function.Predicate<")).output(mark("qn", "reference")).output(literal("> filter) {\n\tnew java.util.ArrayList<>(")).output(mark("name", "FirstLowerCase")).output(literal("List()).stream().filter(filter).forEach(io.intino.magritte.framework.Layer::delete$);\n}")),
				rule().condition((allTypes("node", "create")), not(anyTypes("aspect", "final", "instance")), (trigger("new"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" ")).output(mark("name", "FirstLowerCase", "javaValidName")).output(literal("(")).output(mark("variable", "parameters").multiple(", ")).output(literal(") {\n\t")).output(mark("qn", "reference")).output(literal(" newElement = core$().graph().concept(")).output(mark("qn", "reference")).output(literal(".class).createNode(this.name, core$()).as(")).output(mark("qn", "reference")).output(literal(".class);\n\t")).output(mark("variable", "assign").multiple("\n")).output(literal("\n\treturn newElement;\n}")),
				rule().condition((allTypes("node", "create")), not(anyTypes("single", "final", "instance")), (trigger("clear"))).output(literal("public void ")).output(mark("name", "FirstLowerCase", "javaValidWord")).output(literal("(java.util.function.Predicate<")).output(mark("qn", "reference")).output(literal("> filter) {\n\tnew java.util.ArrayList<>(")).output(mark("name", "FirstLowerCase")).output(literal("List()).stream().filter(filter).forEach(io.intino.magritte.framework.Layer::delete$);\n}")),
				rule().condition((allTypes("node", "owner")), (trigger("new"))),
				rule().condition((allTypes("node", "owner")), (trigger("clear"))),
				rule().condition((type("availableAspect")), not(type("abstract")), (attribute("variable")), (trigger("availableaspect"))).output(literal("public ")).output(mark("qn")).output(literal(" as")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\treturn a$(")).output(mark("qn", "reference")).output(literal(".class);\n}\n\npublic ")).output(mark("qn")).output(literal(" as")).output(mark("name", "FirstUpperCase")).output(literal("(")).output(expression().output(mark("variable", "parameters").multiple(", "))).output(literal(") {\n\t")).output(mark("qn", "reference")).output(literal(" newElement = core$().addAspect(")).output(mark("qn", "reference")).output(literal(".class);\n\t")).output(mark("variable", "assign").multiple("\n")).output(literal("\n\treturn newElement;\n}\n\npublic boolean is")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\treturn core$().is(")).output(mark("qn", "reference")).output(literal(".class);\n}\n\npublic void remove")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\tcore$().removeAspect(")).output(mark("qn", "reference")).output(literal(".class);\n}")),
				rule().condition((type("availableAspect")), not(type("abstract")), (trigger("availableaspect"))).output(literal("\npublic ")).output(mark("qn", "reference")).output(literal(" as")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\tio.intino.magritte.framework.Layer as = a$(")).output(mark("qn", "reference")).output(literal(".class);\n\treturn as != null ? (")).output(mark("qn", "reference")).output(literal(") as : ")).output(expression().output(mark("abstract")).next(expression().output(literal("core")).output(literal("$")).output(literal("().addAspect(")).output(mark("qn", "reference")).output(literal(".class)")))).output(literal(";\n}\n\npublic boolean is")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\treturn core$().is(")).output(mark("qn", "reference")).output(literal(".class);\n}\n\npublic void remove")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\tcore$().removeAspect(")).output(mark("qn", "reference")).output(literal(".class);\n}")),
				rule().condition((type("availableAspect")), (trigger("availableaspect"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" as")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\treturn a$(")).output(mark("qn", "reference")).output(literal(".class);\n}\n\npublic boolean is")).output(mark("name", "FirstUpperCase")).output(literal("() {\n\treturn core$().is(")).output(mark("qn", "reference")).output(literal(".class);\n}")),
				rule().condition((type("core")), (trigger("core"))).output(literal("public ")).output(mark("qn", "reference")).output(literal(" as")).output(mark("name")).output(literal("() {\n\treturn (")).output(mark("qn", "reference")).output(literal(") a$(")).output(mark("qn", "reference")).output(literal(".class);\n}")),
				rule().condition((type("aspect")), not(type("overriden")), (trigger("aspectassign"))).output(literal("if (layer instanceof ")).output(mark("qn", "reference")).output(literal(") _")).output(mark("name", "FirstLowerCase")).output(literal(" = (")).output(mark("qn", "reference")).output(literal(") layer;")),
				rule().condition((type("constraint")), (trigger("aspectassign"))).output(literal("if (layer instanceof ")).output(mark("qn", "reference")).output(literal(") _")).output(mark("name", "FirstLowerCase")).output(literal(" = (")).output(mark("qn", "reference")).output(literal(") layer;")),
				rule().condition((trigger("decorable"))).output(literal("Abstract")),
				rule().condition(not(type("target")), (type("nodeimpl")), not(type("instance")), (trigger("node"))).output(literal("public ")).output(expression().output(mark("inner"))).output(literal(" ")).output(expression().output(mark("abstract"))).output(literal(" class ")).output(mark("decorable")).output(expression().output(mark("name", "javaValidName", "FirstUpperCase"))).output(literal(" ")).output(expression().output(literal("extends ")).output(mark("parent")).next(expression().output(literal(" extends io.intino.magritte.framework.Layer")))).output(literal(" ")).output(expression().output(literal("implements ")).output(mark("flag", "tag").multiple(", "))).output(literal(" {\n\t")).output(expression().output(mark("variable", "declaration").multiple("\n"))).output(literal("\n\t")).output(expression().output(mark("node", "declaration").multiple("\n"))).output(literal("\n\t")).output(expression().output(mark("aspect", "declaration").multiple("\n"))).output(literal("\n\t")).output(expression().output(mark("constraint", "declaration").multiple("\n"))).output(literal("\n\t")).output(expression().output(mark("metaType"))).output(literal("\n\t")).output(expression().output(mark("metaAspect").multiple("\n"))).output(literal("\n\n\tpublic ")).output(mark("decorable")).output(mark("name", "javaValidName", "FirstUpperCase")).output(literal("(io.intino.magritte.framework.Node node) {\n\t\tsuper(node);\n\t\t")).output(expression().output(literal("_metaType = a")).output(literal("$")).output(literal("(")).output(mark("metaType", "typeInit")).output(literal(".class);"))).output(literal("\n\t\t")).output(expression().output(literal("_")).output(mark("metaAspect", "name")).output(literal(" = a")).output(literal("$")).output(literal("(")).output(mark("metaAspect", "typeInit")).output(literal(".class);"))).output(literal("\n\t}\n\n\t")).output(expression().output(mark("variable", "getter").multiple("\n\n"))).output(literal("\n\n\t")).output(expression().output(mark("variable", "setter").multiple("\n\n"))).output(literal("\n\n\t")).output(expression().output(mark("node", "getter").multiple("\n\n"))).output(literal("\n\n\t")).output(expression().output(mark("node", "setter").multiple("\n\n"))).output(literal("\n\n\t")).output(expression().output(mark("availableAspect").multiple("\n\n"))).output(literal("\n\t")).output(expression().output(mark("core"))).output(literal("\n\n\t")).output(expression().output(literal("protected java.util.List<io.intino.magritte.framework.Node> componentList")).output(literal("$")).output(literal("() {")).output(literal("\n")).output(literal("\tjava.util.Set<io.intino.magritte.framework.Node> components = new java.util.LinkedHashSet<>(super.componentList")).output(literal("$")).output(literal("());")).output(literal("\n")).output(literal("\t")).output(mark("node", "componentList").multiple("\n")).output(literal("\n")).output(literal("\treturn new java.util.ArrayList<>(components);")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t@Override\n\tprotected java.util.Map<java.lang.String, java.util.List<?>> variables$() {\n\t\tjava.util.Map<java.lang.String, java.util.List<?>> map = new java.util.LinkedHashMap<>(")).output(mark("parent", "var")).output(literal(");\n\t\t")).output(expression().output(mark("variable", "list").multiple("\n"))).output(literal("\n\t\treturn map;\n\t}\n\n\t")).output(expression().output(literal("@Override")).output(literal("\n")).output(literal("protected void addNode")).output(literal("$")).output(literal("(io.intino.magritte.framework.Node node) {")).output(literal("\n")).output(literal("\tsuper.addNode")).output(literal("$")).output(literal("(node);")).output(literal("\n")).output(literal("\t")).output(mark("node", "add").multiple("\n")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t")).output(expression().output(literal("@Override")).output(literal("\n")).output(literal("protected void removeNode")).output(literal("$")).output(literal("(io.intino.magritte.framework.Node node) {")).output(literal("\n")).output(literal("\tsuper.removeNode")).output(literal("$")).output(literal("(node);")).output(literal("\n")).output(literal("\t")).output(mark("node", "remove").multiple("\n")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t@Override\n\tprotected void load$(java.lang.String name, java.util.List<?> values) {\n\t\tsuper.load$(name, values);\n\t\t")).output(expression().output(mark("aspect", "init"))).output(literal("\n\t\t")).output(expression().output(mark("metaType", "metaTypeLoad"))).output(literal("\n\t\t")).output(expression().output(mark("metaAspect", "metaAspectLoad"))).output(literal("\n\t\t")).output(expression().output(mark("variable", "init").multiple("\nelse "))).output(literal("\n\t}\n\n\t@Override\n\tprotected void set$(java.lang.String name, java.util.List<?> values) {\n\t\tsuper.set$(name, values);\n\t\t")).output(expression().output(mark("aspect", "set"))).output(literal("\n\t\t")).output(expression().output(mark("metaType", "metaTypeSet"))).output(literal("\n\t\t")).output(expression().output(mark("metaAspect", "metaAspectSet"))).output(literal("\n\t\t")).output(expression().output(mark("variable", "set").multiple("\nelse "))).output(literal("\n\t}\n\n\t")).output(expression().output(literal("@Override")).output(literal("\n")).output(literal("protected void sync")).output(literal("$")).output(literal("(io.intino.magritte.framework.Layer layer) {")).output(literal("\n")).output(literal("\tsuper.sync")).output(literal("$")).output(literal("(layer);")).output(literal("\n")).output(literal("\t")).output(mark("aspect", "aspectAssign").multiple("\nelse ")).output(literal("\n")).output(literal("\t")).output(mark("constraint", "aspectAssign").multiple("\nelse ")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t")).output(expression().output(literal("public Create create() {")).output(literal("\n")).output(literal("\treturn new Create(null);")).output(literal("\n")).output(literal("}")).output(literal("\n")).output(literal("\n")).output(literal("public Create create(java.lang.String name) {")).output(literal("\n")).output(literal("\treturn new Create(name);")).output(literal("\n")).output(literal("}")).output(literal("\n")).output(literal("\n")).output(literal("public class Create ")).output(mark("parentName", "parentCreate")).output(literal(" {")).output(literal("\n")).output(literal("\t")).output(mark("parentSuper", "parentvariable")).output(literal("\n")).output(literal("\n")).output(literal("\tpublic Create(java.lang.String name) {")).output(literal("\n")).output(literal("\t\t")).output(mark("parentSuper", "parentinit")).output(literal("\n")).output(literal("\t}")).output(literal("\n")).output(literal("\n")).output(literal("\t")).output(mark("node", "new").multiple("\n\n")).output(literal("\n")).output(literal("\t")).output(mark("create", "new").multiple("\n\n")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t")).output(expression().output(literal("public Clear clear() {")).output(literal("\n")).output(literal("\treturn new Clear();")).output(literal("\n")).output(literal("}")).output(literal("\n")).output(literal("\n")).output(literal("public class Clear ")).output(mark("parentClearName", "parentClear")).output(literal(" {")).output(literal("\n")).output(literal("\t")).output(mark("node", "clear").multiple("\n\n")).output(literal("\n")).output(literal("}"))).output(literal("\n\n\t")).output(expression().output(mark("node").multiple("\n"))).output(literal("\n\n\tpublic ")).output(mark("workingPackage", "javaValidName", "lowerCase")).output(literal(".")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph graph() {\n\t\treturn (")).output(mark("workingPackage", "javaValidName", "lowerCase")).output(literal(".")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph) core$().graph().as(")).output(mark("workingPackage", "javaValidName", "lowerCase")).output(literal(".")).output(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).output(literal("Graph.class);\n\t}\n}\n")),
				rule().condition((trigger("parentcreate"))).output(literal("extends ")).output(mark("")).output(literal(".Create")),
				rule().condition((trigger("parentclear"))).output(literal("extends ")).output(mark("")).output(literal(".Clear")),
				rule().condition((type("metaAspect")), (trigger("name"))).output(mark("name", "javaValidName", "FirstLowerCase")),
				rule().condition((type("metaAspect")), (trigger("typeinit"))).output(mark("type")),
				rule().condition((trigger("typeinit"))).output(mark("")),
				rule().condition((attribute("", "true")), (trigger("parentinit"))).output(literal("super(name);")),
				rule().condition((attribute("", "false")), (trigger("parentinit"))).output(literal("this.name = name;")),
				rule().condition((attribute("", "true")), (trigger("parentvariable"))),
				rule().condition((attribute("", "false")), (trigger("parentvariable"))).output(literal("protected final java.lang.String name;")),
				rule().condition((trigger("metatypeload"))).output(literal("core$().load(_metaType, name, values);")),
				rule().condition((trigger("metatypeset"))).output(literal("core$().set(_metaType, name, values);")),
				rule().condition((trigger("metaaspectload"))).output(literal("core$().load(_")).output(mark("name", "FirstLowerCase")).output(literal(", name, values);")),
				rule().condition((trigger("metaaspectset"))).output(literal("core$().set(_")).output(mark("name", "FirstLowerCase")).output(literal(", name, values);")),
				rule().condition((trigger("metatype"))).output(literal("protected ")).output(mark("")).output(literal(" _metaType;")),
				rule().condition((trigger("metaaspect"))).output(literal("protected ")).output(mark("type")).output(literal(" _")).output(mark("name", "FirstLowerCase")).output(literal(";")),
				rule().condition((trigger("tag"))).output(literal("io.intino.magritte.framework.tags.")).output(mark("", "lowerCase", "FirstUpperCase")),
				rule().condition((attribute("layer")), (trigger("var"))),
				rule().condition((trigger("var"))).output(literal("super.variables$()")),
				rule().condition((attribute("", "true")), (trigger("inner"))).output(literal("static")),
				rule().condition((attribute("", "true")), (trigger("abstract"))).output(literal("abstract"))
		);
	}
}