package io.intino.tara.compiler.codegeneration.magritte.natives;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilderContext;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Variable;

import java.io.File;

import static io.intino.Configuration.Artifact.Model.Level.Platform;


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
		TypesProvider.getTypes(variable, Platform).forEach(context::add);
		createFrame(variable, context);
	}

	private void createFrame(final Variable variable, FrameBuilderContext context) {
		if (!(variable.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) variable.values().get(0);
		String value = body.get();
		NativeFormatter formatter = new NativeFormatter(language, outDsl, subPackage, workingPackage, languageWorkingPackage, false, importsFile);
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForFunctionVariable(variable, value, context);
		else formatter.fillFrameNativeVariable(context, variable, value);
	}

}