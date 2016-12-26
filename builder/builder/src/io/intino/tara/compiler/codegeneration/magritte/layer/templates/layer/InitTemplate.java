package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class InitTemplate extends Template {

	protected InitTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new InitTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "facetTarget")), (condition("type", "overriden")), (condition("trigger", "init"))).add(literal("_")).add(mark("name", "firstLowerCase")).add(literal(".node().load(_")).add(mark("name", "firstLowerCase")).add(literal(", name, values);")),
			rule().add((condition("type", "variable & word & multiple & outDefined & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = WordLoader.load(values, ")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(".class, this);")),
			rule().add((condition("type", "variable & word & outDefined & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = WordLoader.load(values, ")).add(mark("workingPackage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(".class, this).get(0);")),
			rule().add((condition("type", "variable & word & multiple & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = WordLoader.load(values, ")).add(mark("type")).add(literal(".class, this);")),
			rule().add((condition("type", "variable & word & owner")), not(condition("type", "inherited | overriden | reactive")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = WordLoader.load(values, ")).add(mark("type")).add(literal(".class, this).get(0);")),
			rule().add((condition("type", "variable & reactive & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = FunctionLoader.load(values, this, Expression.class).get(0);")),
			rule().add((condition("type", "variable & objectVariable & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = ObjectLoader.load(values,")).add(mark("type", "withoutGeneric")).add(literal(".class, this).get(0);")),
			rule().add((condition("type", "variable & function & owner")), not(condition("type", "inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = FunctionLoader.load(values, this, ")).add(mark("workingPackage")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(".class).get(0);")),
			rule().add((condition("type", "variable & owner & multiple")), (condition("type", "time | date")), not(condition("type", "concept | inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "lowercase", "capitalize")).add(literal("Loader.load(values, this);")),
			rule().add((condition("type", "variable & owner")), (condition("type", "time | date")), not(condition("type", "concept | multiple | inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "lowercase", "capitalize")).add(literal("Loader.load(values, this).get(0);")),
			rule().add((condition("type", "variable & resource & owner")), not(condition("type", "multiple | concept | inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = ResourceLoader.load(values, this).get(0);")),
			rule().add((condition("type", "variable & resource & owner & multiple")), not(condition("type", "inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = ResourceLoader.load(values, this);")),
			rule().add((condition("type", "variable & owner & multiple")), not(condition("type", "inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "lowercase", "capitalize")).add(literal("Loader.load(values, this);")),
			rule().add((condition("type", "variable & owner")), not(condition("type", "multiple | concept | inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "javaValidName", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "lowercase", "capitalize")).add(literal("Loader.load(values, this).get(0);"))
		);
		return this;
	}
}