package tara.templates.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class LayerTemplate extends Template {

	protected LayerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new LayerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Layer"))).add(literal("package ")).add(mark("package", "javaValidName", "lowercase")).add(literal(";\n\nimport ")).add(mark("generatedLanguage", "javaValidName", "lowercase")).add(literal(".*;\n")).add(expression().add(mark("imports").multiple("\n")).add(literal("\n"))).add(literal("\nimport java.util.*;\n\n")).add(mark("node")),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = instance.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "add"))),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = null;")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "remove"))),
			rule().add((condition("type", "node")), not(condition("type", "final")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n    return new")).add(mark("name", "firstUpperCase")).add(literal("((String)null")).add(expression().add(literal(", ")).add(mark("variable", "name").multiple(", "))).add(literal(");\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(String name")).add(expression().add(literal(", ")).add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = model().conceptOf(")).add(mark("qn", "reference")).add(literal(".class).newNode(name, instance()).as(")).add(mark("qn", "reference")).add(literal(".class);\n    ")).add(expression().add(mark("variable", "assign").multiple("\n"))).add(literal("\n    return newElement;\n}")),
			rule().add((condition("type", "availableFacet")), (condition("slot", "variable")), (condition("trigger", "availableFacet"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn this.as(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n\t")).add(mark("qn", "reference")).add(literal(" newElement = newFacet(")).add(mark("qn", "reference")).add(literal(".class);\n\t")).add(expression().add(mark("variable", "assign").multiple("\n"))).add(literal("\n    return newElement;\n}\n\npublic boolean is")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn is(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic void delete")).add(mark("name", "firstUpperCase")).add(literal("() {\n\tthis.deleteFacet(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "availableFacet")), (condition("trigger", "availableFacet"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name", "firstUpperCase")).add(literal("() {\n\ttara.magritte.Layer as = this.as(")).add(mark("qn", "reference")).add(literal(".class);\n\treturn as != null ? (")).add(mark("qn", "reference")).add(literal(") as : ")).add(expression().add(mark("abstract")).or(expression().add(literal("newFacet(")).add(mark("qn", "reference")).add(literal(".class)")))).add(literal(";\n}\n\npublic boolean is")).add(mark("name", "firstUpperCase")).add(literal("() {\n\treturn is(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "core")), (condition("trigger", "asCore"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" as")).add(mark("name")).add(literal("() {\n\treturn (")).add(mark("qn", "reference")).add(literal(") this.as(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "node & owner")), (condition("trigger", "new"))),
			rule().add((condition("type", "facetTarget")), not(condition("type", "overriden")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add((condition("type", "constraint")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add(not(condition("type", "target")), (condition("type", "nodeimpl")), (condition("trigger", "Node"))).add(literal("public")).add(expression().add(literal(" ")).add(mark("inner"))).add(expression().add(literal(" ")).add(mark("abstract"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(expression().add(literal(" extends ")).add(mark("parent")).or(expression().add(literal(" extends tara.magritte.Layer")))).add(expression().add(literal(" implements ")).add(mark("flag", "tag").multiple(", "))).add(literal(" {\n\t")).add(mark("variable", "declaration").multiple("\n")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("facetTarget", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("constraint", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("metaType"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("metaFacet").multiple("\n"))).add(literal("\n\n\tpublic ")).add(mark("name", "javaValidName")).add(literal("(tara.magritte.Instance instance) {\n\t\tsuper(instance);\n\t\t")).add(expression().add(literal("_metaType = instance.as(")).add(mark("metaType", "typeInit")).add(literal(".class);"))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "new").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("availableFacet").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("asCore")).add(literal("\n")).add(literal("\t"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\tpublic tara.magritte.Concept concept() {")).add(literal("\n")).add(literal("\t\treturn this.model().conceptOf(")).add(mark("qn")).add(literal(".class);")).add(literal("\n")).add(literal("\t}")).add(literal("\n")).add(literal("\t"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void addInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("\t\tsuper.addInstance(instance);")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("    protected void deleteInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("        super.deleteInstance(instance);")).add(literal("\n")).add(literal("        ")).add(mark("node", "remove").multiple("\n")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t@Override\n\tprotected void _load(String name, java.util.List<?> values) {\n\t\tsuper._load(name, values);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("facetTarget", "init"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaType", "metaTypeLoad"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaFacet", "metaFacetLoad"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "init").multiple("\nelse "))).add(literal("\n\t}\n\n\t@Override\n\tprotected void _set(String name, java.util.List<?> values) {\n\t\tsuper._set(name, values);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("facetTarget", "set"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaType", "metaTypeSet"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("metaFacet", "metaFacetSet"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "set").multiple("\nelse "))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic void _facet(tara.magritte.Layer layer) {")).add(literal("\n")).add(literal("\t\tsuper._facet(layer);")).add(literal("\n")).add(literal("\t    ")).add(mark("facetTarget", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t    ")).add(mark("constraint", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic java.util.List<tara.magritte.Instance> instances() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> instances = new java.util.LinkedHashSet<>(super.instances());")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "list").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(instances);")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\tpublic List<tara.magritte.Instance> features() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> features = new java.util.LinkedHashSet<>(super.features());")).add(literal("\n")).add(literal("    \t")).add(mark("node", "featureList").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(features);")).add(literal("\n")).add(literal("    }"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("    public List<tara.magritte.Instance> components() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> components = new java.util.LinkedHashSet<>(super.components());")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "componentList").multiple("\n")).add(literal("|:")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(components);")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t@Override\n\tpublic java.util.Map<String, java.util.List<?>> variables() {\n\t\tjava.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(")).add(mark("parent", "var")).add(literal(");\n\t\t")).add(mark("variable", "list").multiple("\n")).add(literal("|:\n\t\treturn map;\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node").multiple("\n"))).add(literal("\n\n\tpublic ")).add(mark("generatedLanguage", "javaValidName", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(" ")).add(mark("ModelType", "lowerCase")).add(literal("() {\n\t\treturn ((")).add(mark("generatedLanguage", "javaValidName", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(") model().")).add(mark("ModelType", "firstLowerCase")).add(literal("());\n\t}\n}\n")),
			rule().add((condition("trigger", "typeInit"))).add(mark("value")),
			rule().add((condition("trigger", "metaTypeLoad"))).add(literal("instance().load(_metaType, name, values);")),
			rule().add((condition("trigger", "metaTypeSet"))).add(literal("instance().set(_metaType, name, values);")),
			rule().add((condition("trigger", "metaFacetLoad"))).add(literal("instance().load(_")).add(mark("name", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("trigger", "metaFacetSet"))).add(literal("instance().set(_")).add(mark("name", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("trigger", "metaType"))).add(literal("protected ")).add(mark("value")).add(literal(" _metaType;")),
			rule().add((condition("trigger", "metaFacet"))).add(literal("protected ")).add(mark("type")).add(literal(" _")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("trigger", "tag"))).add(literal("tara.magritte.tags.")).add(mark("value", "lowerCase", "FirstUpperCase")),
			rule().add((condition("value", "Layer")), (condition("trigger", "var"))),
			rule().add((condition("trigger", "var"))).add(literal("super.variables()")),
			rule().add((condition("value", "true")), (condition("trigger", "inner"))).add(literal("static")),
			rule().add((condition("value", "true")), (condition("trigger", "abstract"))).add(literal("abstract"))
		);
		return this;
	}
}