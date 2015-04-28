package siani.tara.templates;

import org.siani.itrules.*;
import java.util.Locale;

import static org.siani.itrules.Encoding.LineSeparator.*;

public class MorphTemplate extends Template {

public MorphTemplate(Locale locale, Encoding encoding) {
		super(locale, encoding);
	}

	public static String format(Object object) {
		return template().render(object);
	}

	public static Template template() {
		return new MorphTemplate(Locale.ENGLISH, Encoding.with("UTF-8", LF));
	}

    @Override
    public void definition() {
        add(
        rule().add(condition("type", "Box")).add(literal("package magritte.store;\n\nimport magritte.editors.Box;\n\npublic class ")).add(mark("name", "firstUppercase")).add(literal(" extends Box.Dsl {\npublic static final Box box = new ")).add(mark("name", "firstUppercase")).add(literal("();\n\nprotected Box[] includes() {\n    return new Box[]{ ")).add(mark("namebox").multiple(",\n")).add(literal("\n    };\n}\n}"))
        );
    }
}