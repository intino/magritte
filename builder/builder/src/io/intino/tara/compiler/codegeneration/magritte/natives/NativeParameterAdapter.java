package io.intino.tara.compiler.codegeneration.magritte.natives;

import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.Primitive;

import java.io.File;

import static io.intino.tara.compiler.shared.Configuration.Level.System;

class NativeParameterAdapter extends Generator implements Adapter<Parameter>, TemplateTags {

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
	public void execute(Frame frame, Parameter source, FrameContext<Parameter> frameContext) {
		frame.addTypes(TypesProvider.getTypes(source));
		createFrame(frame, source);
	}

	private void createFrame(Frame frame, final Parameter parameter) {
		createNativeFrame(frame, parameter);
	}

	private void createNativeFrame(Frame frame, Parameter parameter) {
		if (!(parameter.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) parameter.values().get(0);
		String value = body.get();
		final NativeFormatter formatter = new NativeFormatter(language, outDsl, aPackage, workingPackage, languageWorkingPackage, level.compareLevelWith(System) == 0, importsFile);
		if (Primitive.FUNCTION.equals(parameter.type())) formatter.fillFrameForFunctionParameter(frame, parameter, value);
		else formatter.fillFrameNativeParameter(frame, parameter, value);
	}
}