package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.io.File;

import static tara.compiler.shared.Configuration.Level.Platform;

class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final String subPackage;
	private final File importsFile;

	NativeVariableAdapter(Language language, String outDSL, String workingPackage, String languageWorkingPackage, String subPackage, File importsFile) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.subPackage = subPackage;
		this.importsFile = importsFile;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> frameContext) {
		frame.addTypes(TypesProvider.getTypes(source, Platform));
		createFrame(frame, source);
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