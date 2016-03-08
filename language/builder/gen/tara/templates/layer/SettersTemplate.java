package tara.templates.layer;

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
			rule().add((condition("type", "Variable & Word")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "native")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord")).add(literal("(")).add(mark("qn", "reference")).add(literal(".")).add(mark("name", "javaValidWord", "reference")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable & file")), not(condition("type", "concept")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "native")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value, String destiny) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(value, destiny);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "concept")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "function")), not(condition("type", "native")), not(condition("type", "final")), not(condition("type", "Word")), not(condition("type", "function")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable & Word")), (condition("type", "outDefined")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Variable & Word")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(expression().add(mark("qn", "reference")).add(literal("."))).add(mark("name", "javaValidWord", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Variable & resource")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(java.net.URL url, String destiny) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = model().save(url, destiny, this.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(", _instance());\n}\n\npublic void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(java.io.InputStream stream, String destiny) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = model().save(stream, destiny, this.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(", _instance());\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "concept")), not(condition("type", "overriden")), not(condition("type", "function")), not(condition("type", "native")), not(condition("type", "multiple")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "final")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(java.util.List<tara.magritte.Concept> value) {\n\tthis.")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "final")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(tara.magritte.Concept value) {\n\tthis.")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "final")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("qn", "reference")).add(literal(" value) {\n\tthis.")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Node")), (condition("trigger", "setter")))
		);
		return this;
	}
}