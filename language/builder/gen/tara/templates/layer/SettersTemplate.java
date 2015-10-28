package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class SettersTemplate extends Template {

	protected SettersTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new SettersTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), (condition("type", "Word")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name")).add(literal("(")).add(mark("qn", "reference")).add(literal(".")).add(mark("type", "firstUpperCase")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), not(condition("type", "Word")), (condition("type", "native")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives.")).add(mark("rule", "interfaceClass")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "definition")), not(condition("type", "multiple")), not(condition("type", "owner")), not(condition("type", "readOnly")), not(condition("type", "Word")), not(condition("type", "native")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis._")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase")).add(literal("(value);\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), (condition("type", "outDefined")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Variable")), (condition("type", "Word")), not(condition("type", "target")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(expression().add(mark("qn", "reference")).add(literal("."))).add(mark("type", "firstUpperCase")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "variable")), (condition("type", "native")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("generatedLanguage", "LowerCase")).add(literal(".natives.")).add(mark("rule", "interfaceClass")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "definition")), not(condition("type", "overriden")), not(condition("type", "multiple")), not(condition("type", "readOnly")), (condition("trigger", "setter"))).add(literal("public void ")).add(mark("name", "firstLowerCase")).add(literal("(")).add(mark("type", "variableType")).add(literal(" value) {\n\tthis.")).add(mark("name", "firstLowerCase")).add(literal(" = value;\n}"))
		);
		return this;
	}
}