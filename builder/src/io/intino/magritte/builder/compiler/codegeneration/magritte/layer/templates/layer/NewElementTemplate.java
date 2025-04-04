package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class NewElementTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "Word", "multiple", "required"), not(allTypes("outDefined"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(literal("java.util.List<")).output(placeholder("qn")).output(literal(".")).output(placeholder("type", "FirstUpperCase")).output(literal("> ")).output(placeholder("name", "javaValidWord")));
		rules.add(rule().condition(all(all(all(allTypes("variable", "Word", "multiple", "OutDefined", "required"), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(literal("java.util.List<")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal("> ")).output(placeholder("name", "FirstLowerCase")));
		rules.add(rule().condition(all(all(all(allTypes("variable", "Word", "outDefined", "required"), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(" ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "Word", "required"), not(allTypes("outDefined"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(placeholder("qn", "reference")).output(literal(".")).output(placeholder("type", "FirstUpperCase")).output(literal(" ")).output(placeholder("name", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "reactive", "multiple", "required"), not(allTypes("empty"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(literal("io.intino.magritte.framework.Expression<java.util.List<")).output(placeholder("type", "fullType", "reference")).output(literal(">> ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "reactive", "required"), not(any(allTypes("multiple"), allTypes("empty")))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(literal("io.intino.magritte.framework.Expression<")).output(placeholder("type", "fullType", "reference")).output(literal("> ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "function", "required"), not(allTypes("empty"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(placeholder("workingPackage", "LowerCase")).output(literal(".functions.")).output(placeholder("rule", "interfaceClass")).output(literal(" ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "multiple", "required"), not(any(allTypes("concept"), allTypes("empty")))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(literal("java.util.List<")).output(placeholder("type", "fullType", "reference")).output(literal("> ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(allTypes("variable", "resource", "required"), not(any(any(allTypes("concept"), allTypes("empty")), allTypes("multiple")))), trigger("parameters"))).output(placeholder("type", "reference", "variableType")).output(literal(" ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "required"), not(any(any(allTypes("concept"), allTypes("empty")), allTypes("multiple")))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("parameters"))).output(placeholder("type", "reference", "variableType")).output(literal(" ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(allTypes("variable", "concept", "multiple", "required"), not(allTypes("empty"))), not(attribute("values"))), trigger("parameters"))).output(literal("java.util.List<io.intino.magritte.framework.Concept> ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(allTypes("variable", "concept", "required"), not(any(allTypes("multiple"), allTypes("empty")))), not(attribute("values"))), trigger("parameters"))).output(literal("io.intino.magritte.framework.Concept ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "reactive", "required"), not(allTypes("empty"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", java.util.Collections.singletonList(")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal("));")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "multiple", "required"), not(allTypes("empty"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", ")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(");")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "required"), not(any(allTypes("multiple"), allTypes("empty")))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", java.util.Collections.singletonList(")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal("));")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "required"), not(allTypes("empty"))), not(attribute("values"))), not(attribute("wordvalues"))), trigger("name"))).output(placeholder("name", "javaValidWord")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}