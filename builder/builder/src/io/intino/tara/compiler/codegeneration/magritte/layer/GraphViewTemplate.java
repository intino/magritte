package io.intino.tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.HelpersTemplate;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.NewElementTemplate;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class GraphViewTemplate extends Template {

	protected GraphViewTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new GraphViewTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(NewElementTemplate.create().rules());
		add(io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.GraphViewTemplate.create().rules());
		add(HelpersTemplate.create().rules());
		return this;
	}


}
