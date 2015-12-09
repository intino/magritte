package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ConstructorTemplate extends Template {

	protected ConstructorTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ConstructorTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "wordValues")), (condition("type", "word")), (condition("type", "multiple")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("wordValues", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("type", "word")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("wordValues", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "native")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Collections.singletonList(")).add(mark("generatedLanguage")).add(literal(".natives.")).add(mark("package")).add(literal(".")).add(mark("name", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(".class.getName())));")),
			rule().add((condition("type", "Variable")), (condition("type", "function")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("slot", "body")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Collections.singletonList(")).add(mark("generatedLanguage")).add(literal(".natives.")).add(mark("package")).add(literal(".")).add(mark("name", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(".class.getName())));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "date")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "time")), not(condition("type", "inherited")), (condition("Slot", "values")), not(condition("type", "multiple")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\",  new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "double")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("type", "multiple")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(new Double[] {")).add(mark("values").multiple(", ")).add(literal("})));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "reference")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("type", "multiple")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(_model().conceptOf(\"")).add(mark("type")).add(literal("\"))));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), (condition("type", "measure")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values", "quoted").multiple(", ")).add(literal(")));")),
			rule().add((condition("type", "Variable")), (condition("type", "owner")), not(condition("type", "inherited")), (condition("Slot", "values")), (condition("trigger", "constructor"))).add(literal("_load(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList<>(java.util.Arrays.asList(")).add(mark("values").multiple(", ")).add(literal(")));"))
		);
		return this;
	}
}