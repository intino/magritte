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

import static tara.compiler.core.CompilerConfiguration.ModuleType.Platform;

class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final String aPackage;
	private final File importsFile;

	NativeVariableAdapter(Language language, String generatedLanguage, String aPackage, File importsFile) {
		super(language, generatedLanguage);
		this.aPackage = aPackage;
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
		NativeFormatter formatter = new NativeFormatter(outDsl, language, aPackage, false, importsFile);
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForFunctionVariable(frame, variable, value);
		else formatter.fillFrameNativeVariable(frame, variable, value);

	}
}