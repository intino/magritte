package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.NativeRule;

import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveRule;

public class NativeVariableAdapter implements Adapter<Variable> {

	private final String generatedLanguage;
	private final Language language;

	public NativeVariableAdapter(String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		frame.addTypes("native");
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Variable variable) {
		createNativeFrame(frame, variable);
	}

	private void createNativeFrame(Frame frame, Variable variable) {
		if (!(variable.defaultValues().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) variable.defaultValues().get(0);
		String value = body.get();
		if (Primitive.NATIVE.equals(variable.type())) fillFrameForNativeVariable(frame, variable);
		else
			new NativeFormatter(generatedLanguage, language, isM0(variable)).fillFrameExpressionVariable(frame, variable, value);
	}

	private boolean isM0(Variable variable) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf((PsiElement) variable));
		return facet != null && facet.getConfiguration().isM0();
	}

	private void fillFrameForNativeVariable(Frame frame, Variable variable) {
		final TaraRuleContainer attributeType = ((TaraVariable) variable).getRuleContainer();
		if (attributeType == null || attributeType.getRule() == null) return;
		Rule rule = attributeType.getRule();
		PsiElement reference = resolveRule(rule);
		if (reference == null) return;
		final String signature = NativeFormatter.getSignature((PsiClass) reference);
		final String nativeContainer = cleanQn(NativeFormatter.buildContainerPath((NativeRule) variable.rule(), variable.container(), language, generatedLanguage));
		frame.addFrame("name", variable.name());
		frame.addFrame("signature", signature);
		frame.addFrame("generatedLanguage", generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		if (!(language instanceof Proteo))
			frame.addFrame("language", language.languageName());
		frame.addFrame("rule", ((TaraRule) variable.rule()).getText());
		frame.addFrame("return", NativeFormatter.getReturn((PsiClass) reference, variable.defaultValues().get(0).toString()));
	}

	private static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}