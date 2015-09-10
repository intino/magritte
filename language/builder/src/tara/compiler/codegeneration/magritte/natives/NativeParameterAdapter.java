package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.morph.TypesProvider;
import tara.language.model.Parameter;
import tara.language.model.Primitives;

public class NativeParameterAdapter extends Generator implements Adapter<Parameter>, TemplateTags {

	private final String generatedLanguage;
	private final Language language;

	public NativeParameterAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
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
		if (!(parameter.values().get(0) instanceof Primitives.Expression)) return;
		final Primitives.Expression body = (Primitives.Expression) parameter.values().get(0);
		String value = body.get();
		if (Primitives.NATIVE.equals(parameter.inferredType())) {
			fillFrameForNativeParameter(frame, parameter, value);
		} else NativeFormatter.fillFrameExpressionParameter(frame, parameter, value, language, generatedLanguage);

	}

	private void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		final String signature = NativeFormatter.getSignature(parameter);
		final String nativeContainer = NameFormatter.cleanQn(NativeFormatter.buildContainerPath(parameter.contract(), parameter.container(), language, generatedLanguage));
		NativeExtractor extractor = new NativeExtractor(nativeContainer, parameter.name(), signature);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame("qn", parameter.container().qualifiedName());
		frame.addFrame("file", parameter.file());
		frame.addFrame("line", parameter.line());
		frame.addFrame("column", parameter.column());
		frame.addFrame("body", NativeFormatter.formatBody(body, signature));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		frame.addFrame("language", NativeFormatter.getScope(parameter, language));
		frame.addFrame("contract", NameFormatter.cleanQn(NativeFormatter.getInterface(parameter)));
		frame.addFrame("signature", signature);
		frame.addFrame("uid", parameter.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}

}