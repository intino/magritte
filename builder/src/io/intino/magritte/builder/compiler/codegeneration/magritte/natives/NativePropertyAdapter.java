package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.Language;
import io.intino.tara.model.Primitive;
import io.intino.tara.model.Property;

import java.io.File;

import static io.intino.tara.model.Primitive.FUNCTION;

class NativePropertyAdapter extends Generator implements Adapter<Property>, TemplateTags {
	private final String subPackage;
	private final File importsFile;

	NativePropertyAdapter(Language language, String outDSL, String workingPackage, String languageWorkingPackage, String subPackage, File importsFile) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.subPackage = subPackage;
		this.importsFile = importsFile;
	}

	@Override
	public void adapt(Property prop, FrameBuilderContext context) {
		TypesProvider.getTypes(prop).forEach(context::add);
		createFrame(prop, context);
	}

	private void createFrame(final Property prop, FrameBuilderContext context) {
//		if (!()) return;
		String value = prop.values().get(0) instanceof Primitive.Expression body ? body.get() : prop.values().get(0).toString();
		NativeFormatter formatter = new NativeFormatter(language, outDsl, subPackage, workingPackage, languageWorkingPackage, false, importsFile);
		if (FUNCTION.equals(prop.type())) formatter.fillFrameForFunctionProperty(prop, value, context);
		else formatter.fillFrameFunctionProperty(context, prop, value);
	}

}