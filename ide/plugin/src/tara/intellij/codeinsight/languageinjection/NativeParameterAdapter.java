package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiElement;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;

import static tara.lang.model.Primitive.FUNCTION;

public class NativeParameterAdapter implements Adapter<Parameter> {

	private final String generatedLanguage;
	private final Language language;

	public NativeParameterAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	@Override
	public void execute(Frame frame, Parameter source, FrameContext<Parameter> frameContext) {
		frame.addTypes(source.type().getName());
		frame.addTypes(source.flags().toArray(new String[source.flags().size()]));
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Parameter parameter) {
		createNativeFrame(frame, parameter);
	}

	private void createNativeFrame(Frame frame, Parameter parameter) {
		if (!(parameter.values().get(0) instanceof Primitive.Expression)) return;
		final Expression expression = ((Valued) parameter).getValue().getExpressionList().get(0);
		String value = expression.getValue();
		final NativeFormatter formatter = new NativeFormatter(generatedLanguage, language, isM0(parameter));
		if (FUNCTION.equals(parameter.type())) formatter.fillFrameForNativeParameter(frame, parameter, value);
		else formatter.fillFrameExpressionParameter(frame, parameter, value);
	}

	private boolean isM0(Parameter variable) {
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf((PsiElement) variable));
		return facet != null && facet.getConfiguration().isM0();
	}


}