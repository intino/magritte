package tara.templates.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class SetTemplate extends Template {

	protected SetTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new SetTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & word")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "outDefined")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(">) objects);")),
			rule().add((condition("type", "variable & word & multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("type")).add(literal(">) objects);")),
			rule().add((condition("type", "variable & word")), (condition("type", "outDefined")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = (")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(") objects.get(0);")),
			rule().add((condition("type", "variable & word")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = (")).add(mark("type")).add(literal(") objects.get(0);")),
			rule().add((condition("type", "variable & reactive")), (condition("type", "owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(objects.get(0), this, tara.magritte.Expression.class);")),
			rule().add((condition("type", "variable & function")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(objects.get(0), this, ")).add(mark("generatedLanguage")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(".class);")),
			rule().add((condition("type", "variable & time & multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = new java.util.ArrayList<>((List<java.time.LocalTime>) objects);")),
			rule().add((condition("type", "variable & multiple & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("type", "variableTypeList")).add(literal(">) objects);")),
			rule().add((condition("type", "variable")), not(condition("type", "multiple | concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidWord", "firstLowercase")).add(literal(" = (")).add(mark("type", "variableTypeList")).add(literal(") objects.get(0);"))
		);
		return this;
	}
}