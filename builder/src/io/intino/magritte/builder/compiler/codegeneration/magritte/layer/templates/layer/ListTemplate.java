package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class ListTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(allTypes("Variable", "multiple", "owner"), not(any(any(allTypes("reactive"), allTypes("inherited")), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(");")));
		rules.add(rule().condition(all(all(allTypes("Variable", "multiple", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(")) : java.util.Collections.emptyList());")));
		rules.add(rule().condition(all(all(allTypes("Variable", "reference", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(")) : java.util.Collections.emptyList());")));
		rules.add(rule().condition(all(all(allTypes("Variable", "function", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(")) : java.util.Collections.emptyList());")));
		rules.add(rule().condition(all(all(allTypes("Variable", "owner"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(")));")));
		rules.add(rule().condition(all(all(allTypes("Variable", "metaType", "multiple"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", _")).output(placeholder("containerName", "FirstLowerCase")).output(literal(".")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal("());")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "metaType"), any(allTypes("function"), allTypes("reactive"))), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(io.intino.magritte.framework.utils.NativeExtractor.extract(\"")).output(placeholder("name")).output(literal("\", _")).output(placeholder("containerName", "FirstLowerCase")).output(literal("))));")));
		rules.add(rule().condition(all(all(allTypes("Variable", "metaType"), not(any(allTypes("inherited"), allTypes("overriden")))), trigger("list"))).output(literal("map.put(\"")).output(placeholder("name", "FirstLowerCase")).output(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(this._")).output(placeholder("containerName", "FirstLowerCase")).output(literal(".")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal("())));")));
		rules.add(rule().condition(all(all(allTypes("Node", "single", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("instance")))), trigger("list"))).output(literal("if (")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" != null) nodes.add(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(".core$());")));
		rules.add(rule().condition(all(all(allTypes("Node", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("instance")))), trigger("list"))).output(placeholder("name", "toCamelCase", "FirstLowerCase")).output(literal("List.stream().forEach(c -> nodes.add(c.core$()));")));
		rules.add(rule().condition(all(all(allTypes("Node", "single", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("instance")))), trigger("componentlist"))).output(literal("if (")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" != null) components.add(this.")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(".core$());")));
		rules.add(rule().condition(all(all(allTypes("Node", "owner"), not(any(any(allTypes("inherited"), allTypes("overriden")), allTypes("instance")))), trigger("componentlist"))).output(literal("new java.util.ArrayList<>(")).output(placeholder("name", "toCamelCase", "toCamelCase", "FirstLowerCase")).output(literal("List).forEach(c -> components.add(c.core$()));")));
		rules.add(rule().condition(all(allTypes("Node"), trigger("list"))));
		rules.add(rule().condition(all(allTypes("Node"), trigger("componentlist"))));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}