package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;
import tara.compiler.codegeneration.magritte.layer.templates.layer.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class LayerTemplate extends Template {

	protected LayerTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new LayerTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(tara.compiler.codegeneration.magritte.layer.templates.layer.LayerTemplate.create().rules());
		add(DeclarationTemplate.create().rules());
		add(ConstructorTemplate.create().rules());
		add(GettersTemplate.create().rules());
		add(SettersTemplate.create().rules());
		add(NewElementTemplate.create().rules());
		add(InitTemplate.create().rules());
		add(Init_referenceTemplate.create().rules());
		add(SetTemplate.create().rules());
		add(Set_referenceTemplate.create().rules());
		add(ListTemplate.create().rules());
		add(HelpersTemplate.create().rules());
		return this;
	}


}
