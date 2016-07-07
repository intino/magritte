package tara.compiler.codegeneration.magritte.layer.templates.layer;

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
			rule().add((condition("type", "variable & word & outDefined & multiple")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.util.List<")).add(mark("language")).add(literal(".rules.")).add(mark("externalClass")).add(literal("> values) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord")).add(literal("(values);\n}")),
			rule().add((condition("type", "Variable & Word & outDefined & multiple")), not(condition("type", "inherited | overriden | owner | concept")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.util.List<")).add(mark("generatedLanguage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> values) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord")).add(literal("(values);\n}")),
			rule().add((condition("type", "variable & word & outDefined")), (condition("slot", "externalClass")), not(condition("type", "inherited | overriden | owner")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("language")).add(literal(".rules.")).add(mark("externalClass")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable & Word & outDefined")), not(condition("type", "inherited | overriden | owner | concept")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("generatedLanguage")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable & word")), not(condition("type", "multiple | owner | reactive | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord")).add(literal("(")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "javaValidWord", "reference")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable & resource")), not(condition("type", "multiple | owner | reactive | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value, String destiny) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(value, destiny);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple | owner | concept | function | resource | reactive | final | word")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable & Concept")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "function")), not(condition("type", "reactive")), not(condition("type", "final")), not(condition("type", "Word")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(tara.magritte.Concept value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable & Word & outDefined")), not(condition("type", "target | inherited | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & Word")), not(condition("type", "target | inherited | overriden | multiple | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(expression().add(mark("qn", "reference")).add(literal("."))).add(mark("name", "javaValidWord", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & resource")), not(condition("type", "target | inherited | overriden | reactive | multiple | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.net.URL url, String destiny) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = graph().save(url, destiny, this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(", node());\n}\n\npublic void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.io.InputStream stream, String destiny) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = graph().save(stream, destiny, this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(", node());\n}\n\npublic java.io.OutputStream ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(String destiny) {\n\treturn null;//TODO\n}")),
			rule().add((condition("type", "variable & resource & multiple")), not(condition("type", "target | inherited | overriden | reactive | final")), (condition("trigger", "setter"))).add(literal("public void add")).add(mark("name", "firstUpperCase")).add(literal("(java.net.URL url, String destiny) {\n\tjava.net.URL newElement = graph().save(url, destiny, null, node());\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(".add(newElement);\n}\n\npublic void add")).add(mark("name", "firstUpperCase")).add(literal("(java.io.InputStream stream, String destiny) {\n\tjava.net.URL newElement = graph().save(stream, destiny, null, node());\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(".add(newElement);\n}\n\npublic java.io.OutputStream ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(String destiny) {\n\treturn null;//TODO\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited | concept | overriden | function | reactive | multiple | final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "final | inherited | overriden")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(java.util.List<tara.magritte.Concept> value) {\n\tthis.")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "final")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(tara.magritte.Concept value) {\n\tthis.")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("qn", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Node")), (condition("trigger", "setter")))
		);
		return this;
	}
}