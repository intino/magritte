package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;
import tara.templates.DynamicLayerTemplate;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class DynamicTemplate extends Template {

	protected DynamicTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new DynamicTemplate(Locale.ENGLISH, LF).define();
	}

	private Template define() {
		add(DynamicLayerTemplate.create().rules());
		return this;
	}


}
