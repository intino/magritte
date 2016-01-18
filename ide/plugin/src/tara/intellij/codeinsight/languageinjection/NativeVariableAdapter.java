package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiElement;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Tag;
import tara.lang.model.Variable;

public class NativeVariableAdapter implements Adapter<Variable> {

	private final String generatedLanguage;
	private final Language language;

	public NativeVariableAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		frame.addTypes(source.type().getName());
		for (Tag tag : source.flags()) frame.addTypes(tag.name().toLowerCase());
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Variable variable) {
		if (!(variable.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) variable.values().get(0);
		final NativeFormatter formatter = new NativeFormatter(generatedLanguage, language, isM0(variable));
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForNativeVariable(frame, variable);
		else formatter.fillFrameExpressionVariable(frame, variable, body.get());
	}

	private boolean isM0(Variable variable) {
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf((PsiElement) variable));
		return facet != null && facet.getConfiguration().isM0();
	}
}