package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.rules.variable.NativeRule;

public class NativeParameterAdapter extends Generator implements Adapter<Parameter>, TemplateTags {

	private final String generatedLanguage;
	private final Language language;
	private final String aPackage;

	public NativeParameterAdapter(String generatedLanguage, Language language, String aPackage) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
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
		if (Primitive.NATIVE.equals(parameter.inferredType())) fillFrameForNativeParameter(frame, parameter, value);
		else NativeFormatter.fillFrameExpressionParameter(frame, parameter, value, language, generatedLanguage);
	}

	private void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		final String signature = NativeFormatter.getSignature(parameter);
		final String nativeContainer = NameFormatter.cleanQn(NativeFormatter.buildContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage));
		NativeExtractor extractor = new NativeExtractor(nativeContainer, parameter.name(), signature);
		frame.addFrame(GENERATED_LANGUAGE, this.generatedLanguage.toLowerCase());
		frame.addFrame(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) frame.addFrame(PACKAGE, this.aPackage.toLowerCase());
		frame.addFrame(QN, parameter.container().qualifiedName());
		frame.addFrame(LANGUAGE, NativeFormatter.getLanguageScope(parameter, language));
		frame.addFrame(RULE, NameFormatter.cleanQn(NativeFormatter.getInterface(parameter)));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame("file", parameter.file());
		frame.addFrame("line", parameter.line());
		frame.addFrame("column", parameter.column());
		frame.addFrame("nativeContainer", nativeContainer);
		frame.addFrame("uid", parameter.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("body", NativeFormatter.formatBody(body, signature));
		frame.addFrame("returnType", extractor.returnValue());
	}

}