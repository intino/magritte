package tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.fix.CreateMeasureClassIntention;
import tara.intellij.annotator.fix.CreateWordClassIntention;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Primitives;
import tara.language.model.Variable;

import java.util.HashMap;
import java.util.Map;

public class VariableAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String NATIVES = "natives";
	private static final String METRICS = "metrics";
	private static final String WORDS = "words";

	private static Map<String, String> packageRelation = new HashMap();

	static {
		packageRelation.put(Primitives.NATIVE, NATIVES);
		packageRelation.put(Primitives.WORD, WORDS);
		packageRelation.put(Primitives.MEASURE, METRICS);
	}

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		holder = annotationHolder;
		if (psiElement instanceof Variable) {
			TaraVariable variable = (TaraVariable) psiElement;
			if (!Primitives.NATIVE.equals(variable.type()))
				return;
			if (variable.getAttributeType() != null && variable.getAttributeType().getContract() != null && !hasCorrespondingJavaClass(variable)) {
				final Annotation errorAnnotation = holder.createErrorAnnotation(variable, MessageProvider.message("no.java.generated.class"));
				final IntentionAction correspondingFix = getCorrespondingFix(variable);
				if (correspondingFix != null) errorAnnotation.registerFix(correspondingFix);
			} else if (Primitives.NATIVE.equals(variable.type()) && !hasSignature(variable)) {
				holder.createErrorAnnotation(variable, MessageProvider.message("no.java.signature.found"));
			}
		}
	}

	private boolean hasSignature(TaraVariable variable) {
		final PsiClass psiClass = (PsiClass) ReferenceManager.resolveJavaClassReference(variable.getProject(), nativeClass(variable.getAttributeType().getContract(), variable.type()));
		return psiClass != null && (psiClass.getMethods().length != 0);
	}

	private boolean hasCorrespondingJavaClass(TaraVariable variable) {
		return isCreated(variable.getProject(), nativeClass(variable.getAttributeType().getContract(), variable.type()));
	}

	private boolean isCreated(Project project, String qn) {
		return ReferenceManager.resolveJavaClassReference(project, qn) != null;
	}

	private String nativeClass(Contract contract, String type) {
		return getGeneratedDslName(contract).toLowerCase() + DOT + packageRelation.get(type) + DOT + contract.getFormattedName();
	}

	private IntentionAction getCorrespondingFix(TaraVariable variable) {
		final String generatedDslName = getGeneratedDslName(variable);
		if (Primitives.WORD.equals(variable.type()))
			return new CreateWordClassIntention(variable.contract(), generatedDslName.toLowerCase() + DOT + packageRelation.get(variable.type()));
		else if (Primitives.MEASURE.equals(variable.type()))
			return new CreateMeasureClassIntention(variable.contract(), generatedDslName.toLowerCase() + DOT + packageRelation.get(variable.type()));
		else return null;
	}

	private String getGeneratedDslName(PsiElement element) {
		final Module moduleOf = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(moduleOf);
		if (facet == null) return moduleOf.getName();
		return facet.getConfiguration().getGeneratedDslName();
	}
}
