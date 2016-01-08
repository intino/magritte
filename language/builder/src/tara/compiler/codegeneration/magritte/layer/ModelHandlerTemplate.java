package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class ModelHandlerTemplate extends Template {

	protected ModelHandlerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModelHandlerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(tara.templates.NewElementTemplate.create().rules());
		add(tara.templates.ModelHandlerTemplate.create().rules());
		add(tara.templates.layer.HelpersTemplate.create().rules());
		return this;
	}


}
