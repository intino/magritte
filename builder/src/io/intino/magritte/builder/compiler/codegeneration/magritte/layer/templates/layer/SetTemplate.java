package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class SetTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(allTypes("aspect", "overriden"), trigger("set"))).output(literal("_")).output(placeholder("name", "FirstLowerCase")).output(literal(".core$().set(_")).output(placeholder("name", "FirstLowerCase")).output(literal(", name, values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "outDefined", "owner", "multiple"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase")).output(literal(" = new java.util.ArrayList<>((java.util.List<")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(">) values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase")).output(literal(" = new java.util.ArrayList<>((java.util.List<")).output(placeholder("type")).output(literal(">) values);")));
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "word"), allTypes("outDefined")), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), allTypes("owner")), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(") values.get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (")).output(placeholder("type")).output(literal(") values.get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "reactive", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.FunctionLoader.load(values.get(0), this, io.intino.magritte.framework.Expression.class);")));
		rules.add(rule().condition(all(all(allTypes("variable", "function", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.FunctionLoader.load(values.get(0), this, ")).output(placeholder("workingPackage")).output(literal(".functions.")).output(placeholder("rule", "interfaceClass")).output(literal(".class);")));
		rules.add(rule().condition(all(all(allTypes("variable", "time", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = new java.util.ArrayList<>((List<java.time.LocalTime>) values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = new java.util.ArrayList<>((java.util.List<")).output(placeholder("type", "fullType")).output(literal(">) values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "owner"), not(any(any(any(any(allTypes("multiple"), allTypes("concept")), allTypes("inherited")), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (")).output(placeholder("type", "fullType")).output(literal(") values.get(0);")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}