package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Set_referenceTemplate extends Template {

	protected Set_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Set_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "FirstLowerCase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>((java.util.List<io.intino.tara.magritte.Concept>) values);")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "FirstLowerCase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).add(literal(" = (io.intino.tara.magritte.Concept) values.get(0);")),
			rule().add((condition("type", "variable & reference & multiple & owner")), not(condition("type", "concept | inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "FirstLowerCase")).add(literal("\")) this.")).add(mark("name", "FirstLowerCase")).add(literal(" = ((java.util.List<java.lang.Object>) values).stream().\n\tmap(s -> graph().core$().load(((io.intino.tara.magritte.Layer) s).core$().id()).as(")).add(mark("type", "reference")).add(literal(".class)).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable & reference & owner")), not(condition("type", "concept | inherited | reactive | overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "FirstLowerCase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "FirstLowerCase", "javaValidWord")).add(literal(" = values.get(0)!= null ? core$().graph().load(((io.intino.tara.magritte.Layer) values.get(0)).core$().id()).as(")).add(mark("type", "reference")).add(literal(".class) : null;"))
		);
		return this;
	}
}