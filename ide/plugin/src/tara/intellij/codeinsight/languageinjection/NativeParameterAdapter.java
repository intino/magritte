package tara.intellij.codeinsight.languageinjection;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.semantics.Constraint;

import static tara.lang.model.Primitive.FUNCTION;

public class NativeParameterAdapter implements Adapter<Parameter> {

	private final NativeFormatter formatter;

	public NativeParameterAdapter(Module module, String generatedLanguage, Language language) {
		this.formatter = new NativeFormatter(module, generatedLanguage, language);
	}

	@Override
	public void execute(Frame frame, Parameter source, FrameContext<Parameter> frameContext) {
		if (source.type() == null) return;
		frame.addTypes(source.type().getName());
		frame.addTypes(source.flags().toArray(new String[source.flags().size()]));
		final Constraint.Parameter constraint = TaraUtil.getConstraint(TaraPsiImplUtil.getContainerNodeOf((PsiElement) source), source);
		if (constraint != null) constraint.annotations().forEach(frame::addTypes);
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Parameter parameter) {
		createNativeFrame(frame, parameter);
	}

	private void createNativeFrame(Frame frame, Parameter parameter) {
		if (parameter.values() == null || parameter.values().isEmpty() || !(parameter.values().get(0) instanceof Primitive.Expression))
			return;
		final Expression expression = ((Valued) parameter).getBodyValue() != null ? ((Valued) parameter).getBodyValue().getExpression() : ((Valued) parameter).getValue().getExpressionList().get(0);
		if (expression == null) return;
		String value = expression.getValue();

		if (FUNCTION.equals(parameter.type())) formatter.fillFrameForNativeParameter(frame, parameter, value);
		else formatter.fillFrameExpressionParameter(frame, parameter, value);
	}

	private boolean isM0(Module module) {
		final TaraFacet facet = TaraFacet.of(module);
		return facet != null && facet.getConfiguration().isM0();
	}
}