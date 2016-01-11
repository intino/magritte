package tara.templates.layer;

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
			rule().add((condition("type", "Layer"))).add(literal("package ")).add(mark("package", "lowercase")).add(literal(";\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n")).add(expression().add(mark("imports").multiple("\n")).add(literal("\n"))).add(literal("\nimport java.util.*;\n\n")).add(mark("node")),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = instance.as(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "add"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "add"))),
			rule().add((condition("type", "single & owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = null;")),
			rule().add((condition("type", "owner")), not(condition("type", "overriden")), (condition("trigger", "remove"))).add(literal("if (instance.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.remove(instance.as(")).add(mark("qn", "reference")).add(literal(".class));")),
			rule().add((condition("type", "overriden")), (condition("trigger", "remove"))),
			rule().add((condition("type", "node")), not(condition("type", "final")), (condition("trigger", "new"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(")).add(expression().add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = _model().conceptOf(")).add(mark("qn", "reference")).add(literal(".class).newInstance(_instance()).as(")).add(mark("qn", "reference")).add(literal(".class);\n    ")).add(expression().add(literal("newElement.")).add(mark("variable", "assign").multiple("\n\tnewElement."))).add(literal("\n    return newElement;\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" new")).add(mark("name", "firstUpperCase")).add(literal("(String _name")).add(expression().add(literal(", ")).add(mark("variable", "parameters").multiple(", "))).add(literal(") {\n    ")).add(mark("qn", "reference")).add(literal(" newElement = _model().conceptOf(")).add(mark("qn", "reference")).add(literal(".class).newInstance(_name, _instance()).as(")).add(mark("qn", "reference")).add(literal(".class);\n    ")).add(expression().add(literal("newElement.")).add(mark("variable", "assign").multiple("\n\tnewElement."))).add(literal("\n    return newElement;\n}")),
			rule().add((condition("type", "facetTarget")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add((condition("type", "constraint")), (condition("trigger", "facet"))).add(literal("if (layer instanceof ")).add(mark("qn", "reference")).add(literal(") _")).add(mark("name", "firstLowerCase")).add(literal(" = (")).add(mark("qn", "reference")).add(literal(") layer;")),
			rule().add(not(condition("type", "target")), (condition("type", "nodeimpl")), (condition("trigger", "Node"))).add(literal("public")).add(expression().add(literal(" ")).add(mark("inner"))).add(expression().add(literal(" ")).add(mark("abstract"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(expression().add(literal(" extends ")).add(mark("parent")).or(expression().add(literal(" extends tara.magritte.Layer")))).add(expression().add(literal(" implements ")).add(mark("flag", "tag").multiple(", "))).add(literal(" {\n\t")).add(mark("variable", "declaration").multiple("\n")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("facetTarget", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("constraint", "declaration").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("typeInstance"))).add(literal("\n\n\tpublic ")).add(mark("name", "javaValidName")).add(literal("(tara.magritte.Instance instance) {\n\t\tsuper(instance);\n\t\t")).add(expression().add(literal("_metaType = instance.as(")).add(mark("typeInstance", "typeInit")).add(literal(".class);"))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("variable", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node", "getter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "setter").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "new").multiple("\n\n"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void _addInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("\t\tsuper._addInstance(instance);")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("    protected void _removeInstance(tara.magritte.Instance instance) {")).add(literal("\n")).add(literal("        super._removeInstance(instance);")).add(literal("\n")).add(literal("        ")).add(mark("node", "remove").multiple("\n")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t@Override\n\tprotected void _load(String name, java.util.List<?> objects) {\n\t\tsuper._load(name, objects);\n\t\t")).add(mark("typeInstance", "typeInstanceLoad")).add(literal("\n\t\t")).add(mark("variable", "init").multiple("\nelse ")).add(literal("\n\t}\n\n\t@Override\n\tprotected void _set(String name, java.util.List<?> objects) {\n\t\tsuper._set(name, objects);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("typeInstance", "typeInstanceLoad"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "set").multiple("\nelse "))).add(literal("\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic void _facet(tara.magritte.Layer layer) {")).add(literal("\n")).add(literal("\t\tsuper._facet(layer);")).add(literal("\n")).add(literal("\t    ")).add(mark("facetTarget", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t    ")).add(mark("constraint", "facet").multiple("\nelse ")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic java.util.List<tara.magritte.Instance> _instances() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> instances = new java.util.LinkedHashSet<>(super._instances());")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "list").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(instances);")).add(literal("\n")).add(literal("\t}"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\tpublic List<tara.magritte.Instance> _features() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> features = new java.util.LinkedHashSet<>(super._features());")).add(literal("\n")).add(literal("    \t")).add(mark("node", "featureList").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(features);")).add(literal("\n")).add(literal("    }"))).add(expression().add(literal("\n")).add(literal("\n")).add(literal("    public List<tara.magritte.Instance> _components() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Instance> components = new java.util.LinkedHashSet<>(super._components());")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "componentList").multiple("\n")).add(literal("|:")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(components);")).add(literal("\n")).add(literal("    }"))).add(literal("\n\n\t@Override\n\tpublic java.util.Map<String, java.util.List<?>> _variables() {\n\t\tjava.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(")).add(mark("parent", "var")).add(literal(");\n\t\t")).add(mark("variable", "list").multiple("\n")).add(literal("|:\n\t\treturn map;\n\t}")).add(expression().add(literal("\n")).add(literal("\n")).add(literal("\t")).add(mark("node").multiple("\n"))).add(literal("\n\n\tpublic ")).add(mark("generatedLanguage", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(" _")).add(mark("ModelType", "lowerCase")).add(literal("() {\n\t\treturn ((")).add(mark("generatedLanguage", "lowerCase")).add(literal(".")).add(mark("generatedLanguage", "firstUpperCase")).add(mark("ModelType")).add(literal(") _model().")).add(mark("ModelType", "firstLowerCase")).add(literal("());\n\t}\n}\n")),
			rule().add((condition("trigger", "typeInit"))).add(mark("value")),
			rule().add((condition("trigger", "typeInstanceLoad"))).add(literal("_instance().load(_metaType, name, objects);")),
			rule().add((condition("trigger", "typeInstance"))).add(mark("value")).add(literal(" _metaType;")),
			rule().add((condition("trigger", "tag"))).add(literal("tara.magritte.tags.")).add(mark("value", "lowerCase", "FirstUpperCase")),
			rule().add((condition("value", "Layer")), (condition("trigger", "var"))),
			rule().add((condition("trigger", "var"))).add(literal("super._variables()")),
			rule().add((condition("value", "true")), (condition("trigger", "inner"))).add(literal("static")),
			rule().add((condition("value", "true")), (condition("trigger", "abstract"))).add(literal("abstract"))
		);
		return this;
	}
}