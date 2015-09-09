package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.morph.TypesProvider;
import tara.language.model.Primitives;
import tara.language.model.Variable;

public class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final String generatedLanguage;
	private final Language language;

	public NativeVariableAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> frameContext) {
		frame.addTypes(TypesProvider.getTypes(source, 2));
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Variable variable) {
		createNativeFrame(frame, variable);
	}

	private void createNativeFrame(Frame frame, Variable variable) {
		if (!(variable.defaultValues().get(0) instanceof Primitives.Expression)) return;
		final Primitives.Expression body = (Primitives.Expression) variable.defaultValues().get(0);
		String value = body.get();
		if (Primitives.NATIVE.equals(variable.type())) {
			fillFrameForNativeVariable(frame, variable, value);
		} else fillFrameExpressionVariable(frame, variable, value);

	}

	private void fillFrameForNativeVariable(Frame frame, Variable variable, String body) {
		final String signature = NativeFormatter.getSignature(variable);
		final String nativeContainer = NameFormatter.cleanQn(NativeFormatter.buildContainerPath(variable.contract(), variable.container(), language, generatedLanguage));
		NativeExtractor extractor = new NativeExtractor(nativeContainer, variable.name(), signature);
		frame.addFrame(NAME, variable.name());
		frame.addFrame("body", NativeFormatter.formatBody(body, signature));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		frame.addFrame("language", generatedLanguage.toLowerCase());
		frame.addFrame("contract", NameFormatter.cleanQn(NativeFormatter.getInterface(variable)));
		frame.addFrame("signature", signature);
		frame.addFrame("uid", variable.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}


	public void fillFrameExpressionVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String type = NativeFormatter.mask(variable.type());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", NativeFormatter.formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.name()).
			addFrame(CONTAINER, NativeFormatter.buildContainerPathOfExpression(variable.container(), generatedLanguage, false)).
			addFrame(INTERFACE, "magritte.Expression<" + type + ">").
			addFrame(SIGNATURE, signature).
			addFrame(CLASS_NAME, variable.name() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

}