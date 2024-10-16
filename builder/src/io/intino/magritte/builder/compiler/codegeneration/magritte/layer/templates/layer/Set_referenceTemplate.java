package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class Set_referenceTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(allTypes("variable", "Concept", "reference", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = new java.util.ArrayList<>((java.util.List<io.intino.magritte.framework.Concept>) values);")));
		rules.add(rule().condition(all(all(allTypes("variable", "Concept", "reference", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (io.intino.magritte.framework.Concept) values.get(0);")));
		rules.add(rule().condition(all(all(allTypes("variable", "reference", "multiple", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("reactive")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "FirstLowerCase")).output(literal(" = ((java.util.List<java.lang.Object>) values).stream().\n\tmap(s -> graph().core$().load(((io.intino.magritte.framework.Layer) s).core$().id()).as(")).output(placeholder("type", "reference")).output(literal(".class)).collect(java.util.stream.Collectors.toList());")));
		rules.add(rule().condition(all(all(allTypes("variable", "reference", "owner"), not(any(any(allTypes("inherited"), allTypes("reactive")), allTypes("overriden")))), trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\")) this.")).output(placeholder("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = values.get(0) != null ? core$().graph().load(((io.intino.magritte.framework.Layer) values.get(0)).core$().id()).as(")).output(placeholder("type", "reference")).output(literal(".class) : null;")));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}