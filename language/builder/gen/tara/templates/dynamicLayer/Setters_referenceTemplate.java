package tara.templates.dynamicLayer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Setters_referenceTemplate extends Template {

	protected Setters_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Setters_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference")), not(condition("type", "final")), not(condition("type", "target")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "javaValidWord", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "javaValidWord", "firtLowercase")).add(literal(" = new tara.magritte.Reference(value._instance());\n}"))
		);
		return this;
	}
}