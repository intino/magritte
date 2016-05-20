package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;
import tara.compiler.codegeneration.magritte.layer.templates.layer.HelpersTemplate;
import tara.compiler.codegeneration.magritte.layer.templates.lazy.NewElementTemplate;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;


public class DynamicGraphWrapperTemplate extends Template {

	protected DynamicGraphWrapperTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new DynamicGraphWrapperTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(NewElementTemplate.create().rules());
		add(GraphWrapperTemplate.create().rules());
		add(HelpersTemplate.create().rules());
		return this;
	}


}
