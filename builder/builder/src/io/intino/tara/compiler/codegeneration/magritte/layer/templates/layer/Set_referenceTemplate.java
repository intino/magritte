package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class Set_referenceTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("reference", "owner", "concept", "variable", "multiple")), not(anyTypes("reactive", "inherited", "overriden")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = new java.util.ArrayList<>((java.util.List<io.intino.tara.magritte.Concept>) values);")),
				rule().condition((allTypes("reference", "owner", "concept", "variable")), not(anyTypes("reactive", "inherited", "overriden")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (io.intino.tara.magritte.Concept) values.get(0);")),
				rule().condition((allTypes("reference", "owner", "variable", "multiple")), not(anyTypes("reactive", "inherited", "concept", "overriden")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "FirstLowerCase")).output(literal(" = ((java.util.List<java.lang.Object>) values).stream().\n\tmap(s -> graph().core$().load(((io.intino.tara.magritte.Layer) s).core$().id()).as(")).output(mark("type", "reference")).output(literal(".class)).collect(java.util.stream.Collectors.toList());")),
				rule().condition((allTypes("reference", "owner", "variable")), not(anyTypes("reactive", "inherited", "concept", "overriden")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = values.get(0)!= null ? core$().graph().load(((io.intino.tara.magritte.Layer) values.get(0)).core$().id()).as(")).output(mark("type", "reference")).output(literal(".class) : null;"))
		);
	}
}