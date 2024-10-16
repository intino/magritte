package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.Language;
import io.intino.tara.model.Level;
import io.intino.tara.model.Primitive;
import io.intino.tara.model.PropertyDescription;

import java.io.File;
import java.util.Arrays;

import static io.intino.tara.model.Primitive.FUNCTION;

class NativeParameterAdapter extends Generator implements Adapter<PropertyDescription>, TemplateTags {
	private final Level level;
	private final String aPackage;
	private final File importsFile;

	NativeParameterAdapter(Language language, String outDSL, Level level, String workingPackage, String languageWorkingPackage, String aPackage, File importsFile) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.level = level;
		this.aPackage = aPackage;
		this.importsFile = importsFile;
	}

	@Override
	public void adapt(PropertyDescription parameter, FrameBuilderContext context) {
		Arrays.stream(TypesProvider.getTypes(parameter)).forEach(context::add);
		createFrame(context, parameter);
	}

	private void createFrame(FrameBuilderContext context, final PropertyDescription parameter) {
		createNativeFrame(context, parameter);
	}

	private void createNativeFrame(FrameBuilderContext context, PropertyDescription parameter) {
		if (!(parameter.values().get(0) instanceof Primitive.Expression body)) return;
		String value = body.get();
		final NativeFormatter formatter = new NativeFormatter(language, outDsl, aPackage, workingPackage, languageWorkingPackage, level == Level.M1, importsFile);
		if (FUNCTION.equals(parameter.type())) formatter.fillFrameForFunctionParameter(parameter, value, context);
		else formatter.fillFrameFunctionParameter(context, parameter, value);
	}
}