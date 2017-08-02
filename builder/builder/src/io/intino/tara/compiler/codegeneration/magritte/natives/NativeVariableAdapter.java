package io.intino.tara.compiler.codegeneration.magritte.natives;

import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.Context;
import org.siani.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Variable;

import java.io.File;

import static io.intino.tara.compiler.shared.Configuration.Level.Platform;

class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final String subPackage;
	private final File importsFile;

	NativeVariableAdapter(Language language, String outDSL, String workingPackage, String languageWorkingPackage, String subPackage, File importsFile) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.subPackage = subPackage;
		this.importsFile = importsFile;
	}

	@Override
	public void adapt(Variable variable, Context context) {
		Frame frame = context.frame();
		frame.addTypes(TypesProvider.getTypes(variable, Platform));
		createFrame(frame, variable);
	}

	private void createFrame(Frame frame, final Variable variable) {
		createNativeFrame(frame, variable);
	}

	private void createNativeFrame(Frame frame, Variable variable) {
		if (!(variable.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) variable.values().get(0);
		String value = body.get();
		NativeFormatter formatter = new NativeFormatter(language, outDsl, subPackage, workingPackage, languageWorkingPackage, false, importsFile);
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForFunctionVariable(frame, variable, value);
		else formatter.fillFrameNativeVariable(frame, variable, value);
	}
}