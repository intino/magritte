package tara.templates;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class BoxDSLTemplate extends Template {

	protected BoxDSLTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new BoxDSLTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Box"))).add(literal("package magritte.dsl;\n\npublic class ")).add(mark("name", "firstUppercase")).add(literal("Dsl extends magritte.schema.Box.Dsl {\n    public static final magritte.schema.Box box = new ")).add(mark("name", "firstUppercase")).add(literal("Dsl();\n\n    protected magritte.schema.Box[] components() {\n        return new magritte.schema.Box[]{\n            ")).add(mark("namebox", "reference").multiple(",\n")).add(literal("\n        };\n    }\n}"))
		);
		return this;
	}
}