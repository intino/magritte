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
			rule().add((condition("type", "Variable & Word & multiple")), not(condition("type", "outDefined")), (condition("trigger", "parameters"))).add(literal("List<")).add(mark("type", "firstUpperCase")).add(literal("> ")).add(mark("name")),
			rule().add((condition("type", "Variable & Word & multiple & OutDefined")), (condition("trigger", "parameters"))).add(literal("List<")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal("> ")).add(mark("name")),
			rule().add((condition("type", "Variable & Word & outDefined")), (condition("trigger", "parameters"))).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" ")).add(mark("name")),
			rule().add((condition("type", "Variable & Word")), not(condition("type", "OutDefined")), (condition("trigger", "parameters"))).add(mark("qn")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" ")).add(mark("name")),
			rule().add((condition("type", "Variable & native & multiple")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<java.util.List<")).add(mark("type", "variableTypeList", "reference")).add(literal(">> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & native")), not(condition("type", "multiple")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Expression<")).add(mark("type", "variableTypeList", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & function")), (condition("trigger", "parameters"))).add(mark("generatedLanguage", "LowerCase")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(" ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & multiple")), not(condition("type", "concept")), (condition("trigger", "parameters"))).add(literal("java.util.List<")).add(mark("type", "variableTypeList", "reference")).add(literal("> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable")), not(condition("type", "concept")), not(condition("type", "multiple")), (condition("trigger", "parameters"))).add(mark("type", "reference", "variableType")).add(literal(" ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & concept & multiple")), (condition("trigger", "parameters"))).add(literal("java.util.List<tara.magritte.Concept> ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "Variable & concept")), not(condition("type", "multiple")), (condition("trigger", "parameters"))).add(literal("tara.magritte.Concept ")).add(mark("name", "firstLowerCase")),
			rule().add((condition("type", "variable")), (condition("type", "multiple")), (condition("trigger", "assign"))).add(literal("newElement._instance().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), (condition("trigger", "assign"))).add(literal("newElement._instance().set(newElement, \"")).add(mark("name", "firstLowerCase")).add(literal("\", java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase")).add(literal("));"))
		);
		return this;
	}
}