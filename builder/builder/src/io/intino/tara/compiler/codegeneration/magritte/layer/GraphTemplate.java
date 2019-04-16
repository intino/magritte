package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.LineSeparator;
import io.intino.itrules.Template;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.AbstractGraphTemplate;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.HelpersTemplate;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.NewElementTemplate;

import java.util.Locale;

import static io.intino.itrules.LineSeparator.LF;

public class GraphTemplate extends Template {

	protected GraphTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new GraphTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(NewElementTemplate.create().rules());
		add(AbstractGraphTemplate.create().rules());
		add(HelpersTemplate.create().rules());
		return this;
	}
}
