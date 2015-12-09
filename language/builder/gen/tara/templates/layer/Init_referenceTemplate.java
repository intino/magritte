package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Init_referenceTemplate extends Template {

	protected Init_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Init_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference & multiple & concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.ConceptLoader.load(objects, _model());")),
			rule().add((condition("type", "variable & reference & concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.ConceptLoader.load(objects, _model()).get(0);")),
			rule().add((condition("type", "variable & reference & multiple")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.InstanceLoader.load(objects, _model(), ")).add(mark("type", "reference")).add(literal(".class);")),
			rule().add((condition("type", "variable & reference")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "init"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = tara.magritte.loaders.InstanceLoader.load(objects, _model(), ")).add(mark("type", "reference")).add(literal(".class).get(0);"))
		);
		return this;
	}
}