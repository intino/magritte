package tara.templates;

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
			rule().add((condition("type", "Morph"))).add(literal("package ")).add(mark("package", "lowercase")).add(literal(";\n\nimport ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;\n\nimport java.util.*;\n\n")).add(mark("node")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), (condition("type", "multiple")), (condition("type", "owner")), not(condition("type", "outDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected List<")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name")).add(literal(" = new java.util.ArrayList<>();\n\npublic enum ")).add(mark("name", "firstUpperCase")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), (condition("type", "multiple")), (condition("type", "owner")), (condition("type", "OutDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected List<")).add(mark("generatedLanguage", "LowerCase")).add(literal(".words.")).add(mark("contract", "firstUpperCase")).add(literal("> ")).add(mark("name")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), (condition("type", "owner")), (condition("type", "outDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".words.")).add(mark("contract", "firstUpperCase")).add(literal(" ")).add(mark("name")).add(literal(";")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), (condition("type", "owner")), not(condition("type", "OutDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name")).add(literal(";\n\npublic enum ")).add(mark("name", "firstUpperCase")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Native")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives.")).add(mark("contract", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("type", "variableTypeList", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "Node")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "single")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "Node")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List = new java.util.ArrayList<>();")),
			rule().add((condition("type", "facetTarget")), (condition("trigger", "declaration"))).add(literal("protected final ")).add(mark("qn", "reference")).add(literal(" _")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "constraint")), (condition("trigger", "declaration"))).add(literal("protected final ")).add(mark("qn", "reference")).add(literal(" _")).add(mark("name", "firstLowerCase")).add(literal(";")),
			rule().add((condition("type", "facetTarget")), (condition("trigger", "init"))).add(literal("_")).add(mark("name", "firstLowercase")).add(literal(" = node.morph(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "constraint")), (condition("trigger", "init"))).add(literal("_")).add(mark("name", "firstLowercase")).add(literal(" = node.morph(")).add(mark("qn", "reference")).add(literal(".class);")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "wordValues")), (condition("type", "word")), (condition("type", "multiple")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("wordValues", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("type", "word")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("wordValues", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("type", "native")), (condition("Slot", "body")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("contract", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(".class.getName());")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "date")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "time")), not(condition("type", "inherited")), (condition("Slot", "values")), not(condition("type", "multiple")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\",  new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "double")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("type", "multiple")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(new Double[] {")).add(mark("values").multiple(", ")).add(literal("})));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("trigger", "init"))).add(literal("_set(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), not(condition("type", "owner")), (condition("type", "target")), (condition("type", "Word")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal(".")).add(mark("type")).add(literal("> ")).add(mark("name")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name")).add(literal("();\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "owner")), (condition("type", "target")), (condition("type", "Word")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("language")).add(literal(".")).add(mark("qn", "reference")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name")).add(literal("();\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "owner")), (condition("type", "target")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "owner")), (condition("type", "target")), (condition("type", "native")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "return")).add(literal(" _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters", "WithOutType")).add(literal(");\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "target")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}")),
			rule().add((condition("type", "Node")), (condition("type", "target")), (condition("type", "single")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowercase")).add(literal("();\n}")),
			rule().add((condition("type", "Node")), (condition("type", "target")), (condition("type", "multiple")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List();\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "target")), (condition("type", "Word")), not(condition("type", "outDefined")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type")).add(literal("> ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), not(condition("type", "outDefined")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "target")), (condition("type", "Word")), (condition("type", "outDefined")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("generatedLanguage")).add(literal(".words.")).add(mark("contract", "firstUpperCase")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), not(condition("type", "outDefined")), (condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("generatedLanguage")).add(literal(".words.")).add(mark("contract", "firstUpperCase")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "return")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(".")).add(mark("methodName")).add(literal("(")).add(mark("parameters", "WithoutType")).add(literal(");\n}")),
			rule().add(not(condition("value", "void")), (condition("trigger", "return"))).add(literal("return")),
			rule().add((condition("value", "void")), (condition("trigger", "return"))),
			rule().add((condition("type", "Variable")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(";\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".get(index);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowercase")).add(literal(";\n}")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), (condition("type", "single")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowercase")).add(literal(";\n}")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List.get(index);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), (condition("type", "Word")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name")).add(literal("(")).add(mark("language")).add(literal(".")).add(mark("qn", "reference")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" value) {\n\t_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), not(condition("type", "Word")), (condition("type", "native")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives.")).add(mark("contract", "javaValidName")).add(literal(" value) {\n\t_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), not(condition("type", "Word")), not(condition("type", "native")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\t_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("type", "Word")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(expression().add(mark("qn", "reference")).add(literal("."))).add(mark("type", "firstUpperCase")).add(literal(" value) {\n\t")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("type", "native")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives.")).add(mark("contract", "javaValidName")).add(literal(" value) {\n\t")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\t")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "single")), (condition("type", "owner")), (condition("trigger", "add"))).add(literal("if (component.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = component.morph(")).add(mark("name", "javaValidName")).add(literal(".class);")),
			rule().add((condition("type", "owner")), (condition("trigger", "add"))).add(literal("if (component.is(\"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal("List.add(component.morph(")).add(mark("name", "javaValidName")).add(literal(".class));")),
			rule().add((condition("type", "variable")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "outDefined")), (condition("type", "owner")), (condition("type", "word")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<java.lang.String>) object).stream().\n\tmap(o -> ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".words.")).add(mark("contract", "firstUpperCase")).add(literal(".valueOf(o.toString())).\n\tcollect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "word")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<")).add(mark("type")).add(literal(">) object).stream().\n\tmap(o -> ")).add(mark("type")).add(literal(".valueOf(o.toString())).\n\tcollect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "word")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = ")).add(mark("type")).add(literal(".valueOf(((java.util.List<Object>) object).get(0).toString());")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "native")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = (")).add(mark("generatedLanguage")).add(literal(".natives.")).add(mark("contract", "javaValidName")).add(literal(") _link((tara.magritte.NativeCode) _newInstanceOf(object));")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "multiple")), (condition("type", "reference")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _loadNode(((java.util.List<Object>) object)).stream().\n\tmap(n -> n.morph(")).add(mark("type", "reference")).add(literal(".class)).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "reference")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _loadNode(((java.util.List<Object>) object).get(0)).morph(")).add(mark("type", "reference")).add(literal(".class);")),
			rule().add((condition("type", "variable")), (condition("type", "time")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _asTime((java.util.List<String>) object);")),
			rule().add((condition("type", "variable")), (condition("type", "time")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _asTime(((java.util.List<String>) object).get(0));")),
			rule().add((condition("type", "variable")), (condition("type", "date")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _asDate((java.util.List<String>) object);")),
			rule().add((condition("type", "variable")), (condition("type", "date")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = _asDate(((java.util.List<String>) object).get(0));")),
			rule().add((condition("type", "variable")), (condition("type", "owner")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = (java.util.List<")).add(mark("type", "variableTypeList")).add(literal(">) object;")),
			rule().add((condition("type", "variable")), (condition("type", "measure")), not(condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = java.lang.Double.parseDouble(((java.util.List<Object>) object).get(0).toString());")),
			rule().add((condition("type", "variable")), (condition("type", "double")), not(condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = java.lang.Double.parseDouble(((java.util.List<Object>) object).get(0).toString());")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if(name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<")).add(mark("type", "variableTypeList")).add(literal(">) object).get(0);")),
			rule().add((condition("value", "date")), (condition("trigger", "variableType"))).add(literal("java.time.LocalDateTime")),
			rule().add((condition("value", "time")), (condition("trigger", "variableType"))).add(literal("java.time.LocalTime")),
			rule().add((condition("value", "Double")), (condition("trigger", "variableType"))).add(literal("double")),
			rule().add((condition("value", "integer")), (condition("trigger", "variableType"))).add(literal("int")),
			rule().add((condition("value", "file")), (condition("trigger", "variableType"))).add(literal("java.io.File")),
			rule().add((condition("value", "integer")), (condition("trigger", "variableTypeList"))).add(literal("Integer")),
			rule().add((condition("value", "natural")), (condition("trigger", "variableTypeList"))).add(literal("Integer")),
			rule().add((condition("value", "double")), (condition("trigger", "variableTypeList"))).add(literal("Double")),
			rule().add((condition("value", "boolean")), (condition("trigger", "variableTypeList"))).add(literal("Boolean")),
			rule().add((condition("value", "string")), (condition("trigger", "variableTypeList"))).add(literal("String")),
			rule().add((condition("value", "measure")), (condition("trigger", "variableType"))).add(literal("double")),
			rule().add((condition("value", "natural")), (condition("trigger", "variableType"))).add(literal("int")),
			rule().add((condition("value", "string")), (condition("trigger", "variableType"))).add(literal("String")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "multiple")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\",")).add(mark("name", "firstLowerCase")).add(literal(");")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\",")).add(mark("name", "firstLowerCase")).add(literal(");")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "single")), (condition("trigger", "list"))).add(literal("if(")).add(mark("name", "firstLowerCase")).add(literal(" != null) nodes.add(")).add(mark("name", "firstLowerCase")).add(literal("._node());")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "list"))).add(mark("name", "firstLowerCase")).add(literal("List.stream().forEach(c -> nodes.add(c._node()));")),
			rule().add((condition("type", "native")), not(condition("type", "inherited")), (condition("type", "owner")), (condition("Slot", "body")), (condition("trigger", "native"))).add(literal("public static class ")).add(mark("contract", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(" implements ")).add(expression().add(mark("generatedLanguage", "lowercase")).add(literal(".natives."))).add(mark("contract", "firstUpperCase")).add(literal(", tara.magritte.NativeCode  {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\t@Override\n\t")).add(mark("signature")).add(literal(" {\n\t\t")).add(mark("body")).add(literal("\n\t}\n\n\t@Override\n\tpublic void set(tara.magritte.Morph context) {\n\t\t$ = (")).add(mark("nativeContainer")).add(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends tara.magritte.Morph> $Class() {\n\t\treturn ")).add(mark("nativeContainer")).add(literal(".class;\n\t}\n}")),
			rule().add((condition("trigger", "typeInit"))).add(mark("value")),
			rule().add((condition("trigger", "typeDeclaration"))).add(mark("value")).add(literal(" _metaType;")),
			rule().add(not(condition("type", "target")), (condition("type", "nodeimpl")), (condition("trigger", "node"))).add(literal("public")).add(expression().add(literal(" ")).add(mark("inner"))).add(expression().add(literal(" ")).add(mark("abstract"))).add(literal(" class ")).add(mark("name", "javaValidName")).add(literal(" ")).add(mark("parent")).add(literal(" {\n\tpublic static String _type = \"")).add(mark("qn", "noPackage", "withDollar")).add(literal("\";\n\t")).add(mark("variable", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("node", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("facetTarget", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("constraint", "declaration").multiple("\n")).add(literal("|:\n\t")).add(mark("typeDeclaration")).add(literal("|:\n\tpublic ")).add(mark("name", "javaValidName")).add(literal("(tara.magritte.Node node) {\n\t\tsuper(node);\n\t\t")).add(mark("variable", "init").multiple("\n")).add(literal("|:\n\t\t")).add(mark("facetTarget", "init").multiple("\n")).add(literal("|:\n\t\t")).add(mark("constraint", "init").multiple("\n")).add(literal("|:\n\t\t")).add(expression().add(literal("_metaType = node.morph(")).add(mark("typeDeclaration", "typeInit")).add(literal(".class);"))).add(literal("\n\t}\n\t")).add(mark("variable", "getter").multiple("\n\n")).add(literal("|:\n\t")).add(mark("variable", "setter").multiple("\n\n")).add(literal("|:\n\t")).add(mark("node", "getter").multiple("\n\n")).add(literal("|:\n\t")).add(expression().add(literal("@Override")).add(literal("\n")).add(literal("\tprotected void _add(tara.magritte.Node component) {")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "add").multiple("\n")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("@Override")).add(literal("\n")).add(literal("\tprotected void _set(String name, java.lang.Object object) {")).add(literal("\n")).add(literal("\t\tsuper._set(name, object);")).add(literal("\n")).add(literal("\t\t")).add(mark("variable", "set").multiple("\n else ")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("@Override")).add(literal("\n")).add(literal("\tpublic java.util.List<tara.magritte.Node> _components() {")).add(literal("\n")).add(literal("\t\tjava.util.Set<tara.magritte.Node> nodes = new java.util.LinkedHashSet<>();")).add(literal("\n")).add(literal("\t\t")).add(mark("node", "list").multiple("\n")).add(literal("\n")).add(literal("\t\treturn new java.util.ArrayList(nodes);")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(expression().add(literal("@Override")).add(literal("\n")).add(literal("\tpublic java.util.Map<String, java.lang.Object> _variables() {")).add(literal("\n")).add(literal("\t\tjava.util.Map<String, Object> map = new java.util.LinkedHashMap<>(")).add(mark("parent", "var")).add(literal(");")).add(literal("\n")).add(literal("\t\t")).add(mark("variable", "list").multiple("\n")).add(literal("|:")).add(literal("\n")).add(literal("\t\treturn map;")).add(literal("\n")).add(literal("\t}"))).add(literal("\n\t")).add(mark("variable", "native").multiple("\n")).add(literal("|:\n\t")).add(mark("parameter", "native").multiple("\n")).add(literal("|:\n\t")).add(mark("node").multiple("\n")).add(literal("|:\n}")),
			rule().add((condition("value", "Morph")), (condition("trigger", "var"))),
			rule().add((condition("trigger", "var"))).add(literal("super._variables()")),
			rule().add((condition("value", "true")), (condition("trigger", "inner"))).add(literal("static")),
			rule().add((condition("value", "true")), (condition("trigger", "abstract"))).add(literal("abstract")),
			rule().add((condition("trigger", "parent")), (condition("slot", "value"))).add(literal("extends ")).add(mark("value", "reference"))
		);
		return this;
	}
}