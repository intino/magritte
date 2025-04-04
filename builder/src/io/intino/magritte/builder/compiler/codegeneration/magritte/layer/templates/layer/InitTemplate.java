package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class InitTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(allTypes("aspect"), allTypes("overriden")), trigger("init"))).output(literal("_")).output(placeholder("name", "FirstLowerCase")).output(literal(".core$().load(_")).output(placeholder("name", "FirstLowerCase")).output(literal(", name, values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "multiple", "outDefined", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.WordLoader.load(values, ")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(".class, this);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "outDefined", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.WordLoader.load(values, ")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(".class, this).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.WordLoader.load(values, ")).output(placeholder("type")).output(literal(".class, this);")));
		rules.add(rule().condition(all(all(allTypes("variable", "word", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.WordLoader.load(values, ")).output(placeholder("type")).output(literal(".class, this).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "reactive", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.FunctionLoader.load(values, this, io.intino.magritte.framework.Expression.class).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "objectVariable", "owner", "multiple"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ObjectLoader.load(values,")).output(placeholder("type", "withoutGeneric")).output(literal(".class, this);")));
		rules.add(rule().condition(all(all(allTypes("variable", "objectVariable", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ObjectLoader.load(values,")).output(placeholder("type", "withoutGeneric")).output(literal(".class, this).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "function", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.FunctionLoader.load(values, this, ")).output(placeholder("workingPackage")).output(literal(".functions.")).output(placeholder("rule", "interfaceClass")).output(literal(".class).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "owner", "multiple"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.")).output(placeholder("type")).output(literal("Loader.load(values, this);")));
		rules.add(rule().condition(all(all(allTypes("variable", "owner"), not(any(any(any(allTypes("multiple"), allTypes("concept")), allTypes("inherited")), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.")).output(placeholder("type")).output(literal("Loader.load(values, this).get(0);")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}