package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;

public class NativeParameterAdapter extends Generator implements Adapter<Parameter>, TemplateTags {

	private final String aPackage;

	public NativeParameterAdapter(String generatedLanguage, Language language, String aPackage) {
		super(language, generatedLanguage);
		this.aPackage = aPackage;
	}

	@Override
	public void execute(Frame frame, Parameter source, FrameContext<Parameter> frameContext) {
		frame.addTypes(TypesProvider.getTypes(source));
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Parameter parameter) {
		createNativeFrame(frame, parameter);
	}

	private void createNativeFrame(Frame frame, Parameter parameter) {
		if (!(parameter.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) parameter.values().get(0);
		String value = body.get();
		final NativeFormatter formatter = new NativeFormatter(generatedLanguage, language, aPackage, false);
		if (Primitive.FUNCTION.equals(parameter.type())) formatter.fillFrameForNativeParameter(frame, parameter, value);
		else formatter.fillFrameExpressionParameter(frame, parameter, value);
	}

}