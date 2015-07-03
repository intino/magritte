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
			rule().add((condition("type", "Word")), (condition("trigger", "wordInstantiation"))).add(literal("public enum ")).add(mark("name", "javaValidName", "firstUpperCase")).add(literal(" {\n\t")).add(mark("words", "javaValidName", "FirstUpperCase").multiple(", ")).add(literal("\n}")),
			rule().add((condition("type", "Word")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("name", "javaValidName", "reference")).add(literal("[] ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn magritte.primitives.Enumerate.cardinal(_getMultiple(\"")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("\").asInteger(), ")).add(mark("name", "javaValidName", "firstUpperCase")).add(literal(".values());\n}")),
			rule().add((condition("type", "Word")), not(condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("name", "javaValidName", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _definition()._get(\"")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("\").asEnumerate(")).add(mark("name", "javaValidName", "reference")).add(literal(".values());\n}")),
			rule().add((condition("type", "Word")), (condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstUpperCase")).add(literal("(")).add(mark("name", "javaValidNamefirstUpperCase")).add(literal("... values) {\n\t_edit().set(\"")).add(mark("name", "javaValidName")).add(literal("\", magritte.primitives.Enumerate.ordinal(values));\n}")),
			rule().add((condition("type", "Word")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstUpperCase")).add(literal("(")).add(mark("name", "javaValidName", "firstUpperCase")).add(literal(" value) {\n\t_edit().set(\"")).add(mark("name", "javaValidName")).add(literal("\", magritte.primitives.Enumerate.ordinal(value));\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), (condition("type", "Date")), (condition("trigger", "getter"))).add(literal("public magritte.primitives.Date ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").asDate();\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), (condition("type", "Measure")), (condition("trigger", "getter"))).add(literal("public double ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").asDouble();\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), (condition("type", "file")), (condition("trigger", "getter"))).add(literal("public Resource ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").asResource();\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), not(condition("type", "reference")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "firstUpperCase", "primitiveType")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").as")).add(mark("type", "firstUpperCase")).add(literal("();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), (condition("type", "terminal")), (condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public magritte.Set<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _getMultiple(\"")).add(mark("name")).add(literal("\").as(")).add(mark("type", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("().get(index);\n}\n\npublic magritte.Set<magritte.wraps.Type> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("TypeSet() {\n\treturn _definition()._getAssignable(\"entity\").as(magritte.wraps.Type.class);\n}\n\npublic magritte.wraps.Type ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Type(int index) {\n\treturn ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("TypeSet().get(index);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "terminal")), (condition("type", "reference")), (condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public magritte.Set<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _getMultiple(\"")).add(mark("name")).add(literal("\").as(")).add(mark("type", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("returnValue", "void")), (condition("trigger", "getter"))).add(literal("public void ")).add(mark("name")).add(literal("(")).add(mark("parameters")).add(literal("){\n\t((")).add(mark("generatedLanguage")).add(literal(".natives.")).add(mark("interfaceName")).add(literal(")")).add(expression().add(mark("definition")).add(literal(" _definition()."))).add(literal("_get(\"")).add(mark("name")).add(literal("\").asNative()).")).add(mark("methodName")).add(literal("(")).add(mark("parameters", "WithoutType")).add(literal(");\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnValue")).add(literal(" ")).add(mark("name")).add(literal("(")).add(mark("parameters")).add(literal("){\n\treturn ((")).add(mark("generatedLanguage")).add(literal(".natives.")).add(mark("interfaceName")).add(literal(")")).add(expression().add(mark("definition")).add(literal(" _definition()."))).add(literal("_get(\"")).add(mark("name")).add(literal("\").asNative()).")).add(mark("methodName")).add(literal("(")).add(mark("parameters", "WithoutType")).add(literal(");\n}")),
			rule().add((condition("type", "variable")), (condition("type", "terminal")), (condition("type", "reference")), not(condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").as(")).add(mark("type", "reference")).add(literal(".class);\n}\n\npublic magritte.Set<magritte.wraps.Type> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Types() {\n\treturn _definition()._getAssignable(\"entity\").as(magritte.wraps.Type.class);\n}\n\npublic magritte.wraps.Type ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Type(int index) {\n\treturn ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Types().get(index);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "terminal")), (condition("type", "reference")), not(condition("type", "multiple")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _get(\"")).add(mark("name")).add(literal("\").as(")).add(mark("type", "reference")).add(literal(".class);\n}")),
			rule().add((condition("value", "Integer")), (condition("trigger", "primitiveType"))).add(literal("int")),
			rule().add((condition("value", "Double")), (condition("trigger", "primitiveType"))).add(literal("double")),
			rule().add((condition("value", "Natural")), (condition("trigger", "primitiveType"))).add(literal("int")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), not(condition("type", "reference")), not(condition("type", "word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "javaValidName", "primitiveType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _definition()._get(\"")).add(mark("name")).add(literal("\").as")).add(mark("type", "reference")).add(literal("();\n}")),
			rule().add((condition("type", "variable")), (condition("type", "file")), not(condition("type", "multiple")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(Resource value) {\n\t_edit().set(\"value\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Date")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(magritte.primitives.Date value) {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", value);\n}\n\npublic void ")).add(mark("name", "firstLowerCase")).add(literal("(magritte.Expression<Double> value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(magritte.NativeCode value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "Measure")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(double ")).add(mark("name", "firstLowerCase")).add(literal(") {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");\n}\n\npublic void ")).add(mark("name", "firstLowerCase")).add(literal("(magritte.Expression<Double> value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "final")), not(condition("type", "native")), (condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(")).add(mark("type", "reference", "primitiveType")).add(literal("... ")).add(mark("name", "firstLowerCase")).add(literal(") {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");\n}\n\npublic void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(magritte.Expression<")).add(mark("type", "reference")).add(literal("> value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}\n\npublic void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(magritte.wraps.Operation operation, ")).add(mark("type", "reference", "primitiveType")).add(literal("... ")).add(mark("name", "firstLowerCase")).add(literal(") {\n\t_edit(operation).set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "native")), not(condition("type", "reference")), not(condition("type", "final")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(java.lang.")).add(mark("type", "firstUpperCase")).add(literal(" value) {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", value);\n}\n\npublic void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(magritte.Expression<java.lang.")).add(mark("type", "javaValidName")).add(literal("> value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), not(condition("type", "final")), not(condition("type", "native")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(")).add(mark("type", "reference")).add(literal(" value) {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "final")), not(condition("type", "native")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(")).add(mark("type", "reference")).add(literal(" value) {\n\t_edit().set(\"")).add(mark("name")).add(literal("\", value);\n}\n\npublic void ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(magritte.Expression<")).add(mark("type", "reference")).add(literal("> value) {\n\t_edit().let(\"")).add(mark("name")).add(literal("\", value);\n}")),
			rule().add((condition("type", "nodeReference")), not(condition("type", "final")), (condition("trigger", "modifiable"))).add(literal("public magritte.Set<Type> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Types() {\n    return ")).add(expression().add(mark("definitionAggregable")).add(literal(" _definition()."))).add(literal("_aggregables(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic Type ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Type(int index) {\n    return ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Types().get(index);\n}")),
			rule().add((condition("type", "nodeReference")), not(condition("type", "final")), (condition("type", "single")), (condition("trigger", "modifiable"))).add(literal("public Type ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Type() {\n    return ")).add(expression().add(mark("definitionAggregable")).add(literal(" _definition()."))).add(literal("_aggregables(")).add(mark("qn", "reference")).add(literal(".class).get(0);\n}\n\npublic ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Create() {\n\treturn ")).add(expression().add(mark("definitionAggregable")).add(literal(" _definition()."))).add(literal("_create(")).add(mark("type", "reference")).add(literal(".class);\n}.as(tafat.Job.class);\n\npublic ")).add(mark("type", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Remove() {\n\treturn ")).add(expression().add(mark("definitionAggregable")).add(literal(" _definition()."))).add(literal("_remove(")).add(mark("type", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "nodeReference")), (condition("trigger", "modifiable"))),
			rule().add((condition("type", "nodeReference")), not(condition("type", "single")), (condition("trigger", "component"))).add(literal("public magritte.Set<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Set() {\n\treturn ")).add(expression().add(mark("definition")).add(literal(" _definition()."))).add(literal("_components(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(int index) {\n\t\treturn ")).add(mark("name", "firstLowerCase")).add(literal("Set().get(index);\n}")),
			rule().add((condition("type", "nodeReference")), (condition("type", "single")), (condition("type", "intosingle")), (condition("trigger", "component"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _component(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "nodeReference")), (condition("type", "single")), (condition("trigger", "component"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("() {\n\treturn _component(")).add(mark("qn", "reference")).add(literal(".class);\n}")),
			rule().add((condition("type", "nodeReference")), not(condition("type", "single")), (condition("type", "final")), (condition("trigger", "component"))).add(literal("public magritte.Set<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Set() {\n\treturn _components(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("Set().get(index);\n}")),
			rule().add((condition("type", "nodeimpl")), (condition("trigger", "node"))).add(literal("public")).add(expression().add(mark("inner")).add(literal(" static"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(literal(" ")).add(mark("parent")).add(literal(" {\n\t")).add(mark("variable", "wordInstantiation").multiple("\n")).add(literal("\n\t")).add(mark("variable", "getter").multiple("\n")).add(literal("\n\t")).add(mark("variable", "setter").multiple("\n")).add(literal("\n\t")).add(mark("component", "modifiable").multiple("\n")).add(literal("\n\t")).add(mark("component").multiple("\n")).add(literal("\n\t")).add(mark("node").multiple("\n")).add(literal("\n}\n")),
			rule().add((condition("trigger", "parent")), (condition("slot", "value"))).add(literal("extends ")).add(mark("value", "reference")),
			rule().add((condition("type", "variable")), (condition("trigger", "wordInstantiation"))),
			rule().add((condition("type", "variable")), (condition("trigger", "setter"))),
			rule().add((condition("type", "variable")), (condition("trigger", "getter"))),
			rule().add((condition("trigger", "component")))
		);
		return this;
	}
}