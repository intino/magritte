package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class GettersTemplate extends Template {

	protected GettersTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new GettersTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & word & outDefined & multiple & final")), not(condition("type", "inherited | overriden | owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidName")).add(literal("());\n}")),
			rule().add((condition("type", "Variable & word & outDefined & multiple")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("externalClass")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & word & outDefined & multiple")), not(condition("type", "inherited | overriden | owner | concept")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & word & outDefined")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner | concept")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("externalClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & word & outDefined")), not(condition("type", "inherited | overriden | owner | concept")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & word & multiple")), (condition("type", "target | metaType")), not(condition("type", "owner | concept")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "firstUpperCase", "javaValidName", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & Word")), (condition("type", "target | metaType")), not(condition("type", "owner | concept")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "firstUpperCase", "javaValidName", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & multiple & final")), (condition("type", "target | metaType")), not(condition("type", "owner | concept")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("());\n}\n\npublic ")).add(mark("type", "fullType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "owner | concept")), (condition("type", "target | metaType")), (condition("slot", "name")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}\n\npublic ")).add(mark("type", "fullType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable & function")), (condition("type", "target | metaType")), not(condition("type", "owner")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "isReturn")).add(literal(" _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("parameters", "WithOutType")).add(literal(");\n}")),
			rule().add((condition("type", "Variable & concept & multiple & target & final")), (condition("slot", "name")), not(condition("type", "inherited & owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<io.intino.tara.magritte.Concept> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(_")).add(mark("containerName", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("());\n}\n\npublic io.intino.tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable & concept & multiple & target")), (condition("slot", "name")), not(condition("type", "inherited & owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<io.intino.tara.magritte.Concept> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}\n\npublic io.intino.tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "concept")), not(condition("type", "owner")), (condition("type", "target")), (condition("slot", "name")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public io.intino.tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & target")), not(condition("type", "owner | concept")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Node & instance")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("conceptLayer", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _metaType.")).add(mark("type", "firstLowerCase")).add(literal("(o -> o.name().equals(\"")).add(mark("name")).add(literal("\")).get(0);\n}")),
			rule().add((condition("type", "Node & single & target")), (condition("slot", "name")), not(condition("type", "owner | inherited | instance")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "Node & target & final")), (condition("slot", "name")), not(condition("type", "owner | inherited | instance")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List() {\n\treturn java.util.Collections.unmodifiableList((java.util.List<")).add(mark("qn", "reference")).add(literal(">) _")).add(mark("targetContainer", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List());\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Node & target")), (condition("slot", "name")), not(condition("type", "owner | inherited | instance")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List() {\n\treturn (java.util.List<")).add(mark("qn", "reference")).add(literal(">) _")).add(mark("targetContainer", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List();\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Node & target")), (condition("slot", "name")), not(condition("type", "owner | instance | single")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List() {\n\treturn (java.util.List<")).add(mark("qn", "reference")).add(literal(">) _")).add(mark("targetContainer", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List();\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Node & target & single")), (condition("slot", "name")), not(condition("type", "owner | instance")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn (")).add(mark("qn", "reference")).add(literal(") _")).add(mark("targetContainer", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("();\n}")),
			rule().add((condition("type", "variable & reference & concept & multiple")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<io.intino.tara.magritte.Concept> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firstLowercase", "javaValidName")).add(literal(");\n}")),
			rule().add((condition("type", "variable & reference & concept")), not(condition("type", "inherited | overriden | overriden")), (condition("trigger", "getter"))).add(literal("public io.intino.tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn this.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "variable & word & outDefined & multiple & final")), not(condition("type", "inherited | overriden | target | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(");\n}")),
			rule().add((condition("type", "variable & word & outDefined & multiple")), not(condition("type", "inherited | overriden | target | reactive")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "variable & word & outDefined")), not(condition("type", "inherited | reactive")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "variable & word & multiple & final")), not(condition("type", "target | outDefined | inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name", "javaValidName", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "javaValidName")).add(literal(");\n}")),
			rule().add((condition("type", "variable & word & multiple")), not(condition("type", "target | outDefined | inherited | overriden | reactive")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "variable & word")), not(condition("type", "outDefined | inherited | overriden | reactive")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "variable & reactive & multiple")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".value();\n}")),
			rule().add((condition("type", "variable & reactive")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".value();\n}")),
			rule().add((condition("type", "variable & function")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "isReturn")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".")).add(mark("methodName")).add(literal("(")).add(mark("parameters", "WithoutType")).add(literal(");\n}")),
			rule().add(not(condition("value", "void")), (condition("trigger", "isReturn"))).add(literal("return")),
			rule().add((condition("value", "void")), (condition("trigger", "isReturn"))),
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited | overriden | outdefined")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}\n\npublic ")).add(mark("type", "fullType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".get(index);\n}\n\npublic java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.function.Predicate<")).add(mark("type", "fullType")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Variable & multiple & owner & final")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(");\n}\n\npublic ")).add(mark("type", "fullType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".get(index);\n}\n\npublic java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.function.Predicate<")).add(mark("type", "fullType")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}\n\npublic ")).add(mark("type", "fullType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".get(index);\n}\n\npublic java.util.List<")).add(mark("type", "fullType")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.function.Predicate<")).add(mark("type", "fullType")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Variable & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(";\n}")),
			rule().add((condition("type", "Node & owner & overriden")), not(condition("type", "single")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("containerName", "firstLowercase", "javaValidName")).add(mark("name", "firstUpperCase")).add(literal("List() {\n\treturn new io.intino.tara.magritte.utils.ProxyList<>(")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List, ")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("containerName", "firstLowerCase")).add(mark("name", "firstUpperCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("containerName", "firstLowercase", "javaValidName")).add(mark("name", "firstUpperCase")).add(literal("List().get(index);\n}\n\npublic java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("containerName", "firstLowercase", "javaValidName")).add(mark("name", "firstUpperCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> predicate) {\n\treturn ")).add(mark("containerName", "firstLowercase", "javaValidName")).add(mark("name", "firstUpperCase")).add(literal("List().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Node & owner & final")), not(condition("type", "single | inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List.get(index);\n}\n\npublic java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "toCamelCase")).add(literal("firstLowerCase+List().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "single | inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(int index) {\n\treturn ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List.get(index);\n}\n\npublic java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List(java.util.function.Predicate<")).add(mark("qn", "reference")).add(literal("> predicate) {\n\treturn ")).add(mark("name", "toCamelCase", "firstLowerCase")).add(literal("List().stream().filter(predicate).collect(java.util.stream.Collectors.toList());\n}"))
		);
		return this;
	}
}