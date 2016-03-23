package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;

import java.io.File;

class NativeParameterAdapter extends Generator implements Adapter<Parameter>, TemplateTags {

	private final int level;
	private final String aPackage;
	private final File importsFile;

	NativeParameterAdapter(String generatedLanguage, Language language, int level, String aPackage, File importsFile) {
		super(language, generatedLanguage);
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
		final NativeFormatter formatter = new NativeFormatter(generatedLanguage, language, aPackage, level == 0, importsFile);
		if (Primitive.FUNCTION.equals(parameter.type())) formatter.fillFrameForFunctionParameter(frame, parameter, value);
		else formatter.fillFrameReactiveParameter(frame, parameter, value);
	}

}