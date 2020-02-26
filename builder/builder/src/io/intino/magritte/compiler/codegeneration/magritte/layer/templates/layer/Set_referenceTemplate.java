package io.intino.magritte.compiler.codegeneration.magritte.layer.templates.layer;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class Set_referenceTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((allTypes("variable", "reference", "concept", "multiple", "owner")), not(anyTypes("inherited", "overriden", "reactive")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = new java.util.ArrayList<>((java.util.List<io.intino.magritte.framework.Concept>) values);")),
				rule().condition((allTypes("variable", "reference", "concept", "owner")), not(anyTypes("inherited", "overriden", "reactive")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = (io.intino.magritte.framework.Concept) values.get(0);")),
				rule().condition((allTypes("variable", "reference", "multiple", "owner")), not(anyTypes("concept", "inherited", "overriden", "reactive")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "FirstLowerCase")).output(literal(" = ((java.util.List<java.lang.Object>) values).stream().\n\tmap(s -> graph().core$().load(((io.intino.magritte.framework.Layer) s).core$().id()).as(")).output(mark("type", "reference")).output(literal(".class)).collect(java.util.stream.Collectors.toList());")),
				rule().condition((allTypes("variable", "reference", "owner")), not(anyTypes("concept", "inherited", "reactive", "overriden")), (trigger("set"))).output(literal("if (name.equalsIgnoreCase(\"")).output(mark("name", "FirstLowerCase")).output(literal("\")) this.")).output(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).output(literal(" = values.get(0)!= null ? core$().graph().load(((io.intino.magritte.framework.Layer) values.get(0)).core$().id()).as(")).output(mark("type", "reference")).output(literal(".class) : null;"))
		);
	}
}