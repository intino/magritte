package tara.magritte;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class TaraTemplate extends Template {

	protected TaraTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new TaraTemplate(Locale.ENGLISH, CRLF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Stash"))).add(literal("dsl ")).add(mark("dsl")).add(literal("\n\n")).add(mark("root")),
			rule().add((condition("type", "Case"))).add(mark("types")).add(expression().add(literal(" ")).add(mark("name"))).add(expression().add(literal(" > ")).add(mark("variables").multiple(";"))).add(literal("\n    ")).add(mark("components").multiple("\n")).add(literal("\\")),
			rule().add((condition("type", "Type"))).add(mark("name")),
			rule().add((condition("type", "Variable"))).add(mark("name")).add(literal(" = ")).add(mark("value", "format").multiple(" ")),
			rule().add((condition("type", "String")), (condition("trigger", "format"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("type", "Word")), (condition("trigger", "format"))).add(mark("label")),
			rule().add((condition("type", "Date")), (condition("trigger", "format"))).add(literal("\"")).add(mark("value", "Instant")).add(literal("\"")),
			rule().add((condition("type", "Resource")), (condition("trigger", "format"))).add(literal("\"")).add(mark("id")).add(literal("\"")),
			rule().add((condition("type", "Reference")), (condition("trigger", "format"))).add(literal("\"")).add(mark("name")).add(literal("@")).add(mark("type")).add(literal("\""))
		);
		return this;
	}
}