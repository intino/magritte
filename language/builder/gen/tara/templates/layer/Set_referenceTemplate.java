package tara.templates.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class Set_referenceTemplate extends Template {

	protected Set_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Set_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>((java.util.List<tara.magritte.Concept>) objects);")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = (tara.magritte.Concept) objects.get(0);")),
			rule().add((condition("type", "variable & reference & multiple & owner")), not(condition("type", "concept | inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<Object>) objects).stream().\n\t map(o -> model().loadInstance(((tara.magritte.Layer) objects).instance().id()).as(")).add(mark("type", "reference")).add(literal(".class)).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable & reference & owner")), not(condition("type", "concept | inherited | reactive | overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = model().loadInstance(((tara.magritte.Layer) objects.get(0)).instance().id()).as(")).add(mark("type", "reference")).add(literal(".class);"))
		);
		return this;
	}
}