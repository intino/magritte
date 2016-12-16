package tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class NewElementTemplate extends Template {

	protected NewElementTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new NewElementTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & Word & multiple & required")), not(condition("type", "outDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("java.util.List<")).add(mark("qn")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name", "javaValidWord")),
			rule().add((condition("type", "variable & Word & multiple & OutDefined & required")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("java.util.List<")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "variable & Word & outDefined & required")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & Word & required")), not(condition("type", "outDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("qn", "reference")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name", "javaValidWord")),
			rule().add((condition("type", "variable & reactive & multiple & required")), not(condition("type", "empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<java.util.List<")).add(mark("type", "fullType", "reference")).add(literal(">> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & reactive & required")), not(condition("type", "multiple | empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<")).add(mark("type", "fullType", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & function & required")), not(condition("type", "empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("workingPackage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & multiple & required")), not(condition("type", "concept | empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("java.util.List<")).add(mark("type", "fullType", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & resource & required")), not(condition("type", "concept | empty | multiple")), (condition("trigger", "parameters"))).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & required")), not(condition("type", "concept | empty | multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & concept & multiple & required")), not(condition("type", "empty")), not(condition("slot", "values")), (condition("trigger", "parameters"))).add(literal("java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & concept & required")), not(condition("type", "multiple | empty")), not(condition("slot", "values")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & reactive & required")), not(condition("type", "empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "assign"))).add(literal("newElement.node().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("));")),
			rule().add((condition("type", "variable & multiple & required")), not(condition("type", "empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "assign"))).add(literal("newElement.node().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(");")),
			rule().add((condition("type", "variable & required")), not(condition("type", "multiple | empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "assign"))).add(literal("newElement.node().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("));")),
			rule().add((condition("type", "variable & required")), not(condition("type", "empty")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "name"))).add(mark("name", "javaValidWord"))
		);
		return this;
	}
}