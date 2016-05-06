package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class GraphWrapperTemplate extends Template {

	protected GraphWrapperTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new GraphWrapperTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(tara.templates.layer.NewElementTemplate.create().rules());
		add(tara.templates.layer.GraphWrapperTemplate.create().rules());
		add(tara.templates.layer.HelpersTemplate.create().rules());
		return this;
	}


}