package io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class Init_referenceTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("variable", "reference", "multiple", "concept")), not(anyTypes("inherited", "reactive")), not(type("overriden")), (type("owner")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ConceptLoader.load(values, this);")),
				rule().condition((allTypes("variable", "reference", "concept", "owner")), not(anyTypes("inherited", "reactive", "overriden")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.ConceptLoader.load(values, this).get(0);")),
				rule().condition((allTypes("variable", "reference", "multiple", "owner")), not(anyTypes("concept", "reactive", "inherited", "overriden")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.NodeLoader.load(values,  ")).output(mark("type", "reference")).output(literal(".class, this);")),
				rule().condition((allTypes("variable", "reference")), not(anyTypes("concept", "reactive")), not(type("inherited")), not(type("overriden")), (type("owner")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.magritte.framework.loaders.NodeLoader.load(values, ")).output(mark("type", "reference")).output(literal(".class, this).get(0);"))
		);
	}
}