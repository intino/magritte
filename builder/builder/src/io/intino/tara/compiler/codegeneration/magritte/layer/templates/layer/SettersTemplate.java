package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class SettersTemplate extends Template {

	protected SettersTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new SettersTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & word & outDefined & multiple")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("externalClass")).add(literal("> values) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(values);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable & word & outDefined & multiple")), not(condition("type", "inherited | overriden | owner | concept | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.List<")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> values) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(values);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & word & outDefined")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("externalClass")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable & word & outDefined")), not(condition("type", "inherited | overriden | owner | concept | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("workingPackage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & word")), not(condition("type", "multiple | owner | reactive | final | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "firstUpperCase", "javaValidName", "reference")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & resource")), not(condition("type", "multiple | owner | reactive | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value, String destiny) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value, destiny);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}\n\npublic ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.io.InputStream stream, String destiny) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(stream, destiny);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable & reactive")), not(condition("type", "owner | inherited | concept | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(io.intino.tara.magritte.Expression<")).add(mark("type", "fullType", "reference")).add(literal("> value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable & function")), not(condition("type", "owner | inherited | concept | overriden | multiple | final")), (condition("trigger", "setter"))),
			rule().add((condition("type", "variable")), not(condition("type", "multiple | owner | concept | function | resource | reactive | final | word")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & Concept")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "function")), not(condition("type", "reactive")), not(condition("type", "final")), not(condition("type", "Word")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(io.intino.tara.magritte.Concept value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(value);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & word & outDefined")), not(condition("type", "target | inherited | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = value;\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & word")), not(condition("type", "target | inherited | overriden | multiple | final | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(expression().add(mark("qn", "reference")).add(literal("."))).add(mark("name", "FirstUpperCase", "javaValidName", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = value;\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & resource")), not(condition("type", "target | inherited | overriden | reactive | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("type", "fulltype", "reference")).add(literal(" url, String destiny) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = graph().core$().save(url, destiny, this.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(", core$());\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}\n\npublic ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.io.InputStream stream, String destiny) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = graph().core$().save(stream, destiny, this.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(", core$());\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & resource & multiple")), not(condition("type", "target | inherited | overriden | reactive | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" add")).add(mark("name", "firstUpperCase")).add(literal("(")).add(mark("type", "fulltype", "reference")).add(literal(" url, String destiny) {\n\t")).add(mark("type", "fulltype", "reference")).add(literal(" newElement = graph().core$().save(url, destiny, null, core$());\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".add(newElement);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}\n\npublic ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" add")).add(mark("name", "firstUpperCase")).add(literal("(java.io.InputStream stream, String destiny) {\n\t")).add(mark("type", "fulltype", "reference")).add(literal(" newElement = graph().core$().save(stream, destiny, null, core$());\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".add(newElement);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}\n\npublic java.io.OutputStream add")).add(mark("name", "firstUpperCase", "javaValidName")).add(literal("(String destiny) {\n\t")).add(mark("type", "fulltype", "reference")).add(literal(" newElement = graph().core$().save((java.io.InputStream)null, destiny, null, core$());\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(".add(newElement);\n\ttry {\n\t\treturn newElement.openConnection().getOutputStream();\n\t} catch(java.io.IOException e) {\n\t\tjava.util.logging.Logger.getGlobal().severe(e.getMessage());\n\t\treturn null;\n\t}\n}")),
			rule().add((condition("type", "Variable & reactive")), not(condition("type", "inherited | concept | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(io.intino.tara.magritte.Expression<")).add(mark("type", "fullType", "reference")).add(literal("> value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable & function")), not(condition("type", "inherited | concept | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("workingPackage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = io.intino.tara.magritte.loaders.FunctionLoader.load(")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(", this, ")).add(mark("workingPackage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(".class);\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited | concept | overriden | function | reactive | multiple | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = value;\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "final | inherited | overriden | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(java.util.List<io.intino.tara.magritte.Concept> value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName", "firstLowercase")).add(literal(" = value;\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "final | inherited | overriden | multiple | reactive")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(io.intino.tara.magritte.Concept value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName", "firstLowercase")).add(literal(" = value;\n\treturn (")).add(mark("container", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited | overriden | final")), (condition("trigger", "setter"))).add(literal("public ")).add(mark("containerName", "firstUpperCase", "javaValidName")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal("(")).add(mark("qn", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidName")).add(literal(" = value;\n\treturn (")).add(mark("containerName", "firstUpperCase", "javaValidName")).add(literal(") this;\n}")),
			rule().add((condition("type", "Node")), (condition("trigger", "setter")))
		);
		return this;
	}
}