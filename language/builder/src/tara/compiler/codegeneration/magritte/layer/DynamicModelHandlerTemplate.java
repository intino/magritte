package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class DynamicModelHandlerTemplate extends Template {

	protected DynamicModelHandlerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new DynamicModelHandlerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(tara.templates.dynamicLayer.NewElementTemplate.create().rules());
		add(tara.templates.layer.ModelWrapperTemplate.create().rules());
		add(tara.templates.layer.HelpersTemplate.create().rules());
		return this;
	}


}
