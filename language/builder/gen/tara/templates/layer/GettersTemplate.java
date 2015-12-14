package tara.templates.layer;

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
			rule().add((condition("type", "Variable & Word & target & multiple")), not(condition("type", "owner")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "reference")).add(literal("> ")).add(mark("name")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & Word & target")), not(condition("type", "owner")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "reference")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name")).add(literal("();\n}")),
			rule().add((condition("type", "Variable & target & multiple & final")), not(condition("type", "owner")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("());\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "owner")), (condition("type", "target")), (condition("slot", "name")), (condition("type", "multiple")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "function")), not(condition("type", "owner")), (condition("type", "target")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "isReturn")).add(literal(" _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters", "WithOutType")).add(literal(");\n}")),
			rule().add((condition("type", "Variable & concept & multiple & target & final")), (condition("slot", "name")), not(condition("type", "inherited & owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("());\n}\n\npublic tara.magritte.Concept ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable & concept & multiple & target")), (condition("slot", "name")), not(condition("type", "inherited & owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}\n\npublic tara.magritte.Concept ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "concept")), not(condition("type", "owner")), (condition("type", "target")), (condition("slot", "name")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public tara.magritte.Concept ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "target")), not(condition("type", "owner & type")), (condition("slot", "name")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("();\n}")),
			rule().add((condition("type", "Node & single & target")), (condition("slot", "name")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowercase")).add(literal("();\n}")),
			rule().add((condition("type", "Node & target & final")), (condition("slot", "name")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn java.util.Collections.unmodifiableList((java.util.List<")).add(mark("qn", "reference")).add(literal(">) _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List());\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Node & target")), (condition("slot", "name")), not(condition("type", "owner")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn (java.util.List<")).add(mark("qn", "reference")).add(literal(">) _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List();\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn _")).add(mark("targetContainer", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Variable & reference & concept & multiple")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firtLowercase")).add(literal(");\n}")),
			rule().add((condition("type", "Variable & reference & concept")), not(condition("type", "inherited | overriden")), (condition("trigger", "getter"))).add(literal("public tara.magritte.Concept ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firtLowercase")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & Word & outDefined & multiple & final")), not(condition("type", "inherited | overriden | target")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("generatedLanguage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name")).add(literal(");\n}")),
			rule().add((condition("type", "Variable & Word & outDefined & multiple")), not(condition("type", "inherited | overriden | target")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("generatedLanguage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & word & outDefined")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("generatedLanguage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & word & multiple & final")), not(condition("type", "target & outDefined & inherited & overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name")).add(literal(");\n}")),
			rule().add((condition("type", "Variable & word & multiple")), not(condition("type", "target & outDefined & inherited & overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "reference")).add(literal("> ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & Word")), not(condition("type", "outDefined & inherited")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type")).add(literal(" ")).add(mark("name")).add(literal("() {\n\treturn ")).add(mark("name")).add(literal(";\n}")),
			rule().add((condition("type", "variable & native & multiple")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".value();\n}")),
			rule().add((condition("type", "variable & native")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".value();\n}")),
			rule().add((condition("type", "variable & function")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("returnType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("parameters")).add(literal(") {\n\t")).add(mark("returnType", "isReturn")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal(".")).add(mark("methodName")).add(literal("(")).add(mark("parameters", "WithoutType")).add(literal(");\n}")),
			rule().add(not(condition("value", "void")), (condition("trigger", "isReturn"))).add(literal("return")),
			rule().add((condition("value", "void")), (condition("trigger", "isReturn"))),
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited & overriden & outdefined")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(";\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".get(index);\n}")),
			rule().add((condition("type", "Variable & multiple & owner & final")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firstLowerCase")).add(literal(");\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".get(index);\n}")),
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("type", "variableTypeList")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(";\n}\n\npublic ")).add(mark("type", "variableTypeList")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal(".get(index);\n}")),
			rule().add((condition("type", "Variable & owner")), not(condition("type", "inherited & overriden")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("type", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowercase")).add(literal(";\n}")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("() {\n\treturn ")).add(mark("name", "firstLowercase")).add(literal(";\n}")),
			rule().add((condition("type", "Node & owner")), (condition("type", "overriden")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(mark("qn", "reference")).add(literal("> extended")).add(mark("name", "firstUpperCase")).add(literal("List() {\n\treturn new tara.magritte.utils.ProxyList<>(categoryList, ")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn extended")).add(mark("name", "firstUpperCase")).add(literal("List().get(index);\n}")),
			rule().add((condition("type", "Node & owner & final")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(expression().add(mark("abstractInner"))).add(literal(" ")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn java.util.Collections.unmodifiableList(")).add(mark("name", "firstLowerCase")).add(literal("List);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List.get(index);\n}")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "inherited")), (condition("trigger", "getter"))).add(literal("public java.util.List<")).add(expression().add(mark("abstractInner")).add(literal(" "))).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")).add(literal("List() {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List;\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "firstLowerCase")).add(literal("(int index) {\n\treturn ")).add(mark("name", "firstLowerCase")).add(literal("List.get(index);\n}"))
		);
		return this;
	}
}