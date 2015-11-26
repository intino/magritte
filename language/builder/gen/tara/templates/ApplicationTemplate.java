package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ApplicationTemplate extends Template {

	protected ApplicationTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ApplicationTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "launcher"))).add(literal("import tara.magritte.Model;\n\npublic class Application {\n\n\tpublic static void main(String[] args){\n\t\tModel model = Model.load().init(")).add(mark("language", "lowerCase")).add(literal(".")).add(mark("language", "firstUpperCase")).add(literal("Domain.class, ")).add(mark("metaLanguage", "lowerCase")).add(literal(".")).add(mark("metaLanguage", "firstUpperCase")).add(literal("Engine.class);\n\t\tmodel.engine().init();\n\t\tmodel.engine().execute();\n\t}\n}"))
		);
		return this;
	}
}