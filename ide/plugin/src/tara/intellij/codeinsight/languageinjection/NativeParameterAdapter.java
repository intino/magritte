package tara.intellij.codeinsight.languageinjection;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Primitives;

public class NativeParameterAdapter implements Adapter<Parameter> {

	private final String generatedLanguage;
	private final Language language;

	public NativeParameterAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	@Override
	public void execute(Frame frame, Parameter source, FrameContext<Parameter> frameContext) {
		frame.addTypes("native");
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Parameter parameter) {
		createNativeFrame(frame, parameter);
	}

	private void createNativeFrame(Frame frame, Parameter parameter) {
		if (!(parameter.values().get(0) instanceof Primitives.Expression)) return;
		final Expression expression = ((Valued) parameter).getValue().getExpressionList().get(0);
		String value = expression.getValue();
		if (Primitives.NATIVE.equals(parameter.inferredType())) {
			fillFrameForNativeParameter(frame, parameter, value);
		} else NativeFormatter.fillFrameExpressionParameter(frame, parameter, value, language, generatedLanguage);
	}

	private void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		final String signature = NativeFormatter.getSignature(parameter);
		final String nativeContainer = cleanQn(NativeFormatter.buildNativeContainerPath(parameter.contract(), parameter.container(), language, generatedLanguage));
		frame.addFrame("name", parameter.name());
		frame.addFrame("signature", signature);
		frame.addFrame("generatedLanguage", generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		if (!(language instanceof Proteo)) frame.addFrame("language", NativeFormatter.getScope(parameter, language));
		frame.addFrame("contract", cleanQn(NativeFormatter.getInterface(parameter)));
		frame.addFrame("return", NativeFormatter.getReturn(body, signature));
	}

	private static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}