package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.Language;
import io.intino.tara.language.model.Primitive;
import io.intino.tara.language.model.Variable;

import java.io.File;

import static io.intino.tara.builder.core.CompilerConfiguration.Level.MetaMetaModel;


class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {
	private final String subPackage;
	private final File importsFile;

	NativeVariableAdapter(Language language, String outDSL, String workingPackage, String languageWorkingPackage, String subPackage, File importsFile) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.subPackage = subPackage;
		this.importsFile = importsFile;
	}

	@Override
	public void adapt(Variable variable, FrameBuilderContext context) {
		TypesProvider.getTypes(variable, MetaMetaModel).forEach(context::add);
		createFrame(variable, context);
	}

	private void createFrame(final Variable variable, FrameBuilderContext context) {
		if (!(variable.values().get(0) instanceof Primitive.Expression body)) return;
		String value = body.get();
		NativeFormatter formatter = new NativeFormatter(language, outDsl, subPackage, workingPackage, languageWorkingPackage, false, importsFile);
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForFunctionVariable(variable, value, context);
		else formatter.fillFrameNativeVariable(context, variable, value);
	}

}