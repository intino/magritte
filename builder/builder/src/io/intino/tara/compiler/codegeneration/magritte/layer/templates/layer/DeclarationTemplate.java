package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class DeclarationTemplate extends Template {

	protected DeclarationTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new DeclarationTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & Word & multiple & owner & reactive")), not(condition("type", "outDefined | inherited")), (condition("trigger", "declaration"))).add(literal("protected Expression<java.util.List<")).add(mark("type", "firstUpperCase")).add(literal(">> ")).add(mark("name", "javaValidWord")).add(literal(";\n\npublic enum ")).add(mark("name", "javaValidName", "javaValidWord", "firstUpperCase")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & Word & multiple & owner")), not(condition("type", "outDefined | inherited")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>();\n\npublic enum ")).add(mark("name", "javaValidName", "javaValidWord", "firstUpperCase")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & Word & multiple & owner & OutDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable & Word & owner & outDefined")), not(condition("type", "inherited")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Variable & Word & owner & reactive")), not(condition("type", "OutDefined | inherited")), (condition("trigger", "declaration"))).add(literal("protected Expression<")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";\n\npublic enum ")).add(mark("name", "firstUpperCase", "javaValidWord")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & Word & owner")), not(condition("type", "OutDefined | inherited")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";\n\npublic enum ")).add(mark("name", "firstUpperCase", "javaValidWord")).add(literal(" {\n\t")).add(mark("words").multiple(", ")).add(literal(";\n}")),
			rule().add((condition("type", "Variable & owner & concept & multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<Concept> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable & reactive & owner & multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected Expression<java.util.List<")).add(mark("type", "fullType", "reference")).add(literal(">> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Variable & reactive & owner")), not(condition("type", "inherited")), not(condition("type", "multiple")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected Expression<")).add(mark("type", "fullType", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Variable & function")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("workingPackage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Variable & owner")), not(condition("type", "concept")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("type", "fullType", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "concept")), not(condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "facetTarget")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("qn", "reference")).add(literal(" _")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "constraint")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("qn", "reference")).add(literal(" _")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "concept")), not(condition("type", "multiple | inherited | overriden | instance")), (condition("trigger", "declaration"))).add(literal("protected Concept ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Node & owner & single")), not(condition("type", "inherited | instance | overriden")), (condition("trigger", "declaration"))).add(literal("protected ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(";")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "inherited | single | overriden | instance")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "javaValidName", "firstLowerCase")).add(literal("List = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Node")), (condition("trigger", "declaration")))
		);
		return this;
	}
}