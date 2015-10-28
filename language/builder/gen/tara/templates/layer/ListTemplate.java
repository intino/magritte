package tara.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ListTemplate extends Template {

	protected ListTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ListTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "multiple")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");")),
			rule().add((condition("type", "Variable")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase")).add(literal(");")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("type", "single")), (condition("trigger", "list"))).add(literal("if(")).add(mark("name", "firstLowerCase")).add(literal(" != null) declarations.add(")).add(mark("name", "firstLowerCase")).add(literal("._declaration());")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "list"))).add(mark("name", "firstLowerCase")).add(literal("List.stream().forEach(c -> declarations.add(c._declaration()));"))
		);
		return this;
	}
}