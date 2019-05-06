package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class NewElementTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("variable", "multiple", "word", "required")), not(type("outdefined")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(literal("java.util.List<")).output(mark("qn")).output(literal(".")).output(mark("type", "FirstUpperCase")).output(literal("> ")).output(mark("name", "javaValidWord")),
				rule().condition((allTypes("outdefined", "variable", "multiple", "word", "required")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(literal("java.util.List<")).output(mark("workingPackage", "LowerCase")).output(literal(".rules.")).output(mark("rule", "externalWordClass")).output(literal("> ")).output(mark("name", "FirstLowerCase")),
				rule().condition((allTypes("outdefined", "variable", "word", "required")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(mark("workingPackage", "LowerCase")).output(literal(".rules.")).output(mark("rule", "externalWordClass")).output(literal(" ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("variable", "word", "required")), not(type("outdefined")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(mark("qn", "reference")).output(literal(".")).output(mark("type", "FirstUpperCase")).output(literal(" ")).output(mark("name", "javaValidWord")),
				rule().condition((allTypes("reactive", "variable", "multiple", "required")), not(type("empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(literal("io.intino.tara.magritte.Expression<java.util.List<")).output(mark("type", "fullType", "reference")).output(literal(">> ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("reactive", "variable", "required")), not(anyTypes("multiple", "empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(literal("io.intino.tara.magritte.Expression<")).output(mark("type", "fullType", "reference")).output(literal("> ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("function", "variable", "required")), not(type("empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(mark("workingPackage", "LowerCase")).output(literal(".functions.")).output(mark("rule", "interfaceClass")).output(literal(" ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("variable", "multiple", "required")), not(anyTypes("concept", "empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(literal("java.util.List<")).output(mark("type", "fullType", "reference")).output(literal("> ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("resource", "variable", "required")), not(anyTypes("concept", "multiple", "empty")), (trigger("parameters"))).output(mark("type", "reference", "variableType")).output(literal(" ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("variable", "required")), not(anyTypes("concept", "multiple", "empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("parameters"))).output(mark("type", "reference", "variableType")).output(literal(" ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("concept", "variable", "multiple", "required")), not(type("empty")), not(attribute("values")), (trigger("parameters"))).output(literal("java.util.List<io.intino.tara.magritte.Concept> ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("concept", "variable", "required")), not(anyTypes("multiple", "empty")), not(attribute("values")), (trigger("parameters"))).output(literal("io.intino.tara.magritte.Concept ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")),
				rule().condition((allTypes("reactive", "variable", "required")), not(type("empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(mark("name", "FirstLowerCase")).output(literal("\", java.util.Collections.singletonList(")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal("));")),
				rule().condition((allTypes("variable", "multiple", "required")), not(type("empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(mark("name", "FirstLowerCase")).output(literal("\", ")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(");")),
				rule().condition((allTypes("variable", "required")), not(anyTypes("multiple", "empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("assign"))).output(literal("newElement.core$().set(newElement, \"")).output(mark("name", "FirstLowerCase")).output(literal("\", java.util.Collections.singletonList(")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal("));")),
				rule().condition((allTypes("variable", "required")), not(type("empty")), not(attribute("values")), not(attribute("wordvalues")), (trigger("name"))).output(mark("name", "javaValidWord"))
		);
	}
}