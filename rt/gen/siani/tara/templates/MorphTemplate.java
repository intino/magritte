package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class MorphTemplate extends Template {

	protected MorphTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new MorphTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Morph"))).add(literal("package ")).add(mark("package", "lowercase")).add(literal(";\n\n")).add(expression().add(literal("import ")).add(mark("import").multiple(";\n"))).add(literal("\nimport magritte.primitives.*;\nimport magritte.wraps.*;\n")).add(mark("imports").multiple("\n")).add(literal("\n\n")).add(mark("node")),
			rule().add((condition("type", "Word")), (condition("trigger", "wordInstantiation"))).add(literal("public enum ")).add(mark("name", "firstUpperCase")).add(literal(" {\n    ")).add(mark("words", "FirstUpperCase").multiple(", ")).add(literal("\n}")),
			rule().add((condition("type", "Word")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("name", "reference")).add(literal("[] ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return Enumerate.cardinal(get(Grip.multiple(\"")).add(mark("name", "firstLowerCase")).add(literal("\")).asInteger(), ")).add(mark("name", "firstUpperCase")).add(literal(".values());\n}")),
			rule().add((condition("type", "Word")), not(condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("name", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return definition().get(\"")).add(mark("name", "firstLowerCase")).add(literal("\").asEnumerate(")).add(mark("name", "reference")).add(literal(".values());\n}")),
			rule().add((condition("type", "Word")), (condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(")).add(mark("name", "firstUpperCase")).add(literal("... values) {\n    edit().set(\"")).add(mark("name")).add(literal("\", Enumerate.ordinal(values));\n}")),
			rule().add((condition("type", "Word")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(")).add(mark("name", "firstUpperCase")).add(literal(" value) {\n    edit().set(\"")).add(mark("name")).add(literal("\", Enumerate.ordinal(value));\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Date")), (condition("trigger", "getter"))).add(literal("public magritte.primitives.Date ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(\"")).add(mark("name")).add(literal("\").asDate();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Measure")), (condition("trigger", "getter"))).add(literal("public double ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(\"")).add(mark("name")).add(literal("\").asDouble();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "definition")), (condition("type", "native")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("name", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(\"")).add(mark("name")).add(literal("\").asNativeOf(scope);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("generatedlanguage")).add(literal(".natives.")).add(mark("contract", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return definition().get(\"")).add(mark("name")).add(literal("\").asNativeOf(node);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "file")), (condition("trigger", "getter"))).add(literal("public Resource ")).add(mark("name", "firstLowerCase")).add(literal("(Resource value) {\n    return get(\"")).add(mark("name")).add(literal("\").asResource();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "definition")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(\"")).add(mark("name")).add(literal("\").as")).add(mark("type", "firstUpperCase")).add(literal("();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public Definition[] ")).add(mark("name", "FirstLowercase")).add(literal("Definitions() {\n    return definition().get(Grip.assignable(\"")).add(mark("name")).add(literal("\")).as(Definition.class);\n}\n\npublic ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(Grip.reference(\"")).add(mark("name")).add(literal("\")).as(")).add(mark("type", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "definition")), not(condition("type", "reference")), (condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "reference")).add(literal("[] ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(Grip.multiple(\"")).add(mark("name")).add(literal("\")).as")).add(mark("type", "reference")).add(literal("();\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "reference")), (condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "reference", "primitiveType")).add(literal("[] ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return get(Grip.multiple(\"")).add(mark("name")).add(literal("\")).as")).add(mark("type", "reference")).add(literal("();\n}")),
			rule().add((condition("value", "Integer")), (condition("trigger", "primitiveType"))).add(literal("int")),
			rule().add((condition("value", "Double")), (condition("trigger", "primitiveType"))).add(literal("double")),
			rule().add((condition("type", "variable")), not(condition("type", "reference")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return definition().get(\"")).add(mark("name")).add(literal("\").as")).add(mark("type", "reference")).add(literal("();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "file")), not(condition("type", "multiple")), not(condition("type", "readonly")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(Resource value) {\n    edit().set(\"value\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Date")), not(condition("type", "readonly")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(magritte.primitives.Date value) {\n    edit().set(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Measure")), not(condition("type", "readonly")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(double ")).add(mark("name", "firstLowerCase")).add(literal(") {\n    edit().set(\"")).add(mark("name")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "readonly")), not(condition("type", "native")), (condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(")).add(mark("type", "reference", "primitiveType")).add(literal("... ")).add(mark("name", "firstLowerCase")).add(literal(") {\n    edit().set(\"")).add(mark("name")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), not(condition("type", "reference")), not(condition("type", "readonly")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(java.lang.")).add(mark("type", "firstUpperCase")).add(literal(" value) {\n    edit().set(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "readonly")), not(condition("type", "native")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void set")).add(mark("name", "firstUpperCase")).add(literal("(")).add(mark("type", "reference")).add(literal(" value) {\n    edit().set(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("trigger", "parent")), (condition("slot", "value"))).add(literal("extends ")).add(mark("value", "reference")),
			rule().add((condition("type", "nodeReference")), (condition("type", "Feature")), (condition("trigger", "component"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return definition().component(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "nodeReference")), (condition("type", "single")), (condition("type", "intosingle")), (condition("trigger", "component"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n    return components(")).add(mark("qn", "reference")).add(literal(".class)[0];\n}")),
			rule().add((condition("type", "nodeReference")), (condition("trigger", "component"))).add(literal("public ")).add(mark("qn", "reference")).add(literal("[] ")).add(mark("name", "firstLowerCase", "plural")).add(literal("() {\n    return components(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n    return ")).add(mark("name", "firstLowerCase", "plural")).add(literal("()[index];\n}")),
			rule().add((condition("type", "nodeimpl")), (condition("type", "intention")), not(condition("type", "facetInstance")), (condition("type", "facet")), (condition("trigger", "node"))).add(literal("public")).add(expression().add(mark("inner")).add(literal(" static"))).add(literal(" class ")).add(mark("name", "firstUpperCase")).add(literal(" ")).add(mark("parent")).add(literal(" implements ")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention {\n    public ")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention extension() {\n        return extensions(")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention.class)[0];\n    }\n    ")).add(mark("variable", "wordInstantiation").multiple("\n")).add(literal("\n    ")).add(mark("variable", "getter").multiple("\n")).add(literal("\n    ")).add(mark("variable", "setter").multiple("\n")).add(literal("\n    ")).add(mark("component").multiple("\n")).add(literal("\n\n    ")).add(mark("node").multiple("\n")).add(literal("\n}\n")),
			rule().add((condition("type", "nodeimpl")), (condition("type", "intention")), not(condition("type", "facet")), (condition("type", "facetInstance")), (condition("trigger", "node"))).add(literal("public")).add(expression().add(mark("inner")).add(literal(" static"))).add(literal(" class ")).add(mark("name", "firstUpperCase")).add(literal(" ")).add(mark("parent")).add(literal(" implements ")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention {\n    public ")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention[] extensions() {\n        return extensions(")).add(mark("project", "lowercase")).add(literal(".intentions.")).add(mark("name", "firstUpperCase")).add(literal("Intention.class);\n    }\n    ")).add(mark("variable", "wordInstantiation").multiple("\n")).add(literal("\n    ")).add(mark("variable", "getter").multiple("\n")).add(literal("\n    ")).add(mark("variable", "setter").multiple("\n")).add(literal("\n    ")).add(mark("component").multiple("\n")).add(literal("\n\n    ")).add(mark("node").multiple("\n")).add(literal("\n}\n")),
			rule().add((condition("type", "nodeimpl")), (condition("trigger", "node"))).add(literal("public")).add(expression().add(mark("inner")).add(literal(" static"))).add(literal(" class ")).add(mark("name", "firstUpperCase")).add(literal(" ")).add(mark("parent")).add(literal(" {\n    ")).add(expression().add(mark("aggregable")).add(literal("\n")).add(literal("    public Definition")).add(literal("[")).add(literal("]")).add(literal(" aggregables() {")).add(literal("\n")).add(literal("        return definition().aggregables();")).add(literal("\n")).add(literal("    }"))).add(literal("\n    ")).add(mark("variable", "wordInstantiation").multiple("\n")).add(literal("\n    ")).add(mark("variable", "getter").multiple("\n")).add(literal("\n    ")).add(mark("variable", "setter").multiple("\n")).add(literal("\n    ")).add(mark("component").multiple("\n")).add(literal("\n\n    ")).add(mark("node").multiple("\n")).add(literal("\n}\n")),
			rule().add((condition("type", "variable")), (condition("trigger", "wordInstantiation"))),
			rule().add((condition("type", "variable")), (condition("trigger", "setter"))),
			rule().add((condition("type", "variable")), (condition("trigger", "getter"))),
			rule().add((condition("trigger", "component")))
		);
		return this;
	}
}