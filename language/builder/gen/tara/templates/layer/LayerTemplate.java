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
			rule().add((condition("type", "Layer"))).add(literal("package ")).add(mark("package", "lowercase")).add(literal(";\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n\nimport java.util.*;\n\n")).add(mark("node")),
			rule().add((condition("type", "single")), (condition("type", "owner")), (condition("trigger", "add"))).add(literal("if (component.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = component.as(")).add(mark("name", "javaValidName")).add(literal(".class);")),
			rule().add((condition("type", "owner")), (condition("trigger", "add"))).add(literal("if (component.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal("List.add(component.as(")).add(mark("name", "javaValidName")).add(literal(".class));")),
			rule().add(not(condition("type", "target")), (condition("type", "nodeimpl")), (condition("trigger", "Node"))).add(literal("public")).add(expression().add(literal(" ")).add(mark("inner"))).add(expression().add(literal(" ")).add(mark("abstract"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(expression().add(literal(" extends ")).add(mark("parent")).or(expression().add(literal(" extends tara.magritte.Layer")))).add(expression().add(literal(" implements ")).add(mark("annotations", "annotation").multiple(", "))).add(literal(" {\n\t")).add(mark("variable", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("facetTarget", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("constraint", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("typeDeclaration")).add(literal("|:\n\n\tpublic ")).add(mark("name", "javaValidName")).add(literal("(tara.magritte.Declaration declaration) {\n\t\tsuper(declaration);")).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("variable", "constructor").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("facetTarget", "constructor").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t\t")).add(mark("constraint", "constructor").multiple("\n"))).add(expression().add(literal("\n")).add(literal("\t\t_metaType = declaration.as(")).add(mark("typeDeclaration", "typeInit")).add(literal(".class);"))).add(literal("\n\t}\n\t")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("variable", "getter").multiple("\n\n"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("variable", "setter").multiple("\n\n"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t")).add(mark("node", "getter").multiple("\n\n"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void _addComponent(tara.magritte.Declaration component) {")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void _load(String name, java.lang.Object object) {")).add(literal("\n")).add(literal("\t\tsuper._load(name, object);")).add(literal("\n")).add(literal("\t\t")).add(mark("variable", "init").multiple("\nelse ")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tprotected void _set(String name, java.lang.Object object) {")).add(literal("\n")).add(literal("\t\tsuper._set(name, object);")).add(literal("\n")).add(literal("\t\t")).add(mark("variable", "set").multiple("\nelse ")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic java.util.List<tara.magritte.Declaration> _components() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Declaration> declarations = new java.util.LinkedHashSet<>();")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "list").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList<>(declarations);")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("\n")).add(literal("\t@Override")).add(literal("\n")).add(literal("\tpublic java.util.Map<String, java.lang.Object> _variables() {")).add(literal("\n")).add(literal("\t\tjava.util.Map<String, Object> map = new java.util.LinkedHashMap<>(")).add(mark("parent", "var")).add(literal(");")).add(literal("\n")).add(literal("\t\t")).add(mark("variable", "list").multiple("\n")).add(literal("|:")).add(literal("\n")).add(literal("\t\treturn map;")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(mark("node").multiple("\n")).add(literal("|:\n}")),
			rule().add((condition("trigger", "typeInit"))).add(mark("value")),
			rule().add((condition("trigger", "typeDeclaration"))).add(mark("value")).add(literal(" _metaType;")),
			rule().add((condition("trigger", "typeDeclaration"))).add(literal("tara.magritte.annotations.")).add(mark("value")).add(literal(";")),
			rule().add((condition("value", "Layer")), (condition("trigger", "var"))),
			rule().add((condition("trigger", "var"))).add(literal("super._variables()")),
			rule().add((condition("value", "true")), (condition("trigger", "inner"))).add(literal("static")),
			rule().add((condition("value", "true")), (condition("trigger", "abstract"))).add(literal("abstract"))
		);
		return this;
	}
}