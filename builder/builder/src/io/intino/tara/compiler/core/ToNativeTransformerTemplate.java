package io.intino.tara.compiler.core;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ToNativeTransformerTemplate extends Template {

	protected ToNativeTransformerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ToNativeTransformerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "native")), (condition("type", "resouce"))).add(literal("try {\n\treturn new java.net.URL(")).add(mark("value", "url", "quoted")).add(literal(");\n} catch (java.net.MalformedURLException e) {\n\treturn null;\n};\n")),
			rule().add((condition("type", "native")), (condition("type", "date"))).add(literal("DateLoader.load(java.util.Collections.singletonList(\"")).add(mark("value")).add(literal("\"), self).get(0)")),
			rule().add((condition("type", "native")), (condition("type", "instant"))).add(literal("InstantLoader.load(java.util.Collections.singletonList(\"")).add(mark("value")).add(literal("\"), self).get(0)")),
			rule().add((condition("type", "native")), (condition("type", "emptyNode")), (condition("type", "reference"))).add(literal("null")),
			rule().add((condition("type", "native")), (condition("type", "reference"))).add(literal("self.graph().loadInstance(\"")).add(mark("value")).add(literal("\");")),
			rule().add((condition("type", "native")), (condition("type", "resource"))).add(literal("self.graph().loadResource(\"")).add(mark("value", "url")).add(literal("\");")),
			rule().add((condition("type", "native")), (condition("type", "string"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "native")), not(condition("type", "string"))).add(mark("value"))
		);
		return this;
	}
}