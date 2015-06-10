package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class BoxDSLTemplate extends Template {

	protected BoxDSLTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new BoxDSLTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Box"))).add(literal("package magritte.dsl;\n\npublic class ")).add(mark("name", "firstUppercase")).add(literal(" extends magritte.editors.Box.Metamodel {\n    public static final magritte.editors.Box box = new ")).add(mark("name", "firstUppercase")).add(literal("();\n\n    protected magritte.editors.Box[] includes() {\n        return new magritte.editors.Box[]{\n            ")).add(mark("namebox").multiple(",\n")).add(literal("\n        };\n    }\n}"))
		);
		return this;
	}
}