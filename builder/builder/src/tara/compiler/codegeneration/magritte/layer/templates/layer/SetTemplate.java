package tara.compiler.codegeneration.magritte.layer.templates.layer;

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
			rule().add((condition("type", "facetTarget & overriden")), (condition("trigger", "set"))).add(literal("_")).add(mark("name", "firstLowerCase")).add(literal(".node().set(_")).add(mark("name", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("type", "variable & word & outDefined & owner & multiple")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(">) values);")),
			rule().add((condition("type", "variable & word & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("type")).add(literal(">) values);")),
			rule().add((condition("type", "variable & word")), (condition("type", "outDefined")), not(condition("type", "inherited | overriden | reactive")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = (")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(") values.get(0);")),
			rule().add((condition("type", "variable & word & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = (")).add(mark("type")).add(literal(") values.get(0);")),
			rule().add((condition("type", "variable & reactive & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, tara.magritte.Expression.class);")),
			rule().add((condition("type", "variable & function & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, ")).add(mark("workingPackage")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(".class);")),
			rule().add((condition("type", "variable & time & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>((List<java.time.LocalTime>) values);")),
			rule().add((condition("type", "variable & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new ArrayList<>((java.util.List<")).add(mark("type", "fullType")).add(literal(">) values);")),
			rule().add((condition("type", "variable & owner")), not(condition("type", "multiple | concept | inherited | overriden | reactive")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = (")).add(mark("type", "fullType")).add(literal(") values.get(0);"))
		);
		return this;
	}
}