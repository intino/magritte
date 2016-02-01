package tara.templates.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class InitTemplate extends Template {

	protected InitTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new InitTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & word")), (condition("type", "multiple")), (condition("type", "outDefined")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.WordLoader.load(objects, ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(".class);")),
			rule().add((condition("type", "variable & word")), (condition("type", "outDefined")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.WordLoader.load(objects, ")).add(mark("generatedLanguage", "LowerCase")).add(literal(".rules.")).add(mark("rule", "externalWordClass")).add(literal(".class).get(0);")),
			rule().add((condition("type", "variable & word")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.WordLoader.load(objects, ")).add(mark("type")).add(literal(".class);")),
			rule().add((condition("type", "variable & word")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) ")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.WordLoader.load(objects, ")).add(mark("type")).add(literal(".class).get(0);")),
			rule().add((condition("type", "variable")), (condition("type", "native")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(objects, this, tara.magritte.Expression.class).get(0);")),
			rule().add((condition("type", "variable")), (condition("type", "function")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.FunctionLoader.load(objects, this, ")).add(mark("generatedLanguage")).add(literal(".functions.")).add(mark("rule", "interfaceClass")).add(literal(".class).get(0);")),
			rule().add((condition("type", "variable")), (condition("type", "time | Date")), (condition("type", "multiple")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "Capitalize")).add(literal("Loader.load(objects);")),
			rule().add((condition("type", "variable")), (condition("type", "time | Date")), not(condition("type", "multiple")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "Capitalize")).add(literal("Loader.load(objects).get(0);")),
			rule().add((condition("type", "variable & resource")), not(condition("type", "multiple")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.ResourceLoader.load(objects, _model()).get(0);")),
			rule().add((condition("type", "variable & resource")), (condition("type", "owner")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.ResourceLoader.load(objects, _model());")),
			rule().add((condition("type", "variable")), (condition("type", "owner")), (condition("type", "multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "variableTypeList")).add(literal("Loader.load(objects);")),
			rule().add((condition("type", "variable")), (condition("type", "owner")), not(condition("type", "multiple")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.")).add(mark("type", "variableTypeList")).add(literal("Loader.load(objects).get(0);"))
		);
		return this;
	}
}