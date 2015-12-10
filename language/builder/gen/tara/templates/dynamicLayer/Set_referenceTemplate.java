package tara.templates.dynamicLayer;

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
			rule().add((condition("type", "variable & reference & concept & multiple & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new java.util.ArrayList<>((java.util.List<tara.magritte.Concept>) objects);")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = (tara.magritte.Concept) objects;")),
			rule().add((condition("type", "variable & reference & multiple")), not(condition("type", "inherited")), not(condition("type", "concept")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<Object>)objects).stream().map(o -> new tara.magritte.Reference(o.toString(), _model())).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "concept")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new tara.magritte.Reference((String) objects, _instance.model());"))
		);
		return this;
	}
}