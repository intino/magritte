package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class Init_referenceTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("reference", "concept", "variable", "multiple")), not(anyTypes("reactive", "inherited")), not(type("overriden")), (type("owner")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.tara.magritte.loaders.ConceptLoader.load(values, this);")),
				rule().condition((allTypes("reference", "owner", "concept", "variable")), not(anyTypes("reactive", "inherited", "overriden")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.tara.magritte.loaders.ConceptLoader.load(values, this).get(0);")),
				rule().condition((allTypes("reference", "owner", "variable", "multiple")), not(anyTypes("reactive", "inherited", "concept", "overriden")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.tara.magritte.loaders.NodeLoader.load(values,  ")).output(mark("type", "reference")).output(literal(".class, this);")),
				rule().condition((allTypes("reference", "variable")), not(anyTypes("reactive", "concept")), not(type("inherited")), not(type("overriden")), (type("owner")), (trigger("init"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = io.intino.tara.magritte.loaders.NodeLoader.load(values, ")).output(mark("type", "reference")).output(literal(".class, this).get(0);"))
		);
	}
}