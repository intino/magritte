package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class ConstructorTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "word", "multiple"), allTypes("owner")), not(allTypes("inherited"))), attribute("words")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).output(placeholder("wordValues", "quoted").multiple(", ")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "word"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).output(placeholder("wordValues", "quoted").multiple(", ")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "reactive"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Collections.singletonList(")).output(placeholder("workingPackage")).output(literal(".natives.")).output(placeholder("package")).output(literal(".")).output(placeholder("name", "javaValidName")).output(literal("_")).output(placeholder("uid")).output(literal(".class.getName())));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "function"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("body")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Collections.singletonList(")).output(placeholder("workingPackage")).output(literal(".natives.")).output(placeholder("package")).output(literal(".")).output(placeholder("name", "javaValidName")).output(literal("_")).output(placeholder("uid")).output(literal(".class.getName())));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "date"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).output(placeholder("values", "quoted").multiple(", ")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(all(all(allTypes("Variable", "time"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), not(allTypes("multiple"))), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\",  new java.util.ArrayList<>(java.util.Arrays.asList(")).output(placeholder("values", "quoted").multiple(", ")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "double", "multiple"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(new Double[] {")).output(placeholder("values").multiple(", ")).output(literal("})));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "double"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Collections.singletonList((double) ")).output(placeholder("values")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(all(all(allTypes("Variable", "reference"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), allTypes("multiple")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(graph().concept(\"")).output(placeholder("type")).output(literal("\"))));")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "resource"), allTypes("owner")), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", io.intino.magritte.framework.loaders.StringLoader.load(Arrays.asList(")).output(placeholder("values", "quoted").multiple(", ")).output(literal(")));")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "owner"), not(any(allTypes("inherited"), allTypes("empty")))), attribute("values")), trigger("constructor"))).output(literal("_load(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).output(placeholder("values").multiple(", ")).output(literal(")));")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}