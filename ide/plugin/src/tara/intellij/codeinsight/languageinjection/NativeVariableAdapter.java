package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.TaraAttributeType;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Node;
import tara.language.model.Primitive;
import tara.language.model.Variable;

import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveContract;

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
		else NativeFormatter.fillFrameExpressionVariable(frame, variable, value, generatedLanguage, isM0(variable));
	}

	private boolean isM0(Variable variable) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf((PsiElement) variable));
		return facet != null && facet.getConfiguration().isM0();
	}

	private void fillFrameForNativeVariable(Frame frame, Variable variable) {
		final TaraAttributeType attributeType = ((TaraVariable) variable).getAttributeType();
		if (attributeType == null || attributeType.getContract() == null) return;
		Contract contract = attributeType.getContract();
		PsiElement reference = resolveContract(contract);
		if (reference == null) return;
		final String signature = NativeFormatter.getSignature((PsiClass) reference);
		final String nativeContainer = cleanQn(NativeFormatter.buildNativeContainerPath(variable.contract(), variable.container(), language, generatedLanguage));
		frame.addFrame("name", variable.name());
		frame.addFrame("signature", signature);
		frame.addFrame("generatedLanguage", generatedLanguage.toLowerCase());
		frame.addFrame("nativeContainer", nativeContainer);
		if (!(language instanceof Proteo))
			frame.addFrame("language", language.languageName());
		frame.addFrame("contract", variable.contract());
		frame.addFrame("return", NativeFormatter.getReturn((PsiClass) reference, variable.defaultValues().get(0).toString()));
	}

	private static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}