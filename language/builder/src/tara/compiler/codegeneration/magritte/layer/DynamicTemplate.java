package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;
import tara.compiler.codegeneration.magritte.layer.templates.layer.*;
import tara.compiler.codegeneration.magritte.layer.templates.lazy.*;
import tara.compiler.codegeneration.magritte.layer.templates.lazy.Init_referenceTemplate;
import tara.compiler.codegeneration.magritte.layer.templates.lazy.NewElementTemplate;
import tara.compiler.codegeneration.magritte.layer.templates.lazy.Set_referenceTemplate;

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
		add(LayerTemplate.create().rules());
		add(DeclarationTemplate.create().rules());
		add(Declaration_referenceTemplate.create().rules());
		add(ConstructorTemplate.create().rules());
		add(GettersTemplate.create().rules());
		add(Getters_referenceTemplate.create().rules());
		add(SettersTemplate.create().rules());
		add(Setters_referenceTemplate.create().rules());
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
