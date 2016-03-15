package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

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
		add(tara.templates.layer.LayerTemplate.create().rules());
		add(tara.templates.layer.DeclarationTemplate.create().rules());
		add(tara.templates.layer.ConstructorTemplate.create().rules());
		add(tara.templates.layer.GettersTemplate.create().rules());
		add(tara.templates.layer.SettersTemplate.create().rules());
		add(tara.templates.layer.NewElementTemplate.create().rules());
		add(tara.templates.layer.InitTemplate.create().rules());
		add(tara.templates.layer.Init_referenceTemplate.create().rules());
		add(tara.templates.layer.SetTemplate.create().rules());
		add(tara.templates.layer.Set_referenceTemplate.create().rules());
		add(tara.templates.layer.ListTemplate.create().rules());
		add(tara.templates.layer.HelpersTemplate.create().rules());
		return this;
	}


}
