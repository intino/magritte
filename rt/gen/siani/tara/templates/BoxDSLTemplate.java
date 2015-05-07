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
			rule().add(condition("type", "Box")).add(literal("package magritte.store;\n\nimport magritte.handlers.Box;\n\npublic class ")).add(mark("name", "firstUppercase")).add(literal(" extends Box.Dsl {\npublic static final Box box = new ")).add(mark("name", "firstUppercase")).add(literal("();\n\nprotected Box[] includes() {\n    return new Box[]{ ")).add(mark("namebox").multiple(",\n")).add(literal("\n    };\n}\n}"))
		);
		return this;
	}
}