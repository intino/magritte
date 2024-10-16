package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;

import java.util.ArrayList;
import java.util.List;

import static io.intino.itrules.template.condition.predicates.Predicates.*;
import static io.intino.itrules.template.outputs.Outputs.literal;
import static io.intino.itrules.template.outputs.Outputs.placeholder;

public class DeclarationTemplate extends Template {

	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "multiple", "owner", "reactive"), not(any(allTypes("outDefined"), allTypes("inherited")))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Expression<java.util.List<")).output(placeholder("type", "FirstUpperCase")).output(literal(">> ")).output(placeholder("name", "javaValidName")).output(literal(";\n\npublic enum ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal(" {\n\t")).output(placeholder("words").multiple(", ")).output(literal(";\n}")));
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "multiple", "owner"), not(any(allTypes("outDefined"), allTypes("inherited")))), trigger("declaration"))).output(literal("protected java.util.List<")).output(placeholder("type", "FirstUpperCase")).output(literal("> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" = new java.util.ArrayList<>();\n\npublic enum ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal(" {\n\t")).output(placeholder("words").multiple(", ")).output(literal(";\n}")));
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "multiple", "owner", "OutDefined"), not(allTypes("inherited"))), trigger("declaration"))).output(literal("protected java.util.List<")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal("> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" = new java.util.ArrayList<>();")));
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "owner", "outDefined"), not(allTypes("inherited"))), trigger("declaration"))).output(literal("protected ")).output(placeholder("workingPackage", "LowerCase")).output(literal(".rules.")).output(placeholder("rule", "externalWordClass")).output(literal(" ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "owner", "reactive"), not(any(allTypes("OutDefined"), allTypes("inherited")))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Expression<")).output(placeholder("type", "FirstUpperCase")).output(literal("> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";\n\npublic enum ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal(" {\n\t")).output(placeholder("words").multiple(", ")).output(literal(";\n}")));
		rules.add(rule().condition(all(all(allTypes("Variable", "Word", "owner"), not(any(allTypes("OutDefined"), allTypes("inherited")))), trigger("declaration"))).output(literal("protected ")).output(placeholder("type", "FirstUpperCase")).output(literal(" ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";\n\npublic enum ")).output(placeholder("name", "FirstUpperCase", "javaValidName")).output(literal(" {\n\t")).output(placeholder("words").multiple(", ")).output(literal(";\n}")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "owner", "concept", "multiple"), not(allTypes("inherited"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected java.util.List<io.intino.magritte.framework.Concept> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" = new java.util.ArrayList<>();")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "reactive", "owner", "multiple"), not(allTypes("inherited"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Expression<java.util.List<")).output(placeholder("type", "fullType", "reference")).output(literal(">> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "reactive", "owner", "concept"), not(allTypes("inherited"))), not(allTypes("multiple"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Expression<io.intino.magritte.framework.Concept> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "reactive", "owner"), not(allTypes("inherited"))), not(allTypes("multiple"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Expression<")).output(placeholder("type", "fullType", "reference")).output(literal("> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(all(all(allTypes("Variable", "function"), allTypes("owner")), not(allTypes("inherited"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected ")).output(placeholder("workingPackage", "LowerCase")).output(literal(".functions.")).output(placeholder("rule", "interfaceClass")).output(literal(" ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(all(all(all(allTypes("Variable", "owner"), not(allTypes("concept"))), allTypes("multiple")), not(allTypes("inherited"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected java.util.List<")).output(placeholder("type", "fullType", "reference")).output(literal("> ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(" = new java.util.ArrayList<>();")));
		rules.add(rule().condition(all(all(all(all(all(allTypes("Variable", "owner"), not(allTypes("concept"))), not(allTypes("multiple"))), not(allTypes("inherited"))), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected ")).output(placeholder("type", "reference", "variableType")).output(literal(" ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(allTypes("aspect"), not(allTypes("overriden"))), trigger("declaration"))).output(literal("protected ")).output(placeholder("qn")).output(literal(" _")).output(placeholder("name", "javaValidName", "FirstLowerCase")).output(literal(";")));
		rules.add(rule().condition(all(allTypes("constraint"), trigger("declaration"))).output(literal("protected ")).output(placeholder("qn", "reference")).output(literal(" _")).output(placeholder("name", "javaValidName", "FirstLowerCase")).output(literal(";")));
		rules.add(rule().condition(all(all(all(allTypes("Variable", "owner"), allTypes("concept")), not(any(any(any(allTypes("multiple"), allTypes("inherited")), allTypes("overriden")), allTypes("instance")))), trigger("declaration"))).output(literal("protected io.intino.magritte.framework.Concept ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(allTypes("Node", "owner", "single"), not(any(any(allTypes("inherited"), allTypes("instance")), allTypes("overriden")))), trigger("declaration"))).output(literal("protected ")).output(placeholder("qn", "reference")).output(literal(" ")).output(placeholder("name", "FirstLowerCase", "javaValidName")).output(literal(";")));
		rules.add(rule().condition(all(all(allTypes("Node", "owner"), not(any(any(any(allTypes("inherited"), allTypes("single")), allTypes("overriden")), allTypes("instance")))), trigger("declaration"))).output(literal("protected java.util.List<")).output(placeholder("qn", "reference")).output(literal("> ")).output(placeholder("name", "javaValidName", "FirstLowerCase")).output(literal("List = new java.util.ArrayList<>();")));
		rules.add(rule().condition(all(allTypes("Node"), trigger("declaration"))));
		return rules;
	}

	public String render(Object object) {
		return new io.intino.itrules.Engine(this).render(object);
	}

	public String render(Object object, java.util.Map<String, io.intino.itrules.Formatter> formatters) {
		return new io.intino.itrules.Engine(this).addAll(formatters).render(object);
	}
}