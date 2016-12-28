package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class LayerTemplate extends Template {

	protected LayerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new LayerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Layer"))).add(literal("package ")).add(mark("package", "javaValidName", "lowercase")).add(literal(";\n\nimport ")).add(mark("workingPackage", "javaValidName", "lowercase")).add(literal(".*;\n")).add(expression().add(mark("imports").multiple("\n")).add(literal("\n"))).add(literal("\n\n")).add(mark("node")),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden | instance")), (condition("trigger", "add"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = node.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden | instance")), (condition("trigger", "add"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(node.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "add"))),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden | instance")), (condition("trigger", "remove"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = null;")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden | instance")), (condition("trigger", "remove"))).add(literal("if (node.is(\"")).add(mark("stashQn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(node.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "remove"))),
			rule().add((condition("type", "node")), not(condition("type", "final | instance | abstract")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("variable", "parameters").multiple(", ")).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = graph().concept(")).add(mark("qn", "reference")).add(literal(".class).createNode(name, node()).as(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(mark("variable", "assign").multiple("\n")).add(literal(" |:\n    return newElement;\n}")),
			rule().add((condition("type", "node & create")), not(condition("type", "final | instance")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("variable", "parameters").multiple(", ")).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = graph().concept(")).add(mark("qn", "reference")).add(literal(".class).createNode(name, node()).as(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(mark("variable", "assign").multiple("\n")).add(literal(" |:\n    return newElement;\n}")),
			rule().add((condition("type", "node & owner")), (condition("trigger", "new"))),
			rule().add((condition("type", "availableFacet")), (condition("slot", "variable")), (condition("trigger", "availableFacet"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn this.as(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n\t")).add(mark("qn", "reference")).add(literal(" newElement = addFacet(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(mark("variable", "assign").multiple("\n")).add(literal(" |:\n    return newElement;\n}\n\npublic boolean is")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn is(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic void remove")).add(mark("name", "firstUpperCase")).add(literal("() {\n\tthis.removeFacet(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "availableFacet")), (condition("trigger", "availableFacet"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("() {\n\tio.intino.tara.magritte.Layer as = this.as(")).add(mark("qn", "reference")).add(literal(".class);\n\treturn as != null ? (")).add(mark("qn", "reference")).add(literal(") as : ")).add(expression().add(mark("abstract")).or(expression().add(literal("addFacet(")).add(mark("qn", "reference")).add(literal(".class)")))).add(literal(";\n}\n\npublic boolean is")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn is(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "core")), (condition("trigger", "asCore"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name")).add(literal("() {\n\treturn (")).add(mark("qn", "reference")).add(literal(") this.as(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "facetTarget")), not(condition("type", "overriden")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add((condition("type", "constraint")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add(not(condition("type", "target")), (condition("type", "nodeimpl")), not(condition("type", "instance")), (condition("trigger", "Node"))).add(literal("public")).add(expression().add(literal(" ")).add(mark("inner"))).add(expression().add(literal(" ")).add(mark("abstract"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(expression().add(literal(" extends ")).add(mark("parent")).or(expression().add(literal(" extends io.intino.tara.magritte.Layer")))).add(expression().add(literal(" implements ")).add(mark("flag", "tag").multiple(", "))).add(literal(" {\n\t")).add(mark("variable", "declaration").multiple("\n")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("facetTarget", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("constraint", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("metaType"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("metaFacet").multiple("\n"))).add(literal("\n\n\tpublic ")).add(mark("name", "javaValidName")).add(literal("(io.intino.tara.magritte.Node node) {\n\t\tsuper(node);")).add(expression().add(literal("\n")).add(literal("\t\t_metaType = node.as(")).add(mark("metaType", "typeInit")).add(literal(".class);"))).add(expression().add(literal("\n")).add(literal("\t\t_")).add(mark("metaFacet", "name")).add(literal(" = node.as(")).add(mark("metaFacet", "typeInit")).add(literal(".class);"))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("availableFacet").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("asCore"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\tpublic java.util.List<io.intino.tara.magritte.Node> componentList() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "componentList").multiple("\n")).add(literal("|:")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(components);")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\n\t@Override\n\tpublic java.util.Map<java.lang.String, java.util.List<?>> variables() {\n\t\tjava.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(")).add(mark("parent", "var")).add(literal(");\n\t\t")).add(mark("variable", "list").multiple("\n")).add(literal("|:\n\t\treturn map;\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\tpublic io.intino.tara.magritte.Concept concept() {")).add(literal("\n")).add(literal("\t\treturn this.graph().concept(")).add(mark("qn")).add(literal(".class);")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void addNode(io.intino.tara.magritte.Node node) {")).add(literal("\n")).add(literal("\t\tsuper.addNode(node);")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("    protected void removeNode(io.intino.tara.magritte.Node node) {")).add(literal("\n")).add(literal("        super.removeNode(node);")).add(literal("\n")).add(literal("        ")).add(mark("node", "remove").multiple("\n")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t@Override\n\tprotected void _load(java.lang.String name, java.util.List<?> values) {\n\t\tsuper._load(name, values);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("facetTarget", "init"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaType", "metaTypeLoad"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaFacet", "metaFacetLoad"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "init").multiple("\nelse "))).add(literal("\n\t}\n\n\t@Override\n\tprotected void _set(java.lang.String name, java.util.List<?> values) {\n\t\tsuper._set(name, values);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("facetTarget", "set"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaType", "metaTypeSet"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaFacet", "metaFacetSet"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "set").multiple("\nelse "))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void _sync(io.intino.tara.magritte.Layer layer) {")).add(literal("\n")).add(literal("\t\tsuper._sync(layer);")).add(literal("\n")).add(literal("\t    ")).add(mark("facetTarget", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t    ")).add(mark("constraint", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\n\tpublic Create create() {\n\t\treturn new Create(null);\n\t}\n\n\tpublic Create create(java.lang.String name) {\n\t\treturn new Create(name);\n\t}\n\n\tpublic class Create")).add(expression().add(literal(" extends ")).add(mark("parent")).add(literal(".Create"))).add(literal(" {\n\t\t")).add(expression().add(mark("parentSuper", "parentvariable"))).add(literal("\n\n\t\tpublic Create(java.lang.String name) {\n\t\t\t")).add(expression().add(mark("parentSuper", "parentinit"))).add(literal("\n\t\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "new").multiple("\n\n"))).add(literal("\n\t\t")).add(mark("create", "new").multiple("\n\n")).add(literal("\n\t}\n\t")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node").multiple("\n")).add(literal("\n")).add(literal("\t"))).add(literal("\n\tpublic ")).add(mark("workingPackage", "javaValidName", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(" ")).add(mark("ModelType", "lowerCase")).add(literal("() {\n\t\treturn ((")).add(mark("workingPackage", "javaValidName", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(") graph().")).add(mark("ModelType", "firstLowerCase")).add(literal("());\n\t}\n}\n")),
			rule().add((condition("type", "metaFacet")), (condition("trigger", "name"))).add(mark("name", "javaValidName", "firstLowerCase")),
			rule().add((condition("type", "metaFacet")), (condition("trigger", "typeInit"))).add(mark("type")),
			rule().add((condition("trigger", "typeInit"))).add(mark("value")),
			rule().add((condition("value", "true")), (condition("trigger", "parentinit"))).add(literal("super(name);")),
			rule().add((condition("value", "false")), (condition("trigger", "parentinit"))).add(literal("this.name = name;")),
			rule().add((condition("value", "true")), (condition("trigger", "parentvariable"))),
			rule().add((condition("value", "false")), (condition("trigger", "parentvariable"))).add(literal("protected final java.lang.String name;")),
			rule().add((condition("trigger", "metaTypeLoad"))).add(literal("node().load(_metaType, name, values);")),
			rule().add((condition("trigger", "metaTypeSet"))).add(literal("node().set(_metaType, name, values);")),
			rule().add((condition("trigger", "metaFacetLoad"))).add(literal("node().load(_")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("trigger", "metaFacetSet"))).add(literal("node().set(_")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("trigger", "metaType"))).add(literal("protected ")).add(mark("value")).add(literal(" _metaType;")),
			rule().add((condition("trigger", "metaFacet"))).add(literal("protected ")).add(mark("type")).add(literal(" _")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal(";")),
			rule().add((condition("trigger", "tag"))).add(literal("io.intino.tara.magritte.tags.")).add(mark("value", "lowerCase", "FirstUpperCase")),
			rule().add((condition("value", "Layer")), (condition("trigger", "var"))),
			rule().add((condition("trigger", "var"))).add(literal("super.variables()")),
			rule().add((condition("value", "true")), (condition("trigger", "inner"))).add(literal("static")),
			rule().add((condition("value", "true")), (condition("trigger", "abstract"))).add(literal("abstract"))
		);
		return this;
	}
}