package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class Init_referenceTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(all(all(allTypes("variable", "reference", "multiple", "concept"), not(any(allTypes("inherited"), allTypes("reactive")))), not(allTypes("overriden"))), allTypes("owner")), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ConceptLoader.load(values, this);")));
		rules.add(rule().condition(all(all(allTypes("variable", "reference", "concept", "owner"), not(any(any(allTypes("inherited"), allTypes("reactive")), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ConceptLoader.load(values, this).get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "reference", "multiple", "owner"), not(any(any(any(allTypes("concept"), allTypes("reactive")), allTypes("inherited")), allTypes("overriden")))), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.NodeLoader.load(values,  ")).output(placeholder("type", "reference")).output(literal(".class, this);")));
		rules.add(rule().condition(all(all(all(all(all(allTypes("variable", "reference"), not(any(allTypes("concept"), allTypes("reactive")))), not(allTypes("inherited"))), not(allTypes("overriden"))), allTypes("owner")), trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.NodeLoader.load(values, ")).output(placeholder("type", "reference")).output(literal(".class, this).get(0);")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}