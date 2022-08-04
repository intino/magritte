package io.intino.magritte.builder.core;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class ToNativeTransformerTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("native")), (type("resouce"))).output(literal("try {\n\treturn new java.net.URL(")).output(mark("value", "url", "quoted")).output(literal(");\n} catch (java.net.MalformedURLException e) {\n\treturn null;\n};\n")),
				rule().condition((type("native")), (type("date"))).output(literal("DateLoader.load(java.util.Collections.singletonList(\"")).output(mark("value")).output(literal("\"), self).get(0)")),
				rule().condition((type("native")), (type("instant"))).output(literal("InstantLoader.load(java.util.Collections.singletonList(\"")).output(mark("value")).output(literal("\"), self).get(0)")),
				rule().condition((type("native")), (type("emptynode")), (type("reference"))).output(literal("null")),
				rule().condition((type("native")), (type("reference"))).output(literal("self.graph().core$().loadInstance(\"")).output(mark("value")).output(literal("\");")),
				rule().condition((type("native")), (type("resource"))).output(literal("self.graph().core$().loadResource(\"")).output(mark("value", "url")).output(literal("\");")),
				rule().condition((type("native")), (type("string"))).output(literal("\"")).output(mark("value")).output(literal("\"")),
				rule().condition((type("native")), not(type("string"))).output(mark("value"))
		);
	}
}