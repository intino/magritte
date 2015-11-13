package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class Set_referenceTemplate extends Template {

	protected Set_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Set_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "variable & reference & definition & multiple & owner")), not(condition("type", "inherited & overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = new java.util.ArrayList<>((java.util.List<tara.magritte.Definition>) object);")),
			rule().add((condition("type", "variable & reference & definition & owner")), not(condition("type", "inherited & overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = (tara.magritte.Definition) object;")),
			rule().add((condition("type", "variable & reference & multiple & owner")), not(condition("type", "definition & inherited & overriden")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = ((java.util.List<Object>) object).stream().\n\t map(o -> _declaration.ownerWith(tara.magritte.Model.class).loadDeclaration(((tara.magritte.Layer) object)._declaration().name()).as(")).add(mark("type", "reference")).add(literal(".class)).collect(java.util.stream.Collectors.toList());")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), not(condition("type", "definition")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "set"))).add(literal("if (name.equalsIgnoreCase(\"")).add(mark("name", "firstLowercase")).add(literal("\")) this.")).add(mark("name", "firstLowercase")).add(literal(" = _declaration.ownerWith(tara.magritte.Model.class).loadDeclaration(((tara.magritte.Layer) object)._declaration().name()).as(")).add(mark("type", "reference")).add(literal(".class);"))
		);
		return this;
	}
}