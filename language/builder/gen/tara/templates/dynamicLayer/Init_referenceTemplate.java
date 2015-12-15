package tara.templates.dynamicLayer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Init_referenceTemplate extends Template {

	protected Init_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Init_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & multiple & reference")), not(condition("type", "inherited")), (condition("type", "concept")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<java.lang.Object>) objects).stream().map(o -> _model().conceptOf(o.toString())).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable & reference")), not(condition("type", "inherited")), (condition("type", "concept")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" =\n((java.util.List<java.lang.Object>) objects).stream().map(o -> _instance.model().conceptOf(o.toString())).collect(java.util.stream.Collectors.toList()).get(0);")),
			rule().add((condition("type", "variable & multiple & reference")), not(condition("type", "inherited")), not(condition("type", "concept")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<Object>) objects).stream().map(o -> new tara.magritte.Reference(o.toString(), _model())).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), not(condition("type", "inherited")), not(condition("type", "concept")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "reference")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new tara.magritte.Reference((String) objects.get(0), _model());"))
		);
		return this;
	}
}