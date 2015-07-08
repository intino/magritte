package itrules;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class StashTemplate extends Template {

	protected StashTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new StashTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("node", "Stash"))).add(literal("dsl ")).add(mark("dsl")).add(literal("\n\n")).add(mark("root")),
			rule().add((condition("node", "Case"))).add(mark("ontology")).add(expression().add(literal(" ")).add(mark("_name"))).add(expression().add(literal(" > ")).add(mark("variables").multiple(";"))).add(literal("\n    ")).add(mark("_components").multiple("\n")).add(literal("\\")),
			rule().add((condition("node", "Type"))).add(mark("_name")),
			rule().add((condition("node", "Variable"))).add(mark("_name")).add(literal(" = ")).add(mark("value", "format").multiple(" ")),
			rule().add((condition("node", "String")), (condition("trigger", "format"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("node", "Word")), (condition("trigger", "format"))).add(mark("label")),
			rule().add((condition("node", "Date")), (condition("trigger", "format"))).add(literal("\"")).add(mark("value", "Instant")).add(literal("\"")),
			rule().add((condition("node", "Resource")), (condition("trigger", "format"))).add(literal("\"")).add(mark("id")).add(literal("\"")),
			rule().add((condition("node", "Reference")), (condition("trigger", "format"))).add(literal("\"")).add(mark("_name")).add(literal("@")).add(mark("node")).add(literal("\""))
		);
		return this;
	}
}