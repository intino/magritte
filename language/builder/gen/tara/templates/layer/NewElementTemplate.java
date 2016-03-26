package tara.templates.layer;

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
			rule().add((condition("type", "Variable & Word & multiple")), not(condition("type", "outDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("List<")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name", "javaValidWord")),
			rule().add((condition("type", "Variable & Word & multiple & OutDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("List<")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & Word & outDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & Word")), not(condition("type", "OutDefined")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("qn")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name", "javaValidWord")),
			rule().add((condition("type", "Variable & reactive & multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<java.util.List<")).add(mark("type", "variableTypeList", "reference")).add(literal(">> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & reactive")), not(condition("type", "multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<")).add(mark("type", "variableTypeList", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & function")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("generatedLanguage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & multiple")), not(condition("type", "concept")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("java.util.List<")).add(mark("type", "variableTypeList", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable")), not(condition("type", "concept")), not(condition("type", "multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & concept & multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "Variable & concept")), not(condition("type", "multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Concept ")).add(mark("name", "firstLowerCase", "javaValidWord")),
			rule().add((condition("type", "variable & multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "assign"))).add(literal("newElement._instance().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(");")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "assign"))).add(literal("newElement._instance().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("));")),
			rule().add((condition("type", "variable")), not(condition("slot", "values")), not(condition("slot", "wordvalues")), (condition("trigger", "name"))).add(mark("name", "javaValidWord"))
		);
		return this;
	}
}