package tara.intellij.codeinsight.languageinjection;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.rules.NativeRule;

import static tara.lang.model.Primitive.NATIVE;

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
		if (!(parameter.values().get(0) instanceof Primitive.Expression)) return;
		final Expression expression = ((Valued) parameter).getValue().getExpressionList().get(0);
		String value = expression.getValue();
		if (NATIVE.equals(parameter.inferredType())) {
			fillFrameForNativeParameter(frame, parameter, value);
		} else NativeFormatter.fillFrameExpressionParameter(frame, parameter, value, language, generatedLanguage);
	}

	private void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		final String signature = NativeFormatter.getSignature(parameter);
		final String nativeContainer = cleanQn(NativeFormatter.buildContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage));
		frame.addFrame("name", parameter.name());
		frame.addFrame("generatedLanguage", generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		if (!(language instanceof Proteo))
			frame.addFrame("language", NativeFormatter.getLanguageScope(parameter, language));
		if (signature != null) frame.addFrame("signature", signature);
		final String anInterface = NativeFormatter.getInterface(parameter);
		if (anInterface != null) frame.addFrame("rule", cleanQn(anInterface));
		if (signature != null) frame.addFrame("return", NativeFormatter.getReturn(body, signature));
	}

	private static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}