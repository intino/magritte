package tara.compiler.codegeneration.magritte.layer.templates.lazy;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class Setters_referenceTemplate extends Template {

	protected Setters_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Setters_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference")), not(condition("type", "final")), not(condition("type", "target")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "firtLowercase", "javaValidWord")).add(literal(" = new tara.magritte.Reference(value.node());\n}"))
		);
		return this;
	}
}