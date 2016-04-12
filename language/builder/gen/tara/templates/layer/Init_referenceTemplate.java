package tara.templates.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class Init_referenceTemplate extends Template {

	protected Init_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Init_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference & multiple & concept")), not(condition("type", "inherited | reactive")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.ConceptLoader.load(values, this);")),
			rule().add((condition("type", "variable & reference & concept & owner")), not(condition("type", "inherited | reactive | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.ConceptLoader.load(values, this).get(0);")),
			rule().add((condition("type", "variable & reference & multiple & owner")), not(condition("type", "concept | reactive | inherited | overriden")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.InstanceLoader.load(values,  ")).add(mark("type", "reference")).add(literal(".class, this);")),
			rule().add((condition("type", "variable & reference")), not(condition("type", "concept | reactive")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = tara.magritte.loaders.InstanceLoader.load(values, ")).add(mark("type", "reference")).add(literal(".class, this).get(0);"))
		);
		return this;
	}
}